
public abstract class CrapsBet {
	private double betValue;
	public CrapsBet(int roll, int betValue) {
		this.betValue = betValue;
	}
	public abstract double resolveBet(int point, int roll);
	public double getBetValue() {
		return betValue;
	}

}
