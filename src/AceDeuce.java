
public class AceDeuce extends CrapsBet {

	public AceDeuce(int roll, int betValue) {
		super(roll, betValue);
	}

	@Override
	public double resolveBet(int point, int roll) {
		if(roll == 3) {
			return super.getBetValue()*15.0;
		}
		else {
			return super.getBetValue()*-1.0;
		}
	}
	public String toString() {
		return "ace deuce bet with a value of " + super.getBetValue();
	}
}
