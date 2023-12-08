package abstraxtaa.aoc2023.day05;

import abstraxtaa.aoc2023.util.Triple;

import java.util.EnumMap;
import java.util.List;

public class SolutionMap extends EnumMap<FarmFactor, EnumMap<FarmFactor, List<Triple<Long, Long, Long>>>>{

    public SolutionMap() {
        super(FarmFactor.class);
    }
}
