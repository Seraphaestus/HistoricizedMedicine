package seraphaestus.historicizedmedicine.Item;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import seraphaestus.historicizedmedicine.Effect.RegisterEffects;

public class PlagueCure extends MedKitBase {

	public PlagueCure() {
		super("plague_cure", 1, -1, -1, null, new Potion[]{RegisterEffects.plague}, null, 0);
	}

	private static boolean isBeingLookedAt(Entity target, EntityPlayer playerIn) {
		Vec3d vec3d = playerIn.getLook(1.0F).normalize();
		Vec3d vec3d1 = new Vec3d(target.posX - playerIn.posX, target.getEntityBoundingBox().minY + target.getEyeHeight() - (playerIn.posY + playerIn.getEyeHeight()), target.posZ - playerIn.posZ);
		double d0 = vec3d1.lengthVector();
		vec3d1 = vec3d1.normalize();
		double d1 = vec3d.dotProduct(vec3d1);
		return (d1 > 1.0D - 0.025D / d0);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 20;
	}

	private Predicate<EntityLiving> truePredicate() {
		return e -> true;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		//use on entity
		for (EntityLiving entity : worldIn.getEntities(EntityLiving.class, truePredicate())) {
			//in range of player
			if (entity.getDistance(playerIn) < entity.getEntityBoundingBox().getAverageEdgeLength() + 1.5f) {
				//
				if (isBeingLookedAt(entity, playerIn)) {
					//has plague
					if (entity.getActivePotionEffect(RegisterEffects.plague) != null) {
						entity.removeActivePotionEffect(RegisterEffects.plague);
						entity.addPotionEffect(new PotionEffect(RegisterEffects.plagueImmunity, 20 * 60));
						return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(hand));
					} else {
						return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(hand));
					}
				}
			}
		}

		//drink onesself	

		ItemStack itemStackHeld = playerIn.getHeldItem(hand);

		if (minHealthReq == -1 || playerIn.getHealth() > minHealthReq) {
			if (maxHealthReq == -1 || playerIn.getHealth() < maxHealthReq) {
				playerIn.setActiveHand(hand);
				return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackHeld);
			}
		}

		return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackHeld);
	}

}
