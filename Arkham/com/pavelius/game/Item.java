package com.pavelius.game;

enum Item {
	
	Shotgun, Tommygun, Axe;	
		
	static final int get(Item type, Token token) {
		switch(token)
		{
		case PhysicCombat:
			switch(type)
			{
			case Shotgun: return 4;
			case Tommygun: return 6;
			case Axe: return 2;
			default: return 0;
			}
		case Hands:
			switch(type)
			{
			case Shotgun:
			case Tommygun:
				return 2;
			case Axe:
				return 1;
			default: return 0;
			}			
		default:
			return 0;
		}
	}
	
}