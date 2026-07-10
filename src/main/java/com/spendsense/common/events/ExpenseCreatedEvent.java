package com.spendsense.expense.events;

import com.spendsense.common.events.DomainEvent;

import java.time.LocalDateTime;
import java.util.UUID;

public record ExpenseCreatedEvent(

        UUID expenseId,

        UUID userId,

        UUID categoryId,

        LocalDateTime occurredOn

) implements DomainEvent {

    @Override
    public UUID aggregateId() {
        return expenseId;
    }

}