package abstraxtaa.aoc2023.day01;

import abstraxtaa.aoc2023.util.MiscUtil;

import java.util.logging.Logger;

public class SecondPartSolution {

    private static final Logger log = Logger.getLogger(SecondPartSolution.class.getName());

    public static void main(final String[] args) {
        final var lines = MiscUtil.readLines("day01.txt");
        log.info(() -> String.valueOf(Solution.applyCommonLogic(lines,
                input -> input.map(MiscUtil::replaceWordsWithDigits))));
    }
}
