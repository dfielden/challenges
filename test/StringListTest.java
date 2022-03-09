
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import static org.junit.Assert.*;

public class StringListTest {

    @Test
    public void empty() {
        StringList list = new StringList();
        assertEquals(0, list.size());
        for (String s : listOfWords()) {
            assertFalse(list.contains(s));
        }
    }

    @Test
    public void addInOrder() throws Exception {
        ArrayList<String> words = listOfWords();
        addListOfWords(words);
    }

    @Test
    public void addInReverse() throws Exception {
        ArrayList<String> words = listOfWords();
        Collections.reverse(words);
        addListOfWords(words);
    }

    @Test
    public void addInRandomOrder() throws Exception {
        ArrayList<String> words = listOfWords();
        Collections.shuffle(words);
        addListOfWords(words);
    }

    @Test
    public void duplicateWords() throws Exception {
        ArrayList<String> words = listOfWords();
        StringList list = new StringList();
        Collections.shuffle(words);
        for (String word : words) {
            list.add(word);
        }
        int listSize = list.size();
        Collections.shuffle(words);

        for (String word : words) {
            list.add(word);
            assertEquals(listSize, list.size());
            checkStringListOrder(list.head);
        }
    }

    private void addListOfWords(ArrayList<String> words) throws Exception {
        StringList list = new StringList();

        for (int i = 0; i < words.size(); i++) {
            list.add(words.get(i));
            assertEquals(i + 1, list.size());
            checkStringListOrder(list.head);

            for (int j = 0; j < words.size(); j++) {
                if (j <= i) {
                    assertTrue(list.contains(words.get(j)));
                } else {
                    assertFalse(list.contains(words.get(j)));
                }
            }
        }
    }

    private void checkStringListOrder(StringList.StringNode head) throws Exception {
        while (head.getNext() != null) {
            assertTrue(head.getS().compareTo(head.getNext().getS()) < 0);
            head = head.getNext();
        }
    }

    private static ArrayList<String> listOfWords() {
        ArrayList<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cat");
        list.add("dog");
        list.add("elephant");
        list.add("frog");
        list.add("goat");
        list.add("hippo");
        list.add("iguana");
        list.add("joke");
        list.add("king");
        list.add("log");
        list.add("monkey");
        list.add("nose");
        list.add("orange");
        list.add("parrot");
        list.add("queen");
        list.add("robot");
        list.add("street");
        list.add("town");
        list.add("umbrella");
        list.add("very");
        list.add("worm");
        list.add("xylophone");
        list.add("zebra");
        return list;
    }
}