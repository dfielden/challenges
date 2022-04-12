package danny.work20220411;

import org.junit.Test;

import static org.junit.Assert.*;

public class EightOffTest {
    @Test
    public void initialiseGame() {
        EightOff eightOff = new EightOff();
        eightOff.initialiseGame();
        assertFalse(eightOff.isWon());
    }

}