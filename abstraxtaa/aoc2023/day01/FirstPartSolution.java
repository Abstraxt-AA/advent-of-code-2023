package abstraxtaa.aoc2023.day01;

import abstraxtaa.aoc2023.util.MiscUtil;

import java.util.function.UnaryOperator;
import java.util.logging.Logger;

public class FirstPartSolution {

    private static final Logger log = Logger.getLogger(FirstPartSolution.class.getName());

    public static void main(final String[] args) {

        final var lines = MiscUtil.readLines("day01.txt");
        log.info(() -> String.valueOf(Solution.applyCommonLogic(lines, UnaryOperator.identity())));
    }
}
