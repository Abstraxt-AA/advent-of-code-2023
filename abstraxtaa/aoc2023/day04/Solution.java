package abstraxtaa.aoc2023.day04;

import abstraxtaa.aoc2023.util.MiscUtil;

import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    private static final Pattern CARD_PATTERN = Pattern.compile("Card\\s+(\\d+): ");

    public static void main(final String[] args) {
        FirstPartSolution.main(args);
        SecondPartSolution.main(args);
    }

    public static int applyCommonLogic(final List<String> lines, final ToIntFunction<Stream<Integer>> operator) {
        return operator.applyAsInt(lines.stream().map(Solution::applyCommonLogic));
    }

    static CardContext accumulator(final CardContext context, final int value) {
        final var tracker = context.tracker();
        final var sum = 1 + context.sum() + tracker.stream().mapToInt(Map.Entry::getKey).sum();
        final var numberOfCopies = tracker.stream().mapToInt(Map.Entry::getKey).sum() + 1;
        final var newTracker = Stream.concat(Stream.of(Map.entry(numberOfCopies, value + 1)), tracker.stream())
                .map(pair -> Map.entry(pair.getKey(), pair.getValue() - 1))
                .filter(pair -> pair.getValue() > 0)
                .toList();
        return new CardContext(newTracker, sum);
    }

    static CardContext combiner(final CardContext first, final CardContext second) {
        final var tracker = Stream.concat(first.tracker().stream(), second.tracker().stream()).toList();
        final var sum = first.sum() + second.sum();
        return new CardContext(tracker, sum);
    }

    private static int applyCommonLogic(final String line) {
        final var cleanedLine = CARD_PATTERN.matcher(line).replaceAll("");
        final var splitLine = cleanedLine.split("\\|");
        final var winningNumbers = MiscUtil.getNumbers(splitLine[0])
                .map(Integer::parseInt).collect(Collectors.toUnmodifiableSet());
        final var receivedNumbers = MiscUtil.getNumbers(splitLine[1])
                .map(Integer::parseInt).collect(Collectors.toSet());
        receivedNumbers.retainAll(winningNumbers);
        return receivedNumbers.size();
    }
}
