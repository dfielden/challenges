package danny.work20220411;

public class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
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
        return this.rank + ":" + this.suit;
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

}

