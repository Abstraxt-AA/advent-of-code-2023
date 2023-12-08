package abstraxtaa.aoc2023.day02;

import abstraxtaa.aoc2023.util.MiscUtil;

import java.util.logging.Logger;

public class FirstPartSolution {

    private static final Logger log = Logger.getLogger(FirstPartSolution.class.getName());

    public static void main(final String[] args) {
        final var lines = MiscUtil.readLines("day02.txt");
        MiscUtil.log(log, Solution.applyCommonLogic(lines,
                games -> games.filter(game -> game.red() < 13 && game.green() < 14 && game.blue() < 15)
                        .map(Game::index)));
    }
}
