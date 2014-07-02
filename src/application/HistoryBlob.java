package application;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class HistoryBlob {

	private String url;
	private String strDate;
	private String strTime;
	private int dayOfMonth;
	private int month;
	private int year;
	private int hour;
	private int minute;
	private Particle particle;

	public HistoryBlob(String url,String strDate,String strTime) {
		setUrl(url);
		this.setStrDate(strDate);
		this.setStrTime(strTime);
		convertToDate();
	}
	
	private void convertToDate() {
		
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");
		DateTime dt = formatter.parseDateTime(strTime);
		
		this.setDayOfMonth(dt.getDayOfMonth());
		this.setMonth(dt.getMonthOfYear());
		this.setYear(dt.getYear());
		this.setHour(dt.getHourOfDay());
		this.setMinute(dt.getMinuteOfHour());
		
	}
	
	

	public Particle getParticle() {
		return particle;
	}

	public void setParticle(Particle particle) {
		this.particle = particle;
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
		return strDate;
	}



	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}




}
