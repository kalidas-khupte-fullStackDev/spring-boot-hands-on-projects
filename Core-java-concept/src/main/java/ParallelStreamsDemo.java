import java.util.ArrayList;
import java.util.List;

public class ParallelStreamsDemo {

    public static void workOnParallelStream() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        numbers.parallelStream().map(n -> {
//        numbers.stream().map(n -> {
            System.out.println(Thread.currentThread().getName());
            return n * 2;
        }).forEach(System.out::println);

        List<Integer> list = new ArrayList<>();

//        numbers.parallelStream()
        numbers.stream()
                .forEach(list::add);   // ❌ Not safe
        System.out.println("Paraller list add"+ list);
    }

    public static void main(String[] args) {
        workOnParallelStream();
    }
}
