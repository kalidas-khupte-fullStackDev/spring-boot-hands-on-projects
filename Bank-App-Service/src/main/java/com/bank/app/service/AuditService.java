package com.bank.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BankService {

    private final AuditService auditService;

    // Outer Transaction starts here
    @Transactional
    public void transferMoney(int accountId, int amount) {

        // 1. We log the attempt FIRST in its own independent transaction
        auditService.logAttempt(accountId, amount);

        // 2. Now we try the transfer.
        // If this throws an error and rolls back, the audit log STAYS saved!
        if (amount > getBalance(accountId)) {
            throw new RuntimeException("Insufficient funds!");
        }

        deductMoney(accountId, amount);
    }
}

@Service
public class AuditService {

    // The Magic happens here! This runs in a completely separate bubble.
    @Transactional(propagation = Propagation.)
    public void logAttempt(int accountId, int amount) {
        database.saveLog("User " + accountId + " tried to transfer " + amount);
    }
}