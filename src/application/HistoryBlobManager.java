package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HistoryBlobManager {

	private List<HistoryBlob> hbList;
	private int spawnFieldWidth;
	private int spawnFieldHeight;
	private ParticleManager particleManager;
	private int count;

	public HistoryBlobManager(int spawnFieldWidth, int spawnFieldHeight) {
		this.setSpawnFieldWidth(spawnFieldWidth);
		this.setSpawnFieldHeight(spawnFieldHeight);
		hbList = new ArrayList<HistoryBlob>();
		particleManager = new ParticleManager(spawnFieldWidth,spawnFieldHeight,spawnFieldWidth,spawnFieldHeight);
		readFromFile();
	}
	public void addHistoryParticle() {
//		double perc = (double)hbList.get(count).get / (double)total;
//		double perc = (double)index / (double)total;
//		double perc = (double)count / (double)total;
		
		double perc = (double)count / (double)hbList.size();

		System.out.println("count: " + count);
		System.out.println("perc: " + perc);

		double xc = perc * spawnFieldWidth;
		double xy = 100;
		
		Particle p = new Particle(spawnFieldWidth/2,spawnFieldHeight,xc,xy, 
				spawnFieldWidth,spawnFieldHeight,ColorMaker.makeRandomRGBColor(),10,20);
		particleManager.addParticle(p);
		
		//particleManager.addHistoryParticle(count, hbList.size());
		count ++;
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
			HistoryBlob db = new HistoryBlob(url,null,strTD);
			hbList.add(db);
		}
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
