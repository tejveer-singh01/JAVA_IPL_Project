import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class IPLMatchAnalysis {

    public static void main(String[] args) {
        String matchesFile = "/home/tejveer/Assingnments/JAVA/Java_IPL_project/matches.csv";  // Path to matches.csv
        HashMap<String, Integer> matchesPerYear = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(matchesFile))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String season = data[1].trim(); // Assuming season is in the second column (index 1)

                // Check if the season is already in the map
                if (matchesPerYear.containsKey(season)) {
                    // If it is, increment the count by 1
                    int currentCount = matchesPerYear.get(season);
                    matchesPerYear.put(season, currentCount + 1);
                } else {
                    // If it's not, add it to the map with a count of 1
                    matchesPerYear.put(season, 1);
                }
            }

            // Print the results
            for (String year : matchesPerYear.keySet()) {
                System.out.println("Year: " + year + ", Matches Played: " + matchesPerYear.get(year));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
