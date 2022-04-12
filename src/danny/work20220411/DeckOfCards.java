package danny.work20220411;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DeckOfCards {
    private final List<Card> cards;

    public static void main(String[] args) {
        DeckOfCards deckOfCards = new DeckOfCards();
        deckOfCards.shuffle();
        System.out.println(deckOfCards);
    }

    public DeckOfCards() {
        this.cards = new ArrayList<>();
        for (Card.Rank rank : Card.Rank.values()) {
            for (Card.Suit suit : Card.Suit.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public Card removeTop() {
        Card topCard = cards.get(0);
        cards.remove(cards.get(0));
        return topCard;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int size() {
        return cards.size();
    }

    public Card getCard(int i) {
        return cards.get(i);
    }

    public boolean remove(Card c) {
        return cards.remove(c);
    }

    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    @Override
    public String toString() {
        return this.cards.toString();
    }


}
