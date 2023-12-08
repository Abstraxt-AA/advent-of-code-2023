package abstraxtaa.aoc2023.day08;

import java.util.Map;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;

public abstract sealed class AbstractCoordinateTracker implements IntPredicate, IntUnaryOperator
        permits CoordinateTracker, GhostCoordinateTracker {

    private final int stepsLength;
    private int stepsTaken = 0;

    protected AbstractCoordinateTracker(final String steps) {
        this.stepsLength = steps.length();
    }

    @Override
    public int applyAsInt(final int operand) {
        return operand < this.stepsLength - 1 ? operand + 1 : 0;
    }

    public void incrementStepsTaken() {
        ++this.stepsTaken;
    }

    public long getStepsTaken() {
        return this.stepsTaken;
    }

    public abstract void moveToNextCoordinate(final int index, final String steps,
                                              final Map<String, Coordinate> coordinates);

    protected String getNextCoordinate(final int index, final String steps, final Coordinate coordinate) {
        return switch (steps.charAt(index)) {
            case 'L' -> coordinate.left();
            case 'R' -> coordinate.right();
            default -> throw new IllegalStateException("WTF"); // What a Terrible Failure
        };
    }
}
