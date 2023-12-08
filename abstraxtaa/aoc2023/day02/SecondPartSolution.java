package abstraxtaa.aoc2023.day02;

import abstraxtaa.aoc2023.util.MiscUtil;

import java.util.logging.Logger;

public class SecondPartSolution {

    private static final Logger log = Logger.getLogger(SecondPartSolution.class.getName());

    public static void main(final String[] args) {
        final var lines = MiscUtil.readLines("day02.txt");
        MiscUtil.log(log, Solution.applyCommonLogic(lines,
                games -> games.map(game -> game.red() * game.green() * game.blue())));
    }
}
