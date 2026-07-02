package design.pattern;

public class FactoryPatternDemo {

    // 🛡️ Step 1: The Common Interface
    interface Notification {
        void send();
    }

    // 📦 Step 2: Concrete Implementation A
    static class EmailNotification implements Notification {
        @Override
        public void send() {
            System.out.println("📧 Dispatching an Email notification...");
        }
    }

    // 📦 Step 2: Concrete Implementation B
    static class SMSNotification implements Notification {
        @Override
        public void send() {
            System.out.println("💬 Dispatching an SMS text message...");
        }
    }

    // 🏭 Step 3: The Factory Class
    static class NotificationFactory {
        // Hides instantiation logic behind a conditional rule
        public static Notification createNotification(String platformType) {
            if (platformType == null || platformType.isEmpty()) {
                throw new IllegalArgumentException("Platform type cannot be blank");
            }

            // Evaluates condition and creates the correct concrete class
            switch (platformType.toUpperCase()) {
                case "EMAIL":
                    return new EmailNotification();
                case "SMS":
                    return new SMSNotification();
                default:
                    throw new IllegalArgumentException("Unknown channel: " + platformType);
            }
        }
    }

    public static void main(String[] args) {
        // 🏁 Conditional Instant Creation
        // The client code does not know or care about 'SMSNotification' class details
        Notification message = NotificationFactory.createNotification("SMS");
        message.send();
    }
}