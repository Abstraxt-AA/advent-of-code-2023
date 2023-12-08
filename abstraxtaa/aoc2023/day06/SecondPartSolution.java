package abstraxtaa.aoc2023.day06;

import abstraxtaa.aoc2023.util.MiscUtil;

import java.util.logging.Logger;

public class SecondPartSolution {

    private static final Logger log = Logger.getLogger(SecondPartSolution.class.getName());

    public static void main(final String[] args) {
        final var lines = MiscUtil.readLines("day06.txt")
                .stream()
                .map(line -> line.replaceAll("\\s", ""))
                .toList();
        MiscUtil.log(log, Solution.applyCommonLogic(lines));
    }
}
