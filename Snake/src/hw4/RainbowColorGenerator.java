package hw4;

import java.awt.Color;
import java.util.Random;

import color.ColorGenerator;
import main.Config;

public class RainbowColorGenerator implements ColorGenerator{
	/**
	 * The base color (average color) to generate.
	 */
	public static final Color Red = new Color(0.25f, 0.0f, 0.0f);
	public static final Color Orange = new Color(0.25f, 0.125f, 0.0f);
	public static final Color Yellow = new Color(0.25f, 0.25f, 0.0f);
	public static final Color Green = new Color(0.0f, 0.25f, 0.0f);
	public static final Color Blue = new Color(0.0f, 0.0f, 0.25f);
	public static final Color Purple = new Color(0.25f, 0.0f, 0.25f);
	
	/**
	 * The number generator for creating colors.
	 */
	private Random r = Config.RANDOM;

	/**
	 * Makes a new color. For style, subclasses should create multiple colors.
	 * @return A Color
	 */
	@Override
	public Color createColor() {
		int next = r.nextInt(6);
		switch(next){
		case 0:
			return Red.darker();
		case 1:
			return Orange.darker();
		case 2:
			return Yellow.darker();
		case 3:
			return Green.darker();
		case 4:
			return Blue.darker();
		default:
			return Purple.darker();
		}
	}
	
	
}
