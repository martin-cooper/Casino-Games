//make bust method?
import java.util.ArrayList;
public class BlackJack {
	private ArrayList<Player> players;//all players, initially set to everyone, however it is removed when dealer joins
	private ArrayList<Player> notBusted;//arraylist of players who have not busted for scoring at the end
	private Player dealer;//dealer has special rules, cannot bet, must draw up to 17
	private Deck deck;
	private boolean dealerBust = false;//boolean keeps track of whether dealer has busted
	private boolean singlePlayer;
	public BlackJack(ArrayList<Player> player) {
		deck = new Deck(false);
		players = player;
		if(players.size() > 1) {
			singlePlayer = false;
			dealer = initializeDealer();//initialize dealer turns the specified player into the dealer
			players.remove(dealer);//removes the dealer from players so their turns can be taken separately
		}
		else {
			singlePlayer = true;
			dealer = new Player(0, "dealer");
		}
		notBusted = new ArrayList<Player>();
		for(int i = 0; i < players.size(); i++) {
			notBusted.add(players.get(i));
		}
	}
	//game consists of playing start to finish, includes betting, drawing cards, resolving the bet and resetting before finally printing out everyone's money values
	public void playGame() {
		for(int i = 0; i < players.size(); i++) {
			bet(players.get(i), players.get(i).getMoney());
		}
		playRound();
		resolveBet();
		reset();
		for(int i = 0; i < players.size(); i++) {
			System.out.println(players.get(i));
		}
	}
	//round consist of hit or stand for all players and the dealer's
	private void playRound() {
		deck.shuffle();
		System.out.println("Dealing cards...");
		for(int i = 0; i < players.size(); i++) {
			players.get(i).addCardToHand(deck.dealCard());
			players.get(i).addCardToHand(deck.dealCard());
		}
		dealer.addCardToHand(deck.dealCard());
		dealer.addCardToHand(deck.dealCard());
		for(int i = 0; i < players.size(); i++) {
			System.out.println(players.get(i).getPlayerName() + "'s cards: " + players.get(i).getPlayerHand());//prints out players hand
		}
		System.out.println(dealer.getPlayerName() + "'s cards: " + dealer.getPlayerHand());//prints dealer hand
		for(int i = 0; i < players.size(); i++) {
			cardPlay(players.get(i));	
		}
		if(notBusted.size() > 0 && !singlePlayer) {
			dealerPlay();
		}
		else if(notBusted.size() > 0 && singlePlayer) {
			AIDealerPlay();
		}
		else {
			System.out.println("Everyone has busted!");
		}

	}
	//takes the input for the player's move, two different types one for a dealer's turn where he can't double down, and for normal player can double down
	private int getInput(boolean isDealer, Player player) {
		if(!isDealer) {
			System.out.println("Type h for hit, s for stand, or d for double down");
			char choice;
			do{
				choice = TextIO.getlnChar();
			}while (!isValid(choice,player));
			if(choice == 'h') {
				return 0;
			}
			else if(choice == 's'){
				return 1;
			}
			else {
				return 2;
			}
		}
		else {
			System.out.println("Type h for hit, or s for stand.");
			char choice;
			do{
				choice = TextIO.getlnChar();
			}while (choice != 'h' && choice != 's');
			if(choice == 'h') {
				return 0;
			}
			else{
				return 1;
			}
		}
	}
	private boolean isValid(char choice, Player player) {
		if(choice == 'h' || choice == 's') {
			return true;
		}
		else if(choice == 'd' && player.getBlackJackBet()*2 <= player.getMoney()) {
			return true;
		}
		else {
			return false;
		}
	}
	//does the actual card playing takes the input and does the action until bust, stand, or the double down busts
	private void cardPlay(Player player) {
		System.out.println("\n***It is " + player.getPlayerName() + "'s turn. " + player.getPlayerHand());
		while(true) {
			int hit = getInput(false, player);
			if(hit == 0) {
				System.out.println(drawCard(player));
			}
			else if(hit == 1) {
				break;
			}
			else {
				System.out.println("Doubling down.\n" + drawCard(player));
				player.setBlackJackBet(player.getBlackJackBet()*2.0);
				bust(player);
				break;
			}
			if(bust(player)) {
				break;
			}
		}
	}
	//special dealer turn, draws up to 17, can only hit or stand
	private void dealerPlay() {
		while(true) {
			while(dealer.handValue() < 17) {
				System.out.println("Card value less than 17, drawing card...");
				System.out.println(drawCard(dealer));
				try {
					Thread.sleep(2000);
				}
				catch(InterruptedException ex) 
				{
					Thread.currentThread().interrupt();
				}
			}
			if(bust(dealer)) {
				break;
			}
			int hit = getInput(true, dealer);
			if(hit == 0) {
				System.out.println(drawCard(dealer));
			}
			else {
				break;
			}
			if(bust(dealer)) {
				break;
			}
		}
	}
	//special ai dealer, only draws up to 17
	private void AIDealerPlay() {
			while(dealer.handValue() < 17) {
				System.out.println("Card value less than 17, drawing card...");
				System.out.println(drawCard(dealer));
				try {
					Thread.sleep(2000);
				}
				catch(InterruptedException ex) 
				{
					Thread.currentThread().interrupt();
				}
			}
			bust(dealer);
	}
	//sets the player bet amount to whatever they specify, has to be less than or equal to what they have
	private void bet(Player player, double currentMoney) {
		int betAmount = 0;
		System.out.println(player.getPlayerName() + ", how much would you like to bet?");
		do {
			betAmount = TextIO.getlnInt();
		}while(betAmount > currentMoney);
		player.setBlackJackBet(betAmount);
	}
	//two cases, if dealer lost everyone who stood gets their bet money, if dealer didn't lose, everyone above gets bet, everyone below loses bet, ties nothing happesn
	private void resolveBet() {
		if(dealerBust) {
			for(int i = 0; i < notBusted.size(); i++) {
				Player player = notBusted.get(i);
				player.addMoney(player.getBlackJackBet());
				System.out.println("Dealer has busted! " + notBusted.get(i).getPlayerName() + " has earned " + notBusted.get(i).getBlackJackBet() + 
						notBusted.get(i).getPlayerName() + " now has " + notBusted.get(i).getMoney());
			}
		}
		else {
			for(int i = 0; i < notBusted.size(); i++) {
				Player player = notBusted.get(i);
				if(player.handValue() < dealer.handValue()) {
					player.removeMoney(player.getBlackJackBet());
					System.out.println(player.getPlayerName() + " has lost " + player.getBlackJackBet() +  ". " + player.getPlayerName() + " now has " + 
							player.getMoney());
				}
				else if(player.handValue() > dealer.handValue()){
					player.addMoney(player.getBlackJackBet());
					System.out.println(player.getPlayerName() + " has won " + player.getBlackJackBet() + ". " + player.getPlayerName() + 
							" now has " + player.getMoney());
				}
			}
		}
	}
	//takes one of the players and turns them into the dealer, the dealer cannot bet and has special rulers
	private Player initializeDealer() {
		System.out.println("Please type the dealer's name. The name should be one of the players who is playing the game.");
		String dealerName;
		int dealerIndex;
		do {
			dealerName = TextIO.getlnString();
			dealerIndex = validName(dealerName);
		}while(validName(dealerName) == -1);
		return players.get(dealerIndex);
	}
	//if there is only one player makes a dealer

	private int validName(String name) {
		for(int i = 0; i < players.size(); i++) {
			if(name.toLowerCase().equals(players.get(i).getPlayerName().toLowerCase())) {
				return i;
			}
		}
		return -1;
	}
	//resets the game, clears all player hands, dealer hands, puts the dealer back into the player arraylist, clears the notbusted arraylist
	private void reset() {
		for(int i = 0; i < players.size(); i++) {
			players.get(i).clearHand();
		}
		dealer.clearHand();
		if(!singlePlayer) {
			players.add(dealer);
		}
		notBusted.clear();
	}
	//draws card and returns their hand
	private String drawCard(Player player) {
		player.addCardToHand(deck.dealCard());
		return (player.getPlayerName() + " " + player.getPlayerHand() +"\n");
	}
	private boolean bust (Player player) {
		if(player.handValue() > 21) {
			System.out.println(player.getPlayerName() + " has busted. You Lose.");
			if(player.equals(dealer)) {
				dealerBust = true;
			}
			else {
				player.removeMoney(player.getBlackJackBet());
				notBusted.remove(player);
			}
			return true;
		}
		else {
			return false;
		}
	}
}
