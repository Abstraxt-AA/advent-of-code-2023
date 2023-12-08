package abstraxtaa.aoc2023.day01;

import abstraxtaa.aoc2023.util.MiscUtil;

import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    public static void main(final String[] args) {
        FirstPartSolution.main(args);
        SecondPartSolution.main(args);
    }

    static int applyCommonLogic(final List<String> lines, final UnaryOperator<Stream<String>> processor) {
        return processor.apply(lines.stream())
                .map(MiscUtil::getNumbers)
                .map(numbers -> numbers.collect(Collectors.joining()))
                .map(numberLine -> numberLine.charAt(0) + numberLine.substring(numberLine.length() - 1))
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
