package com.pavelius.game;

class Action {

	String text;
	Token type;
	int count;
	Action next;
	Action success;

	Action(String text, Token type, int count, Action fail, Action success) {
		this.text = text;
		this.type = type;
		this.count = count;
		this.next = fail;
		this.success = success;
	}

	Action(String text, Token type, int count, Action next) {
		this(text, type, count, next, null);
	}

	// Roll or simple resource adding
	Action(String text, Token type, int count) {
		this(text, type, count, null);
	}

	// Roll or simple resource adding
	Action(Token type, int count) {
		this(null, type, count, null);
	}

	// Default Resource or roll
	Action(Token type) {
		this(type, 1);
	}
	
	public void state(String text) {
		if(text!=null)
			System.out.println(text);
	}
	
	public void execute(Investigator e) {
		switch(type)
		{
		case Clue:
		case Money:
			e.set(type, e.get(type)+count);
			state("Gain "+count+" "+type);
			break;
		case Bless:
		case Curse:
			e.set(type, count);
			state("You are " + type);			
			break;
		case Spell:
			state("Gain "+count+" "+type);
			break;
		case Arrested:
			state("You are arrested. Move to the Jail.");
			break;
		case Speed:
		case Stealth:
		case Fight:
		case Will:
		case Lore:
		case Luck:
			state(text);
			int a = e.roll(type, count);
			state("Roll "+type+ " and take "+a+" "+(a>0?"success":"failure"));
			if(a>0)
			{
				if(success!=null)
					success.execute(e);
			}
			else
			{
				if(next!=null)
					next.execute(e);
			}
			return;
		default:
			break;
		}
		state(text);
		if(next!=null)
			next.execute(e);
	}

};