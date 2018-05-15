package seraphaestus.historicizedmedicine.Mob.Rat;

import java.util.Random;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import seraphaestus.historicizedmedicine.Config;
import seraphaestus.historicizedmedicine.Animations.MCACommonLibrary.IMCAnimatedEntity;
import seraphaestus.historicizedmedicine.Animations.MCACommonLibrary.animation.AnimationHandler;
import seraphaestus.historicizedmedicine.Animations.animations.Rat.AnimationHandlerRat;
import seraphaestus.historicizedmedicine.Effect.RegisterEffects;
import seraphaestus.historicizedmedicine.Mob.PlagueDoctor.EntityPlagueDoctor;

public class EntityRat extends EntityMob implements IMCAnimatedEntity {
	protected AnimationHandlerRat animHandler = new AnimationHandlerRat(this);
	
	private String colour = "gray"; public String getColour() { return colour; }
	private static final int whiteChance = 1;
	private static final int brownChance = 3;
	private static final int grayChance = 5;
	private static final int blackChance = 2;
	private boolean initializedPlague = false;
	
	public EntityRat(World par1World) {
		super(par1World);
		this.setSize(0.5f, 0.5f);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
	}
	
	public String generateColour(UUID uuid) {
		Random rnd = new Random(uuid.getMostSignificantBits() | uuid.getLeastSignificantBits());
		int select = rnd.nextInt(whiteChance + brownChance + grayChance + blackChance);
		if(select < whiteChance) { 
			return "white";
		} else if (select < brownChance + whiteChance) {
			return "brown";
		} else if (select < brownChance + whiteChance + grayChance) {
			return "gray";
		} else {
			return "black";
		}
	}

	@Override
	public AnimationHandler getAnimationHandler() {
		return animHandler;
	}

	@Override
	public void onUpdate()
	{
		if(!this.getAnimationHandler().isAnimationActive("TailWag")) {
			this.getAnimationHandler().activateAnimation("TailWag", 0);
		}
		//
		if(!initializedPlague) {
			this.addPotionEffect(new PotionEffect(RegisterEffects.plague, Integer.MAX_VALUE));
			this.initializedPlague = true;
		}
		if(this.rand.nextInt(30) == 0) {
			if(!this.getAnimationHandler().isAnimationActive("Sniff")) {
				this.getAnimationHandler().activateAnimation("Sniff", 0);
			}
		}
        super.onUpdate();
     }

	protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.5d));
        this.tasks.addTask(2, new EntityAIAvoidEntity<EntityPlagueDoctor>(this, EntityPlagueDoctor.class, 6.0F, 1.0D, 1.2D));
		this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(4, new EntityAIWanderAvoidWater(this, 0.4D));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, (Class<?>[])null));
        this.targetTasks.addTask(2, new AIRatAttackPlayer(this));
    }
	
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
        
   }
	
	@Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if(entityIn instanceof EntityPlayer && Config.plagueCarriers) {
        	//apply plague
        	((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(RegisterEffects.plague, Integer.MAX_VALUE));
        	((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(Potion.getPotionById(9), 1));
        }
        return super.attackEntityAsMob(entityIn);
    }
	
    protected boolean isValidLightLevel()
    {
        BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

        if (this.world.getLightFor(EnumSkyBlock.SKY, blockpos) > this.rand.nextInt(32))
        {
            return false;
        }
        else
        {
            int i = this.world.getLightFromNeighbors(blockpos);

            if (this.world.isThundering())
            {
                int j = this.world.getSkylightSubtracted();
                this.world.setSkylightSubtracted(10);
                i = this.world.getLightFromNeighbors(blockpos);
                this.world.setSkylightSubtracted(j);
            }

            return i <= this.rand.nextInt(8);
        }
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && super.getCanSpawnHere();
    }
}