import java.util.ArrayList;

public class Casino {

	public static void main(String[] args) {
		ArrayList<Player> players = initializePlayers();
		playGames(players);
	}
	private static int chooseGameToPlay() {
		System.out.println("Type 0 if you want to play blackjack, 1 if you want to play craps, or 2 if you want to exit. ");
		int gameChoice;
		do {
			gameChoice = TextIO.getlnInt();
		} while(gameChoice != 1 && gameChoice != 0 && gameChoice != 2);
		return gameChoice;
	}
	private static ArrayList<Player> initializePlayers() {
		int numPlayers;
		System.out.println("How many players would you like to have?");
		do {
			numPlayers = TextIO.getlnInt();
		} while(numPlayers < 1 || numPlayers > 6);
		ArrayList<Player> players = new ArrayList<Player>();
		for(int i = 0; i < numPlayers; i++) {
			System.out.println("Please name player " + (i+1));
			String playerName = TextIO.getlnString();
			System.out.println("How much money would you like to start with?" );
			double startingMoney;
			do {
				startingMoney = TextIO.getlnDouble();
			} while(startingMoney <= 0);
			players.add(new Player(startingMoney, playerName));
		}
		return players;
	}
	private static void playGames(ArrayList<Player> players) {
		while(true) {
			int gameChoice = chooseGameToPlay();
			if(gameChoice == 0) {
				BlackJack blackJackGame = new BlackJack(players);
				blackJackGame.playGame();
				System.out.println(checkBalance(players));
				if(players.size() < 1) {
					System.out.println("Not enough players have money. Ending game.");
					break;
				}
			}
			else if (gameChoice == 1){
				Craps craps = new Craps(players);
				craps.playRound();
			}
			else {
				break;
			}
		}
	}
	private static String checkBalance(ArrayList<Player> players) {
		String returnString = "";
		for(int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			if(player.getMoney() == 0) {
				returnString += player.getPlayerName() + " has no money, removing player...";
				players.remove(player);
			}
		}
		return returnString;
	}
}
