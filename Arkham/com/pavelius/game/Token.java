package com.pavelius.game;

enum Token {
	Empthy,
	// Locations
	AdministrationBuilding, ArkhamAsylum, BankOfArkham, Docks,
	TrainStation,
	// Investigator Ability
	Speed, Stealth, Fight, Will, Lore, Luck, Sanity, Stamina, Toughness,
	PhysicCombat, MagicCombat, Combat, Horror,
	Arrested, Curse, Bless,
	// Types
	Money, Clue, Spell, UniqueItem, CommonItem,
	Hands, Cost,
	//
	PosX, PosY,
	// Special ability
	Nightmare, Overwelming, Endless;
	
	int get(Token id)
	{
		switch(id)
		{
		case PosY:
			switch(this)
			{
			case TrainStation:
			case ArkhamAsylum:
			case BankOfArkham:
				return 88;
			case Docks:
				return 1016;
			default:
				return 0; 			
			}
		case PosX:
			switch(this)
			{
			case TrainStation: return 263;
			case BankOfArkham: return 511;
			case Docks: return 112;
			default: return 0; 			
			}
		default:
			return 0;
		}
	}
	
}