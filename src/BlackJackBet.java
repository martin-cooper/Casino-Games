
public class BlackJackBet {
	private double betAmount;
	public BlackJackBet() {
		// TODO Auto-generated constructor stub
	}
	public void setBetAmount(double amount) {
		betAmount = amount;
	}
	public void addToBet(double amount) {
		betAmount += amount;
	}
	public void subtractFromBet(double amount) {
		betAmount -= amount;
	}
	public double getBetAmount() {
		return betAmount;
	}

}
