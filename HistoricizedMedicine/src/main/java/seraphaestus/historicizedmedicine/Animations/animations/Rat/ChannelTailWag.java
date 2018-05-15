package seraphaestus.historicizedmedicine.Animations.animations.Rat;

import seraphaestus.historicizedmedicine.Animations.MCACommonLibrary.animation.Channel;
import seraphaestus.historicizedmedicine.Animations.MCACommonLibrary.animation.KeyFrame;
import seraphaestus.historicizedmedicine.Animations.MCACommonLibrary.math.Quaternion;
import seraphaestus.historicizedmedicine.Animations.MCACommonLibrary.math.Vector3f;

public class ChannelTailWag extends Channel {
	public ChannelTailWag(String _name, float _fps, int _totalFrames, byte _mode) {
		super(_name, _fps, _totalFrames, _mode);
	}

	@Override
	protected void initializeAllFrames() {
		KeyFrame frame0 = new KeyFrame();
		frame0.modelRenderersRotations.put("tail", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
		frame0.modelRenderersTranslations.put("tail", new Vector3f(3.0F, 1.0F, 0.0F));
		keyFrames.put(0, frame0);

		KeyFrame frame1 = new KeyFrame();
		frame1.modelRenderersRotations.put("tail", new Quaternion(0.0F, 0.085F, 0.0F, 1.0F));
		frame1.modelRenderersTranslations.put("tail", new Vector3f(3.0F, 1.0F, 0.0F));
		keyFrames.put(1, frame1);

		KeyFrame frame2 = new KeyFrame();
		frame2.modelRenderersRotations.put("tail", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
		frame2.modelRenderersTranslations.put("tail", new Vector3f(3.0F, 1.0F, 0.0F));
		keyFrames.put(2, frame2);

		KeyFrame frame3 = new KeyFrame();
		frame3.modelRenderersRotations.put("tail", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
		frame3.modelRenderersTranslations.put("tail", new Vector3f(3.0F, 1.0F, 0.0F));
		keyFrames.put(3, frame3);

		KeyFrame frame4 = new KeyFrame();
		frame4.modelRenderersRotations.put("tail", new Quaternion(0.0F, -0.085F, 0.0F, 1.0F));
		frame4.modelRenderersTranslations.put("tail", new Vector3f(3.0F, 1.0F, 0.0F));
		keyFrames.put(4, frame4);

		KeyFrame frame5 = new KeyFrame();
		frame5.modelRenderersRotations.put("tail", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
		frame5.modelRenderersTranslations.put("tail", new Vector3f(3.0F, 1.0F, 0.0F));
		keyFrames.put(5, frame5);

	}
}