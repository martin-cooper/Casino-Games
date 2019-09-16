
public class Lay extends CrapsBet {
	private int point;
	public Lay(int roll, int betValue) {
		super(roll, betValue);
		do {
			System.out.println("What value are you buying?");
			point = TextIO.getlnInt();
		}while(point != 4 && point != 5 && point != 6 && point != 8 && point != 9 && point != 10);
		
	}

	@Override
	public double resolveBet(int point, int roll) {
		if(roll == point) {
			return super.getBetValue()*-1.0 - .05 * super.getBetValue();
		}
		else if((point == 4 || point == 10) && roll == 7) {
			return super.getBetValue()*.5 - .05 * super.getBetValue();
		}
		else if((point == 5 || point == 9) && roll == 7) {
			return super.getBetValue()* (2.0 / 3.0) - .05 * super.getBetValue();
		}
		else if ((point == 6 || point == 8) && roll == 7){
			return super.getBetValue()* (5.0 / 6.0) - .05 * super.getBetValue();
		}
		else {
			return 0;
		}
	}
	public String toString() {
		return "lay bet on: " + point + " with a value of: " + super.getBetValue();
	}

}
