
public class DontPass extends CrapsBet {

	public DontPass(int roll, int betValue) {
		super(roll, betValue);
	}

	@Override
	public double resolveBet(int point, int roll) {
		if((point == 0 && (roll == 7 || roll == 11)) || point == roll) {
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
		return "don't pass bet with a value of " + super.getBetValue();
	}

}
