package seraphaestus.historicizedmedicine.Mob.Rat;

import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;

public class AIRatAttackPlayer extends EntityAINearestAttackableTarget<EntityPlayer>
{
    public AIRatAttackPlayer(EntityRat rat)
    {
        super(rat, EntityPlayer.class, 3, true, true, null);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
            if (super.shouldExecute())
            {
                return true;
            }
            return false;
        
    }

    protected double getTargetDistance()
    {
        return super.getTargetDistance() * 1.0D;
    }
}
