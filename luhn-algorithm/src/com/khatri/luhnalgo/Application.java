package com.khatri.luhnalgo;

/**
 * <p>
 * 		Program to check luhn algorithm.
 *      https://en.wikipedia.org/wiki/Luhn_algorithm
 * </p>
 * @author Yash Khatri
 *
 */
public class Application { 
   
/**
 * Verifies credit card number by luhn algorithm,
 * @param cardNo
 * @return
 *   	True: if the card number is a valid as per luhn algorithm.
 *      Else; false.
 */
public boolean checkLuhn(String cardNo) 
{ 
 int nDigits = cardNo.length(); 

 int nSum = 0; 
 boolean isSecond = false; 
 for (int i = nDigits - 1; i >= 0; i--)  
 { 

     //converting the character to int.
	 int d = cardNo.charAt(i) - '0'; 

     if (isSecond == true) 
         d = d * 2; 

     // Adding two digits to handle cases that make two digits  
     // after doubling 
     nSum += d / 10; 
     nSum += d % 10; 

     isSecond = !isSecond; 
 } 
 return (nSum % 10 == 0); 
} 


 static public void main (String[] args) { 
	 Application app = new Application();
     String cardNo = "3234242444"; 
     if (app.checkLuhn(cardNo)) 
         System.out.println(cardNo + " is a valid card number!"); 
     else
         System.out.println(cardNo + " is not a valid card number!"); 
   
 } 
} 