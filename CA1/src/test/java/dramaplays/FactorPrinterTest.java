package dramaplays;

import dramaplays.model.Invoice;
import dramaplays.model.Performance;
import dramaplays.model.Play;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactorPrinterTest {
    @Test
    public void testPrintWithTragedyAndComedyPerformances() {
        Invoice invoice = new Invoice("John Doe", List.of(
                new Performance("Hamlet", 55),
                new Performance("The Play That Goes Wrong", 35)
        ));

        Map<String, Play> plays = new HashMap<>();
        plays.put("Hamlet", new Play("Hamlet", "tragedy"));
        plays.put("The Play That Goes Wrong", new Play("The Play That Goes Wrong", "comedy"));

        FactorPrinter factorPrinter = new FactorPrinter();

        String result = factorPrinter.print(invoice, plays);

        String expected = """
                Factor for John Doe
                  Hamlet: $650.00 (55 seats)
                  The Play That Goes Wrong: $580.00 (35 seats)
                Amount owed is $1,230.00
                You earned 37 credits
                """;
        assertEquals(expected, result);
    }

    @Test
    public void testPrintWithEmptyPerformanceList() {
        Invoice invoice = new Invoice("John Doe", List.of());

        Map<String, Play> plays = new HashMap<>();

        FactorPrinter factorPrinter = new FactorPrinter();

        String result = factorPrinter.print(invoice, plays);

        String expected = """
                Factor for John Doe
                Amount owed is $0.00
                You earned 0 credits
                """;
        assertEquals(expected, result);
    }

    @Test()
    public void testPrintWithSmallAudience() {
        Invoice invoice = new Invoice("John Doe", List.of(
                new Performance("Hamlet", 20),
                new Performance("The Play That Goes Wrong", 10)
        ));

        Map<String, Play> plays = new HashMap<>();
        plays.put("Hamlet", new Play("Hamlet", "tragedy"));
        plays.put("The Play That Goes Wrong", new Play("The Play That Goes Wrong", "comedy"));

        FactorPrinter factorPrinter = new FactorPrinter();

        String result = factorPrinter.print(invoice, plays);

        String expected = """
                Factor for John Doe
                  Hamlet: $400.00 (20 seats)
                  The Play That Goes Wrong: $330.00 (10 seats)
                Amount owed is $730.00
                You earned 2 credits
                """;
        assertEquals(expected, result);
    }

    @Test()
    public void testPrintWithUnknownPlayType() {
        Invoice invoice = new Invoice("John Doe", List.of(
                new Performance("unknown-play", 100)
        ));

        Map<String, Play> plays = new HashMap<>();
        plays.put("unknown-play", new Play("Unknown Play", "unknown"));

        FactorPrinter factorPrinter = new FactorPrinter();

        assertThrows(Error.class, () -> factorPrinter.print(invoice, plays));
    }
}
