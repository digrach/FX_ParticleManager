package rach.particle;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class UrlRanker {

	private Map<Integer,String> m = new TreeMap<Integer,String>();

	public UrlRanker() {

	}
	
	public void add(String url) {
		if(!m.containsValue(url)) {
			m.put(1, url);
		} else {
			
		}
	}
//	public int getCount(String url) {
//		
//	}
	
	
	
	public int addUrl(String url) {
		int rank = 0;
		if(!m.containsValue(url)) {
			m.put(1, url);
		} else {		
			Iterator i = m.entrySet().iterator();
			while (i.hasNext()) {
				Map.Entry pair = (Map.Entry)i.next();
				int r = (int) pair.getKey();
				String u = (String) pair.getValue();
				if (url.equals(u)) {
					m.put(r + 1, u);
					if (r > rank) {
						rank = r;
					}
				}
			}
		}
		return rank;
	}
	public void check(IParticle p) {
		String url = p.getUrl();


	}

}
