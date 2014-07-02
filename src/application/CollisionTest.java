package application;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javafx.scene.paint.Color;
import junit.framework.Assert;

import org.junit.Test;

public class CollisionTest {

	@Test
	public void test() {
		
		//HistoryBlobManager hbm = new HistoryBlobManager();


//		//		String string = "2014-04-02";
//		String string = "2/04/2014 7:54";
//		java.util.Date date = null;
//		try {
//			//			date = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(string);
//			date = new SimpleDateFormat("MM/dd/yyyy hh:mm", Locale.US).parse(string);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(date); // Sat Jan 02 00:00:00 BOT 2010
//
//
//		Calendar myCal = new GregorianCalendar();
//		myCal.setTime(date);
//		System.out.println(myCal.get(Calendar.MINUTE));
//		System.out.println(myCal.get(Calendar.HOUR_OF_DAY));
//		System.out.println(myCal.get(Calendar.DAY_OF_MONTH));
//		System.out.println(myCal.get(Calendar.DAY_OF_WEEK));
//		System.out.println(myCal.get(Calendar.YEAR));
//
//		List<HistoryBlob> l = new ArrayList<HistoryBlob>();
//		File f = new File("history.csv");
//		Scanner sc = null;
//		try {
//			sc = new Scanner(f);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		sc.useDelimiter("\n");
//		while (sc.hasNext()) {
//			String str = sc.next();
//			int slashCount = 0;
//			int charCount = 0;
//			StringBuilder s = new StringBuilder();
//			while (slashCount < 3) {
//				char c = str.charAt(charCount);
//				if (c == '/') {
//					slashCount ++;
//				}
//				s.append(c);
//				charCount ++;
//			}
//
//			String url = s.toString();
//			System.out.println("s: " + url);
//
//			int datePos = str.lastIndexOf(",");
//			String strDate = str.substring(datePos + 1);
//			System.out.println(strDate);
//			HistoryBlob db = new HistoryBlob(url,strDate);
//			l.add(db);
//		}
//		System.out.println("SIZE: " + l.size());

		//List<DateBlob> l = new ArrayList<DateBlob>();
		//		File f = new File("history.csv");
		//		Scanner sc = null;
		//		try {
		//			sc = new Scanner(f);
		//		} catch (FileNotFoundException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		//		sc.useDelimiter("\n");
		//		int count = 0;
		//		while (sc.hasNext()) {
		//			System.out.println("Count: " + count);
		//			String str = sc.next();
		//
		//			int slashCount = 0;
		//			int charCount = 0;
		//			StringBuilder s = new StringBuilder();
		//			while (slashCount < 3) {
		//				char c = str.charAt(charCount);
		//				if (c == '/') {
		//					slashCount ++;
		//				}
		//				s.append(c);
		//				charCount ++;
		//			}
		//
		//			String url = s.toString();
		//			System.out.println("s: " + url);
		//			//			String[] spl = str.split(",");
		//			//			String url = spl[0]; System.out.println("url: " + url);
		//			//			String title = spl[1]; System.out.println("title: " + title);
		//			//			String strDate = spl[2]; System.out.println("strDate: " + strDate);
		//			//			int visitCount = Integer.parseInt(spl[3]); System.out.println("visitCount: " + visitCount);
		//			//			int typedCount = Integer.parseInt(spl[4]); System.out.println("typedCount: " + typedCount);
		//			//			String referrer = spl[5]; System.out.println("referrer: " + referrer);
		//			//			String visitID = spl[6]; System.out.println("visitID: " + visitID);
		//			//
		//			//			DateBlob db = new DateBlob(url,title,strDate,visitCount,typedCount,referrer,visitID);
		//			//			l.add(db);
		//			count ++;
		//			//System.out.println(sc.next() + "***");
		//		}
		//System.out.println("SIZE: " + l.size());

		//		List<DateBlob> l = new ArrayList<DateBlob>();
		//		File f = new File("history.csv");
		//		Scanner sc = null;
		//		try {
		//			sc = new Scanner(f);
		//		} catch (FileNotFoundException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		//		sc.useDelimiter("\n");
		//		int count = 0;
		//		while (sc.hasNext()) {
		//			System.out.println("Count: " + count);
		//			String str = sc.next();
		//			String[] spl = str.split(",");
		//			String url = spl[0]; System.out.println("url: " + url);
		//			String title = spl[1]; System.out.println("title: " + title);
		//			String strDate = spl[2]; System.out.println("strDate: " + strDate);
		//			int visitCount = Integer.parseInt(spl[3]); System.out.println("visitCount: " + visitCount);
		//			int typedCount = Integer.parseInt(spl[4]); System.out.println("typedCount: " + typedCount);
		//			String referrer = spl[5]; System.out.println("referrer: " + referrer);
		//			String visitID = spl[6]; System.out.println("visitID: " + visitID);
		//
		//			DateBlob db = new DateBlob(url,title,strDate,visitCount,typedCount,referrer,visitID);
		//			l.add(db);
		//			count ++;
		//			//System.out.println(sc.next() + "***");
		//		}
		//		System.out.println("SIZE: " + l.size());

		//		List<DateBlob> l = new ArrayList<DateBlob>();
		//		File f = new File("history.csv");
		//		Scanner sc = null;
		//		try {
		//			sc = new Scanner(f);
		//		} catch (FileNotFoundException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		//		sc.useDelimiter(",");
		//		int count = 0;
		//		while (sc.hasNext()) {
		//			System.out.println("Count: " + count);
		//			String url = sc.next(); System.out.println("url: " + url);
		//			String title = sc.next(); System.out.println("title: " + title);
		//			String strDate = sc.next(); System.out.println("strDate: " + strDate);
		//			int visitCount = Integer.parseInt(sc.next()); System.out.println("visitCount: " + visitCount);
		//			int typedCount = Integer.parseInt(sc.next()); System.out.println("typedCount: " + typedCount);
		//			String referrer = sc.next(); System.out.println("referrer: " + referrer);
		//			String visitID = sc.next(); System.out.println("visitID: " + visitID);
		//
		//			DateBlob db = new DateBlob(url,title,strDate,visitCount,typedCount,referrer,visitID);
		//			l.add(db);
		//			count ++;
		//			//System.out.println(sc.next() + "***");
		//		}
		//		System.out.println("SIZE: " + l.size());

		//		Calendar c = Calendar.getInstance(Locale.US);
		//		c.setTime(date);
		//		System.out.println(Calendar.DAY_OF_MONTH);
		//		System.out.println(Calendar.MONTH);
		//		System.out.println(Calendar.DATE);
		//		System.out.println(Calendar.HOUR_OF_DAY);



		//		double SCENE_WIDTH = 800;
		//		double SCENE_HEIGHT = 600;
		//		ParticleManager pm = new ParticleManager((int)SCENE_WIDTH,(int)SCENE_HEIGHT,(int)SCENE_WIDTH,(int)SCENE_HEIGHT);
		//
		//		Particle p1 = new Particle(400,400,500,500,SCENE_WIDTH,SCENE_HEIGHT);
		//		Particle p2 = new Particle(410,410,500,500,SCENE_WIDTH,SCENE_HEIGHT);
		//
		//		assertTrue(check (p1,p2));
		//		
		//		Particle p3 = new Particle(400,400,500,500,SCENE_WIDTH,SCENE_HEIGHT);
		//		Particle p4 = new Particle(400,411,500,500,SCENE_WIDTH,SCENE_HEIGHT);
		//		
		//		assertTrue(check (p3,p4));

	}

	public boolean check(Particle pp1, Particle pp2) {



		int xDistance;
		int yDistance;
		if (pp1.getPosX() > pp2.getPosX()) {
			// Distance between particles on the x axis.
			xDistance = (int) Math.abs((pp1.getPosX() + pp1.getSize()) - pp2.getPosX());	
		} else {
			// Distance between particles on the x axis.
			xDistance = (int) Math.abs(pp1.getPosX() - (pp2.getPosX() + pp2.getSize()));
		}
		if (pp1.getPosY() > pp2.getPosY()) {
			// Distance between particles on the x axis.
			yDistance = (int) Math.abs((pp1.getPosY() + pp1.getSize()) - pp2.getPosY());
		} else {
			yDistance = (int) Math.abs(pp1.getPosY() - (pp2.getPosY() + pp2.getSize()));
		}				

		// Combined width of both particles.
		int combinedWidth = (int) (pp1.getSize() + pp2.getSize());
		System.out.println("combinedWidth: " + combinedWidth);

		// Combined height of both particles.
		int combinedHeight = (int) (pp1.getSize() + pp2.getSize());
		System.out.println("combinedHeight: " + combinedHeight);


		return xDistance <= combinedWidth && yDistance <= combinedHeight;
	}
}


