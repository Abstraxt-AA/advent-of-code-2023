package abstraxtaa.aoc2023.day02;

import java.util.regex.Pattern;

public record Game(int index, int red, int green, int blue) {

    private static final Pattern INDEX_PATTERN = Pattern.compile("Game (\\d+):");
    private static final Pattern RED_PATTERN = Pattern.compile("(\\d+) red");
    private static final Pattern GREEN_PATTERN = Pattern.compile("(\\d+) green");
    private static final Pattern BLUE_PATTERN = Pattern.compile("(\\d+) blue");

    public static Game generate(final String line) {
        final int index = INDEX_PATTERN.matcher(line).results()
                .mapToInt(result -> Integer.parseInt(result.group(1)))
                .max()
                .orElse(0);
        final int red = RED_PATTERN.matcher(line).results()
                .mapToInt(result -> Integer.parseInt(result.group(1)))
                .max()
                .orElse(0);
        final int green = GREEN_PATTERN.matcher(line).results()
                .mapToInt(result -> Integer.parseInt(result.group(1)))
                .max()
                .orElse(0);
        final int blue = BLUE_PATTERN.matcher(line).results()
                .mapToInt(result -> Integer.parseInt(result.group(1)))
                .max()
                .orElse(0);
        return new Game(index, red, green, blue);
    }
}
