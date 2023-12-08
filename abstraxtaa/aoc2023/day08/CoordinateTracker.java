package abstraxtaa.aoc2023.day08;

import java.util.Map;
import java.util.Objects;

public final class CoordinateTracker extends AbstractCoordinateTracker {

    private String currentCoordinate = "AAA";

    public CoordinateTracker(final String steps) {
        super(steps);
    }

    @Override
    public boolean test(final int value) {
        return !Objects.equals(this.currentCoordinate, "ZZZ");
    }

    public void moveToNextCoordinate(final int index, final String steps,
                                     final Map<String, Coordinate> coordinates) {
        final var coordinate = coordinates.get(this.currentCoordinate);
        this.incrementStepsTaken();
        this.currentCoordinate = getNextCoordinate(index, steps, coordinate);
    }
}
