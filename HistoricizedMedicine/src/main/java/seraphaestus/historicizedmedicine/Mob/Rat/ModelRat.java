package seraphaestus.historicizedmedicine.Mob.Rat;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import seraphaestus.historicizedmedicine.Animations.MCAClientLibrary.MCAModelRenderer;
import seraphaestus.historicizedmedicine.Animations.MCACommonLibrary.MCAVersionChecker;
import seraphaestus.historicizedmedicine.Animations.MCACommonLibrary.animation.AnimationHandler;
import seraphaestus.historicizedmedicine.Animations.MCACommonLibrary.math.Matrix4f;
import seraphaestus.historicizedmedicine.Animations.MCACommonLibrary.math.Quaternion;

import java.util.HashMap;

public class ModelRat extends ModelBase {
	public final int MCA_MIN_REQUESTED_VERSION = 5;
	public HashMap<String, MCAModelRenderer> parts = new HashMap<String, MCAModelRenderer>();

	MCAModelRenderer rat;
	MCAModelRenderer head;
	MCAModelRenderer body;
	MCAModelRenderer snout;
	MCAModelRenderer earL;
	MCAModelRenderer earR;
	MCAModelRenderer bodyBack;
	MCAModelRenderer legFL;
	MCAModelRenderer hindL;
	MCAModelRenderer hindR;
	MCAModelRenderer legFR;
	MCAModelRenderer bum;
	MCAModelRenderer legBL;
	MCAModelRenderer legBR;
	MCAModelRenderer tail;
	MCAModelRenderer bum2;

	public ModelRat() {

		MCAVersionChecker.checkForLibraryVersion(getClass(), MCA_MIN_REQUESTED_VERSION);

		textureWidth = 64;
		textureHeight = 64;

		rat = new MCAModelRenderer(this, "rat", 0, 0);
		rat.mirror = false;
		rat.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0);
		rat.offsetX = -0.15f;
		rat.offsetZ = -0.2f;
		rat.offsetY = 1.45f;
		rat.setInitialRotationPoint(0.0F, 0.0F, 0.0F);
		Quaternion q = new Quaternion(1.0F, 0.00F, 0.00F, 0.00F);
		//Quaternion q = new Quaternion(new Vector3f(0, 0, 1), (float)Math.PI);
		rat.setInitialRotationMatrix(new Matrix4f().set(q).transpose());
		rat.setTextureSize(64, 64);
		parts.put(rat.boxName, rat);

		head = new MCAModelRenderer(this, "head", 0, 20);
		head.mirror = false;
		head.addBox(0.0F, 0.0F, 0.0F, 5, 3, 4);
		head.setInitialRotationPoint(0.0F, 0.0F, 0.0F);
		head.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		head.setTextureSize(64, 64);
		parts.put(head.boxName, head);
		rat.addChild(head);

		body = new MCAModelRenderer(this, "body", 0, 11);
		body.mirror = false;
		body.addBox(0.0F, 0.0F, 0.0F, 7, 4, 4);
		body.setInitialRotationPoint(-1.0F, -0.5F, -4.0F);
		body.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		body.setTextureSize(64, 64);
		parts.put(body.boxName, body);
		rat.addChild(body);

		snout = new MCAModelRenderer(this, "snout", 0, 28);
		snout.mirror = false;
		snout.addBox(0.0F, 0.0F, 0.0F, 3, 2, 3);
		snout.setInitialRotationPoint(1.0F, 0.0F, 3.5F);
		snout.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		snout.setTextureSize(64, 64);
		parts.put(snout.boxName, snout);
		head.addChild(snout);

		earL = new MCAModelRenderer(this, "ear L", 9, 34);
		earL.mirror = false;
		earL.addBox(0.0F, 0.0F, 0.0F, 3, 3, 1);
		earL.setInitialRotationPoint(-1.5F, 2.0F, 0.5F);
		earL.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		earL.setTextureSize(64, 64);
		parts.put(earL.boxName, earL);
		head.addChild(earL);

		earR = new MCAModelRenderer(this, "ear R", 0, 34);
		earR.mirror = false;
		earR.addBox(0.0F, 0.0F, 0.0F, 3, 3, 1);
		earR.setInitialRotationPoint(3.5F, 2.0F, 0.5F);
		earR.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		earR.setTextureSize(64, 64);
		parts.put(earR.boxName, earR);
		head.addChild(earR);

		bodyBack = new MCAModelRenderer(this, "body back", 0, 0);
		bodyBack.mirror = false;
		bodyBack.addBox(0.0F, 0.0F, 0.0F, 9, 5, 5);
		bodyBack.setInitialRotationPoint(-1.0F, 0.0F, -5.0F);
		bodyBack.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		bodyBack.setTextureSize(64, 64);
		parts.put(bodyBack.boxName, bodyBack);
		body.addChild(bodyBack);

		legFL = new MCAModelRenderer(this, "leg F L", 29, 10);
		legFL.mirror = false;
		legFL.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
		legFL.setInitialRotationPoint(-1.0F, 0.0F, 3.0F);
		legFL.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		legFL.setTextureSize(64, 64);
		parts.put(legFL.boxName, legFL);
		body.addChild(legFL);

		hindL = new MCAModelRenderer(this, "hind L", 36, 18);
		hindL.mirror = false;
		hindL.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
		hindL.setInitialRotationPoint(-2.0F, 1.0F, -4.0F);
		hindL.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		hindL.setTextureSize(64, 64);
		parts.put(hindL.boxName, hindL);
		body.addChild(hindL);

		hindR = new MCAModelRenderer(this, "hind R", 29, 18);
		hindR.mirror = false;
		hindR.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
		hindR.setInitialRotationPoint(8.0F, 1.0F, -4.0F);
		hindR.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		hindR.setTextureSize(64, 64);
		parts.put(hindR.boxName, hindR);
		body.addChild(hindR);

		legFR = new MCAModelRenderer(this, "leg F R", 36, 10);
		legFR.mirror = false;
		legFR.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
		legFR.setInitialRotationPoint(7.0F, 0.0F, 3.0F);
		legFR.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		legFR.setTextureSize(64, 64);
		parts.put(legFR.boxName, legFR);
		body.addChild(legFR);

		bum = new MCAModelRenderer(this, "bum", 0, 39);
		bum.mirror = false;
		bum.addBox(0.0F, 0.0F, 0.0F, 7, 3, 1);
		bum.setInitialRotationPoint(1.0F, 0.0F, -1.0F);
		bum.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		bum.setTextureSize(64, 64);
		parts.put(bum.boxName, bum);
		bodyBack.addChild(bum);

		legBL = new MCAModelRenderer(this, "leg B L", 36, 23);
		legBL.mirror = false;
		legBL.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
		legBL.setInitialRotationPoint(0.0F, -1.0F, 1.0F);
		legBL.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		legBL.setTextureSize(64, 64);
		parts.put(legBL.boxName, legBL);
		hindL.addChild(legBL);

		legBR = new MCAModelRenderer(this, "leg B L", 29, 23);
		legBR.mirror = false;
		legBR.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
		legBR.setInitialRotationPoint(0.0F, -1.0F, 1.0F);
		legBR.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		legBR.setTextureSize(64, 64);
		parts.put(legBR.boxName, legBR);
		hindR.addChild(legBR);

		tail = new MCAModelRenderer(this, "tail", 29, 3);
		tail.mirror = false;
		tail.addBox(0.0F, 0.0F, -5.0F, 1, 1, 5);
		tail.setInitialRotationPoint(3.0F, 1.0F, 0.0F);
		tail.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(1.0F, 0.0F, 0.0F, 0.0F)).transpose());
		tail.setTextureSize(64, 64);
		parts.put(tail.boxName, tail);
		bum.addChild(tail);

		bum2 = new MCAModelRenderer(this, "bum 2", 29, 0);
		bum2.mirror = false;
		bum2.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1);
		bum2.setInitialRotationPoint(1.0F, 3.0F, 0.0F);
		bum2.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		bum2.setTextureSize(64, 64);
		parts.put(bum2.boxName, bum2);
		bum.addChild(bum2);

	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		EntityRat entity = (EntityRat) entityIn;

		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

		//List<Channel> channels = new ArrayList<Channel>();
		//channels.add(new ChannelSniff(null, scale, MCA_MIN_REQUESTED_VERSION, 0));
		//channels.add(new ChannelWalk(null, scale, MCA_MIN_REQUESTED_VERSION, 0));
		//entity.getAnimationHandler().animCurrentChannels =
		//public ArrayList<Channel> animCurrentChannels = new ArrayList();
		AnimationHandler.performAnimationInModel(parts, entity);

		this.rat.render(scale);
		//this.rat.render(scale);
		//Render every non-child part
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		this.head.rotateAngleY = netHeadYaw * 0.017453292F;
		this.head.rotateAngleX = headPitch * 0.017453292F;
	}

	public MCAModelRenderer getModelRendererFromName(String name) {
		return parts.get(name);
	}
}