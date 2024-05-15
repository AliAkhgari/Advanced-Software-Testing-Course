package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayTest {
    @Test
    public void testPlayConstructor() {
        String name = "Hamlet";
        String type = "Tragedy";

        Play play = new Play(name, type);

        assertEquals(name, play.name);
        assertEquals(type, play.type);

    }
}
