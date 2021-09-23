import java.util.*;

public class EngineTestHarness
{
    public static void main(String[] args)
    {
        try
        {
            Engine[] engine = new Engine[4];

            //object creation
            engine[0] = new Engine();
            engine[1] = new Engine(20, "DIESEL");
            engine[2] = new Engine(engine[1]);
            engine[3] = engine[1].clone();

            //print out created objects
            System.out.println("CONSTRUCTOR TESTS:");
            for(int i = 0; i < engine.length; i++)
            {
                System.out.println("Engine[" + i + "]: " + engine[i].toString());
            }

            //equals method
            System.out.println("\nEQUALS METHOD TESTS:");
            System.out.println("Equals (object) expected TRUE: " + engine[1].equals(engine[3]));
            System.out.println("Equals (object) expected FALSE: " + engine[0].equals(engine[3]));

            //getters and setters
            System.out.println("\nGETTERS AND SETTERS:");
            engine[0].setCylinder(engine[1].getCylinder());
            System.out.println(engine[0].getCylinder() + " = " + engine[1].getCylinder());

            engine[0].setFuel(engine[2].getFuel());
            System.out.println(engine[0].getFuel() + " = " + engine[2].getFuel());

        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
