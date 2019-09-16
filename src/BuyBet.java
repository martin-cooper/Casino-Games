
public class BuyBet extends CrapsBet {
	private int point;
	public BuyBet(int roll, int betValue) {
		super(roll, betValue);
		do {
			System.out.println("What value are you buying?");
			point = TextIO.getlnInt();
		}while(point != 4 && point != 5 && point != 6 && point != 8 && point !=9 && point !=10);
	}

	@Override
	public double resolveBet(int point, int roll) {
		if(roll == 7 ) {
			return super.getBetValue()*-1.0 - .05 * super.getBetValue();
		}
		else if((roll == 4 || roll == 10) && roll == this.point) {
			return super.getBetValue()*2.0 - .05 * super.getBetValue();
		}
		else if((roll == 5 || roll == 9) && roll == this.point) {
			return super.getBetValue()*1.5 - .05 * super.getBetValue();
		}
		else if ((roll == 6 || roll == 8) && roll == this.point){
			return super.getBetValue()*1.2 - .05 * super.getBetValue();
		}
		else {
			return 0;
		}
	}
	public String toString() {
		return "buy bet on: " + point + " with a value of: " + super.getBetValue();
	}

}
