package danny.work20220407;

import org.junit.Test;

import static org.junit.Assert.*;

public class GoogleDocTest {
    private final String[] arr = new String[]{"danny", "is", "good"};

    @Test
    public void empty() {
        GoogleDoc googleDoc = new GoogleDoc();
        assertEquals(0, googleDoc.latestVersion());
        assertEquals("", googleDoc.render());
    }

    @Test
    public void insertStart() {
        GoogleDoc googleDoc = new GoogleDoc();

        for (int i = 0; i < arr.length; i++) {
            assertEquals(i+1, googleDoc.insert(-1, arr[i]));
            assertEquals(i+1, googleDoc.latestVersion());
        }

        // render version
        assertEquals("", googleDoc.render(0));
        assertEquals("danny", googleDoc.render(1));
        assertEquals("isdanny", googleDoc.render(2));
        assertEquals("goodisdanny", googleDoc.render(3));

        // render
        assertEquals("goodisdanny", googleDoc.render());
    }

    @Test
    public void insertEnd() {
        GoogleDoc googleDoc = new GoogleDoc();

        for (int i = 0; i < arr.length; i++) {
            assertEquals(i+1, googleDoc.insert(googleDoc.render().length() - 1, arr[i]));
            assertEquals(i+1, googleDoc.latestVersion());
        }

        // render version
        assertEquals("", googleDoc.render(0));
        assertEquals("danny", googleDoc.render(1));
        assertEquals("dannyis", googleDoc.render(2));
        assertEquals("dannyisgood", googleDoc.render(3));

        // render
        assertEquals("dannyisgood", googleDoc.render());
    }

    @Test
    public void insertMiddle() {
        GoogleDoc googleDoc = new GoogleDoc();

        // insert text and render
        assertEquals(1, googleDoc.insert(0, "------"));
        assertEquals(1, googleDoc.latestVersion());
        assertEquals("------", googleDoc.render());

        assertEquals(2, googleDoc.insert(1, "**"));
        assertEquals(2, googleDoc.latestVersion());
        assertEquals("--**----", googleDoc.render());

        assertEquals(3, googleDoc.insert(5, "**"));
        assertEquals(3, googleDoc.latestVersion());
        assertEquals("--**--**--", googleDoc.render());

        // render version
        assertEquals("", googleDoc.render(0));
        assertEquals("------", googleDoc.render(1));
        assertEquals("--**----", googleDoc.render(2));
        assertEquals("--**--**--", googleDoc.render(3));
    }

    @Test
    public void deleteStart() {
        GoogleDoc googleDoc = new GoogleDoc();
        // insert then delete text
        assertEquals(1, googleDoc.insert(0, "abcdefghij"));
        assertEquals(1, googleDoc.latestVersion());
        assertEquals("abcdefghij", googleDoc.render());

        assertEquals(2, googleDoc.delete(0, 3));
        assertEquals(2, googleDoc.latestVersion());
        assertEquals("defghij", googleDoc.render());

        assertEquals(3, googleDoc.delete(0, 5));
        assertEquals(3, googleDoc.latestVersion());
        assertEquals("ij", googleDoc.render());

        assertEquals(4, googleDoc.delete(0, 2));
        assertEquals(4, googleDoc.latestVersion());
        assertEquals("", googleDoc.render());

        // render version
        assertEquals("", googleDoc.render(0));
        assertEquals("abcdefghij", googleDoc.render(1));
        assertEquals("defghij", googleDoc.render(2));
        assertEquals("ij", googleDoc.render(3));
        assertEquals("", googleDoc.render(4));
    }

    @Test
    public void deleteEnd() {
        GoogleDoc googleDoc = new GoogleDoc();
        // insert then delete text
        assertEquals(1, googleDoc.insert(0, "abcdefghij"));
        assertEquals(1, googleDoc.latestVersion());
        assertEquals("abcdefghij", googleDoc.render());

        assertEquals(2, googleDoc.delete(7, 10));
        assertEquals(2, googleDoc.latestVersion());
        assertEquals("abcdefg", googleDoc.render());

        assertEquals(3, googleDoc.delete(2, 7));
        assertEquals(3, googleDoc.latestVersion());
        assertEquals("ab", googleDoc.render());

        assertEquals(4, googleDoc.delete(0, 2));
        assertEquals(4, googleDoc.latestVersion());
        assertEquals("", googleDoc.render());

        // render version
        assertEquals("", googleDoc.render(0));
        assertEquals("abcdefghij", googleDoc.render(1));
        assertEquals("abcdefg", googleDoc.render(2));
        assertEquals("ab", googleDoc.render(3));
        assertEquals("", googleDoc.render(4));
    }

    @Test
    public void deleteMiddle() {
        GoogleDoc googleDoc = new GoogleDoc();
        // insert then delete text
        assertEquals(1, googleDoc.insert(0, "abcdefghij"));
        assertEquals(1, googleDoc.latestVersion());
        assertEquals("abcdefghij", googleDoc.render());

        assertEquals(2, googleDoc.delete(3, 6));
        assertEquals(2, googleDoc.latestVersion());
        assertEquals("abcghij", googleDoc.render());

        assertEquals(3, googleDoc.delete(1, 6));
        assertEquals(3, googleDoc.latestVersion());
        assertEquals("aj", googleDoc.render());


        // render version
        assertEquals("", googleDoc.render(0));
        assertEquals("abcdefghij", googleDoc.render(1));
        assertEquals("abcghij", googleDoc.render(2));
        assertEquals("aj", googleDoc.render(3));
    }

}