package danny.work20220407;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public final class GoogleDoc {
    private int version = 0;
    private final List<String> versions = new ArrayList<>();

    public GoogleDoc() {
        versions.add("");
    }

    public int insert(int afterChar, String text) {
        String currentText = versions.get(version);
        String s;
        if (currentText.length() == 0) {
            s = text;
        } else {
            if (afterChar >= currentText.length()) {
                throw new NoSuchElementException();
            }
            s = currentText.substring(0, afterChar + 1) + text + currentText.substring(afterChar + 1);
        }
        versions.add(s);
        return ++version;
    }

    public int delete(int fromInclusive, int endExclusive) {
        String currentText = versions.get(version);
        if (currentText.length() == 0) {
            throw new IllegalStateException();
        }
        if (fromInclusive > endExclusive) {
            throw new IllegalArgumentException();
        }
        if (endExclusive > currentText.length()) {
            throw new NoSuchElementException();
        }
        String s = currentText.substring(0, fromInclusive) + currentText.substring(endExclusive);
        versions.add(s);
        return ++version;
    }

    public int latestVersion() {
        return this.version;
    }

    public String render() {
        return versions.get(version);
    }

    public String render(int version) {
        return versions.get(version);
    }
}
