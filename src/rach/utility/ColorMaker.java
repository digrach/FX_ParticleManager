package rach.utility;

public class ColorMaker {
	
	public static float[] makeRandomRGBColor() {
		float[] color = new float[3];
		color[0]=(float)(Math.random()*360);  
		color[1]=1;  
		color[2]=1; 
		return color;
	}

}
