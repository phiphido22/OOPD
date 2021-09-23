import java.util.*;
import java.text.DecimalFormat;
// Submarine is a type of Ship
public class Submarine extends Ship
{
    //Class constants.
    public static final String STEEL = "STEEL";
    public static final String ALLOY = "ALLOY";
    public static final String TITANIUM = "TITANIUM";

    //private class fields
    private double maxDepth;
    private String hull;

    /************************************************************
     *Default Constructor:
     *IMPORT: none
	   *EXPORT: address of new Submarine object
     *ASSERTION: 200.595 commissioned in 1990, with 16 cylinders and bio fuel, with a titanium hull and max depth of -450.0
     ************************************************************/
    public Submarine()
    {
        //setting default constructor values
        super();
        hull = "TITANIUM";
        maxDepth = -450.0;
    }

    /************************************************************
     *Alternate Constructor:
	 *IMPORT: inSerNum (String), inYear(integer), inEngine (Engine), inHull(String), inDepth(real)
	 *EXPORT: address of new Submarine object
	 *ASSERTION: Creates the object if the imports are valid and FAILS otherwise
     ************************************************************/
    public Submarine(String inSerNum, int inYear, Engine inEngine, String inHull, double inDepth)
    {
        // sending imported values into super alternate constructor
        super(inSerNum, inYear, inEngine);
        // if the copy of the hull&depth is not valid then an exception will be thrown
        if ((validateHull(inHull)) && (validateDepth(inDepth)))
        {
            hull = inHull;
            maxDepth = inDepth;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Import Values");
        }
    }

    /************************************************************
     *Copy Constructor:
	 *IMPORT: inSub (Submarine)
	 *EXPORT: address of new Submarine object
	 *ASSERTION: Creates an object with an identical object state as the import.
     ************************************************************/
    public Submarine(Submarine inSub)
    {
        // creates copies of hull, maxDepth, and super copy constructor is called
        super(inSub);
        hull = inSub.getHull();
		    maxDepth = inSub.getDepth();
    }

    //MUTATORS
    /********************************************************************
     *SUBMODULE: setHull
	 *IMPORT: inHull (String)
	 *EXPORT: none
	 *ASSERTION: sets the hull type if valid and FAILS otherwise.
     *********************************************************************/
    public void setHull(String inHull)
    {
      // checks the validity of the imported hull before setting it as hull
	    if(validateHull(inHull))
        {
		        hull = inHull.toUpperCase();
	      }
        else //otherwise throw
        {
            throw new IllegalArgumentException("Invalid Hull Type");
        }
    }

    /************************************************************
     *SUBMODULE: setDepth
    *IMPORT: inDepth (real)
    *EXPORT: none
    *ASSERTION: sets the maxDepth to inDepth.
     ************************************************************/
    public void setDepth(double inDepth)
    {
      // checks the validity of the imported depth before setting it as depth
      if(validateDepth(inDepth))
        {
            maxDepth = inDepth;
        }
        else //otherwise throw
        {
            throw new IllegalArgumentException("Invalid Max Depth Value");
        }
    }

    //ACCESSORS
    public String getHull()
    {
	    return hull;
    }

    public double getDepth()
    {
	    return maxDepth;
    }

    /********************************************************************
     *SUBMODULE: equals
	 *IMPORT: inObj (Object)
	 *EXPORT: same
	 *ASSERTION: Two submarines are interchangable if they have the same serialNum and year
     *********************************************************************/
    public boolean equals(Object inObj)
    {
        boolean same = false;
        Submarine inSub;
        if(inObj instanceof Submarine)
        {
            inSub = (Submarine)inObj;
            // if the imported submarine is the same as the superclass
            if(super.equals(inSub))
              // if the imported hull is the same as the hull
              if(hull.equals(inSub.getHull()))
                // if the imported depth is the same as the depth
                if(maxDepth == (inSub.getDepth()))
                  same = true;
        }
	      return same;
    }

    public Submarine clone()
    {
        return new Submarine(this);
    }

    public String toString()
    {
        String outStr;
        // expected: ... It is a submarine with a steel hull and a max depth of -500.0 meters
        outStr = super.toString()+ " It is a submarine with a "+hull+" hull and a max depth of "+maxDepth+ " meters.";
        return (outStr);
    }

    // for print/output formatting
    public String toStringOutput()
    {
        // expected: S,...,steel,-500.0
        return ("S,"+super.toStringOutput()+","+hull+","+maxDepth);
    }

    public String calcTravelSub(Ship[] shipArray, int distance)
    {
        Engine engineGet;
        int cylinder;

        // initialising the variables to random/high values
        String serialNum = "<NO SUBMARINES IN STORAGE>";
        String serialNumFinal = "<NO SUBMARINES IN STORAGE>";
        double timeTaken = 1000;
        double shortestTimeTakenSub = 1000000000;

        DecimalFormat df = new DecimalFormat("0.00");

        // for loop checking the shipArray until it detects an empty array slot
        for(int i = 0; shipArray[i] != null; i++)
        {
            // if the current ship array is a submarine
            if (shipArray[i] instanceof Submarine)
            {
                // getting engine of the shipArray[i]
                engineGet = super.getEngine();
                // getting the cylinder from the engineGet
                cylinder = engineGet.getCylinder();
                maxDepth = getDepth();
                serialNum = shipArray[i].getSerNum();
                // given equation to calculate the timeTaken
                timeTaken = (((double)distance / (double)cylinder) * (1.0 / (10.0 + (maxDepth * (-1.0)))));
                // if the time taken is less than the shortest time taken (always initially as it is set to 1000000000)
                if (timeTaken < shortestTimeTakenSub)
                {
                    // sets the new shortestTimeTakenSub as the smaller timeTaken value
                    shortestTimeTakenSub = timeTaken;
                    // sets the serial number as the one of the faster submarine
                    serialNumFinal = serialNum;
                }
                else
                {
                    // leaves the variables as it is
                    shortestTimeTakenSub = shortestTimeTakenSub;
                    serialNumFinal = serialNumFinal;
                }
            }
        }
        String fastestSub = "The fastest submarine "+serialNumFinal+ " travels the distance with a time of "+df.format(shortestTimeTakenSub)+" hours";
        return fastestSub;
    }

    //PRIVATE SUBMODULES:
    /*************************************************************************
     *SUBMODULE: validateHull
	 *IMPORT: inHull (String)
	 *EXPORT: valid (boolean)
	 *ASSERTION: hull must be STEEL, ALLOY or TITANIUM
    ************************************************************************/
    private boolean validateHull(String inHull)
    {
        String valid = inHull.toUpperCase();
        return ((valid.equals(STEEL)) || (valid.equals(ALLOY)) || (valid.equals(TITANIUM)));
    }

    /*************************************************************************
     *SUBMODULE: validateDepth
    *IMPORT: inDepth (real)
    *EXPORT: valid (boolean)
    *ASSERTION: maxDepth is between -500.0 and 0
    ************************************************************************/
    private boolean validateDepth(double inDepth)
    {
        return ((inDepth >= -500.0) && (inDepth <= 0.0));
    }
}
