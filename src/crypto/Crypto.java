package crypto;

import java.util.*;
/**
 *
 * @author Sean
 * 
 * Program that performs a Caesar shift encryption. 
 * 
 */
public class Crypto {
    
    public static void main(String[] args) {
        
        System.out.println("Input a string to normalize: ");
        Scanner scanner = new Scanner(System.in);
        String myString = scanner.nextLine();
        System.out.println("Tell me how much to shift for encryption: ");
        int shift = scanner.nextInt();
        
        System.out.println("Tell me how big the blocks should be: ");
        int size = scanner.nextInt();
        String normString, shiftString, groupString;
        normString = normalizeText(myString);
        System.out.println("Normalized string is: " + normString);
        
        shiftString = caesarify(normString, shift);
        System.out.println("Shifted string is: " + shiftString);
       
        groupString = groupify(shiftString, size);
        System.out.println("Groupified string is: " + groupString);
        
        
    }
    //pulls out any punctuation
    public static String normalizeText(String str){
        
        str = str.toUpperCase();
        str = str.replace(".", "");
        str = str.replace("/", "");
        str = str.replace(",", "");
        str = str.replace("?", "");
        str = str.replace(":", "");
        str = str.replace(";", "");
        str = str.replace("!", "");
        str = str.replace("(", "");        
        str = str.replace(")", "");
        str = str.replace("\"", "");
        str = str.replace(" ", "");
        
        String normalStr = str;
                
        return normalStr;
    }
    //shifts the letters
    public static String caesarify(String str, int k){
        
        int index = 0;
        String newstr = "";
        String shift = shiftAlphabet(k);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int x = 0; x<str.length(); x++){
            
            index = alphabet.indexOf(str.charAt(x));
            newstr = newstr + shift.charAt(index);
        }
        return newstr;
    }
    //helper method for the shift 
    private static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;
        for(; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if(result.length() < 26) {
            for(currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        return result;
    }
    //blocks up the ciphertext based on user defined parameter
    public static String groupify(String str, int k){
        String newStr="";
        
        for(int x=1; x<=str.length(); x++){
            newStr = newStr + str.charAt(x-1);
            if(x%k==0){
                newStr = newStr + " ";
            }           
        }
        if(str.length()%k!=0){
            int pad = k - str.length()%k;
            for (int x =0;x<pad;x++){
                newStr = newStr + "x";
            }
        }    
        return newStr;
    }
}
