package lab2;

import helpers.Reader;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main {
    private static final String path = "/study/algos/ignat/src/main/resources/lines.txt";
    public static void main(String[] args) {
        List<String> words = Reader.readStringFromFile(new File(path));
        Collections.sort(words);
        System.out.println(words);

        Map resultTonight = LinearSearch.linearSearch(words, "tonight");
        Map resultA = LinearSearch.linearSearch(words, "a");
        Map resultMine = LinearSearch.linearSearch(words, "mine");
        Map resultSky = LinearSearch.linearSearch(words, "sky");

        System.out.println("Time: " + resultA.get(1));
        System.out.println("Time: " + resultMine.get(1));
        System.out.println("Time: " + resultSky.get(1));
        System.out.println("Time: " + resultTonight.get(1));
    }
}
