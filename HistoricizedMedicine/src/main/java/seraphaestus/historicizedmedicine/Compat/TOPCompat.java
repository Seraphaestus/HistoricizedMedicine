package seraphaestus.historicizedmedicine.Compat;

import mcjty.theoneprobe.api.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import seraphaestus.historicizedmedicine.HMedicineMod;

import javax.annotation.Nullable;
import java.util.function.Function;

public class TOPCompat {

	private static boolean registered;

	public static void register() {
		if (registered)
			return;
		registered = true;
		FMLInterModComms.sendFunctionMessage("theoneprobe", "getTheOneProbe", "seraphaestus." + HMedicineMod.MODID + ".Compat.TOPCompat$GetTheOneProbe");
	}

	public static class GetTheOneProbe implements Function<ITheOneProbe, Void> {

		public static ITheOneProbe probe;

		@Nullable
		@Override
		public Void apply(ITheOneProbe theOneProbe) {
			probe = theOneProbe;
			//HMedicineMod.logger.log(Level.INFO, "Enabled support for The One Probe");
			probe.registerProvider(new IProbeInfoProvider() {
				@Override
				public String getID() {
					return HMedicineMod.MODID + ":default";
				}

				@Override
				public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
					if (blockState.getBlock() instanceof TOPInfoProvider) {
						TOPInfoProvider provider = (TOPInfoProvider) blockState.getBlock();
						provider.addProbeInfo(mode, probeInfo, player, world, blockState, data);
					}

				}
			});
			return null;
		}
	}
}
