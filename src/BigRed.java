
public class BigRed extends CrapsBet {

	public BigRed(int roll, int betValue) {
		super(roll, betValue);
	}

	@Override
	public double resolveBet(int point, int roll) {
		if(roll == 7) {
			return super.getBetValue()*4.0;
		}
		else {
			return super.getBetValue()*-1.0;
		}
	}
	public String toString() {
		return "big red bet with a value of " + super.getBetValue();
	}
}
