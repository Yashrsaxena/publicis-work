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
        StringBuffer input = new StringBuffer(value);
//        String reversedValue = input.reverse().toString();
//        System.out.println(input+"\n"+input.reverse());
        return value.equals(input.reverse().toString());
    }

    interface Remote{
        String GREETING = "Hello";
        String displayGreeting();
    }
}
