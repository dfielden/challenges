package danny.work20220411;

public class Card {
    private final Rank rank;
    private final Suit suit;
    private final char suitSymbol;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
        this.suitSymbol = createSuitSymbol();
    }

    public final int getValue() {
        return this.rank.value;
    }

    public final Suit getSuit() {
        return this.suit;
    }

    public static void main(String[] args) {
        Card card = new Card(Rank.ACE, Suit.CLUB);
        System.out.println(card.getValue());
    }

    @Override
    public String toString() {
        String rank;

        switch (this.rank) {
            case ACE:
                rank = "A";
                break;
            case JACK:
                rank = "J";
                break;
            case QUEEN:
                rank = "Q";
                break;
            case KING:
                rank = "K";
                break;
            default:
                rank = String.valueOf(getValue());
                break;
        }
        return this.suitSymbol + rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if(!(o instanceof Card)) {
            return false;
        }
        Card c = (Card)o;
        return c.rank == this.rank && c.suit == this.suit;
    }

    @Override
    public int hashCode() {
        return 31 + this.rank.hashCode() + this.suit.hashCode();
    }


    enum Rank {
        ACE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(11),
        QUEEN(12),
        KING(13);

        private final int value;

        Rank(int value) {
            this.value = value;
        }
    }

    enum Suit {CLUB, DIAMOND, HEART, SPADE};

    private char createSuitSymbol() {
        char symbol = 0;

        switch (this.suit) {
            case CLUB:
                symbol = '\u2663';
                break;
            case DIAMOND:
                symbol = '\u2666';
                break;
            case HEART:
                symbol = '\u2764';
                break;
            case SPADE:
                symbol = '\u2660';
                break;
        }
        return symbol;
    }

}

