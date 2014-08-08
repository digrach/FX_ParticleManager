package rach.particle;

public interface IParticle {
	
	String getUrl();

	void update(double spawnFieldWidth, double spawnFieldHeight);

	double getPosY();

	void setPosY(double posY);

	double getPosX();

	void setPosX(double posX);

	double getTargetX();

	void setTargetX(double targetX);

	double getTargetY();

	void setTargetY(double targetY);

	double getSize();

	void setSize(double size);

	float[] getColor();

	void setColor(float[] color);

	double getRandomEase();

	void setRandomEase(double randomEase);

	double getTravelRate();

	void setTravelRate(double travelRate);

	int getMaxCollisionSize();

	void setMaxCollisionSize(int maxCollisionSize);

	double getSpawnFieldWidth();

	void setSpawnFieldWidth(double spawnFieldWidth);

	double getSpawnFieldHeight();

	void setSpawnFieldHeight(double spawnFieldHeight);

	boolean isHasReachedTarget();

	void setHasReachedTarget(boolean hasReachedTarget);

	double getOriginY();

	void setOriginY(double originY);

	boolean isOnWayDown();

	void setOnWayDown(boolean onWayDown);

	double getInitialSize();

	void setInitialSize(double initialSize);

	boolean isFinishedCycle();

	void setFinishedCycle(boolean finishedCycle);

	String getTrackName();

	void setTrackName(String trackName);

	int hashCode();

	boolean equals(Object obj);

}