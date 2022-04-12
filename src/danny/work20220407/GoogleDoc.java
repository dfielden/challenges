package danny.work20220407;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public final class GoogleDoc {
    private int version;
    private String currentText = "";
    private final List<TextEdit> edits = new ArrayList<>();

    private final int checkpointNum = 15;
    private final List<String> checkpoints = new ArrayList<>();

    public GoogleDoc() {
        // Create initial (empty) Version 0.
        checkpoints.add("");
        version = 0;
    }

    public int insert(int afterChar, String text) {
        maybeCreateCheckpoint();
        InsertText insertText = new InsertText(afterChar, text);
        this.currentText = insertText.apply(currentText);
        edits.add(insertText);
        return ++version;
    }

    public int delete(int fromInclusive, int endExclusive) {
        if (this.currentText.length() == 0) {
            throw new IllegalStateException();
        }
        if (fromInclusive > endExclusive) {
            String err = String.format("fromInclusive (%d) <= endExclusive (%d)",
                    fromInclusive, endExclusive);
            throw new IllegalArgumentException(err);
        }
        if (endExclusive > this.currentText.length()) {
            throw new NoSuchElementException();
        }
        maybeCreateCheckpoint();
        DeleteText deleteText = new DeleteText(fromInclusive, endExclusive);
        this.currentText = deleteText.apply(currentText);
        edits.add(deleteText);
        return ++version;
    }

    public int latestVersion() {
        return this.version;
    }

    public String render() {
        return this.currentText;
    }

    public String render(int version) {
        int checkpoint = version / checkpointNum;
        String text = checkpoints.get(checkpoint);
        for (int i = checkpoint*checkpointNum; i < version; i++) {
            text = applyDeltaForVersion(text, i);
        }
        return text;
    }

    /** Applies the delta of version 'version' to the supplied 'currentText'. */
    private String applyDeltaForVersion(String currentText, int version) {
        TextEdit edit = edits.get(version);
        return edit.apply(currentText);
    }

    private void maybeCreateCheckpoint() {
        if (version % checkpointNum == 0 && version != 0) {
            checkpoints.add(this.currentText);
        }
    }

    private static abstract class TextEdit implements Function<String, String> {

    }

    private static final class InsertText extends TextEdit {
        private final int afterChar;
        private final String toInsert;

        public InsertText(int afterChar, String toInsert) {
            this.afterChar = afterChar;
            this.toInsert = toInsert;
        }

        @Override
        public String apply(String currentText) {
            if (currentText.equals("")) {
                return toInsert;
            }
            return currentText.substring(0, afterChar + 1) + toInsert + currentText.substring(afterChar + 1);
        }
    }

    private static final class DeleteText extends TextEdit {
        private final int fromInclusive;
        private final int endExclusive;

        public DeleteText(int fromInclusive, int endExclusive) {
            this.fromInclusive = fromInclusive;
            this.endExclusive = endExclusive;
        }
        @Override
        public String apply(String currentText) {
            return currentText.substring(0, fromInclusive) + currentText.substring(endExclusive);
        }
    }
}

