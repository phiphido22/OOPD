/*************************************************************************
 *  Author: Phi Do                                                       *
 *  Purpose: Contains class fields and methods for the ShipStorage class *
 *  Date: 05/05/2019                                                     *
 *  Last Updated: 24/05/2019                                             *
 *************************************************************************/
import java.util.*;
import java.text.DecimalFormat;

public final class ShipStorage
{
    //CLASS CONSTANTS:
    public static final int MAXARRAY = 30;

    //CLASS FIELDS:
    private Submarine submarine;
    private FighterJet fighterjet;
    private Ship shipArray[];
    private int currentShipCount;

    /********************************************************************
     * DEFAULT CONSTRUCTOR                                              *
     * IMPORT: none                                                     *
     * EXPORT: address of new ShipStorage object                        *
     * DATE: 08/05/2019                                                 *
     ********************************************************************/
    public ShipStorage()
    {
        shipArray = new Ship[MAXARRAY];
        currentShipCount = 0;
    }

    /********************************************************************
     * ALTERNATE CONSTRUCTOR                                            *
     * IMPORT: inShipArray(ARRAY of Ship), shipArray(ARRAY of Ship),    *
               inCurrentShipCount (Integer)                             *
     * EXPORT: address of new ShipStorage object                        *
     * DATE: 08/05/2019                                                 *
     ********************************************************************/
    public ShipStorage(Ship[] shipArray, Ship[] inShipArray, int inCurrentShipCount)
    {
        // if the length of the arrays are the same and the currentchipcount is valid
        if(shipArray.length == inShipArray.length && validateCurrentShipCount(inCurrentShipCount))
        {
            for(int i = 0; i < MAXARRAY; i++)
            {
                shipArray[i] = inShipArray[i];
            }
            currentShipCount = inCurrentShipCount;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Storage Volume");
        }
    }

    /********************************************************************
     * COPY CONSTRUCTOR                                                 *
     * IMPORT: inShipStorage(ShipStorage)                               *
     * EXPORT: Address of new ShipStorage object                        *
     * DATE: 22/05/2019                                                 *
     ********************************************************************/
    public ShipStorage(ShipStorage inShipStorage)
    {
       // creating a new copy of the shipArray
       shipArray = new Ship[MAXARRAY];
       Ship[] inShipArray = inShipStorage.getShipArray();
       for(int i = 0 ; i < MAXARRAY ; i++)
       {
           shipArray[i] = inShipArray[i];
       }
       // getting the current ship count
       currentShipCount = getCurrentShipCount();
    }

    /********************************************************************
     * SUBMODULE: addShip                                               *
     * IMPORT: inShip (Ship)                                            *
     * EXPORT: none                                                     *
     * DATE: 22/05/2019                                                 *
     ********************************************************************/
    public void addShipToArray(Ship inShip)
    {
        int exit = 0;
        int i = 0;

        // while loop where it will not end until an empty spot inth eshipArray is detected
        while(exit == 0)
        {
            // if the shipArray is empty then it will fill that slot with the imported ship value
            if(shipArray[i] == null)
            {
                shipArray[i] = inShip;
                // exit = 1 makes the loop end after the current change to shipArray
                exit = 1;
                // increasing shipCount to keep track of how many ships are in the array
                currentShipCount = currentShipCount + 1;
            }
            i++;
        }
    }

    /********************************************************************
      * SUBMODULE: viewShips                                            *
      * IMPORT: none                                                    *
      * EXPORT: none                                                    *
      * DATE: 22/05/2019                                                *
      ********************************************************************/
      public void viewShips()
      {
          String output;

          // for loop until there is an empty cell in shipArray
          for(int i = 0; shipArray[i] != null; i++)
          {
              output = shipArray[i].toString();
              System.out.println(output+"\n");
          }
      }

     /********************************************************************
      * SUBMODULE: findDuplicates                                        *
      * IMPORT: none                                                     *
      * EXPORT: none                                                     *
      * DATE: 22/05/2019                                                 *
      ********************************************************************/
      public void findDuplicates()
      {
          String output = "";
          Ship[] shipArray = getShipArray();

          // for loop until the shipArray length is reached, and the shipArray cell is null
          for(int i = 0; i < shipArray.length && shipArray[i] != null; i++)
          {
              // for loop until the shipArray length is reached and the shipArray cell is not null
              for(int j = i+1; j < shipArray.length && shipArray[j] != null; j++)
              {
                  // if the shipArray[i] is equal to shipArray[j] then it will be put into the output cache
                  if(shipArray[i].equals(shipArray[j]) && i!=j)
                  {
                      output = shipArray[i].toString();
                  }
              }
          }
          // print the output cache of duplicate ships
          System.out.println("Duplicate Ship Detected: "+output);
      }

    public void destinationCheck(int distance)
    {
        Submarine submarine = new Submarine();
        FighterJet fighterjet = new FighterJet();
        String fastestSub = submarine.calcTravelSub(shipArray, distance);
        String fastestJet = fighterjet.calcTravelJet(shipArray, distance);

        System.out.println(fastestSub+"\n"+fastestJet);
    }

    //PUBLIC SUBMODULES:
    //MUTATORS:
    /********************************************************************
     * SUBMODULE: setShipArray                                          *
     * IMPORT: inShipArray(ARRAY OF Ship)                               *
     * EXPORT: none                                                     *
     * DATE: 22/05/2019                                                 *
     ********************************************************************/
    public void setShipArray(Ship[] inShipArray)
    {
       shipArray = inShipArray;
    }

    /********************************************************************
     * SUBMODULE: setCurrentShipCount                                   *
     * IMPORT: inCurrentShipCount (Integer)                             *
     * EXPORT: none                                                     *
     * DATE: 22/05/2019                                                 *
     ********************************************************************/
    public void setCurrentShipCount(int inCurrentShipCount)
    {
        if(validateCurrentShipCount(inCurrentShipCount))
        {
            currentShipCount = inCurrentShipCount;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Storage Volume");
        }
    }

    //ACCESSORS:
    /********************************************************************
     * SUBMODULE: getShipArray                                          *
     * IMPORT: none                                                     *
     * EXPORT: shipArray(ARRAY OF Ship)                                 *
     * DATE: 22/05/2019                                                 *
     ********************************************************************/
    public Ship[] getShipArray()
    {
        return shipArray;
    }

    /********************************************************************
     * SUBMODULE: getCurrentShipCount                                   *
     * IMPORT: none                                                     *
     * EXPORT: currentShipCount(integer)                                *
     * DATE: 22/05/2019                                                 *
     ********************************************************************/
    public int getCurrentShipCount()
    {
        return currentShipCount;
    }

    /********************************************************************
     * SUBMODULE: equals                                                *
     * IMPORT: inObj (Object)                                           *
     * EXPORT: same (Boolean)                                           *
     * DATE: 22/05/2019                                                 *
     ********************************************************************/
    public boolean equals(Object inObj)
    {
        boolean same = true;
        int count =  1;
        // if the object is the same as shipstorage
        if(inObj instanceof ShipStorage)
        {
            ShipStorage inShipStorage = (ShipStorage)inObj;
            Ship[] inShipArray = inShipStorage.getShipArray();
            // same is true if shipArray and CurrentShipCount are validated
            same = (validateShipArray(inShipArray) && currentShipCount == getCurrentShipCount());
        }
        return same;
    }

    //PRIVATE SUBMODULES:
    /********************************************************************
     * SUBMODULE: validateShipArray                                     *
     * IMPORT: inShipArray (ARRAY OF Ship)                              *
     * EXPORT: valid (boolean)                                          *
     * DATE: 22/05/2019                                                 *
     ********************************************************************/
    private boolean validateShipArray(Ship[] inShipArray)
    {
        boolean equal = true;
        int count;
        // if the lengths of shipArray and inShipArray are not equal then the boolean is false
        if(shipArray.length != inShipArray.length)
        {
            equal = false;
        }
        else
        {
            count = 0;
            do
            {
                // it is equal if the current shipArray equals the current inShipArray
                equal = shipArray[count].equals(inShipArray[count]);
                count = count + 1;
            }while(equal && count < shipArray.length);
        }
        return equal;
    }

    /********************************************************************
     * SUBMODULE: validateCurrentShipCount                              *
     * IMPORT: inCurrentShipCount(Integer)                              *
     * EXPORT: valid (boolean)                                          *
     * DATE: 22/05/2019                                                 *
     ********************************************************************/
    private boolean validateCurrentShipCount(int inCurrentShipCount)
    {
        return(inCurrentShipCount >= 0 && inCurrentShipCount >= currentShipCount);
    }
}
