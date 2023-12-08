package abstraxtaa.aoc2023.day08;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public final class GhostCoordinateTracker extends AbstractCoordinateTracker {

    private List<String> currentCoordinates;
    private final long[] positions;

    public GhostCoordinateTracker(final String steps, final List<String> initialCoordinates) {
        super(steps);
        this.currentCoordinates = initialCoordinates;
        this.positions = new long[initialCoordinates.size()];
    }

    @Override
    public boolean test(final int value) {
        IntStream.range(0, this.currentCoordinates.size())
                .filter(index -> this.currentCoordinates.get(index).endsWith("Z") && positions[index] == 0)
                .forEach(index -> positions[index] = super.getStepsTaken());

        return !Arrays.stream(this.positions)
                .mapToObj(position -> position > 0)
                .reduce(Boolean.TRUE, Boolean::logicalAnd);
    }

    @Override
    public long getStepsTaken() {
        return Arrays.stream(this.positions).mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, this::lcm)
                .longValueExact();
    }

    @Override
    public void moveToNextCoordinate(final int index, final String steps,
                                     final Map<String, Coordinate> coordinates) {
        final var nowCoordinates = this.currentCoordinates.stream().map(coordinates::get).toList();
        this.incrementStepsTaken();
        this.currentCoordinates = nowCoordinates
                .stream()
                .map(coordinate -> getNextCoordinate(index, steps, coordinate))
                .toList();
    }

    private BigInteger lcm(final BigInteger first, final BigInteger second) {
        final var gcd = first.gcd(second);
        final var product = first.multiply(second).abs();
        return product.divide(gcd);
    }
}
