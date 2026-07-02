package design.pattern;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import org.springframework.stereotype.Component;
//import org.springframework.beans.factory.annotation.Autowired;

public class OcpFactoryDemo {

    // 1. The Common Interface
    interface Notification {
        String getType(); // 📣 Each service must declare what type it supports
        void send();
    }

    // 2. Concrete Implementation A
//    @Component
    static class EmailNotification implements Notification {
        @Override public String getType() { return "EMAIL"; }
        @Override public void send() { System.out.println("📧 Email Sent!"); }
    }

    // 3. Concrete Implementation B
//    @Component
    static class SMSNotification implements Notification {
        @Override public String getType() { return "SMS"; }
        @Override public void send() { System.out.println("💬 SMS Sent!"); }
    }

    // 4. The OCP-Compliant Factory Class
//    @Component
    static class NotificationFactory {

        // This map acts as our dynamic registry
        private static final Map<String, Notification> registry = new HashMap<>();

        // 🧠 UNDER THE HOOD: Spring automatically scans the application,
        // finds all classes implementing 'Notification', and injects them into this list.
//        @Autowired
        public NotificationFactory(List<Notification> notifications) {
            for (Notification notification : notifications) {
                // Key: "EMAIL", Value: EmailNotification instance
                registry.put(notification.getType().toUpperCase(), notification);
            }
        }

        // 🎯 O(1) DYNAMIC LOOKUP: No switch, no if-else!
        public static Notification createNotification(String platformType) {
            Notification service = registry.get(platformType.toUpperCase());
            if (service == null) {
                throw new IllegalArgumentException("Unknown channel: " + platformType);
            }
            return service;
        }
    }

    public static void main(String[] args) {
        Notification msg = NotificationFactory.createNotification("SMS");
        msg.send();
    }
}
