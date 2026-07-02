import java.util.function.*;

public class KD123Demo {

    public static void main(String[] args) {
        methoRef();
    }

    public static void methoRef() {

        Function<String, Integer> parser = Integer::parseInt;
        Function<String, String> str = String::toUpperCase;

        Integer tes = parser.apply("2344");

        String kd = "Kalidas";

        Supplier<char[]> tochar = kd::toCharArray;
        Supplier<Double> random = Math::random;


        Consumer<String> printer = System.out::println;
        for (char c : tochar.get()) {
            printer.accept(" char: " + c);
            printer.accept(" random double: " + random.get());
        }

        System.out.println(str.apply("kslidass"));

        System.out.println("tes" + tes);

        Predicate<Integer> isEven = num -> num % 2 == 0;
        System.out.println(isEven.test(4));


        Function<String, Integer> lengthFinder = String::length;
        Integer len = lengthFinder.apply("Java");
    }


}
