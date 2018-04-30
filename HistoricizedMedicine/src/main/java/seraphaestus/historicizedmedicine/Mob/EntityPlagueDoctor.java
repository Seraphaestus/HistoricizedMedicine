package seraphaestus.historicizedmedicine.Mob;

import javax.annotation.Nullable;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import seraphaestus.historicizedmedicine.HMedicineMod;

public class EntityPlagueDoctor extends EntityVillager  {


    public static final ResourceLocation LOOT = new ResourceLocation(HMedicineMod.MODID, "entities/plague_doctor");

    public EntityPlagueDoctor(World worldIn) {
        super(worldIn);
        setSize(0.6F, 1.95F);
    }
    
    

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        // Here we set various attributes for our mob. Like maximum health, armor, speed, ...
    }

    @Override
    protected void initEntityAI() {
    	super.initEntityAI();
    }

    private void applyEntityAI() {
        //this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }
    
	@Override
    public IEntityLivingData finalizeMobSpawn(DifficultyInstance p_190672_1_, @Nullable IEntityLivingData p_190672_2_, boolean p_190672_3_)
    {
        this.setProfession(VillagerProfessions.PlagueDoctorProfession);
        
        return super.finalizeMobSpawn(p_190672_1_, p_190672_2_, false);
    }
}