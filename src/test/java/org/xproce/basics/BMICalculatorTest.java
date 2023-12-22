package org.xproce.basics;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.xproce.BMICalculator;
import org.xproce.Coder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {
    @Test
    void test(){
        assertTrue(BMICalculator.isDietRecommended(89, 1.72));
    }

    @Test
    void should_ReturnTrue_When_Diet_Recommended() {
    //given: initial conditions or inputs valus
        double weight = 89.0;
        double height = 1.72;
    // when: act
        boolean recommended = BMICalculator.isDietRecommended(weight, height);
    //then:
        assertTrue(recommended);
    }

    @Test
    void should_ThrowArithmeticException_When_HeightZero() {
        //given
        double weight = 50.0;
        double height = 0.0;
        // when
        Executable executable = () -> BMICalculator.isDietRecommended(weight, height);
        //then
        assertThrows(ArithmeticException.class, executable);
    }

    @Test
    void should_ReturnCoderWithWorstBMI_when_CoderListNotEmpty_ByTestin_AllAssertions(){
        //given
        List<Coder> coders = new ArrayList<>();
        coders.addAll(List.of(
                new Coder(1.80, 60.0),
                new Coder(1.82, 98.0),
                new Coder(1.82, 64.7)
        ));
        //when
        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
        //then
        assertAll(
                () -> assertEquals(1.82, coderWorstBMI.getHeight()),
                () -> assertEquals(98.0, coderWorstBMI.getWeight())
        );
    }
    @Test
    void should_ReturnNullWorstBMI_when_CoderListEmpty_ByTestin_AllAssertions(){
        //given
        List<Coder> coders = new ArrayList<>();
        //when
        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
        //then
        assertNull(coderWorstBMI);
    }
    @Test
    void should_ReturnCorrectBMIScoresArray_when_CoderListNotEmpty(){
        //given
        List<Coder> coders = new ArrayList<>();
        coders.addAll(List.of(
                new Coder(1.80, 60.0),
                new Coder(1.82, 98.0),
                new Coder(1.82, 64.7)
        ));
        //when
        double[] bmiScores = BMICalculator.getBMIScores(coders);
        //then
        assertArrayEquals(new double[]{18.52, 29.59, 19.53}, bmiScores);
    }
}