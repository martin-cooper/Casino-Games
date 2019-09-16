import java.util.ArrayList;

public class CrapsTester {

	public static void main(String[] args) {
		Player player = new Player(Integer.MAX_VALUE/2, "Martin");
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player);
		Craps craps = new Craps(players);
		craps.playRound();
		System.out.println(player.getMoney());
	}

}
