package com.example;

public class CipherTools {
    /**
     * Value to shift 'A' (65) in ASCII to 1.
     */
    private final int ASCII_SHIFT = 64;
    /**
     * Modolo value for the alphabet 1-26.
     */
    private final int ALPHABET_MODULO = 26;
    
    /**
     * Value to shift the cipher left or descend 1 position
     */
    public final int SHIFT_LEFT = -1;
    /**
     * Value to shift the cihper right or ascend 1 position
     */
    public final int SHIFT_RIGHT = 1; 

    /**
     * Creates an instance of CipherTools
     */
    public CipherTools(){
    }

    /**
     * Shifts the passed char c by the specified amount if char c is not a space,
     * ' ', char. The character is shifted using modular arithmetic with a modulo
     *  of 26. 
     * @param c char to be shifted
     * @param key amount the char is to be shifted by
     * @return char shifted by the specified amount
     */
    public char shift(char c, int key){
        //Checks c is not a space.
        if(c != ' '){
            //Converts c to it's ASCII value
            int x = (int)c;
            //ASCII_SHIFT is used to shift the ASCII values from 65 - 90 to 1 - 26
            x = Math.floorMod((x + key - ASCII_SHIFT), ALPHABET_MODULO) + ASCII_SHIFT;
            c = (char)x;
        }
        return c;
    }

    /**
     * Decrypts the passed cipher. The cipher is decrypted when the inResult is
     * found as a substring
     * @param cipher the cipher to be decrypted
     * @param inResult the substring found in the decrypted cipher
     * @return the decrypted cipher
     */
    public String crackCipher(String cipher, String inResult){
        boolean cracked = false;
        //Trackes the key of the cipher
        int key = 0;
        char[] cipherArr = cipher.toCharArray();
        while(!cracked){
            for(int i = 0; i < cipherArr.length; i++){
                cipherArr[i] = shift(cipherArr[i], SHIFT_LEFT);
            }
            if(checkCipher(String.valueOf(cipherArr), inResult)){
                cracked = true;
            }
            key--;
        }
        //Prints the final value of the key
        System.out.println("Decryption Key: " + key);
        return String.valueOf(cipherArr);
    }

    /**
     * Checks if the inResult is a substring of cipherText. If the substring is
     * found the cipher is decrypted
     * @param cipherText the cipher to check for suubstring
     * @param inResult the substring to be found in the cipher
     * @return  true if the substring is found
     *          false if the substring is not found
     */
    public boolean checkCipher(String cipherText, String inResult){
        if(cipherText.indexOf(inResult) != -1){
            return true;
        }
        return false;
    }
}
