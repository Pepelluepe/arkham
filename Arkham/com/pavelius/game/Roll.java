package com.pavelius.game;

class Roll {

	public static int dice(int count, int border) {
		int result = 0;
		for (int i = 0; i < count; i++)
			result += Math.round(Math.random()) % border;
		return result;
	}

	
	public static int random(int minimum, int maximum) {
		return minimum + (int)(Math.random()*maximum);
	}
	
}