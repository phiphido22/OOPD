/*************************************************************************
 *  Author: Phi Do                                                       *
 *  Purpose: Contains all the submodules required for input of values by *
 *           the user                                                    *
 *  Date: 06/05/2019                                                     *
 *  Last Updated: 06/05/2019                                             *
 *************************************************************************/
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public final class UserInterface
 {

     /********************************************************************
      * SUBMODULE: menu                                                  *
      * PURPOSE: Outputs a prompt for user to input option of what to do *
      *          within program                                          *
      * IMPORT:                                                          *
      * EXPORT:                                                          *
      * DATE: 06/05/2019                                                 *
      ********************************************************************/
     public void menu()
     {
         int selection;
         ShipStorage shipStorage = new ShipStorage();

         do
         {
            //menu selection between 7 options, only exits if 7 is input
             selection = intInput("Select the number beside the option desired."
                                     +"\n1. Add Ship to Storage"
                                     +"\n2. Display Ships in Storage"
                                     +"\n3. Find Duplicates"
                                     +"\n4. Destination Check"
                                     +"\n5. Export File"
                                     +"\n6. Import File"
                                     +"\n7. Exit");
             // switch and case for menu
             switch (selection) {
               case 1: addShips(shipStorage);
                       break;
               case 2: shipStorage.viewShips();
                       break;
               case 3: shipStorage.findDuplicates();
                       break;
               case 4: int distance = intInput("What is the distance (in meters) to be traversed?");
                       shipStorage.destinationCheck(distance);
                       break;
               case 5: FileManager.printStorage(shipStorage);
                       break;
               case 6: FileManager.readStorage(shipStorage);
                       break;
               case 7: System.out.println("Goodbye");
                       break;
               default:System.out.println("Error: Selection must be between 1 and 7"
                                          +"\nPlease Try Again");
                       break;
             }
          } while(selection != 7);
     }
     /********************************************************************
      * SUBMODULE: addShips                                              *
      * PURPOSE: Outputs a prompt for user to input option of what type  *
      *          of ship to add                                          *
      * IMPORT:                                                          *
      * EXPORT:                                                          *
      * DATE: 22/05/2019                                                 *
      ********************************************************************/
     public void addShips(ShipStorage shipStorage)
     {
         int selection;

         try
         {
             // will loop if integers that arent between 1 and 2 are selected
             selection = intInput("Which type of ship would you like to add?"
                                   +"\n1. Add Submarine to Storage"
                                   +"\n2. Add Fighter Jet to Storage");
             while(selection < 0 || selection > 3)
             {
                 selection = intInput("Error: Selection must be between 1 and 2"
                                       +"\nPlease Try Again");
             }
         }
         // will return to main menu if a character is detected in input
         catch(InputMismatchException e)
         {
             System.out.println("Error: Invalid Input\nReturning to Menu");
             selection = -1;
         }
         switch (selection) {
           case 1: shipStorage.addShipToArray(makeSub());
                   break;
           case 2: shipStorage.addShipToArray(makeFighter());
                   break;
           default:break;
         }
     }

    /********************************************************************
     * SUBMODULE: makeSub                                               *
     * PURPOSE: Allows user make an 'inShip' class object               *
     * IMPORT: none                                                     *
     * EXPORT: inShip (Submarine)                                       *
     * DATE: 22/05/2019                                                 *
     ********************************************************************/
     public Ship makeSub()
     {
         String serialNum, hull, fuel;
         int year, cylinder;
         double maxDepth;
         FighterJet fighterjet;

         // will prompt to input values for submarine class object
         try
         {
             serialNum = serialInput("Input Serial Number of Submarine");
             year = yearInput("Input Year of Ship's Commission");
             cylinder = cylinderInput("Input Number of Cylinders");
             fuel = fuelInput("Input Type of Fuel");
             hull = hullInput("Input Type of Hull");
             maxDepth = depthInput("Input Maximum Depth of Submarine");
         }
         catch(InputMismatchException e)
         {
             throw new IllegalArgumentException("Invalid Input Value");
         }
         // creates an engine class object
         Engine engine = new Engine(cylinder, fuel);
         // creates the submarine class object using engine rather than cylinder and fuel
         Ship inShip = new Submarine(serialNum, year, engine, hull, maxDepth);
         return inShip;
     }

      /********************************************************************
       * SUBMODULE: makeFighter                                           *
       * PURPOSE: Allows user make an 'inShip' class object               *
       * IMPORT: none                                                     *
       * EXPORT: inShip (FighterJet)                                      *
       * DATE: 22/05/2019                                                 *
       ********************************************************************/
     public Ship makeFighter()
     {
         String serialNum, ordanance, fuel;
         int year, cylinder;
         double wingSpan;
         Submarine submarine;

         try
         {
             serialNum = serialInput("Input Serial Number of Fighterjet");
             year = yearInput("Input Year of Ship's Commission");
             cylinder = cylinderInput("Input Number of Cylinders");
             fuel = fuelInput("Input Type of Fuel");
             wingSpan = wingSpanInput("Input Wingspan of Fighterjet");
             ordanance = stringInput("Input Ordanance of Fighterjet");
         }
         catch(InputMismatchException e)
         {
             throw new IllegalArgumentException("Invalid Input Value");
         }
         // creates an engine class object
         Engine engine = new Engine(cylinder, fuel);
         // creates the fighterjet class object using engine rather than cylinder and fuel
         Ship inShip = new FighterJet(serialNum, year, engine, wingSpan, ordanance);
         return inShip;
     }

   /********************************************************************
    * SUBMODULE: intInput                                              *
    * PURPOSE: Outputs a prompt and scans a user defined integer       *
    * IMPORT: prompt(String)                                           *
    * EXPORT: scan(Integer)                                            *
    * DATE: 06/05/2019                                                 *
    ********************************************************************/
   public int intInput(String prompt) throws InputMismatchException
   {
       System.out.println(prompt);
       Scanner sc = new Scanner(System.in);
       int scan;

       do
       {
           try
           {
               scan = sc.nextInt();
               // if input is 0 or less prompt for another input
               if (scan < 0 || scan == 0)
               {
                   System.out.println("ERROR: Negative values are invalid\n"+prompt);
                   scan = 0;
               }
           }
           // if input has a character then prompt for another input
           catch (InputMismatchException e)
           {
               String flush = sc.nextLine();
               System.out.println("ERROR: Invalid character detected\n"+prompt);
               scan = 0;
           }
         } while ((scan <0) || (scan == 0));
         return scan;
   }

    /********************************************************************
    * SUBMODULE: yearInput                                             *
    * PURPOSE: Outputs a prompt and scans a user defined integer       *
    * IMPORT: prompt(String)                                           *
    * EXPORT: scan(Integer)                                            *
    * DATE: 23/05/2019                                                 *
    ********************************************************************/
   public int yearInput(String prompt) throws InputMismatchException
   {
       System.out.println(prompt);
       Scanner sc = new Scanner(System.in);
       int scan;

       do
       {
           try
           {
               scan = sc.nextInt();
               // if input is not between 1950 and 2022 for another input
               if (scan < 1950 || scan > 2022)
               {
                   System.out.println("ERROR: Invalid Year entered, must be between 1950 and 2022\n"+prompt);
                   scan = 0;
               }
           }
           // if input has a character then prompt for another input
           catch (InputMismatchException e)
           {
               String flush = sc.nextLine();
               System.out.println("ERROR: Invalid character detected\n"+prompt);
               scan = 0;
           }
         } while ((scan < 0) || (scan == 0));

         return scan;
   }

    /********************************************************************
    * SUBMODULE: cylinderInput                                         *
    * PURPOSE: Outputs a prompt and scans a user defined integer       *
    * IMPORT: prompt(String)                                           *
    * EXPORT: scan(Integer)                                            *
    * DATE: 23/05/2019                                                 *
    ********************************************************************/
   public int cylinderInput(String prompt) throws InputMismatchException
   {
       System.out.println(prompt);
       Scanner sc = new Scanner(System.in);
       int scan;

       do
       {
           try
           {
               scan = sc.nextInt();
               // if input is not between 2 and 20 for another input
               if (scan < 2 || scan > 20)
               {
                   System.out.println("ERROR: Cylinder amount entered, must be between 2 and 20\n"+prompt);
                   scan = 0;
               }
           }
           // if input has a character then prompt for another input
           catch (InputMismatchException e)
           {
               String flush = sc.nextLine();
               System.out.println("ERROR: Invalid character detected\n"+prompt);
               scan = 0;
           }
         } while ((scan < 0) || (scan == 0));

         return scan;
   }

   /********************************************************************
    * SUBMODULE: depthInput                                            *
    * PURPOSE: Outputs a prompt and scans a user defined real number   *
    * IMPORT: prompt(String)                                           *
    * EXPORT: scan(Real)                                               *
    * DATE: 23/05/2019                                                 *
    ********************************************************************/
   public double depthInput(String prompt) throws InputMismatchException
   {
       DecimalFormat df = new DecimalFormat("0.00");
       System.out.println(prompt);
       Scanner sc = new Scanner(System.in);
       double scan;

       do
       {
           try
           {
               scan = sc.nextDouble();
               // if input is not between -500.0 and 0.0 for another input
               if(scan < -500.0 || scan > 0.0)
               {
                    System.out.println("ERROR: Invalid maximum depth, must be between 0 and -500.0\n" +prompt);
                    scan = 0.0;
               }
           }
           // if input has a character then prompt for another input
           catch (InputMismatchException e)
           {
               String flush = sc.nextLine();
               System.out.println("ERROR: Invalid character detected\n"+prompt);
               scan = 0.0;
           }
       } while (scan == 0);
         return scan;
   }

    /********************************************************************
    * SUBMODULE: wingSpanInput                                         *
    * PURPOSE: Outputs a prompt and scans a user defined real number   *
    * IMPORT: prompt(String)                                           *
    * EXPORT: scan(Real)                                               *
    * DATE: 23/05/2019                                                 *
    ********************************************************************/
   public double wingSpanInput(String prompt) throws InputMismatchException
   {
       DecimalFormat df = new DecimalFormat("0.00");
       System.out.println(prompt);
       Scanner sc = new Scanner(System.in);
       double scan;

       do
       {
           try
           {
               scan = sc.nextDouble();
               // if input is not between 2.20 and 25.6 for another input
               if(scan < 2.20 || scan > 25.6)
               {
                    System.out.println("ERROR: Invalid wingspan, must be between 2.20 and 25.6\n" +prompt);
                    scan = 0.0;
               }
           }
           // if input has a character then prompt for another input
           catch (InputMismatchException e)
           {
               String flush = sc.nextLine();
               System.out.println("ERROR: Invalid character detected\n"+prompt);
               scan = 0.0;
           }
       } while (scan == 0);
         return scan;
   }

   /********************************************************************
    * SUBMODULE: stringInput                                           *
    * PURPOSE: Outputs a prompt and scans a user defined integer       *
    * IMPORT: prompt(String)                                           *
    * EXPORT: scan(String)                                             *
    * DATE: 06/05/2019                                                 *
    ********************************************************************/
   public static String stringInput(String prompt)
   {
       Scanner sc = new Scanner(System.in);
       String scan;
       System.out.println(prompt);
       scan = sc.nextLine();
       return scan;
   }

   /********************************************************************
    * SUBMODULE: serialInput                                           *
    * PURPOSE: Outputs a prompt and scans a user defined integer       *
    * IMPORT: prompt(String)                                           *
    * EXPORT: scan(String)                                             *
    * DATE: 24/05/2019                                                 *
    ********************************************************************/

   public static String serialInput(String prompt)
   {
       Scanner sc = new Scanner(System.in);
       String scan = "invalid";
       int gate = 0;
       System.out.println(prompt);

       do
       {
           try
           {
               scan = sc.nextLine();
               boolean valid = scan.matches("[0-9{6,}]");
               double doubleScan = Double.parseDouble(scan);
               double doubleInt = ((doubleScan - (int)doubleScan) * 100);
               // if XXX is not between 100 and 300, and YYY is not between 1 and 999, then user will be prompted to input again
               if((doubleInt >= 1) && (doubleInt <= 999) && ((int)doubleScan >= 100) && ((int)doubleScan <= 300))
               {
                   gate = 1;
               }
               else
               {
                   System.out.println("ERROR: Invalid serial number, for XXX.YYY\n(XXX must be between 100 and 300 and YYY must be between 001 and 999)\n" +prompt);
                   gate = 0;
               }
           }
           catch (IllegalArgumentException e)
           {
               System.out.println("ERROR: Invalid input detected\n" +prompt);
               gate = 0;
           }
       } while (gate == 0);
         return scan;
   }

   /********************************************************************
    * SUBMODULE: fuelInput                                           *
    * PURPOSE: Outputs a prompt and scans a user defined integer       *
    * IMPORT: prompt(String)                                           *
    * EXPORT: scan(String)                                             *
    * DATE: 24/05/2019                                                 *
    ********************************************************************/

   public static String fuelInput(String prompt)
   {
       Scanner sc = new Scanner(System.in);
       String scan = "invalid";
       int valid = 0;
       System.out.println(prompt);

       do
       {
           try
           {
               scan = sc.nextLine();
               String scanCaps = scan.toUpperCase();
               // if input is bio, diesel or battery the loop will end
               if((scanCaps.equals("BIO")) || (scanCaps.equals("DIESEL")) || (scanCaps.equals("BATTERY")))
               {
                   valid = 1;
               }
               // if input is not bio, diesel or battery the user will be prompted to input again
               else
               {
                   System.out.println("ERROR: Invalid fuel type, must be bio, diesel or battery\n" +prompt);
                   valid = 0;
               }
           }
           // if special characters or numbers are input prompt to input again
           catch (IllegalArgumentException e)
           {
               System.out.println("ERROR: Invalid input detected\n" +prompt);
               valid = 0;
           }
       } while (valid == 0);
         return scan;
   }

   /********************************************************************
    * SUBMODULE: hullInput                                             *
    * PURPOSE: Outputs a prompt and scans a user defined string        *
    * IMPORT: prompt(String)                                           *
    * EXPORT: scan(String)                                             *
    * DATE: 24/05/2019                                                 *
    ********************************************************************/

   public static String hullInput(String prompt)
   {
       Scanner sc = new Scanner(System.in);
       String scan = "invalid";
       int valid = 0;
       System.out.println(prompt);

       do
       {
           try
           {
               scan = sc.nextLine();
               String scanCaps = scan.toUpperCase();
               // if input is steel, alloy or titanium the loop will end
               if((scanCaps.equals("STEEL")) || (scanCaps.equals("ALLOY")) || (scanCaps.equals("TITANIUM")))
               {
                   valid = 1;
               }
               // if input is not steel, alloy or titanium the user will be prompted to input again
               else
               {
                   System.out.println("ERROR: Invalid hull type, must be steel, alloy or titanium\n" +prompt);
                   valid = 0;
               }
           }
           // if special characters or numbers are input prompt to input again
           catch (IllegalArgumentException e)
           {
               System.out.println("ERROR: Invalid input detected\n" +prompt);
               valid = 0;
           }
       } while (valid == 0);
         return scan;
   }
}
