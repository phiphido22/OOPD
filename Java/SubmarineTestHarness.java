import java.util.*;

public class SubmarineTestHarness
{
    public static void main(String[] args)
    {
        try
        {
            Engine engine = new Engine();
            Submarine[] submarine = new Submarine[4];

            //object creation
            submarine[0] = new Submarine();
            submarine[1] = new Submarine("100.555", 1998, engine, "STEEL", -200.0);
            submarine[2] = new Submarine(submarine[1]);
            submarine[3] = submarine[1].clone();

            //print out created objects
            System.out.println("CONSTRUCTOR TESTS:");
            for(int i = 0; i < submarine.length; i++)
            {
                System.out.println("Submarine[" + i + "]: " + submarine[i].toString());
            }

            //equals method
            System.out.println("\nEQUALS METHOD TESTS:");
            System.out.println("Equals (object) expected TRUE: " + submarine[1].equals(submarine[3]));
            System.out.println("Equals (object) expected FALSE: " + submarine[0].equals(submarine[3]));

            //getters and setters
            System.out.println("\nGETTERS AND SETTERS:");
            submarine[0].setSerNum(submarine[1].getSerNum());
            System.out.println(submarine[0].getSerNum() + " = " + submarine[1].getSerNum());

            submarine[0].setYear(submarine[2].getYear());
            System.out.println(submarine[0].getYear() + " = " + submarine[2].getYear());

            submarine[0].setEngine(submarine[1].getEngine());
            System.out.println(submarine[0].getEngine() + " = " + submarine[1].getEngine());

            submarine[0].setHull(submarine[1].getHull());
            System.out.println(submarine[0].getHull() + " = " + submarine[1].getHull());

            submarine[0].setDepth(submarine[1].getDepth());
            System.out.println(submarine[0].getDepth() + " = " + submarine[1].getDepth());

        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
