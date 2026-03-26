import java.util.stream.Collectors;

class ZeroShiftingCode {
    public static void main(String[] args) {
        String input = "5040301";

        System.out.println(performLogic(input));
    }

    static String  performLogic(String input){
        input.chars().filter(value -> value != '0').mapToObj(value -> )
        return java.util.stream.Stream.concat(
                input.chars().filter(c -> c != '0').mapToObj(c -> String.valueOf((char) c)), // Stream 1: "5431"
                input.chars().filter(c -> c == '0').mapToObj(c -> String.valueOf((char) c))  // Stream 2: "000"
        ).collect(Collectors.joining());
    }
}
