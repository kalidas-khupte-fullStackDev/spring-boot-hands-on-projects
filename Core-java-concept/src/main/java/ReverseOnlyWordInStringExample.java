import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReverseOnlyWordInStringExample {

    // The broken way

    public static void main(String[] args) throws InterruptedException {
         String input = "Hello World";
        System.out.println(reverseOnlyWordInString(input));
        Function<String, Integer> parser = Integer::parseInt;

        System.out.println(parser.apply("123"));
        List<String> names = List.of("Hi", "Bye");

        names.stream()
                .flatMap(w -> Arrays.stream(w.split("")))
//                .map(w -> Arrays.stream(w.split("")))
//                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    public static String reverseOnlyWordInString(String input) {
        return Arrays.stream(input.split(" ")).map(word -> new StringBuffer(word).reverse().toString()).collect(Collectors.joining(" "));
    }
}