package abstraxtaa.aoc2023.day04;

import abstraxtaa.aoc2023.util.MiscUtil;

import java.util.logging.Logger;

public class FirstPartSolution {

    private static final Logger log = Logger.getLogger(FirstPartSolution.class.getName());

    public static void main(final String[] args) {
        final var lines = MiscUtil.readLines("day04.txt");
        MiscUtil.log(log, Solution.applyCommonLogic(
                        lines, stream -> stream
                                .mapToInt(value -> value < 1 ? 0 : (int) Math.pow(2, Math.subtractExact(value, 1)))
                                .sum()
                ));
    }
}
