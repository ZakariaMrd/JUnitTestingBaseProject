package org.xproce.advancedconcepts;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.xproce.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DietPlannerTest {
    private DietPlanner dietPlanner;
    private String environment = "dev";
    @BeforeEach
    void setup() {
        dietPlanner = new DietPlanner(20, 30, 50);
    }
    @AfterEach
    void afterEach() {
        System.out.println("A unit test was finished.");
    }
    @RepeatedTest(10)
    void should_ReturnCorrectDiet_Plan_when_CorrectCoder(){
        Coder coder =new Coder(1.82,75.0,26, Gender.MALE);
        DietPlan expected = new DietPlan(2202,110,73,275);
        DietPlan actual = dietPlanner.calculateDiet(coder);
        assertAll(
                ()->assertEquals(expected.getCarbohydrate(),actual.getCarbohydrate()),
                ()->assertEquals(expected.getFat(),actual.getFat()),
                ()->assertEquals(expected.getProtein(),actual.getProtein()),
                ()->assertEquals(expected.getCalories(),actual.getCalories())
        );
    }

    @RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME)
    void should_ReturnCorrectDiet_Plan_when_CorrectCoder1(){
        //given
        Coder coder =new Coder(1.82,75.0,26, Gender.MALE);
        DietPlan expected = new DietPlan(2202, 110, 73, 275);
        //when
        DietPlan actual = dietPlanner.calculateDiet(coder);
        //then
        assertAll(
                ()->assertEquals(expected.getCalories(),actual.getCalories()),
                ()->assertEquals(expected.getProtein(),actual.getProtein()),
                ()->assertEquals(expected.getFat(),actual.getFat()),
                ()->assertEquals(expected.getCarbohydrate(),actual.getCarbohydrate())
        );
    }

    @Test
    void should_ReturnCoderWithWorstBMIIn1Ms_when_CoderListHas10000Elements(){
        List<Coder> coders = new ArrayList<>();
        for(int i=0;i<10000;i++){
            coders.add(new Coder(1.0 + i,10.0+i));
        }
        // when
        Executable executable = ()-> BMICalculator.findCoderWithWorstBMI(coders);
        // then
        assertTimeout(Duration.ofMillis(500),executable);
    }
    @Test
    void should_ReturnCoderWithWorstBMIIn1Ms_when_CoderListHas10000Elements1(){
        Assumptions.assumeTrue(this.environment.equals("prod"));
        List<Coder> coders = new ArrayList<>();
        for(int i=0;i<10000;i++){
            coders.add(new Coder(1.0 + i,10.0+i));
        }
        // when
        Executable executable = ()-> BMICalculator.findCoderWithWorstBMI(coders);
        // then
        assertTimeout(Duration.ofMillis(500),executable);
    }

}