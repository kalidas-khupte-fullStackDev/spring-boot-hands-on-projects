package multithreading;

public class UserSessionContextTest{

    static class UserSessionContext {
        private static final ThreadLocal<String> userContext = new ThreadLocal<>();

        public static void setUserId(String userId) { userContext.set(userId); }
        public static String getUserId() { return userContext.get(); }

        // 👑 The Golden Rule of ThreadLocal
        public static void clear() { userContext.remove(); }
    }

    public static void main(String[] args) {
        processRequest("slides K");
        System.out.println("UserSessionContext Thread local : " + UserSessionContext.userContext.get());
    }
    // Inside your request interceptor or service execution layer:
    public static void processRequest(String incomingUser) {
        try {
            UserSessionContext.setUserId(incomingUser);
            System.out.println("UserSessionContext Thread local BEFORE CLean : " + UserSessionContext.userContext.get());
            executeComplexGymBusinessLogic(); // Can call getUserId() internally without parameter passing
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        finally {
//            UserSessionContext.clear(); // 🛡️ Crucial! Cleans the locker before the thread goes back to the pool
//        }
    }

    public static void executeComplexGymBusinessLogic() {
            System.out.println("UserSessionContext.getUserId()" + UserSessionContext.getUserId());
//        try {
//        } finally {
//            UserSessionContext.clear(); // 🛡️ Crucial! Cleans the locker before the thread goes back to the pool
//        }
    }


}
