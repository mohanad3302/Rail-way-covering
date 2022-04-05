import java.util.*;
import java.io.*;
public class SetCovering {
    public static void main ( String [] args){

        long start = System.currentTimeMillis();

        Set<String> LinesNeeded = new HashSet<>();
        HashMap<String,Set<String>> Trains = new HashMap<>();
        File data = new File("data5.txt");
        LinesNeeded = TotalLinesSet(data);
        Trains = hashMap(data);
        
        Set<String> finalTrains = new HashSet<>();

        while (LinesNeeded.size() != 0){
            String bestTrain = null ;
            Set<String> statesCovered = new HashSet<>();

            for (Map.Entry<String,Set<String>> x : Trains.entrySet()){
                Set<String> Covered = new HashSet<>(LinesNeeded) ;
                Covered.retainAll(x.getValue());
                if (Covered.size() > statesCovered.size() ){
                    bestTrain = x.getKey() ;
                    statesCovered = Covered ;
                }
            }
            LinesNeeded.removeAll(statesCovered);
            finalTrains.add(bestTrain);
        }

        System.out.println(finalTrains);
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        System.out.println("Time = " + elapsedTime);
    }


    public static Set<String> TotalLinesSet (File file) {
        Set<String> set = new HashSet<>();
        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()){
                set.add(input.next());
            }    
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }

        return set ;
    }
    public static HashMap<String , Set<String>> hashMap (File file) {
        HashMap<String,Set<String>> hashMap = new HashMap<>() ;
        try {
            FileReader reader = new FileReader(file);
            BufferedReader input = new BufferedReader(reader) ;
            
            try{
               String data = input.readLine();
               int i = 1 ;
               while (data != null ){
                String key = "Train" + i;
                  String[] s = data.split(" ");
                  Set<String> value = new HashSet<>();
                  for (String x : s ){
                    value.add(x);
                  }
                  hashMap.put(key, value);
                  data = input.readLine() ;
                  i++ ;
               }
               input.close();
            }
            catch(IOException o){
               System.out.println("file is empty");
            }
         }catch (FileNotFoundException e ){
            System.out.println("file not found");
         }

        return hashMap ;
    }
}