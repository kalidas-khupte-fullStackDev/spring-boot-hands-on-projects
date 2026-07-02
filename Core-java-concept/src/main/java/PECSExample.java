import org.testng.annotations.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PECSExample {

    public static void main(String[] args) throws InterruptedException {

        List<Integer> intList = List.of(12,3,4,34);
        List<Double> dobList = List.of(1.2,34.5,40.9,3.4);
        List<Number> dest = new ArrayList<>();
        copyPEcs(dest, intList);
        System.out.println(" Dest : " + dest);
        copyPEcs(dest, dobList);
        System.out.println(" Dest 2: " + dest);

        double discount = 0.15;

        List<Double> prodValueList = List.of(234.8,896.89, 865.56, 150.0);

        List<Double> prodValueAfterList = prodValueList.stream().map(p -> p - p * discount).toList();

        System.out.println("Before dis" + prodValueList);
        System.out.println("After dis" + prodValueAfterList);

//        try (MyResourceClearner myResourceClearner = new MyResourceClearner()){
//            System.out.println(myResourceClearner);
//            System.out.println(myResourceClearner.hashCode());
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        testCleanerRuns();


        Person p1= new Person();
        p1.setId("123");
        p1.setName("KD");

        Person p2= new Person();
        p2.setId("123");
        p2.setName("KD");

        System.out.println("Is equal " + p1.equals(p2));
        Object s1 = "karmadata";
        if (s1 instanceof String s && s.length() > 0) {     System.out.println(s.toUpperCase()); // s already cast
             }

    }

    public static <T extends Number> void copyPEcs(List<? super T> dest, List<? extends T> src) {
        dest.addAll(src); // addAll handles the resizing automatically
    }

    static void testCleanerRuns() {
        MyResourceClearner res = new MyResourceClearner();
        res = null; // Remove the reference

        // Force the GC to run and hope the cleaner picks it up
        System.gc();
    }
}

class Person {
    private String id;
    private String name;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person person)) return false;
        return Objects.equals(id, person.id) && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}