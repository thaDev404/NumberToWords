package com.company;    import java.util.Scanner;

public class NumbersToWords {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        long i= input.nextLong();
        sliceNumbers(i + "", i);
    }

    public static void sliceNumbers(String number, long l){
        int noOfTimes = (number.length() % 3 == 0) ? number.length()/3 : (number.length()/3) + 1;
        int count = 1;        int start = number.length() - 3;       int end = number.length();
        int placeValue;    String result = "";
        for (int j = 1; j <= noOfTimes; j++){
            placeValue = (count != 1) ? count : 0;
            if (count == noOfTimes && number.length() % 3 != 0){
                start = 0;
                end = number.length() % 3;            }
            String n = number.substring(start,end);
            String res = numberToWords(Integer.parseInt(n), placeValue);
            res = (count == 1 && Integer.parseInt(n) < 100 && Integer.parseInt(n) > 0 && l > 99) ? " and " + res: res;
            result = res  + result; start -= 3; end -= 3; count ++;
        }
        System.out.println(result);
    }

    public static String numberToWords(long number, int placeValue) {
        String[] singleWords = {"Zero","One", "Two", "Three","Four","Five","Six","Seven","Eight","Nine","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
        String[] tensOfWords ={"Ten","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
        String[] placeValues = {"","Hundred","Thousand","Million","Billion","Trillion"};
        int numbers = (int) number;
        String a;        int length = (int) Math.log10(numbers) +1; String result = "";

        result = (numbers == 0)? (""):(result);
        result = (length == 1)? (singleWords[numbers] + " " + placeValues[placeValue] + " "):(result);
        result = ((20 > number) && (number > 10))? (singleWords[numbers - 1] + " " + placeValues[placeValue] + " "):(result);
        result = (length == 2 && number % 10 == 0)?(tensOfWords[numbers/10 - 1] + " " + placeValues[placeValue] + " "):(result);
        result = (length == 2 && numbers % 10 != 0) ? (tensOfWords[((numbers - (numbers%10))/10 -1)] + " " + singleWords[numbers%10] + " " + placeValues[placeValue] + " ") : (result);
        if (length == 3){
            a = (number % 100 == 0) ? " " : " and ";
            result = singleWords[(numbers - numbers % 100)/100] + " " + placeValues[1] + a + numberToWords(number % 100, 0)  + placeValues[placeValue] + ", ";            }
        return result;
    }
}
