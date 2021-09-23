import java.util.*;

public abstract class Ship
{
    //private class fields
    private int year;
    private String serialNum;
    private Engine engine;

    /************************************************************
     *Default Constructor:
     *IMPORT: none
     *EXPORT: address of new Ship object
     *ASSERTION: 200.595 commissioned in 1990 running on bio fuel and 16 cylinders
     ************************************************************/
    public Ship()
    {
        //setting default constructor values
        serialNum = "200.595";
        year = 1990;
        engine = new Engine();
    }

    /************************************************************
     *Alternate Constructor:
	   *IMPORT: inSerNum (String), inYear(integer), inEngine(Engine)
	   *EXPORT: address of new Ship object
	   *ASSERTION: Creates the object if the imports are valid and FAILS otherwise
     ************************************************************/
    public Ship(String inSerNum, int inYear, Engine inEngine)
    {
        // if the copy of the serial number is not valid then an exception will be thrown
        if(!(validateSerNum(inSerNum)))
        {
            throw new IllegalArgumentException("Invalid Serial Number");
        }
        // if the copy of the year is not valid then an exception will be thrown
        if(!(validateYear(inYear)))
        {
            throw new IllegalArgumentException("Invalid Commission Year");
        }
        // if the copy of the engine does not exist, then an exception will be thrown
        if(inEngine == null)
        {
            throw new IllegalArgumentException("Invalid Engine");
        }

        //creating copies of variables in Ship
        serialNum = new String(inSerNum);
        year = inYear;
        engine = inEngine.clone();
    }

    /************************************************************
    *Copy Constructor:
	  *IMPORT: inShip (Ship)
	  *EXPORT: address of new Ship object
	  *ASSERTION: Creates an object with an identical object state as the import.
     ************************************************************/
    public Ship(Ship inShip)
    {
        // creating copies of serial number, year and engine
        serialNum = inShip.getSerNum();
        year = inShip.getYear();
        engine = inShip.getEngine();
    }

    /********************************************************************
    *SUBMODULE: equals
    *IMPORT: inObj (Object)
    *EXPORT: same
    *ASSERTION: Two Ships are interchangable if they have the same serialNum, year and engine
     *********************************************************************/
    public boolean equals(Object inObject)
    {
        // checking if there is two of the same ship by comparing variables
        Ship inShip;
        boolean isEqual = false;
        if(inObject instanceof Ship)
        {
            inShip = (Ship)inObject;
              // if the serial number of the ship is the same as the inShip's
              if(serialNum.equals(inShip.getSerNum()))
                // if the year of commission is the same as the inShip's
                if(year == getYear())
                // if the engine is the same as the inShip's engine
                  if(engine.equals(inShip.getEngine()))
                    isEqual = true;
        }
        return isEqual;
    }

    public String toString()
    {
        String outStr = "";
        // expected: The ship 198.465 was commissioned in 1999, with 16 cylinders and runs on bio fuel
        outStr = "The ship "+serialNum+" was commissioned in "+year+", "+engine.toString();
        return outStr;
    }

    // for print/output formatting
    public String toStringOutput()
    {
        String outStr = "";
        // expected: 198.465,1985,16,bio
        outStr = serialNum+","+year+"," +engine.toStringEngineOutput();
        return outStr;
    }

    public abstract Ship clone();
    // no body to clone due to being an abstract method, only needs to be called from Submarine or FighterJet

    //MUTATORS
    /************************************************************
     *SUBMODULE: setSerNum
	 *IMPORT: inSerNum (String)
	 *EXPORT: none
	 *ASSERTION: sets the serialNum to inSerNum.
     ************************************************************/
    public void setSerNum(String inSerNum)
    {
        // checks the validity of the imported serialNum before setting it as serialNum
        if(validateSerNum(inSerNum))
        {
            serialNum = inSerNum;
        }
        else //otherwise throw
        {
            throw new IllegalArgumentException("Invalid Serial Number");
        }
    }

    /********************************************************************
     *SUBMODULE: setYear
	 *IMPORT: inYear (integer)
	 *EXPORT: none
	 *ASSERTION: sets the current year if valid and FAILS otherwise.
     *********************************************************************/
    public void setYear(int inYear)
    {
        // checks the validity of the imported year before setting it as year
        if(validateYear(inYear))
        {
		        year = inYear;
	      }
        else //otherwise throw
        {
            throw new IllegalArgumentException("Invalid Year");
        }
    }

    public void setEngine(Engine inEngine)
    {
        if(inEngine == null)  //otherwise throw
        {
            throw new IllegalArgumentException("Invalid Engine");
        }
        engine = new Engine(inEngine); //import engine as engine
    }

    //ACCESSORS
    public String getSerNum()
    {
	     return serialNum;
    }

    public int getYear()
    {
	     return year;
    }

    public Engine getEngine()
    {
        return new Engine(engine);
    }

    //VALIDATIONS
    private boolean validateSerNum(String inSerNum)
    {
        //checks to make sure inSerNum has 6 digits and all digits are between 1 and 9
        boolean valid = inSerNum.matches("[0-9{6,}]");
        // converts the String into a Double value
        double inSerDouble = Double.parseDouble(inSerNum);
        // converts the double into an int and subtracts the original double by the int to get the latter 3 digits
        double inSerInt = ((inSerDouble - (int)inSerDouble) * 100);
        // if XXX is not between 100 and 300, and YYY is not between 1 and 999, then the serial number is not valid
        return ((inSerInt >= 1) && (inSerInt <= 999) && ((int)inSerDouble >= 100) && ((int)inSerDouble <= 300));
    }

    private boolean validateYear(int inYear)
    {
        // if the imported year is not between 1950 and 2022 then the year will not be returned
        return ((inYear >= 1950) && (inYear <= 2022));
    }
}
