
public class FieldBet extends CrapsBet {

	public FieldBet(int roll, int betValue) {
		super(roll, betValue);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double resolveBet(int point, int roll) {
		if(roll == 3 || roll == 4 || roll == 9 || roll == 10 || roll == 11) {
			return super.getBetValue();
		}
		else if(roll == 2 || roll == 12) {
			return super.getBetValue()*2.0;
		}
		else {
			return super.getBetValue()*-1.0;
		}
	}
	public String toString() {
		return "field bet with a value of " + super.getBetValue();
	}
}
