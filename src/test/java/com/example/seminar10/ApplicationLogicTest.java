package com.example.seminar10;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ApplicationLogicTest {
    @Mock
    private SomeDependency someDependency;

    @InjectMocks
    private ApplicationLogic applicationLogic;

    @BeforeEach
    public void setUp() {
        initMocks(this);
}
    @Test
    public void testCalculateLCM() {

        int a = 4;
        int b = 6;
        int c = 8;
        int expectedLCM = 24;
        int actualLCM = applicationLogic.calculateLCM(a, b, c);
        assertEquals(expectedLCM, actualLCM, "The LCM of 4, 6, and 8 should be 24");
    }
    @Test
    public void testEvaluatePolynomial() {

        double x = 2.0;
        double expectedValue = 3 * x * x + 5 * x + 7;
        double actualValue = applicationLogic.evaluatePolynomial(x);
        assertEquals(expectedValue, actualValue, "The polynomial value should match the expected value");
    }
    @Test
    public void testDependencyInteraction() {

        when(someDependency.someMethod()).thenReturn("Mocked Response");
        String response = applicationLogic.useDependency();
        assertEquals("Mocked Response", response, "The response should match the mocked response");
        verify(someDependency).someMethod(); // Verify that the method was called
    }
}
class ApplicationLogic {

    private SomeDependency someDependency;
    public ApplicationLogic(SomeDependency someDependency) {
        this.someDependency = someDependency;
    }
    
    public int calculateLCM(int a, int b, int c) {
        return (a * b) / GCD(a, b) * (c / GCD((a * b) / GCD(a, b), c));
    }
    
    private int GCD(int a, int b) {
        if (b == 0) return a;
        return GCD(b, a % b);
    }
    
    public double evaluatePolynomial(double x) {
        int A = 3; 
        int B = 5; 
        int C = 7; 
        return A * x * x + B * x + C;
    }
    
    public String useDependency() {
        return someDependency.someMethod();
    }
}

interface SomeDependency {
    String someMethod();
}