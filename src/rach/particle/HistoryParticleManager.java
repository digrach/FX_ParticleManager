package rach.particle;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import rach.utility.ColorMaker;

public class HistoryParticleManager {

	private List<IParticle> historyParticleList;
	private int spawnFieldWidth;
	private int spawnFieldHeight;
	private ParticleManager particleManager;
	private Map<String,float[]> colorMap;
	private boolean endOfList;
	private List<IParticle> particleList;
	private Map<String,HistoryUrl> urlMap = new HashMap<String,HistoryUrl>();
	private List<PropertyChangeListener> listener = new ArrayList<PropertyChangeListener>();

	
	
	public HistoryParticleManager(int spawnFieldWidth, int spawnFieldHeight, int startIndex) {
		this.setSpawnFieldWidth(spawnFieldWidth);
		this.setSpawnFieldHeight(spawnFieldHeight);
		historyParticleList = new ArrayList<IParticle>();
		particleManager = new ParticleManager(spawnFieldWidth,spawnFieldHeight,spawnFieldWidth,spawnFieldHeight);
		
		System.out.println("START P COUNT: " + particleManager.getCurrentParticleCount());
		colorMap = new HashMap<String,float[]>();
		endOfList = false;
		readFromFile();
		createHistoryParticles();
		if (startIndex >= particleList.size()) {
			startIndex = 0;
		}
		particleManager.setCurrentParticleCount(startIndex);
		if (startIndex > 0) {
			addStarterHistoryParticles(startIndex);
		}
	}
	public void addChangeListener(PropertyChangeListener newListener) {
		listener.add(newListener);
	}
	private void endOfList() {
		if (particleManager.getCurrentParticleCount() == historyParticleList.size() -1) {
			endOfList = true;
			notifyListeners(this,"endOfList","false","true");
		}
	}
	private void notifyListeners(Object object, String property, String oldValue, String newValue) {
		for (PropertyChangeListener name : listener) {
			name.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
		}
	}
	public void createHistoryParticles() { // Create the history particles from file data - happens once.
		particleList = new ArrayList<IParticle>();
		for (int x = 0; x < historyParticleList.size(); x++) {
			IParticle historyParticle = historyParticleList.get(x);

			particleList.add(ParticleGenerator.historyParticle(x, historyParticleList.size(), spawnFieldWidth, spawnFieldHeight, 
					historyParticle.getColor(), historyParticle.getUrl(), historyParticle.getStrDate(), historyParticle.getStrTime(), historyParticle.getColor()));
		}
	}
	private void addStarterHistoryParticles(int startIndex) {
		// Called when start index passed to constructor is greater than 0.
		// Initialises the url counts without creating a particle to render.
		//if (startIndex >= particleList.size()) return;
		for (int x = 0; x < startIndex; x++) {
			IParticle currentParticle = particleList.get(x);
			addUrl(currentParticle.getUrl(),currentParticle.getColor()); // Has the url been added before?
		}
	}
	public void addHistoryParticle() {	
		IParticle currentParticle = particleList.get(particleManager.getCurrentParticleCount());
		particleManager.addParticle(currentParticle);
		addUrl(currentParticle.getUrl(),currentParticle.getColor()); // Has the url been added before?
		endOfList();
	}
	public List<HistoryUrl> getUrlPosition() {
		List<HistoryUrl> uList = new ArrayList<HistoryUrl>(urlMap.values());
		Collections.sort(uList);
		if (uList.size() > 10) {
			uList = uList.subList(uList.size() - 11, uList.size()-1);
		}
		return uList;
	}
	public String getParticleURL(int index,IParticle p) {
		return historyParticleList.get(index).getUrl();
	}
	public List<IParticle> updateAllParticles() {
		return particleManager.updateAllParticles(spawnFieldWidth, spawnFieldHeight, false);
	}
	public List<IParticle> getHbList() {
		return historyParticleList;
	}
	
	private void addUrl(String url, float[] color) { // check if the current url has been added.
		HistoryUrl u = urlMap.get(url); // urlMap - key:String, value:UrlRender object.
		if(u == null) { // if the url doesn't exist, add it.
			u = new HistoryUrl(color,url);
			urlMap.put(url, u);
		} else { // if the url does exist, increment it's count.
			u.incrementCount();
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
	public void setHbList(List<IParticle> hbList) {
		this.historyParticleList = hbList;
	}
	public int getCurrentParticleCount() {
		return particleManager.getCurrentParticleCount();
	}

	private void writeToFile() {
		File f = new File("urls.txt");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (IParticle hb : historyParticleList) {
			pw.println(hb.getUrl() + "," + hb.getColor()[0] + "," + hb.getColor()[1] + "," + hb.getColor()[2]);
		}
		pw.close();
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

			IParticle hbp = new HistoryParticle(0,0,0,0,
					0,0,
					col,0,0,
					newUrl,strTD,strTime,col);
			historyParticleList.add(hbp);
		}
	}

	//	private void printTrackUrls() {
	//		Iterator i = urlMap.entrySet().iterator();
	//		while (i.hasNext()) {
	//			Map.Entry pairs = (Map.Entry)i.next();
	//			String key = (String) pairs.getKey();
	//			HistoryUrl val = (HistoryUrl) pairs.getValue();
	//		}
	//	}
	//	public HistoryUrl getUrlCount(String url) {
	//	int c = 0;
	//	HistoryUrl ur = urlMap.get(url);
	//	if (ur != null) {
	//		c = ur.getCount();
	//	}
	//	return ur;
	//}

}
