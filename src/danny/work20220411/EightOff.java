package danny.work20220411;

import com.google.common.collect.Iterables;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class EightOff {
    private final Card[] spaces = new Card[8];
    private final Deque<Card>[] stacks = new Deque[8];

    public static void main(String[] args) {
        EightOff eightOff = new EightOff();
        eightOff.initialiseGame();
        for (Deque<Card> d : eightOff.stacks) {
            System.out.println(d);
        }
    }

    public void initialiseGame() {
        DeckOfCards initialDeck = new DeckOfCards();

        // Shuffle deck
        initialDeck.shuffle();

        // Add one card from the deck to the first four spaces.
        for (int i = 0; i < 4; i++) {
            Card topCard = initialDeck.removeTop();
            spaces[i] = topCard;
        }

        // evenly distribute the remaining cards to the eight stacks.
        Iterator<Card> it = initialDeck.iterator();
        int i = 0;
        while(it.hasNext()) {
            Card c = it.next();
            if (i == 8) {
                i = 0;
            }
            if (stacks[i] == null) {
                Deque<Card> deque = new ArrayDeque<>();
                deque.push(c);
                stacks[i] = deque;
            } else {
                stacks[i].push(c);
            }
            it.remove();
            i++;
        }
    }

    public void moveCardFromStackToSpace(int fromStackIndex, int toSpaceIndex) throws Exception {
        if (spaces[toSpaceIndex] != null) {
            throw new IllegalMoveException("You cannot move cards to non-empty spaces.");
        }
        Card c = stacks[fromStackIndex].poll();
        if (c == null) {
            throw new IllegalStateException("Cannot remove card from empty stack: " + fromStackIndex);
        }
        spaces[toSpaceIndex] = c;
    }

    public void moveCardFromSpaceToStack(int fromSpaceIndex, int toStackIndex) throws Exception {
        // Get card from space.
        Card toAdd = (spaces[fromSpaceIndex]);
        if (toAdd == null) {
            throw new IllegalStateException("Cannot remove card from empty space: " + fromSpaceIndex);
        }
        // Get card from top of stack.
        Card topOfStack = stacks[toStackIndex].peek();
        if (isStackAdditionAllowed(topOfStack, toAdd)) {
            stacks[toStackIndex].push(toAdd);
            spaces[fromSpaceIndex] = null;
        } else {
            throw new IllegalMoveException("Addition of card: " + toAdd + " to stack: " + toStackIndex + " against rules.");
        }

    }

    public void swapCardsBetweenSpaces(int index1, int index2) {
        Card c = spaces[index1];
        spaces[index1] = spaces[index2];
        spaces[index2] = c;
    }

    public boolean isWon() {
        int emptyCount = 0;
        for (Deque<Card> stack : stacks) {
            if (stack.isEmpty()) {
                emptyCount += 1;
            } else {
                if (!validEndGameStack(stack)) {
                    return false;
                }
            }
        }
        return emptyCount == 4;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stacks:\n");

		int maxHeight = 0;
		for (Deque<Card> stack : stacks) {
			if (stack.size() > maxHeight) {
				maxHeight = stack.size();
			}
		}
		for (int i = 0; i < stacks.length; i++) {
			sb.append(String.format("%4s", i));
		}
		sb.append('\n');
		for (int i = 0; i < stacks.length; i++) {
			sb.append("____");
		}
		sb.append('\n');
		for (int row=0; row<maxHeight; row++) {
			for (int i = 0; i < stacks.length; i++) {
				Deque<Card> stack = stacks[i];
				if (row >= stack.size()) {
					sb.append("    ");
				} else {
					Card card = Iterables.get(stack, stack.size() - row - 1);  // Print upside down.
					sb.append(String.format("%4s", card));
				}
			}
			sb.append("\n");
		}

        sb.append("\n");
        sb.append("Spaces:\n");
        sb.append(spaces());
        sb.append("\n");
        return sb.toString();
    }

    private boolean isStackAdditionAllowed(Card topOfStack, Card toAdd) {
        if (topOfStack == null) {
            return toAdd.getValue() == 13;
        }
        return topOfStack.getSuit() == toAdd.getSuit() && topOfStack.getValue() == toAdd.getValue() + 1;
    }

    private boolean validEndGameStack(Deque<Card> stack) {
        Card topOfStack = stack.peek();
        if (stack.size() != 13 || topOfStack.getValue() != 1) {
            return false;
        }
        Card.Suit suit = topOfStack.getSuit();
        Iterator<Card> it = stack.iterator();
        int value = 1;
        while (it.hasNext()) {
            Card c = it.next();
            if (c.getSuit() != suit || c.getValue() != value) {
                return false;
            }
            value += 1;
        }
        return true;
    }

    private String spaces() {
        StringBuilder sb = new StringBuilder();
		for (int i = 0; i < stacks.length; i++) {
			sb.append(String.format("%4s", i));
		}
		sb.append('\n');
		for (int i = 0; i < stacks.length; i++) {
			sb.append("____");
		}
		sb.append('\n');
		for (int i = 0; i < spaces.length; i++) {
			if (spaces[i] == null) {
				sb.append("    ");
			} else {
         	   sb.append(String.format("%4s", spaces[i]));
			}
        }
        return sb.toString();
    }
}
