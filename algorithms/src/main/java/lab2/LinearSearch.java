package lab2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinearSearch {
    public static Map<Integer, Long> linearSearch(List<String> words, String line){
        Map<Integer, Long> result = new HashMap<>();
        long start = System.nanoTime();
        for (int i = 0; i< words.size(); i++){
            String iter = words.get(i);
            if (line.equals(iter)){
                long end = System.nanoTime();
                result.put(1, end - start);
                return result;
            }
        }
        long end = System.nanoTime();
        result.put(-1, end-start);
        return result;
    }
}
