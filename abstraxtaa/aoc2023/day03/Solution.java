package abstraxtaa.aoc2023.day03;

import abstraxtaa.aoc2023.util.MiscUtil;

import java.util.List;
import java.util.function.BiFunction;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {

    public static void main(final String[] args) {
        FirstPartSolution.main(args);
        SecondPartSolution.main(args);
    }

    public static int applyCommonLogic(final List<String> lines,
                                       final Pattern symbolPattern,
                                       final BiFunction<List<List<MatchResult>>, MatchResult, Integer> reducer) {
        final var symbolResults = lines.stream()
                .map(line -> symbolPattern.matcher(line).results().toList())
                .toList();
        final var numberResults = lines.stream()
                .map(MiscUtil::getNumberMatches)
                .map(Stream::toList)
                .toList();
        return IntStream.range(0, lines.size())
                .map(index -> reduceSymbols(subList(index, numberResults), symbolResults.get(index), reducer))
                .sum();
    }

    private static <E> List<List<E>> subList(final int index, final List<List<E>> full) {
        return full.subList(Math.max(0, index - 1), Math.min(full.size(), index + 2));
    }

    private static int reduceSymbols(final List<List<MatchResult>> numbers,
                                     final List<MatchResult> symbols,
                                     final BiFunction<List<List<MatchResult>>, MatchResult, Integer> reducer) {
        return symbols.stream().mapToInt(symbol -> reducer.apply(numbers, symbol)).sum();
    }
}
