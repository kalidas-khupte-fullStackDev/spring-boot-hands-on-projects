import java.util.*;

// 1. COMPARABLE: The Student class implements its own default rule
class Student implements Comparable<Student> {
    int rollNumber;
    String name;
    double marks;

    public Student(int rollNumber, String name, double marks) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.marks = marks;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    // The Built-in Rule: Sort by Roll Number
    @Override
    public int compareTo(Student otherStudent) {
        // If this returns a negative number, 'this' goes first.
        // If positive, 'otherStudent' goes first.
        return this.rollNumber - otherStudent.rollNumber;
    }

    @Override
    public String toString() {
        return rollNumber + "-" + name;
    }
}

public class SortingExample {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(3, "Charlie", 100));
        students.add(new Student(1, "Alice", 83.56));
        students.add(new Student(2, "Bob", 78.23));

        // --- USING COMPARABLE (The Default Rule) ---
        Collections.sort(students);
        System.out.println("Default Sort (Roll Number): " + students);
        // Output: [1-Alice, 2-Bob, 3-Charlie]

        // --- USING COMPARATOR (The Outside Helper Rule) ---
        // We create a custom rule to sort by Name instead!

        students.sort(Comparator.comparing(Student::getName));
        System.out.println("Custom Sort (Name): " + students);
        students.sort(Comparator.comparing(Student::getMarks));
        System.out.println("Custom Sort (Marks): " + students);
        // Output: [1-Alice, 2-Bob, 3-Charlie] (Alphabetical)
    }
}