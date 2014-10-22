package rach.particle;

public class HistoryUrl implements Comparable<HistoryUrl> {

	private float[] color;
	private String url;
	private int count;

	public HistoryUrl(float[] color, String url) {
		super();
		this.color = color;
		this.url = url;
		this.setCount(1);
	}
	public int incrementCount() {
		count ++;
		return count;
	}
	public float[] getColor() {
		return color;
	}
	public void setColor(float[] color) {
		this.color = color;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public int compareTo(HistoryUrl o) {
		return Integer.compare(count, o.getCount());
	}



}
