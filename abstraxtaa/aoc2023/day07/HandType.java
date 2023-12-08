package abstraxtaa.aoc2023.day07;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public enum HandType {

    HIGH_CARD {
        @Override
        boolean matches(Map<Integer, Integer> cards) {
            return true; // if all else fails, map to this type
        }
    },
    ONE_PAIR {
        @Override
        boolean matches(final Map<Integer, Integer> cards) {
            final var joker = cards.containsKey(Hand.JOKER_VALUE) ? cards.remove(Hand.JOKER_VALUE) : 0;
            if (joker == 1) {
                return true;
            }
            final var max = cards.values().stream().mapToLong(Long::valueOf).max().orElse(0);
            return max == 2 && cards.values().stream().filter(val -> val == 2).count() == 1;
        }
    },
    TWO_PAIR {
        @Override
        boolean matches(final Map<Integer, Integer> cards) {
            final var joker = cards.containsKey(Hand.JOKER_VALUE) ? cards.remove(Hand.JOKER_VALUE) : 0;
            final var max = cards.values().stream().mapToLong(Long::valueOf).max().orElse(0);
            if (joker == 0) {
                return max == 2 && cards.values().stream().filter(val -> val + joker >= 2).count() == 2;
            }
            return max == 2 && cards.values().stream().anyMatch(val -> val < max && val + joker == max);
        }
    },
    THREE_OF_A_KIND {
        @Override
        boolean matches(final Map<Integer, Integer> cards) {
            final var joker = cards.containsKey(Hand.JOKER_VALUE) ? cards.remove(Hand.JOKER_VALUE) : 0;
            if (joker == 2) {
                return true;
            }
            final var max = cards.values().stream().mapToLong(Long::valueOf).max().orElse(0);
            return max + joker == 3;
        }
    },
    FULL_HOUSE {
        @Override
        boolean matches(final Map<Integer, Integer> cards) {
            final var joker = cards.containsKey(Hand.JOKER_VALUE) ? cards.remove(Hand.JOKER_VALUE) : 0;
            final var max = cards.values().stream().mapToLong(Long::valueOf).max().orElse(0);
            return switch (joker) {
                case 0 -> max == 3 && cards.values().stream().filter(val -> val == 2).count() == 1;
                case 1 -> max == 2 && cards.values().stream().filter(val -> val == 2).count() == 2;
                case 2 -> max == 2 && cards.values().stream().filter(val -> val == 1).count() == 1;
                default -> throw new IllegalStateException("WTF"); // What a Terrible Failure
            };
        }
    },
    FOUR_OF_A_KIND {
        @Override
        boolean matches(final Map<Integer, Integer> cards) {
            final var joker = cards.containsKey(Hand.JOKER_VALUE) ? cards.remove(Hand.JOKER_VALUE) : 0;
            if (joker == 3) {
                return true;
            }
            final var max = cards.values().stream().mapToLong(Long::valueOf).max().orElse(0);
            return max + joker == 4 && cards.values().stream().anyMatch(val -> val == max);
        }
    },
    FIVE_OF_A_KIND {
        @Override
        boolean matches(final Map<Integer, Integer> cards) {
            final var joker = cards.containsKey(Hand.JOKER_VALUE) ? cards.remove(Hand.JOKER_VALUE) : 0;
            if (joker >= 4) {
                return true;
            }
            final var max = cards.values().stream().mapToLong(Long::valueOf).max().orElse(0);
            return max + joker == 5 && cards.values().stream().filter(val -> val == max).count() == 1;
        }
    };

    abstract boolean matches(final Map<Integer, Integer> cards);

    public static HandType of(final int[] cards) {
        final var handCards = Arrays.stream(cards)
                .boxed()
                .collect(Collectors.groupingBy(val -> val, Collectors.summingInt(val -> 1)));
        return Arrays.stream(values())
                .sorted(Collections.reverseOrder()) // to capture the highest possible type first
                .filter(type -> type.matches(new HashMap<>(handCards)))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("WTF")); // What a Terrible Failure
    }
}
