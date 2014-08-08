package rach.particle;

import java.util.Random;

import rach.utility.ColorMaker;

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

	public static IParticle historyParticle(int count, int total, double spawnFieldWidth, double spawnFieldHeight, float color[], 
			String url, String date, String time, float[] color2) {
		double perc = (double)count / (double)total;
		double xc = perc * spawnFieldWidth;
		double xy = 100;

		IParticle hbp = new HistoryBlob(spawnFieldWidth/2,spawnFieldHeight,xc,xy, 
				spawnFieldWidth,spawnFieldHeight,color,10,20, url, date, time, color2);

		return hbp;
	}
	private static int makeRandomNumInRange(int min, int max) {
		int num = 0;
		Random r = new Random();
		num = (r.nextInt(max - min));
		return num;
	}

}
