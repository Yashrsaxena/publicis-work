package com.yash;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

    }
    int divide(int first, int second){
        return first/second;
    }

    public boolean checkForPalindrome(String value) {
        return value.equals("naman");
    }

    interface Remote{
        String GREETING = "Hello";
        String displayGreeting();
    }
}
