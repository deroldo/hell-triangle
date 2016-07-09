package br.com.deroldo.helltriangle.calculator.impl;

import br.com.deroldo.helltriangle.calculator.TriangleCalculator;
import br.com.deroldo.helltriangle.exception.InvalidTriangleException;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class TriangleCalculatorImpl implements TriangleCalculator {

    public int calculate(final int[][] array) {
        return Stream.of(validate(array))
                .sorted(baseOnTopSort())
                .reduce(higherValueReduce())
                .get()[0];
    }

    private int[][] validate(final int[][] array){
        if (array == null || array.length == 0){
            throw new InvalidTriangleException("The triangle must have at least one value");
        }
        for (int index = 0; index < array.length; index++) {
            if (array[index].length != index + 1){
                throw new InvalidTriangleException(String.format("Row index %d must have %d values", index, index + 1));
            }
        }
        return array.clone();
    }

    private Comparator<Object> baseOnTopSort() {
        return Comparator.comparing(Array::getLength).reversed();
    }

    private BinaryOperator<int[]> higherValueReduce() {
        return (first, second) -> {
            for (int index = 0; index < second.length; index++) {
                second[index] += getHigherPredecessor(first, index);
            }
            return second;
        };
    }

    private int getHigherPredecessor(final int[] array, final int index) {
        final int first = array[index];
        final int second = array[index + 1];
        return first > second ? first : second;
    }

}
