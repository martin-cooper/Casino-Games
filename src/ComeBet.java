
public class ComeBet extends CrapsBet {
	private int point;
	public ComeBet(int roll, int betValue) {
		super(roll, betValue);
		if(roll != 2 && roll != 3 && roll != 7 && roll != 11 && roll != 12) {
			point = roll;
		}
		else {
			point = 0;
		}
	}

	public double resolveBet(int point, int roll) {
		if(this.point == 0 && (roll == 7 || roll == 11) || this.point == roll) {
			return super.getBetValue();
		}
		else if((point == 0 && (roll == 2 || roll == 3 || roll == 12)) || (roll == 7 && point != 0)) {
			return -1.0*super.getBetValue();
		}
		else {
			return 0;
		}
	}
	public String toString() {
		return "come bet with a value of " + super.getBetValue();
	}
}
