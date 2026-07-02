import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GenericExample {

    public static void main(String[] args) {

        Box<String> strObj = new Box<>();
        strObj.setContent("slides");
        List<Box<String>> strBox = new ArrayList<>();
        List<Box<?>> box = new ArrayList<>();

        Box<Integer> integerBoxObj = new Box<>();
        integerBoxObj.setContent(123);
        List<Box<Integer>> intBox = new ArrayList<>();

        box.add(strObj);
        box.add(integerBoxObj);

        strBox.add(strObj);
        intBox.add(integerBoxObj);

        System.out.println("Str box");
        strBox.forEach(System.out::println);
        System.out.println("Generic box");
        box.forEach(boxTemp -> System.out.println(" gen :" + boxTemp));
        System.out.println("Int box");
        intBox.forEach(System.out::println);


       Object s = findMax("Kalidas", "Feroza");
       Object s2 = findMax(123, 456);
       Integer iq = findMax(234, 345);

    }

    public static  <T extends Comparable<T>> T findMax(T a, T b) {
        return (a.compareTo(b) > 0) ? a : b;
    }

}
