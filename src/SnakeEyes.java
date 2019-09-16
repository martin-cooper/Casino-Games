
public class SnakeEyes extends CrapsBet {

	public SnakeEyes(int roll, int betValue) {
		super(roll, betValue);
	}

	@Override
	public double resolveBet(int point, int roll) {
		if(roll == 2) {
			return super.getBetValue()*30.0;
		}
		else {
			return super.getBetValue()*-1.0;
		}
	}
	public String toString() {
		return "snake eyes bet with a value of " + super.getBetValue();
	}
}
