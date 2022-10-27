package com.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args){
        App app = new App();
        app.run();
    }

    /**
     * Starts running the app. Handles a FileNotFoundException
     */
    private void run(){
        ArrayList<String> ciphers = new ArrayList<>();
        try{
            setup(ciphers);
            Menu menu = new Menu(ciphers);
            menu.run();
        }
        catch(FileNotFoundException e){
            System.out.println("File not Found");
        }
        finally{
            System.out.println("Goodbye");
        }
    }

    /**
     * Gets the file "ciphertext.txt" and adds the ciphers contained in the file to
     * the passed Arraylist.
     * @param ciphers an Arraylist for the ciphers to be added to
     * @throws FileNotFoundException if the file containing the ciphers, "ciphertext.txt",
     *  is not found
     */
    private void setup(ArrayList<String> ciphers) throws FileNotFoundException{
            File cipherFile = new File("ciphertext.txt");
            
            Scanner fileScanner = new Scanner(cipherFile);
            while(fileScanner.hasNextLine()){
                ciphers.add(fileScanner.nextLine().toUpperCase());
            }
            fileScanner.close();
    }
}
