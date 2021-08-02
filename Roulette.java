
import java.io.*;
import java.util.*;

public class Roulette {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		File file = new File("players.txt");
		List<String> players = loadPlayers(file);
		for (String player : players) {
		List<String> bets = loadBets(player);
		List<String> winnings = calcWinnings(bets);
		DisplayWinnings(player, winnings);
			
		}
	
	}
	
	
	public static List<String> loadPlayers(File file) {
		List<String> players = new ArrayList<>();
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String line = br.readLine();
			while(line != null) {
				players.add(line);
				line = br.readLine();
			}
			br.close();
			return players;	
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<String> loadBets(String player) {
		List<String> inputs = new ArrayList<>();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print(player  + " ");
		inputs.add(sc.nextLine());
		
		System.out.print("Place another bet? ");
		String flag =  sc.nextLine();
		
		while(!(flag.equalsIgnoreCase("no"))) {
			System.out.print(player + " ");
			inputs.add(sc.nextLine());
			
			
			System.out.print("Place another bet? ");
			flag = sc.nextLine();
		}
		
		 return inputs;
		
	}
	
	public static List<String> calcWinnings(List<String> paramBets) {
		int winningNumber = 1 + (int)(Math.random() * 36);
		System.out.println("Number: " + winningNumber);
		String strBet = null;
		List<String> winnings = new ArrayList<>();
		for (String paramBet : paramBets) {
		String[] betAndAmt = paramBet.split(" ");
		try {
			int bet = Integer.parseInt(betAndAmt[0]);
			double betAmt = Double.parseDouble(betAndAmt[1]);
			if (winningNumber == bet) {
			    double winningAmt = 36 * betAmt;
				winnings.add(Double.toString(betAmt) + "\t" + "WIN\t" + Double.toString(winningAmt));
			} else {
				double winningAmt = 0.0;
				winnings.add(Double.toString(betAmt) + "\t" + "LOSE\t" + Double.toString(winningAmt));
			}
		
		} catch(Exception e) {
			strBet = betAndAmt[0];
			double betAmt = Double.parseDouble(betAndAmt[1]);
			if (strBet != null) {
				if (strBet.equalsIgnoreCase("EVEN") && (winningNumber % 2 == 0)) {
					double winningAmt = 2 * betAmt;
					winnings.add(strBet + "\t" + "WIN\t" + Double.toString(winningAmt));
				} else if (strBet.equalsIgnoreCase("EVEN") && (winningNumber % 2 != 0)) {
					double winningAmt = 0.0;
					
					winnings.add(strBet + "\t" + "LOSE\t" + Double.toString(winningAmt));
				} else if (strBet.equalsIgnoreCase("ODD") && (winningNumber % 2 != 0)) {
				    double winningAmt = 2 * betAmt;
					winnings.add(strBet + "\t" + "WIN\t" + Double.toString(winningAmt));
				} 
				else if (strBet.equalsIgnoreCase("ODD")  && (winningNumber % 2 == 0)) {
					double winningAmt = 0.0;
					winnings.add(strBet + "\t" + "LOSE\t" + Double.toString(winningAmt));
				}
			}
			
		  }
		}
		
		return winnings;
		
	}
	
	public static void DisplayWinnings(String player, List<String> winnings) {
		String format = "%-11s %5s %10s %10s\n";
		System.out.printf(format,"Player","Bet","Outcome","Winnings");
		System.out.println("-----");
	    for (String winning : winnings) {
			String formatResults = "%-13s %5s\n";
			System.out.printf(formatResults, player,winning);
			
		}
		
	}
	
	
}