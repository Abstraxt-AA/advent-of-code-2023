package abstraxtaa.aoc2023.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

public class CustomCollectors {

    @SuppressWarnings("ClassEscapesDefinedScope")
    public static <T> TripleCollector<T> toTriple() {
        return new TripleCollector<>();
    }

    @SuppressWarnings("ClassEscapesDefinedScope")
    public static <T> SeedRangeListCollector<T> toSeedRanges() {
        return new SeedRangeListCollector<>();
    }

    private record TripleCollector<T>() implements Collector<T, List<T>, Triple<T, T, T>> {

        @Override
        public Supplier<List<T>> supplier() {
            return ArrayList::new;
        }

        @Override
        public BiConsumer<List<T>, T> accumulator() {
            return List::add;
        }

        @Override
        public BinaryOperator<List<T>> combiner() {
            return (first, second) -> {
                first.addAll(second);
                return first;
            };
        }

        @Override
        public Function<List<T>, Triple<T, T, T>> finisher() {
            return accumulator -> new Triple<>(accumulator.get(0), accumulator.get(1), accumulator.get(2));
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Set.of();
        }
    }

    private record SeedRangeListCollector<T>() implements Collector<T, List<T>, List<Triple<T, T, T>>> {

        @Override
        public Supplier<List<T>> supplier() {
            return ArrayList::new;
        }

        @Override
        public BiConsumer<List<T>, T> accumulator() {
            return List::add;
        }

        @Override
        public BinaryOperator<List<T>> combiner() {
            return (first, second) -> {
                first.addAll(second);
                return first;
            };
        }

        @Override
        public Function<List<T>, List<Triple<T, T, T>>> finisher() {
            return acc -> IntStream.iterate(0, index -> index < acc.size(), index -> index + 2)
                    .mapToObj(index -> new Triple<>(acc.get(index), acc.get(index), acc.get(index + 1)))
                    .toList();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Set.of();
        }
    }
}
