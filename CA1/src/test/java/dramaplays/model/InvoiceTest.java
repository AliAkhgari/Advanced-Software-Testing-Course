package dramaplays.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvoiceTest {
    @Test
    public void testInvoiceWithEmptyPerformances() {
        String customer = "John Doe";
        List<Performance> emptyPerformances = new ArrayList<>();

        Invoice invoice = new Invoice(customer, emptyPerformances);

        assertEquals(customer, invoice.customer);
        assertEquals(0, invoice.performances.size());
    }

    @Test
    public void testInvoiceWithNonEmptyPerformances() {
        String customer = "Jane Smith";
        List<Performance> performances = new ArrayList<>();
        performances.add(new Performance("1234", 120));
        performances.add(new Performance("4321", 90));

        Invoice invoice = new Invoice(customer, performances);

        assertEquals(customer, invoice.customer);
        assertEquals(2, invoice.performances.size());

        assertEquals("1234", invoice.performances.get(0).playID);
        assertEquals(120, invoice.performances.get(0).audience);

        assertEquals("4321", invoice.performances.get(1).playID);
        assertEquals(90, invoice.performances.get(1).audience);
    }
}
