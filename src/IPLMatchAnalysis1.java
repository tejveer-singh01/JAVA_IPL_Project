import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class IPLMatchAnalysis1 {

    public static void main(String[] args) {
        String matchesFile = "/home/tejveer/Assingnments/JAVA/Java_IPL_project/matches.csv";  // Path to matches.csv
        HashMap<String, Integer> matchesWonByTeam = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(matchesFile))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String winner = data[10].trim(); // Winner column (index 10)

                // Check if the winner is not an empty string (some matches may not have a winner)
                if (!winner.isEmpty()) {
                    // Check if the team is already in the map
                    if (matchesWonByTeam.containsKey(winner)) {
                        // If it is, increment the count by 1
                        int currentCount = matchesWonByTeam.get(winner);
                        matchesWonByTeam.put(winner, currentCount + 1);
                    } else {
                        // If it's not, add it to the map with a count of 1
                        matchesWonByTeam.put(winner, 1);
                    }
                }
            }

            // Print the results
            for (String team : matchesWonByTeam.keySet()) {
                System.out.println("Team: " + team + ", Matches Won: " + matchesWonByTeam.get(team));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
