/**
 * @author Antonio Martorana, Alex Bgatov
 * @version March 10, 2017
 */

import java.lang.Math;
import java.util.ArrayList;
import java.awt.*;
import java.util.Random;

/**
 * Grid creates a grid object which also hold
 * information on other objects on the grid
 */
public class Grid implements GridInfo, CoordInfo
{
   private int row;
   private int col;
   private int originX;
   private int originY;
   private SharedCar [][] carInfo;
   private Obstacle [][] obstacleInfo;
   private boolean [][] gridInfo;
   private Rider rider;
   private final int HALF = 2;

   public Grid (int rows, int cols)
   {
      this.row = Math.abs(rows);
      this.col = Math.abs(cols);

      originX = row / HALF;
      originY = col / HALF;

      carInfo = new SharedCar [rows][cols];
      obstacleInfo = new Obstacle [rows][cols];
      gridInfo = new boolean [rows][cols];
   }

   // Return true if SharedCar succesfully claimed the location
	public boolean claim(SharedCar car, Coord loc)
	{
	   int i = loc.row;
	   int j = loc.col;

       if(i >= 0 && i< row)
	   {
	   	  if(j >= 0 && j< col)
	   	  {
             if(gridInfo[i][j] == false)
	         {
	   	        carInfo[i][j] = car;
	   	        car.setLocation(loc);
	   	        gridInfo[i][j] = true;

	   	        return true;
	         }	   	     
	   	  }
	   }
	   return false;
	}

	// Return true if SharedCar  successfully loaded rider 
	public boolean riderLoaded(SharedCar car)
	{
		return true;
	}

	/** Determine if a Coordinate is free
 	 * @param loc location to query
 	 * @return  true if loc is in bounds and available
 	 *          else false.  
 	 *          return false if loc is null
	*/
	public boolean coordFree(Coord loc)
	{
	   int i = loc.row;
	   int j = loc.col;
        
	   if(i >= 0 && i< row)
	   {
	   	  if(j >= 0 && j< col)
	   	  {
	   	     if(gridInfo[i][j] == false)
	   	     {
	   	        return true;
	   	     }
	   	  }
	   }

	   return false;
	}

	public void drive()
	{
       for(int i = 0; i < row; i++)
       {
       	  for(int j = 0; j < col; j++)
       	  {
       	     if(gridInfo[i][j] == true)
       	     {
       	        carInfo[i][j].drive();
       	     }
       	  }
       }  
	}

	public boolean addCar()
	{
	   return false; //REMEMBER TO POSSIBLY IMPLEMENT IF CAN IMPLEMENT RANDOM CARCONTROLLER CLASS
	}

	public boolean addCar(SharedCar newCar)
	{
	   int i = 0;
	   int j = 0;

	   i = newCar.getLocation().row;
	   j = newCar.getLocation().col;

	   if(i >= 0 && i < row)
	   {
	   	  if(j >= 0 && j < col)
	   	  {
	   	     if(gridInfo[i][j] == false)
	         {
		        carInfo[i][j] = newCar;
	            gridInfo[i][j] = true;

	            return true; 
 	         }
	   	  }
	   }

       return false;
	}

	public boolean addCar(CarController actualController, Coord desiredLoc) 
	{
       int testRow = 0;
	   int testCol = 0;

       testRow = desiredLoc.row;
       testCol = desiredLoc.col;

       if(testRow >= 0 && testRow< row)
	   {
	   	  if(testCol >= 0 && testCol< col)
	   	  {
             if(gridInfo[testRow][testCol] == false)
	         {
		        carInfo[testRow][testCol] = new SharedCar (actualController,this);
		        carInfo[testRow][testCol].setLocation(desiredLoc);
	            gridInfo[testRow][testCol] = true;

	            return true;
 	         }	   	     
	   	  }
	   }

 	   return false;
	} //Remember that actualController must be converted from String to CarController in main method after being parsed
	  //from GridSetup

	public boolean addRider(Rider rider)
	{
	   int i = 0;
	   int j = 0;

	   i = (rider.getLocation()).row;
	   j = (rider.getLocation()).col;

	   if(gridInfo[i][j] == false)
	   {
	   	  this.rider = rider;
	   	  gridInfo[i][j] = true;

	   	  return true;
	   }

	   return false;
	}

	public boolean addRider (Coord desiredLoc)
	{
	   int i = 0;
	   int j = 0;

	   i = desiredLoc.row;
	   j = desiredLoc.col;


       if(i >= 0 && i< row)
	   {
	   	  if(j >= 0 && j< col)
	   	  {
	         if(gridInfo[i][j] == false)
	         {
	   	        this.rider = new Rider ();
	   	        gridInfo[i][j] = true;
	   	        rider.setLocation(desiredLoc);

	   	        return true;
	         }
	   	  }
	   }

	   return false;
	}

	public boolean addRider()
	{
       Random random1 = new Random();
       Random random2 = new Random();
       int i = random1.nextInt(row);
       int j = random2.nextInt(col);

       Coord testLoc = new Coord(i,j);

       if(gridInfo[i][j] == false)
       {
       	  gridInfo[i][j] = true;
       	  rider = new Rider();
       	  rider.setLocation(testLoc);

       	  return true;
       }

       return false;     	   
	}

	public boolean addObstacle(Coord desiredLoc)
	{
       int i = desiredLoc.row;
       int j = desiredLoc.col;
       Obstacle newObstacle = null;


       if(i >= 0 && i< row)
	   {
	   	  if(j >= 0 && j< col)
	   	  {
	         if(gridInfo[i][j] == false)
             {
       	        gridInfo[i][j] = true;
       	        obstacleInfo[i][j] = new Obstacle(desiredLoc);

       	        return true;
             }
	   	  }
	   }

       return false;     
	}

	public boolean addObstacle()
	{
       Random random1 = new Random();
       Random random2 = new Random();
       int i = random1.nextInt(row);
       int j = random2.nextInt(col);

       Coord testLoc = new Coord(i,j);

       if(gridInfo[i][j] == false)
       {
       	  gridInfo[i][j] = true;
       	  obstacleInfo[i][j] = new Obstacle(testLoc);

       	  return true;
       }

       return false;     
	}

	@Override
	public String toString()
	{
		String s = "";

		for(int i = 0; i < row; i++)
		{
		   for(int j = 0; j < col; j++)
		   {
		   	  if(i == 0)
		   	  {
		   	     s += "=";
		   	  }
		   	  if(j == 0)
		   	  {
		   	     s += "|";
		   	  }
		   	  if(carInfo[i][j] != null)
		   	  {
		   	     s += carInfo[i][j].getSymbol();
		   	  }
		   	  else if(obstacleInfo[i][j] != null)
		      {
		         s += obstacleInfo[i][j].getSymbol();
		      }
		   	  else
		   	  {
		   	     s += " ";
		   	  }
		   }// end first for loop
           
           s += "\n";

		} //end final for loop check for gridobjects

		return s;
	}
}