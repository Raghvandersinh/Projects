/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package ArrayListProject;

/**
 * The objective of this project is to allow the user to fill in the ArrayList
 * with String data. The user can add data, delete data, search for data,
 * retrieve data from index, see the length of data, and see the contents of the
 * data from the ArrayList.
 * 
 *Name - Raghvandersinh Solanki
 *Date - 3/31/2022
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class ArrayListProject
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //Declaration 
        ArrayList<String> objects = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String choice;
        boolean flag = true;
        
        //loop will continue until the user enter "quit"
        while(flag)
        {
            //displays the command prompt.
            display();
            //allowing user to enter choices
            System.out.print("Enter: ");
            choice = input.nextLine();
            //preventing case sensitive issues
            switch(choice.toLowerCase())
            {
                //adds items to the ArrayList
                case "add":
                    add(objects);
                    break;
                //deletes items from the ArrayList
                case "delete":
                    delete(objects);
                    break;
                //searches for the index of the first occurance of the element
                case "search":
                    search(objects);
                    break;
                //retrieve an element from a certain index
                case "retrieve":
                    retrieve(objects);
                    break;
                //Displays the content of the ArrayList
                case "display":
                    contents(objects);                    
                    break;
                //prints out the total length of the ArrayList
                case "length":
                    System.out.println("The Length: " + objects.size());
                    break;
                //ends the loop when user enters "quit"
                case "quit":
                    System.out.println("End of the program.");
                    flag = false;
                    break; 
                //occurs when user enters the wrong command   
                default:
                    System.out.println("Please enter the correct command.");
            }
        }
    }
    /**
     * Allow user input to enter data in the ArrayList. 
     * @param object - ArrayList objects in the main method.
     */
    public static void add(ArrayList<String> object)
    {
        //Declaration
        Scanner input = new Scanner(System.in);
        String str;
        int index;
        String choice;
        boolean flag = true;
        
        //loop will end when the user input enters the correct commands.
        while(flag)
        {   
            //giving user input two Commnads: Add or Index.
            System.out.println();
            System.out.println("Type: Add or Index");
            System.out.println("Add: Lets you add a Object at the end of the list");
            System.out.println("Index: Lets you pick a location to add the Object");
            System.out.print("Enter: ");
            choice = input.nextLine();
            //if user input choice equals "add".
            if(choice.equalsIgnoreCase("Add"))
            {
                //user input will be prompt to enter a object
                System.out.println();
                System.out.print("Enter a object: ");
                str = input.nextLine();
                //user input data will be stored in the ArrayList
                System.out.println(str + " has been added.");
                object.add(str);
                //loop ends
                flag = false;
            }
            //if user input choice equals "index".
            else if(choice.equalsIgnoreCase("Index"))
            {
                
                try
                {
                    //Asks user input for an index.
                    System.out.println();
                    System.out.print("Enter a number: ");
                    index = input.nextInt();
                    //Asks user input for an object.
                    input.nextLine();
                    System.out.print("Enter a object: ");
                    str = input.nextLine();
                    //adds the object in a certain index.
                    System.out.println(str + " has been added at Index: " + index);
                    object.add(index,str);
                    //loop ends
                    flag = false;
                }
                //loop will end when we get a index out of bound error
                catch(IndexOutOfBoundsException e)
                {
                    System.out.println();
                    System.out.println("Out of Bound.");
                    flag = false;
                }
                //loop will continue until user input index enters a integer.
                catch(InputMismatchException e)
                {
                    System.out.println();
                    System.out.println("Plese enter an Integer");
                    flag = false;
                }
            }
            /*
            When choice does not equal "add" or "index" the loop will continue
            until the user input enters the correct commands. 
            */
            else
            {
                System.out.println();
                System.out.println("Please enter: Add or Index");
            }
        }
    }
    /**
     * Allow user input to delete data from the ArrayList objects
     * @param object = ArrayList objects in the main method.
     */
    public static void delete(ArrayList<String> object)
    {
        //Declaraion
        Scanner input = new Scanner(System.in);
        int index;
        String choice;
        String str;
        boolean flag = true;
        
        //loop will end when the user input types the correct command.
        while(flag)
        {
            //user input will pick two choices: Element or Index
            System.out.println();
            System.out.println("Type: Object or Index");
            System.out.println("Object: type a Object you want to remove.");
            System.out.println("Index: type a index of an Object you want to remove.");
            System.out.print("Enter: ");
            choice = input.nextLine();
            
            //If choice equals "object"
            if(choice.equalsIgnoreCase("Object"))
            {
                //User input would be promt to enter a object.
                System.out.println();
                System.out.print("Enter a Object: ");
                str = input.nextLine();
                //if ArrayList contains the object    
                if(object.contains(str))
                {
                    //the object will be removed from the list.
                    //if a duplicate occurs it will only remove the first occurance.
                    System.out.println();
                    System.out.println(str + " has been removed.");
                    object.remove(str); 
                    //loop ends
                    flag = false;
                }
                //else, if the object doesn't exist. the loop will end
                else
                {
                    System.out.println("object does not exist");
                    flag = false;
                }
            }
            //if choice equals "index"
            else if(choice.equalsIgnoreCase("Index"))
            {
                try
                {
                    //Asks user input to enter a int Index
                    System.out.println();
                    System.out.print("Enter a Index number: ");
                    index = input.nextInt();
                    //Removes the object from the index in the list
                    System.out.println();
                    System.out.println(object.get(index) + " has been removed at index: " + index);
                    object.remove(index);
                    //loop ends
                    flag = false;
                }
                //loop will end when we get a index out of bound error
                catch(IndexOutOfBoundsException e)
                {
                    System.out.println();
                    System.out.println("Index is out of bound.");
                    flag = false;
                }
                //loop will end if user input doesn't enter a Integer.
                catch(InputMismatchException e)
                {
                    System.out.println();
                    System.out.println("Please enter a Interger.");
                    flag = false;
                }
            }
            /*
            when choice does not equal "element" or "index" the will continue 
            until user either one. 
            */
            else
            {
                System.out.println();
                System.out.println("Please Enter: Object or Index");
            }
        }   
    }
    /**
     * displays the content of the ArrayList
     * @param object - ArrayList object in the main method.
     */
    public static void contents(ArrayList<String> object)
    {
        System.out.println();
        System.out.println("These are the contents:");
        for(int i = 0; i < object.size(); i++)
        {
            System.out.print(object.get(i) + ", ");
        }
    }
    /**
     * allows user input to search the index of the element
     * @param object- ArrayList objects from the main 
     */
    public static void search(ArrayList<String> object)
    {
        //Declaration
        Scanner input;
        String str;
        int index;
        
        //Asks user input to enter object they want to search.
        System.out.println();
        System.out.println("Type in the term you want to search:");
        input = new Scanner(System.in);
        str = input.nextLine();
        
        //if the object contains the user input str
        if(object.contains(str))
        {
            //prints the index of the input user str.
            index = object.indexOf(str);
            System.out.println();
            System.out.println("The index of " + str + " is at: " + index );
        }
        //if the object does not contain user input str.
        else
        {
            System.out.println();
            System.out.println("This element does not exist.");
        }
    }
    /**
     * retrieve an element from a certain index
     * @param object - ArrayList objects from the main
     */
    public static void retrieve(ArrayList<String> object)
    {
        //Declaration
        int index;
        Scanner input = new Scanner(System.in);

        try
        {
            //prints out an element from the index user inputed.
            System.out.println();
            System.out.print("Enter the index: ");
            index = input.nextInt();
            System.out.println("The Object: " + object.get(index));
        }
        //if a user input enters a index out of bound. We get an exception
        catch(IndexOutOfBoundsException e)
        {
            System.out.println();
            System.out.println("Out of Bounds.");
        }
        //if a user input enters anything, but a integer. We get an exception
        catch(InputMismatchException e)
        {
            System.out.println();
            System.out.println("Please enter a integer");
        }
        
    }
   
    //Displays the options
    public static void display()
    {
        System.out.println();
        System.out.println("-----------------------");
        System.out.println("Command:");
        System.out.println("Add: add object to the list.");
        System.out.println("Delete: delete the object from the list.");
        System.out.println("Display: display the contents of the list.");
        System.out.println("Search: search the index of the element.");
        System.out.println("Retrive: retrieve an element by index.");
        System.out.println("Length: Total length of the list.");
        System.out.println("Quit: quit the program");
        System.out.println();
    }
    
}

        
  
