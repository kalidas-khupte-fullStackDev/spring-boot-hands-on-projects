package core;

public class PassByValueDemo {

    static class GymMember {
        String name;
        GymMember(String name) { this.name = name; }
    }

    public static void main(String[] args) {
        GymMember member1 = new GymMember("Alice"); // Say this lives at address: 0x111
        GymMember member2 = new GymMember("Bob");   // Say this lives at address: 0x222

        // 🔄 Call the swap method
        swap(member1, member2);

        // 🎯 Verification: If it was pass-by-reference, member1 would now be Bob.
        System.out.println("member1 is still: " + member1.name); // Prints "Alice"
        System.out.println("member2 is still: " + member2.name); // Prints "Bob"
    }

    public static void swap(GymMember m1, GymMember m2) {
        // Under the hood at method entry:
        // m1 is a COPY of the pointer, holding address 0x111
        // m2 is a COPY of the pointer, holding address 0x222

        GymMember temp = m1; // temp = 0x111
        m1 = m2;             // m1 now points to 0x222
        m2 = temp;           // m2 now points to 0x111

        // ❌ The local variables m1 and m2 inside this method were successfully swapped,
        // but the original member1 and member2 in the main method are still pointing
        // to their original addresses!
    }
}
