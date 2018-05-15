package seraphaestus.historicizedmedicine.Animations.animations.Rat;

import java.util.HashMap;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import seraphaestus.historicizedmedicine.Animations.MCACommonLibrary.IMCAnimatedEntity;
import seraphaestus.historicizedmedicine.Animations.MCACommonLibrary.animation.AnimationHandler;
import seraphaestus.historicizedmedicine.Animations.MCACommonLibrary.animation.Channel;

public class AnimationHandlerRat extends AnimationHandler {
	/** Map with all the animations. */
	public static HashMap<String, Channel> animChannels = new HashMap<String, Channel>();
	
	public AnimationHandlerRat(IMCAnimatedEntity entity) {
		super(entity);
		animChannels.put("Sniff", new ChannelSniff("Sniff", 7.0F, 7, Channel.LINEAR));
		animChannels.put("Walk", new ChannelWalk("Walk", 3.0F, 12, Channel.LOOP));
		animChannels.put("TailWag", new ChannelTailWag("TailWag", 3.0F, 6, Channel.LOOP));
}

	@Override
	public void activateAnimation(String name, float startingFrame) {
		super.activateAnimation(animChannels, name, startingFrame);
	}

	@Override
	public void stopAnimation(String name) {
		super.stopAnimation(animChannels, name);
	}

	@Override
	public void fireAnimationEventServerSide(Channel anim, float prevFrame, float frame) {

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void fireAnimationEventClientSide(Channel anim, float prevFrame, float frame) {

	}
}