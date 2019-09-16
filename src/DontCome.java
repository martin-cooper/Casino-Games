
public class DontCome extends CrapsBet {
	private int point;
	public DontCome(int roll, int betValue) {
		super(roll, betValue);
		if(roll != 2 && roll != 3 && roll != 7 && roll != 11 && roll != 12) {
			point = roll;
		}
		else {
			point = 0;
		}
	}

	@Override
	public double resolveBet(int point, int roll) {
		if(this.point == 0 && (roll == 7 || roll == 11) || this.point == roll) {
			return -1.0*super.getBetValue();
		}
		else if((point == 0 && (roll == 2 || roll == 3 || roll == 12)) || (roll == 7 && point != 0)) {
			return super.getBetValue();
		}
		else {
			return 0;
		}
	}
	public String toString() {
		return "don't come bet with a value of " + super.getBetValue();
	}
}
