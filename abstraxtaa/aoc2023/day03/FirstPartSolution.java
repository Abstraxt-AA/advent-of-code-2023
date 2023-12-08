package abstraxtaa.aoc2023.day03;

import abstraxtaa.aoc2023.util.MiscUtil;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class FirstPartSolution {

    private static final Logger log = Logger.getLogger(FirstPartSolution.class.getName());

    public static void main(final String[] args) {
        final var symbolPattern = Pattern.compile("[^\\d.]");
        final var lines = MiscUtil.readLines("day03.txt");
        MiscUtil.log(log, Solution.applyCommonLogic(lines, symbolPattern, FirstPartSolution::reduceSymbol));
    }

    private static int reduceSymbol(final List<List<MatchResult>> numbers, final MatchResult symbol) {
        return numbers.stream()
                .flatMap(Collection::stream)
                .filter(number -> number.start() < symbol.end() + 1 && number.end() > symbol.start() - 1)
                .map(MatchResult::group)
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
