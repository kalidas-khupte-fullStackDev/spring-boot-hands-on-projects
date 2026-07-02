package org.example.aop.service;

import org.springframework.stereotype.Service;

@Service
public class GymMemberService {

    public void checkInMember(String memberName) {
        // Pure business logic layout
        System.out.println("🏋️‍♂️ [Business Logic]: Processing physical facility check-in for " + memberName);
    }

    public String renewMembership(String memberName, int months) {
        System.out.println("💳 [Business Logic]: Extending subscription timeline for " + memberName);
        return "SUCCESS";
    }
}
