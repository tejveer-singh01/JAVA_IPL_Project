import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class IPLMatchAnalysis3 {
    public static void main(String[] args) {
        String deliveriesFile = "/home/tejveer/Assingnments/JAVA/Java_IPL_project/deliveries.csv";
        String matchFile = "/home/tejveer/Assingnments/JAVA/Java_IPL_project/matches.csv";

        List<Integer> idListmatch = new ArrayList<>();
        Map<String, Integer> totalRunsGiven = new HashMap<>();
        Map<String, Integer> totalBallsBowled = new HashMap<>();

        // Reading matches.csv file
        try (BufferedReader br = new BufferedReader(new FileReader(matchFile))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] match = line.split(",");
                int year = Integer.parseInt(match[1]);

                if (year == 2015) {
                    idListmatch.add(Integer.parseInt(match[0]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reading deliveries.csv
        try (BufferedReader br = new BufferedReader(new FileReader(deliveriesFile))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] deliveries = line.split(",");
                int matchId = Integer.parseInt(deliveries[0]);

                if (idListmatch.contains(matchId)) {
                    String bowlerName = deliveries[8];
                    int runs = Integer.parseInt(deliveries[17]);
                    totalRunsGiven.put(bowlerName, totalRunsGiven.getOrDefault(bowlerName, 0) + runs);
                    totalBallsBowled.put(bowlerName, totalBallsBowled.getOrDefault(bowlerName, 0) + 1);
                }
            }

            List<BowlerAverage> bowlerAverages = new ArrayList<>();

            for (String bowler : totalRunsGiven.keySet()) {
                int runsGiven = totalRunsGiven.get(bowler);
                int ballsBowled = totalBallsBowled.get(bowler);

                double economy = (double) runsGiven / (ballsBowled / 6.0); // Calculate economy rate
                bowlerAverages.add(new BowlerAverage(bowler, economy));
            }



            // Print the top 10 economical bowlers
            System.out.println("Top 10 Economical Bowlers in 2015:");
            for (int i = 0; i < Math.min(10, bowlerAverages.size()); i++) {
                BowlerAverage bowlerAverage = bowlerAverages.get(i);
                System.out.println("Player: " + bowlerAverage.getBowlerName() + ", Economy: " + String.format("%.2f",bowlerAverage.getEconomy()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class BowlerAverage {
    private String bowlerName;
    private double economy;

    public BowlerAverage(String bowlerName, double economy) {
        this.bowlerName = bowlerName;
        this.economy = economy;
    }

    public String getBowlerName() {
        return bowlerName;
    }

    public double getEconomy() {
        return economy;
    }
}
