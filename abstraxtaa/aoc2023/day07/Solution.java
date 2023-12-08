package abstraxtaa.aoc2023.day07;

import abstraxtaa.aoc2023.util.MiscUtil;

import java.util.function.Function;
import java.util.stream.IntStream;

public class Solution {

    public static void main(final String[] args) {
        FirstPartSolution.main(args);
        SecondPartSolution.main(args);
    }

    public static long applyCommonSolution(final Function<String, Hand> handMapper) {
        final var lines = MiscUtil.readLines("day07.txt");
        final var hands = lines.stream()
                .map(handMapper)
                .sorted()
                .toList();
        return IntStream.range(0, hands.size())
                .mapToLong(index -> hands.get(index).score() * (index + 1))
                .sum();
    }
}
