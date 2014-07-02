package application;

import java.util.Random;

public class ParticleGenerator {

	public static Particle randomParticle(int spawnFieldWidth, int spawnFieldHeight, int size, int maxCollisionSize) {
		Particle p = null;
		int xSource = makeRandomNumInRange(0,spawnFieldWidth);
		int ySource = makeRandomNumInRange(0,spawnFieldHeight);
		int xTarget = makeRandomNumInRange(0,spawnFieldWidth);
		int yTarget = makeRandomNumInRange(0,spawnFieldHeight);
		p = new Particle(xSource,ySource,xTarget,yTarget,spawnFieldWidth,spawnFieldHeight,ColorMaker.makeRandomRGBColor(), size, maxCollisionSize);
		return p;
	}
	
//	public static Particle historyParticle(int spawnFieldWidth, int spawnFieldHeight, int size, int maxCollisionSize) {
//		Particle p = null;
//		int xSource = makeRandomNumInRange(0,spawnFieldWidth);
//		int ySource = makeRandomNumInRange(0,spawnFieldHeight);
//		int xTarget = makeRandomNumInRange(0,spawnFieldWidth);
//		int yTarget = makeRandomNumInRange(0,spawnFieldHeight);
//		p = new Particle(xSource,ySource,xTarget,yTarget,spawnFieldWidth,spawnFieldHeight,ColorMaker.makeRandomRGBColor(), size, maxCollisionSize);
//		return p;
//	}

	private static int makeRandomNumInRange(int min, int max) {
		int num = 0;
		Random r = new Random();
		num = (r.nextInt(max - min));
		return num;
	}

}
