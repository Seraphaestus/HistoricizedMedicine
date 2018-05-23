package seraphaestus.historicizedmedicine;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import seraphaestus.historicizedmedicine.Mob.PlagueDoctor.EntityPlagueDoctor;
import seraphaestus.historicizedmedicine.Mob.Rat.EntityRat;

import java.util.ArrayList;
import java.util.List;

public class HardCodedValues {

	public static List<String> getRecipeFilePaths() {
		List<String> output = new ArrayList<String>();
		final String ebers = "0ebers/";
		output.add(ebers + "corpus");
		output.add(ebers + "honey_poultice");
		output.add(ebers + "ligature");
		output.add(ebers + "meat_bandage");
		output.add(ebers + "suture");
		final String corpus = "1corpus/";
		output.add(corpus + "canon");
		output.add(corpus + "reed_bandage");
		output.add(corpus + "scalpel");
		output.add(corpus + "statue_end");
		output.add(corpus + "statue_nether");
		output.add(corpus + "statue_wise");
		final String canon = "2canon/";
		output.add(canon + "cautery");
		output.add(canon + "magna");
		output.add(canon + "razor");
		final String magna = "3magna/";
		output.add(magna + "nitrous_oxide");
		return output;
	}

	public static boolean catchesPlague(Entity entityIn) {
		if (!(entityIn instanceof EntityPlayer) && !(entityIn instanceof EntityLiving) && !(entityIn instanceof EntityPlagueDoctor)) {
			return false;
		}
		if (entityIn instanceof EntityRat) {
			return false;
		} else if (entityIn instanceof EntityLiving) {
			EntityLiving living = (EntityLiving) entityIn;
			if (entityIn.getIsInvulnerable() || living.isEntityUndead() || !entityIn.isNonBoss()) {
				return false;
			}
		} else if (entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;
			if (player.isCreative()) {
				return false;
			}
		}
		return true;
	}

	public static Biome[] overworldBiomes() {
		List<Biome> output = new ArrayList<Biome>();
		output.add(Biomes.BEACH);
		output.add(Biomes.BIRCH_FOREST);
		output.add(Biomes.BIRCH_FOREST_HILLS);
		output.add(Biomes.COLD_BEACH);
		output.add(Biomes.COLD_TAIGA);
		output.add(Biomes.COLD_TAIGA_HILLS);
		output.add(Biomes.DEEP_OCEAN);
		output.add(Biomes.DESERT);
		output.add(Biomes.DESERT_HILLS);
		output.add(Biomes.EXTREME_HILLS);
		output.add(Biomes.EXTREME_HILLS_EDGE);
		output.add(Biomes.EXTREME_HILLS_WITH_TREES);
		output.add(Biomes.FOREST);
		output.add(Biomes.FOREST_HILLS);
		output.add(Biomes.FROZEN_OCEAN);
		output.add(Biomes.FROZEN_RIVER);
		output.add(Biomes.ICE_MOUNTAINS);
		output.add(Biomes.ICE_PLAINS);
		output.add(Biomes.JUNGLE);
		output.add(Biomes.JUNGLE_EDGE);
		output.add(Biomes.JUNGLE_HILLS);
		output.add(Biomes.MESA);
		output.add(Biomes.MESA_CLEAR_ROCK);
		output.add(Biomes.MESA_ROCK);
		output.add(Biomes.MUTATED_BIRCH_FOREST);
		output.add(Biomes.MUTATED_BIRCH_FOREST_HILLS);
		output.add(Biomes.MUTATED_DESERT);
		output.add(Biomes.MUTATED_EXTREME_HILLS);
		output.add(Biomes.MUTATED_EXTREME_HILLS_WITH_TREES);
		output.add(Biomes.MUTATED_FOREST);
		output.add(Biomes.MUTATED_ICE_FLATS);
		output.add(Biomes.MUTATED_JUNGLE);
		output.add(Biomes.MUTATED_JUNGLE_EDGE);
		output.add(Biomes.MUTATED_MESA);
		output.add(Biomes.MUTATED_MESA_CLEAR_ROCK);
		output.add(Biomes.MUTATED_MESA_ROCK);
		output.add(Biomes.MUTATED_PLAINS);
		output.add(Biomes.MUTATED_REDWOOD_TAIGA);
		output.add(Biomes.REDWOOD_TAIGA_HILLS);
		output.add(Biomes.RIVER);
		output.add(Biomes.ROOFED_FOREST);
		output.add(Biomes.SAVANNA);
		output.add(Biomes.SAVANNA_PLATEAU);
		output.add(Biomes.STONE_BEACH);
		output.add(Biomes.SWAMPLAND);
		output.add(Biomes.TAIGA);
		output.add(Biomes.TAIGA_HILLS);
		return output.toArray(new Biome[output.size()]);
	}
}
