import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HighestCharFrequencyInStringExample {

    // The broken way

    public static void main(String[] args) throws InterruptedException {
        String input = "Hello Capgemini";
        System.out.println("Output: " + highestCharFrequencyCounter(input));
    }

    public static String highestCharFrequencyCounter(String input) {
        Map<String, Long> mapList = Arrays.stream(input.replaceAll(" ", "").split(""))
//                .collect(Collectors.groupingBy(s -> s, Collectors.counting())).entrySet().stream()
//                .max(Map.Entry.comparingByValue()).map(stringLongEntry -> stringLongEntry.getKey() +" " + stringLongEntry.getValue()).orElse("No charatces fount")
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
        System.out.println("Linked Hashmap: " + mapList);
        return mapList.entrySet().stream().max(Map.Entry.comparingByValue()).map(stringLongEntry -> ("Highest1stCharFrequencyInString: " + stringLongEntry.getKey() + "->" + stringLongEntry.getValue())).orElse("No character found");
    }
}