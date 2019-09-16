import java.util.ArrayList;

public class Player{
	private double money;
	private Hand playerHand;
	private String playerName;
	private BlackJackBet blackJackBet;
	private ArrayList<CrapsBet> comeOutBets; //come out bets only can be made before come out roll
	private ArrayList<CrapsBet> comeBets; //come bets made only after the come out roll
	private ArrayList<CrapsBet> singleRoundBets;//other bets can be made either time
	private ArrayList<CrapsBet> otherMultiRounds;
	private ArrayList<CrapsBet>[] allCrapsBets;
	@SuppressWarnings("unchecked")
	public Player(double money, String playerName) {
		this.money = money;
		this.playerName = playerName;
		blackJackBet = new BlackJackBet();
		playerHand = new Hand();
		comeOutBets = new ArrayList<CrapsBet>();
		comeBets = new ArrayList<CrapsBet>();
		singleRoundBets = new ArrayList<CrapsBet>();
		otherMultiRounds = new ArrayList<CrapsBet>();
		allCrapsBets = (ArrayList<CrapsBet>[]) new ArrayList[] {comeOutBets, comeBets, singleRoundBets, otherMultiRounds};//array of all the different types of bets so it is easy to
		//resolve them in a short amount of code
	}
	
	public void addMoney(double money) {
		this.money += money;
	}
	public void clearHand() {
		playerHand.clear();
	}
	public void removeMoney(double money) {
		this.money -= money;
	}
	public String toString() {
		return playerName + " has " + money + " dollars.";
	}
	public String getPlayerName() {
		return playerName;
	}
	public void addCardToHand(Card card) {
		playerHand.addCard(card);
	}
	public int handValue() {
		return playerHand.getTotalValue();
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public Hand getPlayerHand() {
		return playerHand;
	}
	public void setPlayerHand(Hand playerHand) {
		this.playerHand = playerHand;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public void setBlackJackBet(double betAmount) {
		blackJackBet.setBetAmount(betAmount);;
	}
	public double getBlackJackBet() {
		return blackJackBet.getBetAmount();
	}
	public void setBet(int roll, int betValue, int index) {
		if(index == Craps.PASS) {
			comeOutBets.add(new PassBet(roll, betValue));
		}
		else if(index == Craps.DONT_PASS){
			comeOutBets.add(new DontPass(roll, betValue));
		}
		else if(index == Craps.COME) {
			comeBets.add(new ComeBet(roll, betValue));
		}
		else if(index == Craps.DONT_COME) {
			comeBets.add(new DontCome(roll, betValue));
		}
		else if(index == Craps.ACE_DEUCE) {
			singleRoundBets.add(new AceDeuce(roll, betValue));
		}
		else if(index == Craps.BIG_RED) {
			singleRoundBets.add(new BigRed(roll, betValue));
		}
		else if(index == Craps.ANY_CRAPS) {
			singleRoundBets.add(new AnyCraps(roll, betValue));
		}
		else if(index == Craps.SNAKE_EYES) {
			singleRoundBets.add(new SnakeEyes(roll, betValue));
		}
		else if(index == Craps.BOXCARS) {
			singleRoundBets.add(new BoxCars(roll, betValue));
		}
		else if(index == Craps.YO) {
			singleRoundBets.add(new Yo(roll, betValue));
		}
		else if(index == Craps.FIELD) {
			singleRoundBets.add(new FieldBet(roll, betValue));
		}
		else if(index == Craps.BUY) {
			otherMultiRounds.add(new BuyBet(roll, betValue));
		}
		else if(index == Craps.LAY) {
			otherMultiRounds.add(new Lay(roll, betValue));
		}
		else if(index == Craps.PLACE) {
			otherMultiRounds.add(new Place(roll, betValue));
		}
	}
	public int resolveCrapsBets(int point, int roll) {
		int netMoney = 0;
		for(int i = 0; i < allCrapsBets.length; i++) {
			for(int k = 0; k < allCrapsBets[i].size(); k++) {
				double moneyEarned = allCrapsBets[i].get(k).resolveBet(point, roll);
				netMoney += moneyEarned;
				if(moneyEarned > 0) {
					System.out.println(playerName + " has won " + allCrapsBets[i].get(k));
				}
				else if(moneyEarned < 0) {
					System.out.println(playerName + " has lost " + allCrapsBets[i].get(k));
				}
				if(moneyEarned > 0 || moneyEarned < 0 || i == 2) {
					allCrapsBets[i].remove(k);
					k--;
				}
			}
		}

		return netMoney;
	}
}
