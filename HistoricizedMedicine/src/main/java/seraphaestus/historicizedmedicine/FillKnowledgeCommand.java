package seraphaestus.historicizedmedicine;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import seraphaestus.historicizedmedicine.Item.KnowledgeSheet;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FillKnowledgeCommand extends CommandBase {

	private final List<String> aliases;

	public FillKnowledgeCommand() {
		aliases = new ArrayList<String>();
		aliases.add("knowledgeFill");
	}

	@Override
	@Nonnull
	public String getName() {
		return "knowledgeFill";
	}

	@Override
	@Nonnull
	public String getUsage(@Nonnull ICommandSender sender) {
		return "knowledgeFill";
	}

	@Override
	@Nonnull
	public List<String> getAliases() {
		return aliases;
	}

	@Override
	public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {

		if (sender.getCommandSenderEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();
			ItemStack is = player.getHeldItemMainhand();
			if (is.getItem() instanceof KnowledgeSheet) {
				KnowledgeSheet ks = (KnowledgeSheet) is.getItem();
				ks.setMaxKnowledge(is);
			}
		}

	}

	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}

	@Override
	@Nonnull
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
		return Collections.emptyList();
	}
}

