
public class AnyCraps extends CrapsBet {

	public AnyCraps(int roll, int betValue) {
		super(roll, betValue);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double resolveBet(int point, int roll) {
		if(roll == 2 || roll == 3 || roll == 12) {
			return super.getBetValue()*7.0;
		}
		else {
			return super.getBetValue()*-1.0;
		}
	}
	public String toString() {
		return "craps bet with a value of " + super.getBetValue();
	}
}
