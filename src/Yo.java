
public class Yo extends CrapsBet {

	public Yo(int roll, int betValue) {
		super(roll, betValue);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double resolveBet(int point, int roll) {
		if(roll == 11) {
			return super.getBetValue()*15.0;
		}
		else {
			return super.getBetValue()*-1.0;
		}
	}
	public String toString() {
		return "eleven bet with a value of " + super.getBetValue();
	}
}
