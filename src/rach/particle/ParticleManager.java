package rach.particle;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParticleManager {

	private List<IParticle> particles = new ArrayList<IParticle>();
	private List<IParticle>[] checkList;

	private int spawnFieldWidth;
	private int spawnFieldHeight;
	private int screenWidth;
	private int screenHeight;
	
	private int currentParticleCount;

	public ParticleManager(int spawnFieldWidth, int spawnFieldHeight, int screenWidth, int screenHeight) {
		this.spawnFieldWidth = spawnFieldWidth;
		this.spawnFieldHeight = spawnFieldHeight;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}
	// Random
	public void addParticle() {
		particles.add(ParticleGenerator.randomParticle(spawnFieldWidth, spawnFieldHeight, 10, 20));
		currentParticleCount ++;
	}
	public void addParticle(IParticle p) {
		particles.add(p);
		currentParticleCount ++;
	}
	public List<IParticle> updateAllParticles(int currentScreenWidth, int currentScreenHeight, boolean detectCollision) {
		if (currentScreenHeight > screenHeight) {
			this.screenHeight = currentScreenHeight;
		}
		this.screenWidth = currentScreenWidth;
		spawnFieldWidth = currentScreenWidth;
		spawnFieldHeight = currentScreenHeight;

		updateParticles(detectCollision);
		if (detectCollision == true) {
			checkForCollisions();
		}
		return particles;
	}

	private void updateParticles(boolean detectCollision) {

		checkList = new List[screenHeight];

		Iterator<IParticle> i = particles.iterator();
		while (i.hasNext()) {
			IParticle p = i.next();
			if (p.isFinishedCycle()) {
				i.remove();
				continue;
			}
			p.update(spawnFieldWidth, spawnFieldHeight);

			if (detectCollision == true) {
				if (checkList[(int)p.getPosY()] == null) {
					checkList[(int)p.getPosY()] = new ArrayList<IParticle>();
				}
				checkList[(int)p.getPosY()].add(p);
			}
		}
	}

	private void checkForCollisions() {
		for (int x = 0; x < particles.size(); x++) {
			if (checkForColision(particles.get(x))) {
				particles.get(x).setSize(particles.get(x).getMaxCollisionSize());
			}
		}
	}

	private boolean checkForColision(IParticle p) {
		boolean collision = false;

		int startRowIndex = (int) (p.getPosY() - p.getMaxCollisionSize());
		if (startRowIndex < 0) {
			startRowIndex = 0;
		}
		int endRowIndex = (int) (p.getPosY() + p.getMaxCollisionSize());
		if (endRowIndex > checkList.length - 1) {
			endRowIndex = checkList.length - 1;
		}
		for (int x = startRowIndex; x <= endRowIndex; x ++) {
			List<IParticle> l = checkList[x]; 
			if (l != null) { 
				for (int i = 0; i < l.size(); i++ ) { 
					IParticle cp = l.get(i);
					if (p != cp) {

						int xDistance;
						int yDistance;
						// Distance between particles on the x axis.
						if (p.getPosX() > cp.getPosX()) {
							xDistance = (int) Math.abs((p.getPosX() + p.getSize()) - cp.getPosX());	
						} else {
							xDistance = (int) Math.abs(p.getPosX() - (cp.getPosX() + cp.getSize()));
						}
						// Distance between particles on the x axis.
						if (p.getPosY() > cp.getPosY()) {
							yDistance = (int) Math.abs((p.getPosY() + p.getSize()) - cp.getPosY());
						} else {
							yDistance = (int) Math.abs(p.getPosY() - (cp.getPosY() + cp.getSize()));
						}				
						// Combined width of both particles.
						int combinedWidth = (int) (p.getSize() + cp.getSize());
						// Combined height of both particles.
						int combinedHeight = (int) (p.getSize() + cp.getSize());
						// If the distance on BOTH axis is less than or equal to the combined
						// width and height - a collision has occurred.
						if (xDistance<= combinedWidth && yDistance <= combinedHeight) {
							cp.setSize(cp.getMaxCollisionSize());
							collision = true;						
						}
					}
				}
			}
		}
		return collision;
	}

	public int getSpawnFieldWidth() {
		return spawnFieldWidth;
	}

	public int getSpawnFieldHeight() {
		return spawnFieldHeight;
	}

	public void setSpawnFieldWidth(int spawnFieldWidth) {
		this.spawnFieldWidth = spawnFieldWidth;
	}

	public void setSpawnFieldHeight(int spawnFieldHeight) {
		this.spawnFieldHeight = spawnFieldHeight;
	}

	public int getCurrentScreenHeight() {
		return screenHeight;
	}

	public void setCurrentScreenHeight(int currentScreenHeight) {
		this.screenHeight = currentScreenHeight;
	}

	public List<IParticle> getParticles() {
		return particles;
	}

	public void setParticles(List<IParticle> particles) {
		this.particles = particles;
	}

	public List<IParticle>[] getCheckList() {
		return checkList;
	}

	public void setCheckList(List<IParticle>[] checkList) {
		this.checkList = checkList;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}
	public int getCurrentParticleCount() {
		return currentParticleCount;
	}
	public void setCurrentParticleCount(int currentParticleCount) {
		this.currentParticleCount = currentParticleCount;
	}



}

