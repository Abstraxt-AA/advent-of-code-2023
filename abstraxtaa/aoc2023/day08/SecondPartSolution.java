package abstraxtaa.aoc2023.day08;

import abstraxtaa.aoc2023.util.MiscUtil;

import java.util.logging.Logger;

public class SecondPartSolution {

    private static final Logger log = Logger.getLogger(SecondPartSolution.class.getName());

    public static void main(final String[] args) {
        MiscUtil.log(log, Solution.applyCommonLogic(Coordinate::ghostCoordinate, true));
    }
}
