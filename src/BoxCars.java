
public class BoxCars extends CrapsBet {

	public BoxCars(int roll, int betValue) {
		super(roll, betValue);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double resolveBet(int point, int roll) {
		if(roll == 12) {
			return super.getBetValue()*30.0;
		}
		else {
			return super.getBetValue()*-1.0;
		}
	}
	public String toString() {
		return "boxcars bet with a value of " + super.getBetValue();
	}
}
