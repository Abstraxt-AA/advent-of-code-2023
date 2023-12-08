package abstraxtaa.aoc2023.day06;

import abstraxtaa.aoc2023.util.MiscUtil;

import java.util.logging.Logger;

public class FirstPartSolution {

    private static final Logger log = Logger.getLogger(FirstPartSolution.class.getName());

    public static void main(final String[] args) {
        final var lines = MiscUtil.readLines("day06.txt");
        MiscUtil.log(log, Solution.applyCommonLogic(lines));
    }
}
