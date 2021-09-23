import java.util.*;
import java.text.DecimalFormat;
// Fighterjet is a type of Ship
public class FighterJet extends Ship
{
    //private class fields
    private double wingSpan;
    private String ordanance;

    /************************************************************
     *Default Constructor:
     *IMPORT: none
	   *EXPORT: address of new FighterJet object
     *ASSERTION: equipped with 10 homing missiles and a wing span of 2.25
     ************************************************************/
    public FighterJet()
    {
        //setting default constructor values
        super();
        ordanance = "10 homing missiles";
        wingSpan = 2.25;
    }

    /************************************************************
     *Alternate Constructor:
	   *IMPORT: inSerNum (String), inYear(integer), inEngine (Engine), inOrdanance(String), inWingSpan(real)
	   *EXPORT: address of new FighterJet object
	   *ASSERTION: Creates the object if the imports are valid and FAILS otherwise
     ************************************************************/
    public FighterJet(String inSerNum, int inYear, Engine inEngine, double inWingSpan, String inOrdanance)
    {
        // sending imported values into super alternate constructor
        super(inSerNum, inYear, inEngine);
        // if the copy of the wingspan&ordanance is not valid then an exception will be thrown
        if ((validateWingSpan(inWingSpan)) && (validateOrdanance(inOrdanance)))
        {
            wingSpan = inWingSpan;
            ordanance = inOrdanance;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Import Values");
        }
    }

    /************************************************************
     *Copy Constructor:
	 *IMPORT: inJet (FighterJet)
	 *EXPORT: address of new FighterJet object
	 *ASSERTION: Creates an object with an identical object state as the import.
     ************************************************************/
    public FighterJet(FighterJet inJet)
    {
        // creates copies of wingspan, ordanance, and super copy constructor is called
        super(inJet);
        wingSpan = inJet.getWingSpan();
        ordanance = inJet.getOrdanance();
    }

    //MUTATORS
    /********************************************************************
     *SUBMODULE: setOrdanance
	 *IMPORT: inOrdanance (String)
	 *EXPORT: none
	 *ASSERTION: sets the ordanance if valid and FAILS otherwise.
     *********************************************************************/
    public void setOrdanance(String inOrdanance)
    {
      // checks the validity of the imported ordanance before setting it as ordanance
	    if(validateOrdanance(inOrdanance))
        {
		    ordanance = inOrdanance;
	    }
        else//otherwise throw
        {
            throw new IllegalArgumentException("Invalid Ordanance");
        }
    }

    /********************************************************************
     *SUBMODULE: setWingSpan
	 *IMPORT: inWingSpan (real)
	 *EXPORT: none
	 *ASSERTION: sets the wingSpan.
     *********************************************************************/
    public void setWingSpan(double inWingSpan)
    {
      // checks the validity of the imported wingspan before setting it as wingspan
      if(validateWingSpan(inWingSpan))
        {
		    wingSpan = inWingSpan;
	    }
        else//otherwise throw
        {
            throw new IllegalArgumentException("Invalid Wingspan Value");
        }
    }

    //ACCESSORS
    public String getOrdanance()
    {
	    return ordanance;
    }

    public double getWingSpan()
    {
	    return wingSpan;
    }

    /********************************************************************
     *SUBMODULE: equals
	 *IMPORT: inObj (Object)
	 *EXPORT: same
	 *ASSERTION: Two FighterJets are interchangable if they have the same serialNum and year
     *********************************************************************/
    public boolean equals(Object inObj)
    {
        boolean same = false;
        FighterJet inJet;
        if(inObj instanceof FighterJet)
        {
            inJet = (FighterJet)inObj;
            // if the imported fighterjet is the same as the superclass
            if(super.equals(inJet))
              // if the imported wingspan is the same as the wingspan
              if(wingSpan == (inJet.getWingSpan()))
                // if the imported ordanance is the same as the ordanance
                if(ordanance.equals(inJet.getOrdanance()))
                  same = true;
        }
	    return same;
    }

    public FighterJet clone()
    {
        return new FighterJet(this);
    }

    public String toString()
    {
        // expected: ... It is a fighter jet with a wingspan of 20.0 meters and equipped with 16 torpedoes
        return super.toString()+" It is a fighter jet with a wing span of "+
        wingSpan+" meters and equipped with "+ordanance+".";
    }

    // for print/output formatting
    public String toStringOutput()
    {
        // expected: F,...,20.0,16 torpedoes
        return ("F,"+super.toStringOutput()+","+wingSpan+","+ordanance);
    }

    public String calcTravelJet(Ship[] shipArray, int distance)
    {
        Engine engineGet;
        int cylinder;

        // initialising the variables to random/high values
        String serialNum = "<NO FIGHTERJETS IN STORAGE>";
        String serialNumFinal = "<NO FIGHTERJETS IN STORAGE>";
        double timeTaken = 1000;
        double shortestTimeTakenJet = 1000000000;

        DecimalFormat df = new DecimalFormat("0.00");

        // for loop checking the shipArray until it detects an empty array slot
        for(int i = 0; shipArray[i] != null; i++)
        {
            // if the current ship array is a fighterjet
            if (shipArray[i] instanceof FighterJet)
            {
                // getting engine of the shipArray[i]
                engineGet = super.getEngine();
                // getting the cylinder from the engineGet
                cylinder = engineGet.getCylinder();
                wingSpan = getWingSpan();
                serialNum = shipArray[i].getSerNum();
                // given equation to calculate the timeTaken
                timeTaken = (((double)distance) / ((wingSpan * (double)cylinder * 150.0)));
                // if the time taken is less than the shortest time taken (always initially as it is set to 1000000000)
                if (timeTaken < shortestTimeTakenJet)
                {
                    // sets the new shortestTimeTakenJet as the smaller timeTaken value
                    shortestTimeTakenJet = timeTaken;
                    // sets the serial number as the one of the faster fighterjet
                    serialNumFinal = serialNum;
                }
                else
                {
                    // leaves the variables as it is
                    shortestTimeTakenJet = shortestTimeTakenJet;
                    serialNumFinal = serialNumFinal;
                }
            }
        }
        String fastestJet = "The fastest fighterjet "+serialNumFinal+ " travels the distance with a time of "+df.format(shortestTimeTakenJet)+" hours";
        return fastestJet;
    }
    //PRIVATE SUBMODULES:
    /*************************************************************************
     *SUBMODULE: validateOrdanance
	 *IMPORT: inOrdanance (String)
	 *EXPORT: valid (boolean)
	 *ASSERTION: ordanance must be a non-empty String
    ************************************************************************/
    private boolean validateOrdanance(String inOrdanance)
    {
        return (inOrdanance != null);
    }

    /*************************************************************************
     *SUBMODULE: validateWingSpan
    *IMPORT: inWingSpan (real)
    *EXPORT: valid (boolean)
    *ASSERTION: wingSpan is between 2.20 and 25.6
    ************************************************************************/
    private boolean validateWingSpan(double inWingSpan)
    {
        return ((inWingSpan >= 2.20) && (inWingSpan <= 25.6));
    }
}
