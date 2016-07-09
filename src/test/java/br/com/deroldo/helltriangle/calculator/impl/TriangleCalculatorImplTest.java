package br.com.deroldo.helltriangle.calculator.impl;

import br.com.deroldo.helltriangle.calculator.TriangleCalculator;
import br.com.deroldo.helltriangle.exception.InvalidTriangleException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TriangleCalculatorImplTest {

    private TriangleCalculator calculator;

    @Before
    public void before(){
        this.calculator = new TriangleCalculatorImpl();
    }

    @Test(expected = InvalidTriangleException.class)
    public void invalid_triangle_null(){
        this.calculator.calculate(null);
    }

    @Test(expected = InvalidTriangleException.class)
    public void invalid_triangle_empty(){
        this.calculator.calculate(new int[][]{});
    }

    @Test(expected = InvalidTriangleException.class)
    public void invalid_triangle_row_length(){
        this.calculator.calculate(new int[][]{{6}, {3, 5}, {9, 7, 1}, {4, 6, 8}});
    }

    @Test
    public void valid_triangle_1_rows(){
        final int value = this.calculator.calculate(new int[][]{{6}});
        assertEquals(6, value);
    }

    @Test
    public void valid_triangle_2_rows(){
        final int value = this.calculator.calculate(new int[][]{{6}, {3, 5}});
        assertEquals(11, value);
    }

    @Test
    public void valid_triangle_3_rows(){
        final int value = this.calculator.calculate(new int[][]{{6}, {3, 5}, {9, 7, 1}});
        assertEquals(18, value);
    }

    @Test
    public void valid_triangle_4_rows(){
        final int value = this.calculator.calculate(new int[][]{{6}, {3, 5}, {9, 7, 1}, {4, 6, 8, 4}});
        assertEquals(26, value);
    }

    @Test
    public void valid_triangle_5_rows(){
        final int value = this.calculator.calculate(new int[][]{{6}, {3, 5}, {9, 7, 1}, {4, 6, 8, 4}, {0, 2, 4, 6, 8}});
        assertEquals(32, value);
    }

}