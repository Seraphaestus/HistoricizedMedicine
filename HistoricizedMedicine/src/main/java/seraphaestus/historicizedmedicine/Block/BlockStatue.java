package seraphaestus.historicizedmedicine.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import seraphaestus.historicizedmedicine.Config;
import seraphaestus.historicizedmedicine.Util.PotionName;
import seraphaestus.historicizedmedicine.Util.Reduce;

public class BlockStatue extends BlockBase {

	private PotionEffect[] effect;
	private Reduce[] reduce;
	private Potion[] cure;
	private float heal = 0;
	private int cooldown = 0;
	private int lastCooldownSent = 0;
	
	public BlockStatue(String id, Material materialIn, PotionEffect[] effect, Potion[] cure, Reduce[] reduce, float heal) {
		super(id, materialIn);    
		setHardness(8f);
		setResistance(60f);
		this.effect = effect;
		this.cure = cure;
		this.reduce = reduce;
		this.heal = heal;
	}
	
	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
		return;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	    {
			NBTTagCompound nbt = playerIn.getEntityData();	
			int cooldown = nbt.getInteger(this.id + "_cooldown");
			if(cooldown != 0) {
				int cooldownSent = cooldown / 20;
				if(lastCooldownSent != cooldownSent && playerIn.isSneaking()) {
					playerIn.sendMessage(new TextComponentString("The " + this.getLocalizedName() + " is on cooldown: " + cooldownSent + " seconds remaining"));
					lastCooldownSent = cooldownSent;
				}
				return false;
			}
			nbt.setInteger(this.id + "_cooldown", Config.statueCooldown);
			//visual effect
			Random rnd = new Random();
			for(int i = 0; i < Config.statueParticleAmount; i++) {
				float posx = pos.getX() + rnd.nextFloat();
				float posy = pos.getY() + rnd.nextFloat();
				float posz = pos.getZ() + rnd.nextFloat();
				worldIn.spawnParticle(EnumParticleTypes.SPELL, posx, posy + 0.5f, posz, 0, 0, 0, null);
			}
			//effects
			playerIn.heal(heal);
			if(effect != null) {
				for(PotionEffect pe : effect) {
					playerIn.addPotionEffect(new PotionEffect(pe));
				}
			}
			if(cure != null) {
				for(Potion p : cure) {
					if(playerIn.getActivePotionEffect(p) != null) {
						playerIn.removeActivePotionEffect(p);
					}
				}
			}
			if(reduce != null) {
				for(Reduce r : reduce){
		        	PotionEffect pe = playerIn.getActivePotionEffect(r.x);
		        	if(pe != null) {
			        	int dur = pe.getDuration();
			        	playerIn.removeActivePotionEffect(r.x);
			            if(dur - r.y > 0) {
			            	playerIn.addPotionEffect(new PotionEffect(r.x, dur - r.y));
			            }
		        	}
		        }
			}
	        return true;
	    }
	
	@Override
	public void registerItemBlock() {
		List<String> tooltip = null;
		if(Config.canViewEffectsInTooltip) {
			tooltip = new ArrayList<String>();
			if(heal > 0) {
				tooltip.add("Heals by " + heal / 2 + " hearts");
			} else if(heal < 0) {
				tooltip.add("Damages by " + heal / -2 + " hearts");
			}
	    	//re: removing effects
	    	if(cure != null) {
		        for(Potion p : cure){
		        	tooltip.add("Cures " + PotionName.potionName(p));
		        }
	    	}
	    	//re: reducing effects
	    	if(reduce != null) {
		        for(Reduce r : reduce){
		        	tooltip.add("Reduces " + PotionName.potionName(r.x) + " duration by " + (float)r.y / 20 + " seconds");
		        }
	    	}
			//re: adding effects
			if(effect != null) {
		        for(PotionEffect p : effect){
					tooltip.add("Gives " + PotionName.potionName(p.getPotion()) + " for " + (float)p.getDuration() / 20 + " seconds");
		        }
	    	}
		}
		String[] tooltipArr = tooltip.toArray(new String[tooltip.size()]);
    	ForgeRegistries.ITEMS.register(new ItemBlockBase(this, tooltipArr).setRegistryName(this.id));
	}
	
	@Override
	@Deprecated
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	@Deprecated
	public boolean isFullCube(IBlockState state) {
		return false;
	}

}
