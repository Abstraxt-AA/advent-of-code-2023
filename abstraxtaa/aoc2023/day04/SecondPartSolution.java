package abstraxtaa.aoc2023.day04;

import abstraxtaa.aoc2023.util.MiscUtil;

import java.util.logging.Logger;

public class SecondPartSolution {

    private static final Logger log = Logger.getLogger(SecondPartSolution.class.getName());

    public static void main(final String[] args) {
        final var lines = MiscUtil.readLines("day04.txt");
        MiscUtil.log(log, Solution.applyCommonLogic(
                        lines, stream -> stream
                                .reduce(new CardContext(), Solution::accumulator, Solution::combiner)
                                .sum()
                ));
    }
}
