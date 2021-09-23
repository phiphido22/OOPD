import java.util.*;
public class Engine
{
    //Class constants.
    public static final String BATTERY = "BATTERY";
    public static final String DIESEL = "DIESEL";
    public static final String BIO = "BIO";

    //private class fields
    private String fuel;
    private int cylinder;

    /************************************************************
     *Default Constructor:
     *IMPORT: none
	   *EXPORT: address of new Engine object
     *ASSERTION: 200.595 commissioned in 1990, with a BIO hull and max depth of -450.0
     ************************************************************/
    public Engine()
    {
        //setting default constructor values
        cylinder = 16;
        fuel = "BIO";
    }

    /************************************************************
     *Alternate Constructor:
	 *IMPORT: inCylinder (integer), inFuel(String)
	 *EXPORT: address of new Engine object
	 *ASSERTION: Creates the object if the imports are valid and FAILS otherwise
     ************************************************************/
    public Engine(int inCylinder, String inFuel)
    {
        // if the copy of the cylinder&fuel is not valid then an exception will be thrown
        if ((validateCylinder(inCylinder)) && (validateFuel(inFuel)))
        {
            cylinder = inCylinder;
            fuel = inFuel;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Import Values/Unable to Create Engine");
        }
    }

    /************************************************************
     *Copy Constructor:
	 *IMPORT: inEngine (Engine)
	 *EXPORT: address of new Engine object
	 *ASSERTION: Creates an object with an identical object state as the import.
     ************************************************************/
    public Engine(Engine inEngine)
    {
        // creates copies of cylinder and fuel
	      cylinder = inEngine.getCylinder();
        fuel = inEngine.getFuel();
    }

    //MUTATORS
    /********************************************************************
     *SUBMODULE: setCylinder
	 *IMPORT: inCylinder (integer)
	 *EXPORT: none
	 *ASSERTION: sets the current year if valid and FAILS otherwise.
     *********************************************************************/
    public void setCylinder(int inCylinder)
    {
      // checks the validity of the imported cylinder beofre setting it as cylinder
	    if(validateCylinder(inCylinder))
        {
		    cylinder = inCylinder;
	    }
        else //otherwise throw
        {
            throw new IllegalArgumentException("Invalid Cylinder Value");
        }
    }

    /********************************************************************
     *SUBMODULE: setFuel
	 *IMPORT: inFuel (String)
	 *EXPORT: none
	 *ASSERTION: sets the fuel type if valid and FAILS otherwise.
     *********************************************************************/
    public void setFuel(String inFuel)
    {
      // checks the validity of the imported fuel value before setting it as fuel
	    if(validateFuel(inFuel))
        {
		    fuel = inFuel.toUpperCase();
	    }
        else //otherwise throw
        {
            throw new IllegalArgumentException("Invalid Fuel Type");
        }
    }

    //ACCESSORS
    public int getCylinder()
    {
	    return cylinder;
    }

    public String getFuel()
    {
	    return fuel;
    }

    /********************************************************************
     *SUBMODULE: equals
	   *IMPORT: inObj (Object)
	   *EXPORT: same
	   *ASSERTION: Two submarines are interchangable if they have the same serialNum and year
     *********************************************************************/
    public boolean equals(int inCylinder, String inFuel)
    {
        boolean same = false;
        // if cylinder is equal to imported cylinder, and fuel is equal to imported fuel then same is valid
        same = ((cylinder == inCylinder) && (fuel == inFuel));
 	      return same;
    }

    public boolean equals(Object inObj)
    {
        boolean same = false;
        // if cylinder is equal to imported cylinder, and fuel is equal to imported fuel then same is valid
        if(inObj instanceof Engine)
        {
            Engine inEngine = (Engine)inObj;
            same = (cylinder == inEngine.getCylinder()) && (fuel == inEngine.getFuel());
        }
	    return same;
    }

    public Engine clone()
    {
        Engine cloneEngine;

        cloneEngine = new Engine(cylinder, fuel);

        return cloneEngine;
    }

    public String toString()
    {
        // expected: its engine has 16 cylinders and runs on bio fuel
        return ("its engine has " +cylinder+ " cylinders and runs on " +fuel+ " fuel.");
    }

    // for print/output formatting
    public String toStringEngineOutput()
    {
        // expected: 16,bio
        return (cylinder+","+fuel);
    }

    //PRIVATE SUBMODULES:
    /*************************************************************************
     *SUBMODULE: validateCylinder
    *IMPORT: inCylinder (integer)
    *EXPORT: valid (boolean)
    *ASSERTION: between 2 and 20 cylinders, must be positive
    ************************************************************************/
    private boolean validateCylinder(int inCylinder)
    {
        return ((inCylinder >= 2) && (inCylinder <= 20));
    }

    /*************************************************************************
     *SUBMODULE: validateFuel
	 *IMPORT: inFuel (String)
	 *EXPORT: valid (boolean)
	 *ASSERTION: fuel type must be BATTERY, DIESEL or BIO
    ************************************************************************/
    private boolean validateFuel(String inFuel)
    {
        String valid = inFuel.toUpperCase();
        return ((valid.equals(BATTERY)) || (valid.equals(DIESEL)) || (valid.equals(BIO)));
    }
}
