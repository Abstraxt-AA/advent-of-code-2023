package abstraxtaa.aoc2023.day07;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public record Hand(int[] cards, HandType type, long score) implements Comparable<Hand> {

    public static final int JOKER_VALUE = -1;

    public static Hand generate(final String line) {
        return generate(line, false);
    }

    public static Hand generateJoker(final String line) {
        return generate(line, true);
    }

    private static Hand generate(final String line, final boolean joker) {
        final var handAndScore = line.split("\\s+");
        final var hand = handAndScore[0];
        final var score = Long.parseLong(handAndScore[1]);
        final var cards = convertToCards(hand, joker);
        return new Hand(cards, HandType.of(cards), score);
    }

    private static int[] convertToCards(final String hand, final boolean joker) {
        return hand.chars().map(card ->
                        switch (card) {
                            case '2', '3', '4', '5', '6', '7', '8', '9' -> card - '2';
                            case 'T' -> 8;
                            case 'J' -> joker ? JOKER_VALUE : 9;
                            case 'Q' -> 10;
                            case 'K' -> 11;
                            case 'A' -> 12;
                            default -> throw new IllegalStateException("WTF"); // What a Terrible Failure
                        })
                .toArray();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that instanceof Hand hand) {
            return score == hand.score && Arrays.equals(cards, hand.cards);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(type, score);
        result = 31 * result + Arrays.hashCode(cards);
        return result;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + Arrays.toString(cards) +
                ", type=" + type +
                ", score=" + score +
                '}';
    }

    @Override
    public int compareTo(Hand that) {
        if (this.equals(that)) {
            return 0;
        }
        if (this.type != that.type) {
            return this.type.compareTo(that.type);
        }
        return IntStream.range(0, this.cards.length)
                .filter(index -> this.cards[index] != that.cards[index])
                .map(index -> this.cards[index] > that.cards[index] ? 1 : -1)
                .findFirst()
                .orElse(0);
    }
}
