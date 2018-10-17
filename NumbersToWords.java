package com.company;    import java.util.Scanner;

public class NumbersToWords {
    public static void main(String args[]) {
        System.out.println("Input the number you want its value in word");
        Scanner input = new Scanner(System.in);     //Take user's input
        long i= input.nextLong();
        sliceNumbers(i + "", i);               //Pass user's input into number slicing method
    }

    public static void sliceNumbers(String num, long j){    //  This method slices numbers into chunks of three
        int noOfTimes = (num.length() % 3 == 0) ? (num.length() / 3) : ((num.length() / 3) + 1);    //Number of possible place values in the number
        int len;    int fine_chunk;    long rough_chunk;    int place_value;    String result = "";
        for (int i = noOfTimes-1; i >= 0; i--) {    //Divide number into chunk of three digits, with repetitive place value division
            rough_chunk = (j / (long) (Math.pow(1000, i)));
            len = (rough_chunk + "").length();
            fine_chunk = (len > 3) ? (Integer.parseInt((rough_chunk + "").substring(len - 3, len))) : ((int) rough_chunk);
            place_value = (i != 0) ? (i + 1) : 0;
            String res = numberToWords(fine_chunk, place_value);    //Pass number chunk in numberToWords methods and store the word
            res = (i == 0 && noOfTimes > 1 && fine_chunk  > 0 && fine_chunk < 100) ? (" and " + res) : res;     //Take care of semantics
            result = (i > 0 && fine_chunk != 0) ? (result + res.replaceAll("  ", " ") + ", ") : (result + res.substring(0, res.length() - 1));    //Cleanup the words
        }
        System.out.println(result.replaceAll("\\s+$", "").replaceAll(",+$", "") + ".");
    }

    public static String numberToWords(int number, int placeValue){
        String[] placeValues = {"","Hundred","Thousand","Million","Billion","Trillion"};
        String[] singleWords = {"Zero","One", "Two", "Three","Four","Five","Six","Seven","Eight","Nine","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
        String[] tensOfWords = {"Ten","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
        String a;        int length = (int) Math.log10(number) +1;      String result = "";
        result = (number == 0) ? (" ") : (result);
        result = (length == 1) ? (singleWords[number] + " " + placeValues[placeValue]) : (result);
        result = ((number < 20) && (number > 10)) ? (singleWords[number - 1] + " " + placeValues[placeValue]) : (result);
        result = (length == 2 && number % 10 == 0 && (number > 19)) ? (tensOfWords[number/10 - 1] + " " + placeValues[placeValue]) : (result);
        result = (length == 2 && number % 10 != 0 && (number > 19)) ? (tensOfWords[((number - (number%10))/10 -1)] + " " + singleWords[number%10] + placeValues[placeValue]) : (result);
        if (length == 3){
            a = (number % 100 == 0) ? " " : " and ";
            result = singleWords[(number - number % 100)/100] + " " + placeValues[1] + a + numberToWords(number % 100, 0)  + " " + placeValues[placeValue];            }
        return result;            }
    }
