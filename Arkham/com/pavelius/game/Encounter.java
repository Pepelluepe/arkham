package com.pavelius.game;

class Encounter {

	Token type; // Location.
	String text; // Main encounter content (What's happen).
	Action action; // What action we must to do.

	Encounter(Token type, String text, Action action) {
		this.type = type;
		this.text = text;
		this.action = action;
	}

	// TODO: Add more encounters
	static final Encounter[] List = {
			new Encounter(Token.AdministrationBuilding,
					"A student mistakes you for the bursar.", new Action(
							"You want to carry on deception?", Token.Will, -2,
							new Action(Token.Arrested), new Action(Token.Money,
									8))),
			new Encounter(
					Token.AdministrationBuilding,
					"Discuss the opportunity to sell a monograph with the President of the University.",
					new Action(null, Token.Lore, -1, null, new Action(
							Token.Money, 5))),
			new Encounter(
					Token.AdministrationBuilding,
					"The Dean introduces you to an anthropology professor who gives you some insight into your investigation.",
					new Action(Token.Clue)),
			new Encounter(
					Token.AdministrationBuilding,
					"You see old proffesor and studients try to decipher old manuscript.",
					new Action(
							"Do you want to help?",
							Token.Lore,
							-2,
							new Action("Ancient curse unleash on your group!",
									Token.Curse, 1),
							new Action(
									"You correctly decipher this old parchment and gain some old knowledge.",
									Token.Spell, 1))) };

	static Encounter random(Token token) {
		int result = 0;
		for (Encounter e : List) {
			if (e.type == token) {
				result++;
			}
		}
		int a = Roll.random(0, result);
		for (Encounter e : List) {
			if (e.type != token)
				continue;
			if (a == 0)
				return e;
			a--;
		}
		return null;
	}

	void execute(Investigator e) {
		action.state(text);
		action.execute(e);
	}

}