package org.example.aop;

import org.example.aop.service.GymMemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = "org.example")
public class AopDemoApplication implements CommandLineRunner {

    private final GymMemberService memberService;

    // 🎯 THE FIX: Ensure this exact line signature is present inside your class brackets
    public static void main(String[] args) {
        System.out.println("🌱 Java 21 Boot Environment Initializing...");

        // Dispatches management straight to the core Spring framework runtime container
        SpringApplication.run(AopDemoApplication.class, args);
    }

    // Injecting our service bean wrapper proxy
    public AopDemoApplication(GymMemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("🎬 Triggering Member Check-in Workflow...");
        memberService.checkInMember("Kalidas");

        System.out.println("\n----------------------------------------\n");

        System.out.println("🎬 Triggering Subscription Renewal Workflow...");
        memberService.renewMembership("Alice", 12);
    }
}