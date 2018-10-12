package com.company;
import java.util.Scanner;

public class NumbersToWords2 {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input the number you want its value in word, with commas separating the place values e.g 1,209");
        long i= input.nextLong();
        sliceNumbers(i + "");
    }

    public static void sliceNumbers(String num){
        int mod = num.length() % 3; int movs = mod; String result = "";
        int noOfTimes = (mod == 0) ? num.length()/3 : (num.length()/3) + 1;
        int[] numbers= new int[noOfTimes];
        numbers[0] = (mod == 0) ? (Integer.parseInt(num.substring(0,3))): (Integer.parseInt(num.substring(0,mod)));
        for(int m = 1; m<noOfTimes; m++){
            numbers[m] =(mod == 0) ? Integer.parseInt(num.substring(3*m,(3*m) + 3)) : Integer.parseInt(num.substring(movs,movs+3));
            movs += 3;        }
        for (int i=0; i<numbers.length; i++){
            String res = numberToWords(numbers[i], i);
            res = (i == numbers.length-1 && numbers[i] < 100 && numbers[i]  > 0) ? " and " + res: res;
            result += res;        }
        System.out.println(result + "." + "\n");
        }

    public static String numberToWords(int number, int placeValue){
        String[] placeValues = {"Trillion","Billion","Million","Thousand","Hundred",""};
        String[] placeValuess = {"","Hundred","Thousand","Million","Billion","Trillion"};
        String[] singleWords = {"Zero","One", "Two", "Three","Four","Five","Six","Seven","Eight","Nine","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
        String[] tensOfWords ={"Ten","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};

        String a;        int length = (int) Math.log10(number) +1;
        if (number == 0){return "";}
        if (length == 1){
            return singleWords[number] + " " + placeValues[placeValue] + " ";            }
        else if ((20 > number) && (number > 10)){
            return singleWords[number - 1] + " " + placeValues[placeValue] + " ";
        }
        else if (length == 2 && number % 10 == 0){
            return tensOfWords[number/10 - 1] + " " + placeValues[placeValue] + " ";            }

        else if(length == 2 && number % 10 != 0){
            return tensOfWords[((number - (number%10))/10 -1)] + " " + singleWords[number%10] + placeValues[placeValue] + " ";            }

        else if (length == 3){
            a = (number % 100 == 0) ? " " : " and ";
            return singleWords[(number - number % 100)/100] + " " + placeValues[4] + a + numberToWords(number % 100, 0)  + placeValues[placeValue] + " ";            }
        return "error";
    }
    }

