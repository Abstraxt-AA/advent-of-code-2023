package abstraxtaa.aoc2023.day04;

import java.util.List;
import java.util.Map;

public record CardContext(List<Map.Entry<Integer, Integer>> tracker, int sum) {

    public CardContext() {
        this(List.of(), 0);
    }
}
