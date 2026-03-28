import record.Tenant;

import java.util.List;
import java.util.stream.Collectors;

class ZeroShiftingCode {
    public static void main(String[] args) {
//        String input = "5040301";
//        System.out.println(performLogic(input));
        Tenant tenant = new Tenant(1, "Disney", true);
        Tenant tenant2 = new Tenant(2, "ESPN Sports", false);
        Tenant tenant3 = new Tenant(3, " FIFA", true);
        List<Tenant> tenantList = List.of(tenant,tenant2,tenant3);

        System.out.println(filterIsInHouseTenant(tenantList));
    }

    static String  performLogic(String input){
        return java.util.stream.Stream.concat(
                input.chars().filter(c -> c != '0').mapToObj(c -> String.valueOf((char) c)), // Stream 1: "5431"
                input.chars().filter(c -> c == '0').mapToObj(c -> String.valueOf((char) c))  // Stream 2: "000"
        ).collect(Collectors.joining());
    }

    static List<Tenant> filterIsInHouseTenant(List<Tenant> tenantList){
        return tenantList.stream().filter(Tenant::isInHouse).collect(Collectors.toList());
    }
}
