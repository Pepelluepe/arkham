package com.pavelius.game;

enum Monster {
	
	Cultist, DarkYoung;
	
	static final int get(Monster type, Token token) {
		switch(token)
		{
		case Toughness:
			switch(type)
			{
			case DarkYoung: return 3;
			default: return 1;
			}			
		case Combat:
			switch(type)
			{
			case Cultist: return 1;
			default: return 0;
			}
		case Stamina:
			switch(type)
			{
			case DarkYoung: return 3;
			default: return 1;
			}			
		case Horror:
			switch(type)
			{
			default: return 0;
			}
		case Sanity:
			switch(type)
			{
			case DarkYoung: return 3;
			default: return 0;
			}			
		default:
			return 0;
		}
	}
	
}