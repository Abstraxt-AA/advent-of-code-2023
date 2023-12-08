package abstraxtaa.aoc2023.day08;

import abstraxtaa.aoc2023.util.MiscUtil;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    public static void main(final String[] args) {
        FirstPartSolution.main(args);
        SecondPartSolution.main(args);
    }

    public static long applyCommonLogic(final Function<String, Coordinate> coordinateMapper, final boolean ghost) {
        final var lines = MiscUtil.readLines("day08.txt");
        final var steps = lines.remove(0);
        lines.remove(0);
        final var coordinates = lines.stream()
                .map(coordinateMapper)
                .collect(Collectors.toMap(Coordinate::position, coordinate -> coordinate, (f, s) -> s));
        final var tracker = ghost ? new GhostCoordinateTracker(steps, coordinates
                .keySet()
                .stream()
                .filter(coordinate -> coordinate.endsWith("A"))
                .toList()) : new CoordinateTracker(steps);
        IntStream.iterate(0, tracker, tracker)
                .forEach(index -> tracker.moveToNextCoordinate(index, steps, coordinates));
        return tracker.getStepsTaken();
    }
}
