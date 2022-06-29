package com.yash;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.*;

/**
 * Unit test for simple App.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    App theApp;
    //Lifecycle methods
    @BeforeAll //This method is called before all
    static void callFirst(){
        System.out.println("BeforeAll called...");
    }
    @AfterAll //This method is executed at the end and only once
    static void callAtEnd(){
        System.out.println("AfterAll called...");
    }
    @BeforeEach //Called before every test case is executed
    void callBeforeEach(){
        System.out.println("BeforeEach called...");
    }
    @AfterEach
        //Called after every test case is executed
    void callAfterEach(){
        System.out.println("AfterEach called...");
    }
    @Test
    @DisplayName("Division check")
    void testDivide()
    {
        App app = new App();
        int result = app.divide(20, 10);
        String str = "Hello";
//        assertEquals(2, result);
//        assertNotEquals(3, result);
        assertAll(
                () -> assertEquals(2, result),
                () -> assertNotEquals(3, result),
                () -> assertEquals(4, app.divide(100, 25), "Must be equal"),
                () -> assertThrows(ArithmeticException.class, () -> app.divide(20, 0)),
                () -> assertSame("Hello", str)
        );
        assumeTrue(false);
    }

    @Test
    void mockTest(){
        App.Remote ref = mock(App.Remote.class);
        when(ref.displayGreeting()).thenReturn(ref.GREETING);
        assertEquals("Hello", ref.displayGreeting());
        assertEquals("Hello", ref.displayGreeting());
        verify(ref, atLeast(1)).displayGreeting();
        verify(ref, times(2)).displayGreeting();
    }
    @ParameterizedTest
    @Disabled  //Earlier until JUnit 4.2 there was @Ignore used for the same but after that @Disabled replaced it.
    @DisplayName("Testing Palindrome")
    @DisabledOnOs({OS.WINDOWS, OS.LINUX})
    @EnabledOnOs({OS.MAC, OS.SOLARIS})
    @EnabledForJreRange(min=JRE.JAVA_8, max = JRE.JAVA_18)
    @ValueSource(strings = {"Shubh", "Yash", "Teja", "Akshay", "naman"})
    void testPalindrome(String value){
        App app = new App();
        assertEquals(true, app.checkForPalindrome(value));
    }
}
