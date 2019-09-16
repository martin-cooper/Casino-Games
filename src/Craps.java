import java.util.ArrayList;
//add something that prints out bets each turn as well as the point, fix the come and don't come bets
public class Craps {
	public static final int PASS = 0;//constants designate the indices of all the types of bets
	public static final int DONT_PASS = 1;
	public static final int COME = 2;
	public static final int DONT_COME = 3;
	public static final int ACE_DEUCE = 4;
	public static final int BIG_RED = 5;
	public static final int ANY_CRAPS = 6;
	public static final int SNAKE_EYES = 7;
	public static final int BOXCARS = 8;
	public static final int YO = 9;
	public static final int FIELD = 10;
	public static final int BUY = 11;
	public static final int LAY = 12;
	public static final int PLACE = 13;
	private ArrayList<Player> players;
	private int point = 0;
	private int currentRoll;
	private PairOfDice dice;
	public Craps(ArrayList<Player> players) {
		dice = new PairOfDice();
		this.players = players;
	}

	public void playRound() {
		System.out.println("How many rounds would you like to play?");	
		int rounds = TextIO.getlnInt();
		for(int i = 0; i < rounds; i++) {
			boolean gameEnded = comeOutRoll();
			if(!gameEnded) {
				while(true) {
					currentRoll = dice.roll();
					System.out.println("Would anyone like to bet?");
					boolean willBet = TextIO.getlnBoolean();
					if(willBet) {
						for(int j = 0; j < players.size(); j++) {
							bet(players.get(j), false);
						}
					}
					System.out.println("Rolling dice...\n" + "Dice rolled a total of: " + currentRoll );
					resolveBets();
					if(currentRoll == point || currentRoll == 7) {
						System.out.println("Round ending...\n\n");
						break;
					}
				}
			}
			point = 0;
		}
	}

	private void bet(Player player, boolean firstRoll) {
		int betAmount;
		int intBet;
		String betChoice;
		System.out.println(player.getPlayerName() + " how much would you like to bet?");
		do {
			betAmount = TextIO.getlnInt();
		} while(betAmount < 0 || betAmount > player.getMoney());
		if(betAmount != 0) {
			System.out.println("What kind of bet would you like to make?");
			do {
				betChoice = TextIO.getlnString();
				intBet = toInt(betChoice);
			}while(!isValid(intBet, firstRoll) || intBet == -1);
			player.setBet(currentRoll, betAmount, intBet);

		}
	}
	private boolean isValid(int bet, boolean firstRoll) {
		if(firstRoll && (bet != COME && bet != DONT_COME)) {
			return true;
		}
		else if(!firstRoll && (bet!= PASS && bet != DONT_PASS)) {
			return true;
		}
		else {
			return false;
		}
	}
	private int toInt(String str) {
		switch(str.toLowerCase()) {
		case "pass":
			return PASS;
		case "don't pass":
		case "dont pass":
			return DONT_PASS;
		case "come":
			return COME;
		case "don't come":
		case "dont come":
			return DONT_COME;
		case "ace deuce":
		case "three":
		case "3":
			return ACE_DEUCE;
		case "7":
		case "big red":
		case "seven":
			return BIG_RED;
		case "any craps":
		case "craps":
			return ANY_CRAPS;
		case "snake eyes":
		case "2":
		case "snakeeyes":
			return SNAKE_EYES;
		case "boxcars":
		case "12":
			return BOXCARS;
		case "yo":
		case "11":
			return YO;
		case "field":
			return FIELD;
		case "buy":
			return BUY;
		case "lay":
			return LAY;
		case "place":
			return PLACE;
		default:
			return -1;
		}
	}
	private boolean comeOutRoll() {
		System.out.println("Would anyone like to bet?");
		boolean willBet = TextIO.getlnBoolean();
		if(willBet) {
			for(int i = 0; i < players.size(); i++) {
				bet(players.get(i), true);
			}
		}
		currentRoll = dice.roll();
		System.out.println("Rolling dice...\n" + "Dice rolled a total of: " + currentRoll);
		if(currentRoll == 7 || currentRoll ==11) {
			System.out.println("Pass line wins!");
			resolveBets();
			System.out.println("Round ending...");
			return true;
		}
		else if(currentRoll == 2 || currentRoll == 12 || currentRoll == 3) {
			System.out.println("Don't pass line wins!");
			resolveBets();
			System.out.println("Round ending...");
			return true;
		}
		else {
			resolveBets();
			point = currentRoll;
			System.out.println("Point established, value is: " + point);
			return false;
		}
	}
	private void resolveBets() {
		for(int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			int money = player.resolveCrapsBets(point, currentRoll);
			if(money > 0 || money < 0) {
				player.addMoney(money);
				System.out.print(player.getPlayerName() + " now has " + player.getMoney() + " dollars. ");
			}
		}
	}
}
