package seraphaestus.historicizedmedicine.Animations.animations.Rat;

import seraphaestus.historicizedmedicine.Animations.MCACommonLibrary.animation.Channel;
import seraphaestus.historicizedmedicine.Animations.MCACommonLibrary.animation.KeyFrame;
import seraphaestus.historicizedmedicine.Animations.MCACommonLibrary.math.Quaternion;
import seraphaestus.historicizedmedicine.Animations.MCACommonLibrary.math.Vector3f;

public class ChannelSniff extends Channel {
	public ChannelSniff(String _name, float _fps, int _totalFrames, byte _mode) {
		super(_name, _fps, _totalFrames, _mode);
	}

	@Override
	protected void initializeAllFrames() {
		KeyFrame frame0 = new KeyFrame();
		frame0.modelRenderersRotations.put("snout", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
		frame0.modelRenderersTranslations.put("snout", new Vector3f(1.0F, 0.0F, 3.5F));
		keyFrames.put(0, frame0);

		KeyFrame frame1 = new KeyFrame();
		frame1.modelRenderersRotations.put("snout", new Quaternion(-0.05F, 0.0F, 0.0F, 1.0F));
		frame1.modelRenderersTranslations.put("snout", new Vector3f(1.0F, 0.0F, 3.5F));
		keyFrames.put(1, frame1);

		KeyFrame frame2 = new KeyFrame();
		frame2.modelRenderersRotations.put("snout", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
		frame2.modelRenderersTranslations.put("snout", new Vector3f(1.0F, 0.0F, 3.5F));
		keyFrames.put(2, frame2);

		KeyFrame frame3 = new KeyFrame();
		frame3.modelRenderersRotations.put("snout", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
		frame3.modelRenderersTranslations.put("snout", new Vector3f(1.0F, 0.0F, 3.5F));
		keyFrames.put(3, frame3);

		KeyFrame frame4 = new KeyFrame();
		frame4.modelRenderersRotations.put("snout", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
		frame4.modelRenderersTranslations.put("snout", new Vector3f(1.0F, 0.0F, 3.5F));
		keyFrames.put(4, frame4);

		KeyFrame frame5 = new KeyFrame();
		frame5.modelRenderersRotations.put("snout", new Quaternion(-0.05F, 0.0F, 0.0F, 1.0F));
		frame5.modelRenderersTranslations.put("snout", new Vector3f(1.0F, 0.0F, 3.5F));
		keyFrames.put(5, frame5);

		KeyFrame frame6 = new KeyFrame();
		frame6.modelRenderersRotations.put("snout", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
		frame6.modelRenderersTranslations.put("snout", new Vector3f(1.0F, 0.0F, 3.5F));
		keyFrames.put(6, frame6);

	}
}