package rach.particle;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class HistoryParticle extends Particle {

	private String url;
	private String strDate;
	private String strTime;
	private int dayOfMonth;
	private int month;
	private int year;
	private int hour;
	private int minute;
	private float color[];

	public HistoryParticle(double posx, double posy, double targetx, double targety, 
			double spawnFieldWidth, double spawnFieldHeight, 
			float[] rbgColor, int size, int maxCollisionSize,
			String url,String strDate,String strTime,float color[]) {
		super(posx,posy,targetx,targety,spawnFieldWidth,spawnFieldHeight,rbgColor,size,maxCollisionSize);
		setUrl(url);
		setStrDate(strDate);
		setStrTime(strTime);
		setColor(color);
		//convertToDate();
	}
	public HistoryParticle(String url,String strDate,String strTime,float color[]) {
		super(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, null, 0, 0);
		setUrl(url);
		setStrDate(strDate);
		setStrTime(strTime);
		setColor(color);
		//convertToDate();
	}
	public String getStrTime() {
		return strTime;
	}
	public void setStrTime(String strTime) {
		this.strTime = strTime;
	}
	public int getDayOfMonth() {
		return dayOfMonth;
	}
	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStrDate() {
		//System.out.println("Getting date: " + strDate);
		return strDate;
	}
	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}
	public float[] getColor() {
		return color;
	}
	public void setColor(float[] color) {
		this.color = color;
	}
	@Override
	public void update(double spawnFieldWidth, double spawnFieldHeight) {
		super.update(spawnFieldWidth, spawnFieldHeight);
	}
	@Override
	public double getPosY() {
		return super.getPosY();
	}
	@Override
	public void setPosY(double posY) {
		super.setPosY(posY);		
	}
	@Override
	public double getPosX() {
		return super.getPosX();
	}
	@Override
	public void setPosX(double posX) {
		super.setPosX(posX);		
	}
	@Override
	public double getTargetX() {
		return super.getTargetX();
	}
	@Override
	public void setTargetX(double targetX) {
		super.setTargetX(targetX);		
	}
	@Override
	public double getTargetY() {
		return super.getTargetY();
	}
	@Override
	public void setTargetY(double targetY) {
		super.setTargetY(targetY);		
	}
	@Override
	public double getSize() {
		return super.getSize();
	}
	@Override
	public void setSize(double size) {
		super.setSize(size);		
	}
	@Override
	public double getRandomEase() {
		return super.getRandomEase();
	}
	@Override
	public void setRandomEase(double randomEase) {
		super.setRandomEase(randomEase);		
	}
	@Override
	public double getTravelRate() {
		return super.getTravelRate();
	}
	@Override
	public void setTravelRate(double travelRate) {
		super.setTravelRate(travelRate);		
	}
	@Override
	public int getMaxCollisionSize() {
		return super.getMaxCollisionSize();
	}
	@Override
	public void setMaxCollisionSize(int maxCollisionSize) {
		super.setMaxCollisionSize(maxCollisionSize);		
	}
	@Override
	public double getSpawnFieldWidth() {
		return super.getSpawnFieldWidth();
	}
	@Override
	public void setSpawnFieldWidth(double spawnFieldWidth) {
		super.setSpawnFieldWidth(spawnFieldWidth);		
	}
	@Override
	public double getSpawnFieldHeight() {
		return super.getSpawnFieldHeight();
	}
	@Override
	public void setSpawnFieldHeight(double spawnFieldHeight) {
		super.setSpawnFieldHeight(spawnFieldHeight);		
	}
	@Override
	public boolean isHasReachedTarget() {
		return super.isHasReachedTarget();
	}
	@Override
	public void setHasReachedTarget(boolean hasReachedTarget) {
		super.setHasReachedTarget(hasReachedTarget);		
	}
	@Override
	public double getOriginY() {
		return super.getOriginY();
	}
	@Override
	public void setOriginY(double originY) {
		super.setOriginY(originY);		
	}
	@Override
	public boolean isOnWayDown() {
		return super.isOnWayDown();
	}
	@Override
	public void setOnWayDown(boolean onWayDown) {
		super.setOnWayDown(onWayDown);		
	}
	@Override
	public double getInitialSize() {
		return super.getInitialSize();
	}
	@Override
	public void setInitialSize(double initialSize) {
		super.setInitialSize(initialSize);		
	}
	@Override
	public boolean isFinishedCycle() {
		return super.isFinishedCycle();
	}
	@Override
	public void setFinishedCycle(boolean finishedCycle) {
		super.setFinishedCycle(finishedCycle);	
	}
	@Override
	public String getTrackName() {
		return super.getTrackName();
	}
	@Override
	public void setTrackName(String trackName) {
		super.setTrackName(trackName);		
	}
	private void convertToDate() {
		System.out.println("strTime converting: " + strTime);
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");
		DateTime dt = formatter.parseDateTime(strTime);

		this.setDayOfMonth(dt.getDayOfMonth());
		this.setMonth(dt.getMonthOfYear());
		this.setYear(dt.getYear());
		this.setHour(dt.getHourOfDay());
		this.setMinute(dt.getMinuteOfHour());
	}



}
