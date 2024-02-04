/**
* Assignment: SDEV200_M04_Assignment1_Ex20_11
* File: MatchGroupingSymbols.java
* Version: 1.0
* Date: 2/3/2024
* Author: Tomomi Hobara
* Description: This program is executed from the command line and takes a Java file name as an argument. 
               It checks if the source code contains matching grouping symbols. 
* Variables: 
    - file: a File object, the name of a .java file to be checked
    - symbolStack: a stack for storing grouping symbols
    - copiedLine: String, holds the lines from the input file    
* Steps:
    1. Read the input file line by line.
    2. Change each line to a character array.
    3. Read the array and put opening grouping symbols in a stack ('(', '{', and '[').
    4. When a closing grouping symbol is read, compare it against the symbols in the stack. 
        a. If the stack is empty, indicate a mismatch and exit the program.
        b. If the closing grouping symbol matches with the first symbol in the stack, 
           remove the symbol from the stack.
    5. Repeat the process until the end of the file.
    6. Check if there are any symbols left in the stack. If there is, indicate a mismatch.
    7. Print the result in the console.    
* Note:
    Command line example:
    java -cp "C:\Users\...\bin" MatchGroupingSymbols "C:\Users\...\src\App.java" 
    
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class MatchGroupingSymbols{
    public static void main(String[] args) throws Exception {

        // Check if there is an input in command line. Exit the program if there is no input.
        if (args.length != 1) {
            System.out.println("In correct entry. Please enter: java -cp <Path of MatchGroupingSymbols location> fileNameToTest.java");
            System.exit(1);
        }

        // Create a file object based on the command line argument
        File file = new File(args[0]);


        // Process the file input with try-catch
        try {
            System.out.println(printResult(file));
        }

        catch (Exception ex) {
            System.out.println("Wrong expression: " + args[0]);
        }
    }


    public static String printResult(File file) throws IOException{
        if (evaluateFile(file)) {
            return "The file has matching grouping symbols.";
        }
        else {
            return "The file does not have matching grouping symbols.";
        }

    }


    public static boolean evaluateFile(File file) throws IOException {

        // Create a stack to store grouping symbols
        Stack<Character> symbolStack = new Stack<>(); 

        // Create an object to copy file content
        String copiedLine = "";

        // Create a BufferedReader object to read from a file
        try (BufferedReader lineReader = new BufferedReader(new FileReader(file))) {
        
            // Read each line //TO DO: Need a mechanism to remove symbols that are texts (inside of "" or ''.)
            while ((copiedLine = lineReader.readLine())!= null) {
                for (char c : copiedLine.toCharArray()) {               // Change a line to an array of characters 
                    if (c == '(' || c == '{' || c == '[') {             // Look for an opening symbol in the array
                        symbolStack.push(c);                            // Put an opening symbol in the stack
                    }

                    else if (c == ')' || c == '}' || c == ']') {            // Look for an closing symbol in the array
                        if (symbolStack.isEmpty()) {                   // If the stack is empty return false to main and exit the for each loop
                            return false;
                        }
                        else if (lookForPair(c, symbolStack.peek())) {    // If c match with the first symbol in the stack, remove it from the stack.
                            symbolStack.pop();
                        }                    
                        else {                                         // If c does not match with the first symbol, return false and exit the loop.
                            return false;
                        }       
                    }
                }  // End of a copiedLine
            }      // End of while loop (=End of file) 

            if (!symbolStack.isEmpty()){                                // Check if the stack is empty at the end of the file
            return false;
            }
            
            return true;          // Finished reading the entire file without any problems  

        }
    }
    
    /** Check if opening symbols match with closing symbols. */
    public static boolean lookForPair(char closingSymbol, char symbolInStack) {            
        if ((closingSymbol == ')') && (symbolInStack == '(')) {
            return true;
        }
        else if ((closingSymbol == '}') && (symbolInStack == '{')) {
            return true;
        }
        else if ((closingSymbol == ']') && (symbolInStack == '[')) {
            return true;
        } 
        else {
            return false;
        }
    }
}

    

