package org.xproce.advancedconcepts;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.xproce.BMICalculator;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {
    @ParameterizedTest
    @ValueSource(doubles = {75.0, 80.0, 95.0, 110.0})
    void should_ReturnTrue_When_Diet_Recommended_SIngleValue(double coderWeight) {
        //given
        double weight = coderWeight;
        double height = 1.72;
        //when
        boolean recommended = BMICalculator.isDietRecommended(weight,height);
        //then
        assertTrue(recommended);
    }

    @ParameterizedTest
    @CsvSource(value={"89.0,1.72","95.0,1.75","110.0,1.78"})
    void should_ReturnTrue_When_Diet_Recommended_CSVSource(double coderWeight, double coderHeight) {
        //given
        double weight = coderWeight;
        double height = coderHeight;
        //when
        boolean recommended = BMICalculator.isDietRecommended(weight,height);
        //then
        assertTrue(recommended);
    }

    @ParameterizedTest(name = "weight={0}, height={1}")
    @CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip = 1)
    void should_ReturnTrue_When_Diet_Recommended_CSVFileSource(double coderWeight, double coderHeight) {
        //given
        double weight = coderWeight;
        double height = coderHeight;
        //when
        boolean recommended = BMICalculator.isDietRecommended(weight,height);
        //then
        assertTrue(recommended);
    }

}