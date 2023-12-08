package abstraxtaa.aoc2023.day07;

import abstraxtaa.aoc2023.util.MiscUtil;

import java.util.logging.Logger;

public class SecondPartSolution {

    private static final Logger log = Logger.getLogger(SecondPartSolution.class.getName());

    public static void main(final String[] args) {
        MiscUtil.log(log, Solution.applyCommonSolution(Hand::generateJoker));
    }
}
