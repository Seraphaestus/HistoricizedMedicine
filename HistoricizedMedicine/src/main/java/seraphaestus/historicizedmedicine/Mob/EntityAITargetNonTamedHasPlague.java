package seraphaestus.historicizedmedicine.Mob;

import com.google.common.base.Predicate;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.passive.EntityTameable;
import seraphaestus.historicizedmedicine.Effect.RegisterEffects;

import javax.annotation.Nullable;

public class EntityAITargetNonTamedHasPlague<T extends EntityLivingBase> extends EntityAITargetNonTamed<T> {

	public EntityAITargetNonTamedHasPlague(EntityTameable entityIn, Class<T> classTarget, boolean checkSight, Predicate<T> targetSelector) {
		super(entityIn, classTarget, checkSight, targetSelector);

	}

	@Override
	protected boolean isSuitableTarget(@Nullable EntityLivingBase target, boolean includeInvincibles) {
		if (target.getActivePotionEffect(RegisterEffects.plague) == null) {
			return false;
		}
		return super.isSuitableTarget(target, includeInvincibles);
	}


}
