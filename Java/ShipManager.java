/*************************************************************************
 *  Author: Phi Do                                                       *
 *  Purpose: Contains all the submodules required for the function of    *
 *           menu of the program as well as the functions of the menu          *
 *  Date: 07/05/2019                                                     *
 *  Last Updated: 07/05/2019                                             *
 *************************************************************************/
 import java.util.*;
 public class ShipManager
 {
     public static void main(String[] args)
     {
       try
       {
          UserInterface ui = new UserInterface();
          ui.menu();
       }
       catch(Exception e)
       {
           System.out.println("Error: Invalid");
       }
     }
 }
