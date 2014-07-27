package rach.particle;

import java.util.Arrays;

public class Particle {

	private double posY;
	private double posX;
	private double targetX;
	private double targetY;
	private double  size;
	private float[] color;
	private double randomEase;

	private double travelRate;
	private int maxCollisionSize;

	private double spawnFieldWidth;
	private double spawnFieldHeight;

	private boolean hasReachedTarget;
	private double originY;
	private boolean onWayDown;
	private double initialSize;
	private boolean finishedCycle;

	public String trackName = "";


	public Particle(double posx, double posy, double targetx, double targety, 
			double spawnFieldWidth, double spawnFieldHeight, 
			float[] rbgColor, int size, int maxCollisionSize) {

		this.posY = posy;
		this.posX = posx;
		this.targetX = targetx;
		this.targetY = targety;
		this.spawnFieldWidth = spawnFieldWidth;
		this.spawnFieldHeight = spawnFieldHeight;
		this.size = size;
		this.color = rbgColor;
		this.maxCollisionSize = maxCollisionSize;
		randomEase = Math.random() * 0.03;
		travelRate = 0.03;
	}

	public void update(double spawnFieldWidth, double spawnFieldHeight) {

		if (spawnFieldHeight != this.spawnFieldHeight && hasReachedTarget == false) {
			if (targetX > spawnFieldWidth || targetY > spawnFieldHeight) {
				resetTarget(spawnFieldWidth,spawnFieldHeight);
			}
		} 
		update();
	}

	private void update() {

		//		double floorPosX = Math.floor(posX + 0.5);
		//		double floorPosY = Math.floor(posY + 0.5);

		if (onWayDown == false) {
			if (posX >= (targetX - 1.0) && posX <= (targetX + 1.0)) {
				hasReachedTarget = true;
				// store origin.
				originY = targetY;
				// Reset target.
				targetY = spawnFieldHeight;
				travelRate = 0.01;
				onWayDown = true;
				initialSize = getSize();
			}
		}

		posX += calculateXDistance(targetX) * (travelRate);
		posY += calculateYDistance(targetY) * (travelRate + randomEase);

		if (onWayDown == true) {
			// Decrement size of descending particle
			double span = targetY - originY;
			double currenty = posY - originY;
			double ratio = (1.0 / (span  * 1.0)) * currenty;
			size = initialSize * (1.0 - ratio);

			if (trackName.equals("boo")) {
				System.out.println("\nspan: " + span);
				System.out.println("ratio: " + ratio);
				System.out.println("currenty: " + currenty);
				System.out.println("posY: " + posY);
				System.out.println("originY: " + originY);
				System.out.println("targetY: " + targetY);
				System.out.println("size: " + size);
			}

			if (posY > (spawnFieldHeight - 2)) {
				finishedCycle = true;
			}
		}


	}

	private void resetTarget(double spawnFieldWidth, double spawnFieldHeight) {
		double widthChange;
		if (spawnFieldWidth < this.spawnFieldWidth) {
			widthChange = spawnFieldWidth / this.spawnFieldWidth;
			double diff = targetX * widthChange;
			targetX = diff;
		} else {
			widthChange = this.spawnFieldWidth / spawnFieldWidth;
			double diff = targetX * widthChange;
			targetX = diff;
		}
		double heightChange;
		if (spawnFieldHeight < this.spawnFieldHeight) {
			heightChange = spawnFieldHeight / this.spawnFieldHeight;
			double diff = targetY * heightChange;
			targetY = diff;
		} else {
			heightChange = this.spawnFieldHeight / spawnFieldHeight;
			double diff = targetY * heightChange;
			targetY = diff;
		}

		this.spawnFieldWidth = spawnFieldWidth;
		this.spawnFieldHeight = spawnFieldHeight;
	}

	private double  calculateYDistance(double yTarget) {
		return yTarget - posY;
	}

	private double  calculateXDistance(double xTarget) {
		return xTarget - posX;
	}

	private float[] makeColor() {
		float[] color = new float[3];
		color[0]=(float)(Math.random()*360);  
		color[1]=1;  
		color[2]=1; 
		return color;
	}

	// Get / Set

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getTargetX() {
		return targetX;
	}

	public void setTargetX(double targetX) {
		this.targetX = targetX;
	}

	public double getTargetY() {
		return targetY;
	}

	public void setTargetY(double targetY) {
		this.targetY = targetY;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public float[] getColor() {
		return color;
	}

	public void setColor(float[] color) {
		this.color = color;
	}

	public double getRandomEase() {
		return randomEase;
	}

	public void setRandomEase(double randomEase) {
		this.randomEase = randomEase;
	}

	public double getTravelRate() {
		return travelRate;
	}

	public void setTravelRate(double travelRate) {
		this.travelRate = travelRate;
	}

	public int getMaxCollisionSize() {
		return maxCollisionSize;
	}

	public void setMaxCollisionSize(int maxCollisionSize) {
		this.maxCollisionSize = maxCollisionSize;
	}

	public double getSpawnFieldWidth() {
		return spawnFieldWidth;
	}

	public void setSpawnFieldWidth(double spawnFieldWidth) {
		this.spawnFieldWidth = spawnFieldWidth;
	}

	public double getSpawnFieldHeight() {
		return spawnFieldHeight;
	}

	public void setSpawnFieldHeight(double spawnFieldHeight) {
		this.spawnFieldHeight = spawnFieldHeight;
	}

	public boolean isHasReachedTarget() {
		return hasReachedTarget;
	}

	public void setHasReachedTarget(boolean hasReachedTarget) {
		this.hasReachedTarget = hasReachedTarget;
	}

	public double getOriginY() {
		return originY;
	}

	public void setOriginY(double originY) {
		this.originY = originY;
	}

	public boolean isOnWayDown() {
		return onWayDown;
	}

	public void setOnWayDown(boolean onWayDown) {
		this.onWayDown = onWayDown;
	}

	public double getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(double initialSize) {
		this.initialSize = initialSize;
	}

	public boolean isFinishedCycle() {
		return finishedCycle;
	}

	public void setFinishedCycle(boolean finishedCycle) {
		this.finishedCycle = finishedCycle;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(color);
		result = prime * result + (finishedCycle ? 1231 : 1237);
		result = prime * result + (hasReachedTarget ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(initialSize);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + maxCollisionSize;
		result = prime * result + (onWayDown ? 1231 : 1237);
		temp = Double.doubleToLongBits(originY);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(posX);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(posY);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(randomEase);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(size);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(spawnFieldHeight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(spawnFieldWidth);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(targetX);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(targetY);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((trackName == null) ? 0 : trackName.hashCode());
		temp = Double.doubleToLongBits(travelRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Particle other = (Particle) obj;
		if (!Arrays.equals(color, other.color))
			return false;
		if (finishedCycle != other.finishedCycle)
			return false;
		if (hasReachedTarget != other.hasReachedTarget)
			return false;
		if (Double.doubleToLongBits(initialSize) != Double
				.doubleToLongBits(other.initialSize))
			return false;
		if (maxCollisionSize != other.maxCollisionSize)
			return false;
		if (onWayDown != other.onWayDown)
			return false;
		if (Double.doubleToLongBits(originY) != Double
				.doubleToLongBits(other.originY))
			return false;
		if (Double.doubleToLongBits(posX) != Double
				.doubleToLongBits(other.posX))
			return false;
		if (Double.doubleToLongBits(posY) != Double
				.doubleToLongBits(other.posY))
			return false;
		if (Double.doubleToLongBits(randomEase) != Double
				.doubleToLongBits(other.randomEase))
			return false;
		if (Double.doubleToLongBits(size) != Double
				.doubleToLongBits(other.size))
			return false;
		if (Double.doubleToLongBits(spawnFieldHeight) != Double
				.doubleToLongBits(other.spawnFieldHeight))
			return false;
		if (Double.doubleToLongBits(spawnFieldWidth) != Double
				.doubleToLongBits(other.spawnFieldWidth))
			return false;
		if (Double.doubleToLongBits(targetX) != Double
				.doubleToLongBits(other.targetX))
			return false;
		if (Double.doubleToLongBits(targetY) != Double
				.doubleToLongBits(other.targetY))
			return false;
		if (trackName == null) {
			if (other.trackName != null)
				return false;
		} else if (!trackName.equals(other.trackName))
			return false;
		if (Double.doubleToLongBits(travelRate) != Double
				.doubleToLongBits(other.travelRate))
			return false;
		return true;
	}



}

