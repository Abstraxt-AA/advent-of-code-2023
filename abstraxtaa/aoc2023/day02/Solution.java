package abstraxtaa.aoc2023.day02;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Solution {

    public static void main(final String[] args) {
        FirstPartSolution.main(args);
        SecondPartSolution.main(args);
    }

    static int applyCommonLogic(final List<String> lines, final Function<Stream<Game>, Stream<Integer>> processor) {
        return processor.apply(lines.stream().map(Game::generate))
                .mapToInt(Integer::intValue)
                .sum();
    }
}
