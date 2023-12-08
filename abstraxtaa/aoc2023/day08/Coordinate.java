package abstraxtaa.aoc2023.day08;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public record Coordinate(String position, String left, String right) {

    private static final int[] LETTER_INDEX = IntStream.range(0, 26).map(index -> 1).toArray();
    private static final Map<String, Integer> COORDINATE_TO_NUMBER_MAP = new HashMap<>();
    private static final char normalizer = 'A';

    public Coordinate(final String line) {
        this(line.substring(0, 3), line.substring(7, 10), line.substring(12, 15));
    }

    public static Coordinate ghostCoordinate(final String line) {
        final var position = line.substring(0, 3);
        final var left = line.substring(7, 10);
        final var right = line.substring(12, 15);
        final var ghostPosition = updatePrefix(position);
        final var ghostLeft = updatePrefix(left);
        final var ghostRight = updatePrefix(right);
        return new Coordinate(ghostPosition, ghostLeft, ghostRight);
    }

    private static String updatePrefix(final String coordinate) {
        final var number = COORDINATE_TO_NUMBER_MAP
                .computeIfAbsent(coordinate, key -> LETTER_INDEX[coordinate.charAt(2) - normalizer]++);
        return coordinate.replaceFirst(coordinate.substring(0, 2), String.valueOf(number));
    }
}
