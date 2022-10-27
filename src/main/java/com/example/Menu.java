package com.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Scanner keyboardScanner;
    private CipherTools tools;
    private final ArrayList<String> ciphers;
    private final ArrayList<String> menu;

    /**
     * Creates and instance of class Menu
     * @param ciphers an Arraylist of ciphers as strings
     */
    public Menu(ArrayList<String> ciphers){
        this.ciphers = ciphers;
        this.tools = new CipherTools();
        this.keyboardScanner = new Scanner(System.in);
        this.menu = new ArrayList<>();
        menu.add("1. Task One - Cipher with known key.");
        menu.add("2. Task Two - Cipher with unknown key.");
        menu.add("3. Quit.");
    }

    /**
     * Runs the menu selection until the quit option is selected
     */
    public void run(){
        System.out.println("Welcome");
        boolean run = true;
        while(run){
            try{
                System.out.println("Please select an option:");
                for(int i = 0; i < menu.size(); i++){
                    System.out.println(menu.get(i));
                }
                int selection = keyboardScanner.nextInt() - 1;

                System.out.println("Selection: [" + menu.get(selection) + "]");

                switch(selection){
                    case 0 -> {
                        cipherKnownKey(selection);
                    }
                    case 1 -> {
                        cipherUnknownKey(selection);
                    }
                    case 2 -> {
                        run = false;
                    }
                }
            }
            catch(InputMismatchException e){
                System.out.println("Error: Input was not an integer.");
                //Clear the scanner
                keyboardScanner.nextLine();
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("Error: Menu option does not exist.");
            }
        }
        keyboardScanner.close();
    }

    /**
     * Decryptes a cipher with a known key
     * @param x the cipher to be slected from the ciphers Arraylist
     */
    private void cipherKnownKey(int x){
        char[] cipher = ciphers.get(x).toCharArray();
        System.out.println("Cipher: " + String.valueOf(cipher));
        for(int i = 0; i < cipher.length; i++){
            cipher[i] = tools.shift(cipher[i], -3);
        }
        System.out.println("Solved cipher: " + String.valueOf(cipher));
    }

    /**
     * Decryptes a cipher with an unknown key
     * @param x the cipher to be slected from the ciphers Arraylist
     */
    private void cipherUnknownKey(int x){
        String cipher = ciphers.get(x);
        System.out.println("Cipher: " + cipher);
        cipher = tools.crackCipher(cipher, "DONE");
        System.out.println("Solved cipher: " + cipher);
    }

}
