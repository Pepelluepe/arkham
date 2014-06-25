package com.pavelius.game;
import java.util.ArrayList;

public class Investigator {
	
	private int speed;
	private int stealth;
	private int fight;
	private int will;
	private int lore;
	private int luck;
	private int clues;
	private int money;
	private int stamina;
	private int sanity;
	private Item left;
	private Item right;
	private int focusSpeedStealth;
	private int focusFightWill;
	private int focusLoreLuck;
	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<Token> skills = new ArrayList<Token>();
	private ArrayList<Monster> trophis = new ArrayList<Monster>();
	
	Investigator()
	{
		this.focusSpeedStealth = 0;
		this.focusFightWill = 0;
		this.focusLoreLuck = 0;
		this.speed = 4;
		this.stealth = 1;
		this.fight = 4;
		this.will = 1;
		this.lore = 4;
		this.luck = 1;
		this.sanity = 4;
		this.stamina = 4;
		this.items.add(Item.Shotgun);
		this.skills.add(Token.Speed);
		this.trophis.add(Monster.Cultist);
	}
	
	int bonuses(Token token) {
		int r = 0;
		if(right!=null)
		{
			r += Item.get(right,token);
			if(right==Item.Axe && left==null)
				r++;
		}
		if(left!=null)
			r += Item.get(left,token);
		for(Item e : items)
			r += Item.get(e, token);
		for(Token e : skills) {
			if(e==token)
				r++;
		}
		return r;
	}
	
	public int get(Token token) {
		switch(token)
		{
		case Speed: return speed - focusSpeedStealth + bonuses(token);
		case Stealth: return stealth + focusSpeedStealth + bonuses(token);
		case Fight: return fight - focusFightWill + bonuses(token);
		case Will: return will + focusFightWill + bonuses(token);
		case Lore: return lore - focusLoreLuck + bonuses(token);
		case Luck: return luck + focusLoreLuck + bonuses(token);
		case Combat: return Math.max(get(Token.MagicCombat), get(Token.PhysicCombat));
		case MagicCombat: return get(Token.Fight) + bonuses(token);
		case PhysicCombat: return get(Token.Fight) + bonuses(token);
		case Clue: return clues;
		case Money: return money;
		case Stamina: return stamina;
		case Sanity: return sanity;
		default: return 0;
		}
	}
	
	public boolean set(Token token, int value) {
		switch(token)
		{
		case Clue:
			clues = value;
			break;
		case Money:
			money = value;
			break;
		case Stamina:
			stamina = value;
			break;
		case Sanity:
			sanity = value;
			break;
		default:
			return false;
		}
		return true;
	}
	
	int roll(Token type, int count) {
		int result = 0;
		int number = get(type) - count;
		if(number<=0)
			return 0;
		int succes = 5;
		if(get(Token.Bless)!=0)
			succes = 4;
		if(get(Token.Curse)!=0)
			succes = 6;
		for(int i=0; i<number; i++)
		{
			int r = Roll.dice(1, 6);
			if(r>=succes)
				result++;
		}
		return result;
	}
	
	private Item bestToken(Item i1, Item i2, Token token) {
		if(i1==null)
			return i2;
		int t1 = Item.get(i1,token);
		int t2 = Item.get(i2,token);
		if(t1<t2)
			return i2;
		else
			return i1;
	}
	
	// TODO: Make best algorithm selecting weapon
	void changeWeapon(Monster enemy) {
		right = null;
		left = null;
		for(Item e : items) {
			right = bestToken(right, e, Token.PhysicCombat);
		}
	}
	
	void apply(Token token, int value) {
		set(token, get(token)+value);
	}
	
	boolean makeStealth(Monster enemy) {
		int r = roll(Token.Stealth, Monster.get(enemy, Token.Stealth));
		if(r==0)
			return false;
		apply(Token.Stamina, -Monster.get(enemy, Token.Stamina));
		return true;
	}
	
	boolean makeCombat(Monster enemy) {
		// Sanity check
		if(roll(Token.Horror, Monster.get(enemy, Token.Horror))==0)
			apply(Token.Sanity, -Monster.get(enemy, Token.Sanity));
		if(get(Token.Sanity)<=0)
			return false;
		int toughness = Monster.get(enemy, Token.Toughness);
		// Stamina fight
		while(get(Token.Stamina)>0) {
			changeWeapon(enemy);
			int r = roll(Token.Combat, Monster.get(enemy, Token.Combat));
			if(r>=toughness) {
				trophis.add(enemy);
				return true;
			}
			apply(Token.Stamina, -Monster.get(enemy, Token.Stamina));
			// Try to escape
			if(get(Token.Stamina)==1) {
				if(makeStealth(enemy))
					return true;
			}
		}
		return false;
	}
		
}