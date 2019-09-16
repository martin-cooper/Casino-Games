
public class Place extends CrapsBet {
	private int point;
	private boolean isBuy;
	public Place(int roll, int betValue) {
		super(roll, betValue);
		String response;
		do {
			System.out.println("Would you like to place win or place lose?");
			response = TextIO.getlnString().toLowerCase();
		}while(!response.equals("win") && !response.equals("lose"));
		do {
			System.out.println("What value would you like to place?");
			point = TextIO.getlnInt();
		}while(point != 4 && point != 5 && point != 6 && point != 8 && point !=9 && point !=10);
		if(response.equals("win")) {
			isBuy = true;
		}
		else {
			isBuy = false;
		}
	}
	public double resolveBet(int point, int roll) {
		if(isBuy) {
			if(roll == 7) {
				return super.getBetValue()*(-1.0);
			}
			else if((roll == 4 || roll == 10) && roll == this.point) {
				return super.getBetValue()*(9.0 / 5.0);
			}
			else if((roll == 5 || roll == 9) && roll == this.point) {
				return super.getBetValue() * (7.0 / 5.0);
			}
			else if ((roll == 6 || roll == 8) && roll == this.point){
				return super.getBetValue()* (7.0 / 6.0);
			}
			else {
				return 0;
			}
		}
		else {
			if(roll == point) {
				return super.getBetValue()*-1.0;
			}
			else if((point == 4 || point == 10) && roll == 7) {
				return super.getBetValue()* (5.0 / 9.0);
			}
			else if((point == 5 || point == 9) && roll == 7) {
				return super.getBetValue()* (5.0 / 7.0);
			}
			else if ((point == 6 || point == 8) && roll == 7){
				return super.getBetValue()* (5.0 / 6.0);
			}
			else {
				return 0;
			}
		}
	}
	public String toString() {
		return "place bet with a value of: " + super.getBetValue();
	}
}
