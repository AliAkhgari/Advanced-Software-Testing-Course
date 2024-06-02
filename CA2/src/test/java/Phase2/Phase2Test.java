package Phase2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class Phase2Test {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        outContent.reset();
    }

    @Test
    public void testPath1() {
        // Test case for x <= 6 * 31 = 186
        Phase2.h(0, 2024);
        assertEquals("1 1\n", outContent.toString());
        outContent.reset();

        Phase2.h(31, 2024);
        assertEquals("2 1\n", outContent.toString());
        outContent.reset();

        Phase2.h(186, 2024);
        assertEquals("7 1\n", outContent.toString());
    }

    @Test
    public void testPath2() {
        // Test case for x > 6 * 31 and x - 186 <= 150
        Phase2.h(187, 2024);
        assertEquals("7 2\n", outContent.toString());
        outContent.reset();

        Phase2.h(300, 2024);
        assertEquals("10 25\n", outContent.toString());
        outContent.reset();

        Phase2.h(336, 2024);
        assertEquals("12 1\n", outContent.toString());
    }

    @Test
    public void testPath3LeapYear() {
        // Test case for x > 6 * 31 and x - 186 > 150  and x - (186 + 150) <= 30 and isLeapYear == true
        Phase2.h(337, 2024);
        assertEquals("12 1\n", outContent.toString());
        outContent.reset();

        Phase2.h(366, 2024);
        assertEquals("12 30\n", outContent.toString());
    }

    @Test
    public void testPath4NonLeapYear() {
        // Test case for x > 6 * 31 and x - 186 > 150 and isLeapYear == false
        Phase2.h(337, 2023);
        assertEquals("12 1\n", outContent.toString());
        outContent.reset();

        Phase2.h(365, 2023);
        assertEquals("12 29\n", outContent.toString());
    }

    @Test(expected = RuntimeException.class)
    public void testPath5LeapYearException() {
        Phase2.h(367, 2024);
    }

    @Test(expected = RuntimeException.class)
    public void testPath5NonLeapYearException() {
        Phase2.h(366, 2023);
    }
}
