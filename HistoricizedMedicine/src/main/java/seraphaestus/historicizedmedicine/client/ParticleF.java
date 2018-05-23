package seraphaestus.historicizedmedicine.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleBubble;
import seraphaestus.historicizedmedicine.Effect.PlagueEffect;

public enum ParticleF {
	PLAGUE_EFFECT(new PlagueEffect.Factory())

	private final IParticleF factory;

	ParticleF(IParticleF factory) {
		this.factory = factory;
	}

	public Particle newInstance(double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int... args) {
		return factory.createParticle(Minecraft.getMinecraft().world, x, y, z, xSpeed, ySpeed, zSpeed, args);
	}
}