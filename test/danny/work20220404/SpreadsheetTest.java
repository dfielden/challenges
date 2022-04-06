package danny.work20220404;

import org.junit.Test;

import static org.junit.Assert.*;

public class SpreadsheetTest {

    @Test
    public void empty() {
        Spreadsheet spreadsheet = new Spreadsheet();
        assertEquals("", spreadsheet.getComputed(1, "A"));
        assertEquals("", spreadsheet.getComputed(2, "A"));
    }

    @Test
    public void directNumber() {
        Spreadsheet spreadsheet = new Spreadsheet();
        spreadsheet.set(1, "A", "5");
        assertEquals("5", spreadsheet.getComputed(1, "A"));
    }

    @Test
    public void equalsNumber() {
        Spreadsheet spreadsheet = new Spreadsheet();
        spreadsheet.set(1, "A", "=5");
        assertEquals("5", spreadsheet.getComputed(1, "A"));
    }

    @Test
    public void referenceDirectNumberCell() {
        Spreadsheet spreadsheet = new Spreadsheet();
        spreadsheet.set(1, "A", "5");
        spreadsheet.set(2, "A", "=A1");
        assertEquals("5", spreadsheet.getComputed(1, "A"));
    }

    @Test
    public void referenceEqualsNumberCell() {
        Spreadsheet spreadsheet = new Spreadsheet();
        spreadsheet.set(1, "A", "=5");
        spreadsheet.set(2, "A", "=A1");
        assertEquals("5", spreadsheet.getComputed(1, "A"));
    }

    @Test
    public void referenceReferenceCell() {
        Spreadsheet spreadsheet = new Spreadsheet();
        spreadsheet.set(1, "A", "=5");
        spreadsheet.set(2, "A", "=A1");
        spreadsheet.set(3, "A", "=A2");
        assertEquals("5", spreadsheet.getComputed(1, "A"));
    }

    @Test
    public void addTwoDirectNumbers() {
        Spreadsheet spreadsheet = new Spreadsheet();
        spreadsheet.set(1, "A", "5");
        spreadsheet.set(2, "A", "=A1 + 4");
        assertEquals("9.0", spreadsheet.getComputed(2, "A"));
    }

    @Test
    public void addReferenceToDirectNumber() {
        Spreadsheet spreadsheet = new Spreadsheet();
        spreadsheet.set(1, "A", "5");
        spreadsheet.set(2, "A", "=A1 + 4");
        assertEquals("9.0", spreadsheet.getComputed(2, "A"));
    }

    @Test
    public void addDirectNumberToReference() {
        Spreadsheet spreadsheet = new Spreadsheet();
        spreadsheet.set(1, "A", "5");
        spreadsheet.set(2, "A", "=4 + A1");
        assertEquals("9.0", spreadsheet.getComputed(2, "A"));
    }

    @Test
    public void addTwoReferenceCells() {
        Spreadsheet spreadsheet = new Spreadsheet();
        spreadsheet.set(1, "A", "5");
        spreadsheet.set(2, "A", "=A1");
        spreadsheet.set(3, "A", "=A2 + 2");
        spreadsheet.set(4, "A", "=A2 + A3");

        assertEquals("12.0", spreadsheet.getComputed(4, "A"));
    }

    @Test
    public void chain() {
        Spreadsheet spreadsheet = new Spreadsheet();
        spreadsheet.set(1, "A", "5");
        spreadsheet.set(2, "A", "=10 + A1");
        spreadsheet.set(3, "A", "=50");
        spreadsheet.set(4, "A", "=A3 + A2");
        spreadsheet.set(5, "A", "=A4 + A1");

        assertEquals("5", spreadsheet.getComputed(1, "A"));
        assertEquals("15.0", spreadsheet.getComputed(2, "A"));
        assertEquals("50", spreadsheet.getComputed(3, "A"));
        assertEquals("65.0", spreadsheet.getComputed(4, "A"));
        assertEquals("70.0", spreadsheet.getComputed(5, "A"));

    }
}