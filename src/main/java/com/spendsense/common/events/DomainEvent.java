package com.spendsense.common.events;

import java.time.LocalDateTime;
import java.util.UUID;

public interface DomainEvent {

    UUID aggregateId();

    LocalDateTime occurredOn();

}