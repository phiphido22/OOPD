/*************************************************************************
 *  Author: Phi Do                                                       *
 *  Purpose: Contains all the submodules required for input and output of*
 *           information to and from the user, files or storage          *
 *  Date: 07/05/2019                                                     *
 *  Last Updated: 22/05/2019                                             *
 *************************************************************************/
import java.util.*;
import java.io.*;

public final class FileManager
  {
      /********************************************************************
       * SUBMODULE: readStorage                                           *
       * PURPOSE: Prompts for a file to read from, then reads in storage  *
       *          values from the file and constructs and stores any pre- *
       *          defined food                                            *
       * IMPORT: shipStorage(ARRAY OF ShipStorage)                        *
       * EXPORT: none                                                     *
       * DATE: 22/05/2019                                                 *
       ********************************************************************/
      public static void readStorage(ShipStorage shipStorage)
      {
          String fileName;
          FileInputStream fileStrm = null;
          InputStreamReader rdr;
          BufferedReader bufRdr;
          String line;

          Scanner sc = new Scanner(System.in);

          try
          {
              System.out.println("What file would you like to read from?");
              // user input for name of file to import
              fileName = sc.nextLine();
              //creating new file input stream
              fileStrm = new FileInputStream(fileName);
              // creating new input stream reader
              rdr = new InputStreamReader(fileStrm);
              //creating new buffered reader
              bufRdr = new BufferedReader(rdr);
              // lineArray to read and parse contects of file to library
              String[] lineArray;

              line = bufRdr.readLine();
              //while the line is not empty, split it by the ","
              while(line != null)
              {
              lineArray = line.split(",");

              // if the length of the array is not 7, the file is not valid
              if(lineArray.length != 7)
              {
                  throw new IllegalArgumentException("Invalid File");
              }
              // if the length of the value in the array [0] is not 1, then the file is not valid
              if(lineArray[0].length() != 1)
              {
                  throw new IllegalArgumentException("Invalid File");
              }

              // separates the values and imports them into individual array slots depending on their values
              char type = lineArray[0].charAt(0);
              String serialNum = lineArray[1];
              int year = Integer.parseInt(lineArray[2]);
              int cylinder = Integer.parseInt(lineArray[3]);
              String fuel = lineArray[4];

              // creates an engine with the imported cylinder and fuel
              Engine engine = new Engine(cylinder, fuel);

              switch (type) {
                // differentiates betewen whether the imported ship is a submarine or fighterjet
                case 'S': case 's':
                    String hull = lineArray[5];
                    double maxDepth = Double.parseDouble(lineArray[6]);

                    // creates a new submarine using the imported values
                    Ship inShip = new Submarine(serialNum, year, engine, hull, maxDepth);

                    // adds the created ship to the library/array
                    shipStorage.addShipToArray(inShip);
                    break;
                case 'F': case 'f':
                    double wingSpan = Double.parseDouble(lineArray[5]);
                    String ordanance = lineArray[6];

                    // creates a new fighterjet using the imported values
                    inShip = new FighterJet(serialNum, year, engine, wingSpan, ordanance);

                    // adds the created ship to the library/array
                    shipStorage.addShipToArray(inShip);
                    break;
                }
                line = bufRdr.readLine();
              }
           }
           catch(IOException e)
           {
              if(fileStrm != null)
              {
                  try
                  {
                        fileStrm.close();
                  }
                  catch(IOException ex2){}
              }
              // outputs the specific error with the importing of file
              System.out.println("Error in file processing: " + e.getMessage());
           }
           catch(IllegalArgumentException e)
           {
              if(fileStrm != null)
              {
                  try
                  {
                        fileStrm.close();
                  }
                  catch(IOException ex2){}
              }
              // outputs the specific error with the importing of file
              System.out.println("Error in file processing: " + e.getMessage());
              System.out.println("Returning to menu");
           }
       }
      /********************************************************************
       * SUBMODULE: printStorage                                          *
       * PURPOSE: Prompts for a file to write to, then writes remaining   *
       *          storage space to the file as well as all food stored to *
       *          the file                                                *
       * IMPORT: shipStorage(ARRAY OF ShipStorage)                        *
       * EXPORT: none                                                     *
       * DATE: 22/05/2019                                                 *
       ********************************************************************/
      public static void printStorage(ShipStorage shipStorage)
      {
          String fileOutName;
          FileOutputStream fileStrm = null;
          PrintWriter pw;

          Scanner sc = new Scanner(System.in);

          try
          {
              System.out.println("Enter name of file to be printed to:");
              fileOutName = sc.nextLine();
              // creates new file output stream
              fileStrm = new FileOutputStream(fileOutName);
              // creates new printwriter
              pw = new PrintWriter(fileStrm);

              Ship shipArray[] = shipStorage.getShipArray();
              // for loop until the currentshipcount is reached
              for(int i = 0; i < shipStorage.getCurrentShipCount(); i++)
                // if the shipArray[i] is a submarine or a fighterjet
                if(shipArray[i] instanceof Submarine)
                {
                    pw.println(shipArray[i].toStringOutput());
                }
                else if(shipArray[i] instanceof FighterJet)
                {
                    pw.println(shipArray[i].toStringOutput());
                }

              pw.close();
          }
          catch(IOException e)
          {
              if(fileStrm != null)
              {
                  try
                  {
                        fileStrm.close();
                  }
                  catch(IOException ex2){}
              }
              System.out.println("Error in file processing: " + e.getMessage());
          }
      }
    }
