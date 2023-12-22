package org.xproce.advancedconcepts;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.xproce.BMICalculator;
import org.xproce.Coder;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorNestedTest {
    @BeforeAll
    static void beforeAll() {
        System.out.println("before all unit tests");
    }
    @AfterAll
    static void afterAll() {
        System.out.println("after all unit tests");
    }
@Nested
class IsDietRecommendedTest{
        @ParameterizedTest
        @ValueSource(doubles = {75.0, 89.0, 95.0,110.0})
    void should_ReturnTrue_When_DietRecommended_SingleValue(Double coderWeight) {
        //given
        double weight = coderWeight;
        double height = 1.72;
        //when
        boolean recommended = BMICalculator.isDietRecommended(weight, height);
        //then
        assertTrue(recommended);
    }
    @ParameterizedTest(name = "weight={0}, height={1}")
    @CsvFileSource(resources = "/diet-recommended-input-data.csv",numLinesToSkip = 1)
    void should_ReturnTrue_When_DietRecommended_MultipleValues(Double coderWeight, Double coderHeight) {
        //given
        double weight = coderWeight;
        double height = coderHeight;
        //when
        boolean recommended = BMICalculator.isDietRecommended(weight, height);
        //then
        assertTrue(recommended);
    }
    @Test
    void should_ReturnFalse_When_DietRecommended() {
        //given
        double weight = 50.0;
        double height = 1.92;
        //when
        boolean recommended = BMICalculator.isDietRecommended(weight, height);
        //then
        assertFalse(recommended);
    }
}
    @Nested
    class FindCoderWithWorstBMITest {
        @Test
        void should_ReturnCoderWithWorstBMI_When_CoderListNotEmpty() {

        }

        @Test
        void should_ReturnCoderWithWorstBMI_When_CoderListHas10000Elements() {

        }
        @Test
        @DisplayName(">> sample method display name")
        @Disabled
        @DisabledOnOs(OS.LINUX)
        void should_ReturnFalse_When_Diet_Not_Recommended() {
            //given
            double weight = 50.0;
            double height = 1.92;
            //when
            boolean recommended = BMICalculator.isDietRecommended(weight, height);
            //then
            assertFalse(recommended);
        }
    }

}