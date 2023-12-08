package abstraxtaa.aoc2023.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public final class MiscUtil {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");
    private static final Logger LOGGER = Logger.getAnonymousLogger();

    private MiscUtil() {

    }

    /**
     * Reads the lines of the specified filename
     *
     * @param filename path of the file (relative or absolute)
     * @return List of lines in file
     */
    public static List<String> readLines(final String filename) {
        try {
            return Files.readAllLines(Paths.get(filename));
        } catch (final IOException cause) {
            throw new RuntimeException(cause); //NOSONAR
        }
    }

    public static Stream<String> getNumbers(final String line) {
        return getNumberMatches(line).map(MatchResult::group);
    }

    public static Stream<MatchResult> getNumberMatches(final String line) {
        return NUMBER_PATTERN.matcher(line).results();
    }

    public static String replaceWordsWithDigits(final String line) {
        return Arrays.stream(Digits.values()).reduce(line,
                (val, replacer) -> replacer.getPattern().matcher(val).replaceAll(replacer.getDigit()),
                (val, newVal) -> newVal);
    }

    public static void log(final Logger log, final long solution) {
        log.info(() -> String.valueOf(solution));
    }

    public static void log(final long solution) {
        log(LOGGER, solution);
    }

    private enum Digits {

        ZERO("zero", "z0o"),
        ONE("one", "o1e"),
        TWO("two", "t2o"),
        THREE("three", "t3e"),
        FOUR("four", "f4r"),
        FIVE("five", "f5e"),
        SIX("six", "s6x"),
        SEVEN("seven", "s7n"),
        EIGHT("eight", "e8t"),
        NINE("nine", "n9e");

        private final Pattern pattern;
        private final String digit;

        Digits(final String pattern, final String digit) {
            this.pattern = Pattern.compile(pattern);
            this.digit = digit;
        }

        public Pattern getPattern() {
            return this.pattern;
        }

        public String getDigit() {
            return this.digit;
        }
    }
}
