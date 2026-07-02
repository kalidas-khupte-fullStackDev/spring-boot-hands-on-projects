import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParallelStreamsDemo {

    public static void workOnParallelStream() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        List<Integer> list = new ArrayList<>();
        numbers.parallelStream().map(n -> {
//        numbers.stream().map(n -> {
            System.out.println(Thread.currentThread().getName());
            return n * 1896;
        }).forEach(System.out::println);

        list =  numbers.parallelStream().map(n -> {
//        numbers.stream().map(n -> {
            System.out.println(Thread.currentThread().getName());
            return n * 1896;
        }).toList();

        System.out.println("parallelStream list mod" + list);

//        numbers.parallelStream()
        numbers.stream().map(n -> {
            System.out.println(Thread.currentThread().getName());
            return n * 1896;
        }).forEach(System.out::println);   // ❌ Not safe

         numbers.stream().map(n -> {
            System.out.println(Thread.currentThread().getName());
            return n * 1896;
        }).toList();   // ❌ Not safe
        System.out.println("nor stream list " + list);
    }

    public static void main(String[] args) {
        workOnParallelStream();
    }
}
