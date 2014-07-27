package rach.particle;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Scanner;

import javafx.beans.InvalidationListener;
import rach.utility.ColorMaker;

public class HistoryBlobManager {

	private List<HistoryBlob> hbList;
	private int spawnFieldWidth;
	private int spawnFieldHeight;
	private ParticleManager particleManager;
	private int count;
	private Map<String,float[]> colorMap;
	private boolean endOfList;

	private List<PropertyChangeListener> listener = new ArrayList<PropertyChangeListener>();

	private void endOfList() {
		if (count == hbList.size() -1) {
			endOfList = true;
			notifyListeners(this,"endOfList","false","true");
		}
	}

	private void notifyListeners(Object object, String property, String oldValue, String newValue) {
		for (PropertyChangeListener name : listener) {
			name.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
		}
	}

	public void addChangeListener(PropertyChangeListener newListener) {
		listener.add(newListener);
	}

	public HistoryBlobManager(int spawnFieldWidth, int spawnFieldHeight, int startIndex) {
		this.setSpawnFieldWidth(spawnFieldWidth);
		this.setSpawnFieldHeight(spawnFieldHeight);
		count = startIndex;
		hbList = new ArrayList<HistoryBlob>();
		particleManager = new ParticleManager(spawnFieldWidth,spawnFieldHeight,spawnFieldWidth,spawnFieldHeight);
		colorMap = new HashMap<String,float[]>();
		endOfList = false;
		readFromFile();
		
		//writeToFile();
	}
	public void addHistoryParticle() {
		particleManager.addParticle(ParticleGenerator.historyParticle(
				count, hbList.size(), spawnFieldWidth, spawnFieldHeight,hbList.get(count).getColor()));
		count ++;
		System.out.println("Count: " + count);
		endOfList();
	}

	public String getParticleURL(int index,Particle p) {
		String s = null;
		s = hbList.get(index).getUrl();
		return s;
	}

	private void writeToFile() {
		File f = new File("urls.txt");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (HistoryBlob hb : hbList) {
			pw.println(hb.getUrl() + "," + hb.getColor()[0] + "," + hb.getColor()[1] + "," + hb.getColor()[2]);
		}
		pw.close();

	}

	public List<Particle> updateAllParticles() {
		return particleManager.updateAllParticles(spawnFieldWidth, spawnFieldHeight, false);
	}
	public void printYears() {
		for (HistoryBlob hb : hbList) {
			System.out.println(hb.getYear() + " " + hb.getMonth() + " " + hb.getDayOfMonth() + " " + hb.getHour() + " " + hb.getMinute() + " " + hb.getUrl());
		}
	}
	public List<HistoryBlob> getHbList() {
		return hbList;
	}
	private void readFromFile() {
		File f = new File("history.csv");
		Scanner sc = null;
		try {
			sc = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		sc.useDelimiter("\n");
		while (sc.hasNext()) {
			String str = sc.next();
			int slashCount = 0;
			int charCount = 0;
			StringBuilder s = new StringBuilder();
			while (slashCount < 3) {
				char c = str.charAt(charCount);
				if (c == '/') {
					slashCount ++;
				}
				s.append(c);
				charCount ++;
			}
			String url = s.toString();
			int timrPos = str.lastIndexOf(",");
			String strTime = str.substring((timrPos + 1));
			String strDate = str.substring(timrPos - 10,((timrPos - 10) + 10));
			String strTD = strDate.trim() + " " + strTime.trim();
			HistoryBlob db = new HistoryBlob(url,null,strTD,assignColor(url));
			hbList.add(db);
		}
	}

	private float[] assignColor(String s) {
		float color[] = null;
		if ((color = colorMap.get(s)) == null) {
			colorMap.put(s, color = ColorMaker.makeRandomRGBColor());
		}
		return color;
	}

	public int getSpawnFieldWidth() {
		return spawnFieldWidth;
	}

	public void setSpawnFieldWidth(int spawnFieldWidth) {
		this.spawnFieldWidth = spawnFieldWidth;
	}

	public int getSpawnFieldHeight() {
		return spawnFieldHeight;
	}

	public void setSpawnFieldHeight(int spawnFieldHeight) {
		this.spawnFieldHeight = spawnFieldHeight;
	}

	public void setHbList(List<HistoryBlob> hbList) {
		this.hbList = hbList;
	}




}
