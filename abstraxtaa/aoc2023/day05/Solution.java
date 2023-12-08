package abstraxtaa.aoc2023.day05;

import abstraxtaa.aoc2023.util.CustomCollectors;
import abstraxtaa.aoc2023.util.MiscUtil;
import abstraxtaa.aoc2023.util.Triple;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {

    public static void main(final String[] args) {
        FirstPartSolution.main(args);
        SecondPartSolution.main(args);
    }

    public static long applyCommonLogic(final boolean seedsAsRange) {
        final var lines = MiscUtil.readLines("day05.txt");
        final var mappings = new SolutionMap();
        final var mappingOrdering = List.of(FarmFactor.SEED,
                FarmFactor.SOIL,
                FarmFactor.FERTILIZER,
                FarmFactor.WATER,
                FarmFactor.LIGHT,
                FarmFactor.TEMPERATURE,
                FarmFactor.HUMIDITY,
                FarmFactor.LOCATION);
        final var seedDestination = seedsAsRange ? null : FarmFactor.SEED;
        final var seeds = generateMapping(FarmFactor.SEED, seedDestination, lines);
        mappings.computeIfAbsent(FarmFactor.SEED, ignored -> new EnumMap<>(FarmFactor.class))
                .put(FarmFactor.SEED, seeds);
        final var inputMappings = IntStream.range(1, mappingOrdering.size())
                .mapToObj(index -> Map.entry(mappingOrdering.get(index - 1), mappingOrdering.get(index)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (f, s) -> s,
                        () -> new EnumMap<>(FarmFactor.class)));
        inputMappings.forEach((src, dest) -> mappings.computeIfAbsent(src, ignored -> new EnumMap<>(FarmFactor.class))
                .put(dest, generateMapping(src, dest, lines)));
        inputMappings.forEach((src, dest) -> applyMapping(src, dest, mappings));
        return mappings.get(FarmFactor.SEED).get(FarmFactor.LOCATION)
                .stream()
                .mapToLong(Triple::first)
                .min()
                .orElse(0L);
    }

    private static List<Triple<Long, Long, Long>> generateMapping(final FarmFactor source,
                                                                  final FarmFactor destination,
                                                                  final List<String> lines) {
        if (source == FarmFactor.SEED && destination == null || destination == FarmFactor.SEED) {
            return generateSeeds(destination, lines);
        }
        final var filterPattern = Pattern.compile(source.name().toLowerCase() + "-to-"
                + destination.name().toLowerCase() + " map:");
        final var mappingStream = lines.stream()
                .dropWhile(line -> !filterPattern.matcher(line).find())
                .skip(1)
                .takeWhile(line -> MiscUtil.getNumberMatches(line).findAny().isPresent())
                .map(MiscUtil::getNumbers)
                .map(numbers -> numbers.map(Long::parseLong).collect(CustomCollectors.toTriple()));
        return Stream.concat(mappingStream, Stream.of(new Triple<>(0L, 0L, Long.MAX_VALUE)))
                .toList();
    }

    private static List<Triple<Long, Long, Long>> generateSeeds(final FarmFactor destination,
                                                                final List<String> lines) {
        final var stream = lines.stream()
                .filter(line -> line.startsWith("seeds: "))
                .map(line -> line.replaceFirst("seeds: ", ""))
                .flatMap(MiscUtil::getNumbers)
                .map(Long::parseLong);
        return destination == FarmFactor.SEED ?
                stream.map(seed -> new Triple<>(seed, seed, /* no range as start and end are same */ 0L)).toList() :
                stream.collect(CustomCollectors.toSeedRanges());
    }

    private static Stream<Triple<Long, Long, Long>> mapRange(final Triple<Long, Long, Long> inputRange,
                                                             final List<Triple<Long, Long, Long>> possibleMappings) {
        return possibleMappings.stream()
                .map(possibleMapping -> mapRange(inputRange, possibleMapping, possibleMappings))
                .filter(Objects::nonNull)
                .findFirst()
                .stream().flatMap(val -> val);
    }

    private static Stream<Triple<Long, Long, Long>> mapRange(final Triple<Long, Long, Long> inputRange,
                                                             final Triple<Long, Long, Long> possibleMapping,
                                                             final List<Triple<Long, Long, Long>> possibleMappings) {
        final long seedStart = inputRange.second();
        final long sourceStart = possibleMapping.second();
        final long sourceLength = possibleMapping.third();
        final long sourceEnd = sourceStart + sourceLength - 1;
        final long destinationStart = possibleMapping.first();
        final long inputLength = inputRange.third();
        final long inputStart = inputRange.first();
        final long inputEnd = inputStart + inputLength - 1;
        if (inputStart > sourceEnd || inputEnd < sourceStart) {
            return null;
        }
        if (inputStart >= sourceStart && inputEnd <= sourceEnd) {
            final long newStart = destinationStart + inputStart - sourceStart;
            return Stream.of(new Triple<>(newStart, seedStart, inputLength));
        }
        if (inputStart < sourceStart) {
            final long firstLength = sourceStart - inputStart;
            final long secondStart = seedStart + firstLength;
            final long secondLength = inputLength - firstLength;
            final var newInputRange = new Triple<>(inputStart, seedStart, firstLength);
            return Stream.concat(mapRange(newInputRange, possibleMappings),
                    Stream.of(new Triple<>(destinationStart, secondStart, secondLength)));
        }
        final long firstLength = sourceEnd - inputStart + 1;
        final long firstStart = destinationStart + inputStart - sourceStart;
        final long secondStart = inputStart + firstLength;
        final long secondLength = inputLength - firstLength;
        final var newInputRange = new Triple<>(secondStart, seedStart + firstLength, secondLength);
        return Stream.concat(Stream.of(new Triple<>(firstStart, seedStart, firstLength)),
                mapRange(newInputRange, possibleMappings));
    }

    private static void applyMapping(final FarmFactor src, final FarmFactor dest, final SolutionMap mappings) {
        final var seedMappings = mappings.get(FarmFactor.SEED);
        final var inputs = seedMappings.get(src);
        final var possibleMappings = mappings.get(src).get(dest);
        seedMappings.put(dest, inputs.stream().flatMap(inputRange -> mapRange(inputRange, possibleMappings)).toList());
    }
}
