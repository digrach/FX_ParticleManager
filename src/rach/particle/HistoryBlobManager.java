package rach.particle;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
	private List<IParticle> pList;
	private Map<String,UrlRender> urlMap = new HashMap<String,UrlRender>();



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
		createParticles();
		//writeToFile();
		printTrackUrls();
	}
	public void createParticles() {
		pList = new ArrayList<IParticle>();
		for (int x = 0; x < hbList.size(); x++) {
			HistoryBlob hb = hbList.get(x);
			pList.add(ParticleGenerator.historyParticle(x, hbList.size(), spawnFieldWidth, spawnFieldHeight, 
					hb.getColor(), hb.getUrl(), hb.getStrDate(), hb.getStrTime(), hb.getColor()));
		}
	}
	public void addHistoryParticle() {	
		IParticle currentP = pList.get(count);
		particleManager.addParticle(currentP);
		trackUrl(currentP.getUrl(),currentP.getColor());
		count ++;
		endOfList();
	}

	public List<String> getUrlPosition(UrlRender urlRender) {
		List<String> topList = new ArrayList<String>();
		topList.add(0, urlRender.getUrl());
		int currentRank = 0;

		for (Map.Entry<String, UrlRender> entry : urlMap.entrySet()) {
			String s = entry.getKey();
			UrlRender u = entry.getValue();
			if (urlRender.getCount() > u.getCount()) {
				topList.add(currentRank, u.getUrl());
				currentRank ++;
			}
			if (currentRank == 10) {
				return topList;
			}
		}
		return topList;
	}

	public String getParticleURL(int index,IParticle p) {
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
			e.printStackTrace();
		}
		for (HistoryBlob hb : hbList) {
			pw.println(hb.getUrl() + "," + hb.getColor()[0] + "," + hb.getColor()[1] + "," + hb.getColor()[2]);
		}
		pw.close();

	}

	public List<IParticle> updateAllParticles() {
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
			String newUrl = trimUrlPrefix(url);
			float[] col = doColorMapping(newUrl);
			HistoryBlob db = new HistoryBlob(newUrl,null,strTD,col);
			//trackUrl(newUrl,col);
			hbList.add(db);
		}
	}

	private void trackUrl(String url, float[] color) {
		UrlRender u = urlMap.get(url);
		if(u == null) {
			u = new UrlRender(color,url);
			urlMap.put(url, u);
		} else {
			u.incrementCount();
		}
	}

	public UrlRender getUrlCount(String url) {
		int c = 0;
		UrlRender ur = urlMap.get(url);
		if (ur != null) {
			c = ur.getCount();
		}
		return ur;
	}

	private void printTrackUrls() {
		Iterator i = urlMap.entrySet().iterator();
		while (i.hasNext()) {
			Map.Entry pairs = (Map.Entry)i.next();

			String key = (String) pairs.getKey();
			UrlRender val = (UrlRender) pairs.getValue();

			System.out.println("key: " + key + " count: " + val.getCount());

		}
	}

	private String trimUrlPrefix(String s) {
		int sc = s.lastIndexOf(":");
		String start = s.substring(sc+3);
		return start;
	}

	private float[] doColorMapping(String s) {
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
