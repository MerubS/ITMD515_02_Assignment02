package io.github.merubs.assignment_2.cdi;
import jakarta.enterprise.context.RequestScoped;
import java.time.Instant;
import java.util.UUID;

@RequestScoped
public class RequestInfo {
    private final String requestId = UUID.randomUUID().toString();
    private final Instant startedAt = Instant.now();

    public String getRequestId() { return requestId; }
    public Instant getStartedAt() { return startedAt; }
}
