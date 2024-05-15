package model;

import dramaplays.model.Performance;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PerformanceTest {
    @Test
    public void testPerformanceConstructor() {
        String playID = "1234567890";
        int audience = 100;

        Performance performance = new Performance(playID, audience);

        assertEquals("1234567890", performance.playID);
        assertEquals(100, performance.audience);
    }
}
