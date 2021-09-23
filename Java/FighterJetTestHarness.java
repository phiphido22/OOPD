import java.util.*;

public class FighterJetTestHarness
{
    public static void main(String[] args)
    {
        try
        {
            Engine engine = new Engine();
            FighterJet[] fighterjet = new FighterJet[4];

            //object creation
            fighterjet[0] = new FighterJet();
            fighterjet[1] = new FighterJet("100.555", 1998, engine, 20.0, "5 torpedoes");
            fighterjet[2] = new FighterJet(fighterjet[1]);
            fighterjet[3] = fighterjet[1].clone();

            //print out created objects
            System.out.println("CONSTRUCTOR TESTS:");
            for(int i = 0; i < fighterjet.length; i++)
            {
                System.out.println("FighterJet[" + i + "]: " + fighterjet[i].toString());
            }

            //equals method
            System.out.println("\nEQUALS METHOD TESTS:");
            System.out.println("Equals (object) expected TRUE: " + fighterjet[1].equals(fighterjet[3]));
            System.out.println("Equals (object) expected FALSE: " + fighterjet[0].equals(fighterjet[3]));

            //getters and setters
            System.out.println("\nGETTERS AND SETTERS:");

            fighterjet[0].setSerNum(fighterjet[1].getSerNum());
            System.out.println(fighterjet[0].getSerNum() + " = " + fighterjet[1].getSerNum());

            fighterjet[0].setYear(fighterjet[2].getYear());
            System.out.println(fighterjet[0].getYear() + " = " + fighterjet[2].getYear());

            fighterjet[0].setEngine(fighterjet[1].getEngine());
            System.out.println(fighterjet[0].getEngine() + " = " + fighterjet[1].getEngine());

            fighterjet[0].setWingSpan(fighterjet[1].getWingSpan());
            System.out.println(fighterjet[0].getWingSpan() + " = " + fighterjet[1].getWingSpan());

            fighterjet[0].setOrdanance(fighterjet[1].getOrdanance());
            System.out.println(fighterjet[0].getOrdanance() + " = " + fighterjet[1].getOrdanance());
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
