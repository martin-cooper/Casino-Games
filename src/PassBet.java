
public class PassBet extends CrapsBet {

	public PassBet(int roll, int betValue) {
		super(roll, betValue);
	}
	public double resolveBet(int point, int roll) {
		if((point == 0 && (roll == 7 || roll == 11)) || point == roll) {
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
		return "pass bet with a value of " + super.getBetValue();
	}
}
