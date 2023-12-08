package abstraxtaa.aoc2023.day06;

import abstraxtaa.aoc2023.util.MiscUtil;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Solution {

    public static void main(final String[] args) {
        FirstPartSolution.main(args);
        SecondPartSolution.main(args);
    }

    static long applyCommonLogic(final List<String> lines) {
        final var times = MiscUtil.getNumbers(lines.get(0)).map(Long::parseLong).toList();
        final var distances = MiscUtil.getNumbers(lines.get(1)).map(Long::parseLong).toList();
        return IntStream.range(0, times.size())
                .mapToLong(index -> calculateWaysToWin(times.get(index), distances.get(index)))
                .reduce(1, (a, b) -> a * b);
    }

    private static long calculateWaysToWin(final long time, final long distance) {
        return LongStream.range(1, time)
                .takeWhile(i -> ((double) distance) / i > time - i)
                .max()
                .stream()
                .map(val -> time - val * 2 - 1)
                .sum();
    }
}
