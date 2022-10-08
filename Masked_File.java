/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Masked_File;



import java.util.Random;
import java.util.Scanner;
import java.util.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/**
Name - Raghvandersinh Solanki
Date - March 6, 2021.
Goal: 
    1. Create a 2d array of random integer from -49 to 49 with a input size 
between 1 to 10 (Original table).
    2. Create a method that will create another 2d array of integers with the 
same elements as the Original table(Masked table). The Masked table element will
either be 1 or 0. if the element is >= 1 it will change to 1, else
it will change to 0. Then, we will write it to a text file called "masked.txt".
    3. Create a method that will read the contents of the file "masked.txt".
Which we will use to output.
*/

public class Masked_File
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //Declarations 
        Random rd;
        int row;
        int column;
        int[][] table_before;
        
        //inputs the size of the row between 1 to 10.
        System.out.println("Enter a size between 1 to 10:");
        row = input();
        //inputs the size of the column between 1 to 10.
        System.out.println("Enter a size between 1 to 10:");
        column = input();
        
        /*
            The "Original table". has the size of row and column, which are the
        inputs and has random elements between -49 to 49.
        */
        rd = new Random();
        table_before = new int[row][column];
        
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < column; j++)
            {
                //rd.nextInt(n,n-1)
                table_before[i][j] = rd.nextInt(-49,50);
            }
        }
        //Ouputs the "Original table".
        System.out.println("Table Before:");
        output(table_before);
        //Inputs the vaule of the "Masked table" to a file called "masked.txt"
        write_getMask(table_before);
        //Outputs the vaule of "masked.txt"
        System.out.println("Table After:");
        read_getMask();
    }
    //Validating User Input.
    public static int input()
    {
        //Declarations
        int input;
        Scanner in = new Scanner(System.in);
        
       /*
           A while loop. The user will enter a int input between 1 to 10. if the
        input is  >= 1 and <= 10 the user will break out of the loop, but if he
        enters a vaule outside of the range the user will be prompt to reenter 
        the vaule. 
        
        What if he enters a String, double, ...? For that I used Try and Catch.
        If a User enters a String, double, etc. it will catch a 
        InputMismatchException and User will be prompt enter the vaule again. 
        */ 
       while(true)
       {
           try
           {
               input = in.nextInt();
               
               if(input >= 1 && input <= 10)
               {
                   break;
               }
               else
               {
                   System.out.println("Out of Bound. Please enter again.");
               }       
           }
           catch(InputMismatchException e)
           {
               System.out.println("You did not enter a integer.");
               in.nextLine();
           }
       }
       return input;
    }
    
    
    /**
     *It will print the "Masked table" to file called "masked.txt"
     * @param table
     */
    public static void write_getMask(int[][] table) 
    {
        //declarations
        int[][] mask;
        PrintWriter pw;
        
        try
        {
             //Contructs a file.
            pw = new PrintWriter("masked.txt");
            // 2d array of integer with the size of @param table
            mask = new int[table.length][table[0].length];
            
            for(int i = 0; i < table.length; i++)
            {
                for(int j = 0; j < table[0].length; j++)
                {
                    // If the elements is >= 1, it will be replaced by 1.
                    if(table[i][j] >= 1)
                    {
                        mask[i][j] = 1;
                        /*
                        It will print the vaule in the "masked.txt", with a 
                        formatted space of 7 between elements. 
                        */
                        
                        pw.printf("%7d", mask[i][j]);
                    }
                    //else the element will be replaced by 0.
                    else
                    {
                        mask[i][j] = 0;
                        pw.printf("%7d", mask[i][j]);
                    }
                    
                }
                pw.println();
            }
            pw.close();
        }
       
        catch(FileNotFoundException e)
        {
            System.out.println("Error: " + e);
        }
    }
    
    //will read the vaule from "masked.txt" and ouputs its vaule
    public static void read_getMask() 
    {
      try
      {
       // Construct the file 
        File file = new File("masked.txt");
       // Construct the Scanner
        Scanner scan = new Scanner(file);
       //Prints out the vaule from "masked.txt"
        while(scan.hasNextLine())
        {
            System.out.println(scan.nextLine());
        }
      }
      //Catches a FileNotFound Error
      catch(FileNotFoundException e)
      {
          System.out.println("File Not Found:");
      }
              
    }
    /**
        Formats the Output of the "Original table".
    @param table
    */
    public static void output(int[][] table)
    {
        
        for(int i = 0; i < table.length; i++)
        {
            for(int j = 0; j < table[0].length; j++)
            {
                System.out.printf("%7d", table[i][j]);
            }
            System.out.println("");
        }
    }
    
}
