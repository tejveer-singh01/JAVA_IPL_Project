import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IPLMatchAnalysis2 {

    public static void main(String[] args) {
        String deliveriesFile = "/home/tejveer/Assingnments/JAVA/Java_IPL_project/deliveries.csv";  // Path to deliveries.csv
        String matchFile = "/home/tejveer/Assingnments/JAVA/Java_IPL_project/matches.csv";


        List<Integer> idListmatch = new ArrayList<>();

        // Reading matches.csv file
        try (BufferedReader br = new BufferedReader(new FileReader(matchFile))) {

            String line;

            br.readLine();

            while ((line = br.readLine()) != null){
                String[] match = line.split(",");
                int year = Integer.parseInt(match[1]);

                if(year == 2016){
                    idListmatch.add(Integer.parseInt(match[0]));
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }


        // Reading deliveries.csv file

        List<String> team = new ArrayList<>();
        List<Integer> run = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(deliveriesFile))){
            String line;
            br.readLine();

            while ((line = br.readLine()) != null){
                String[] deliveries = line.split(",");
                for(int i = 0; i < idListmatch.size(); i++){

                    if(Integer.parseInt(deliveries[0]) == idListmatch.get(i)){
                        run.add(Integer.parseInt((deliveries[16])));
                        team.add(deliveries[3]);
                    }
                }
            }

            // Printing extra runs
            HashMap<String, Integer> extraRuns = new HashMap<>();

            for(int i = 0; i < team.size(); i++){
                extraRuns.put(team.get(i), extraRuns.getOrDefault(team.get(i), 0) + run.get(i));
            }
            for(String str : extraRuns.keySet()){
                System.out.println("Team : " + str + " , Runs : " + extraRuns.get(str));
            }

            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
