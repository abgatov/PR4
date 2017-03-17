/**
 * @author Antonio Martorana, Aleksandr Bgatov
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
   /**
    * the index representing the row of the coordinates
    */
   private int row;
   /**
    * the index representing the column of the coordinates
    */
   private int col;
   /**
    * array used to store location of cars on a grid
    */
   private SharedCar [][] carInfo;
   /**
    * array used to store location of obstacles on a grid
    */
   private Obstacle [][] obstacleInfo;
   /**
    * array used to store location of free grid spaces and occupied spaces
    * in the form of booleans
    */
   private boolean [][] gridInfo;
   /**
    * an object of type Rider used to store information about the rider
    */
   private Rider rider;
   /**
    * array used to store location of obstacles on a grid
    */

   /**
    *Constructor initializing rows and columns to carInfo,
    *obstacleInfo and gridInfo arrays of dimension[rows][cols]
    *@param rows index representing the row of the coordinates
    *@param col index representing the columns of the coordinates
    */
   public Grid (int rows, int cols)
   {
      this.row = Math.abs(rows);
      this.col = Math.abs(cols);

      carInfo = new SharedCar [rows][cols];
      obstacleInfo = new Obstacle [rows][cols];
      gridInfo = new boolean [rows][cols];
   }

  /**
   * Determine if SharedCar succesfully claimed the location
   * @param car type SharedCar with information on a car
   * @param loc coordinate giving location of car
   * @return a boolean representing a successful addition of car to a gridspace
   * if true and not successful if false.
   */
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

	/**
  * Determine if SharedCar successfully loaded rider
  * @param car type SharedCar with information on a car
  * @return a boolean representing a successful pickup of rider to a car if true
  */
	public boolean riderLoaded(SharedCar car)
	{
		return true;
	}

	/**
   *Determine if a Coordinate is free
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

  /**
  * invokes the drive method of carInfo for each gridspace with an object
  */
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

  /**
  * adds a car of type SharedCar
  * @param newCar car to add
  * @return true if car can be added and is added, false if not added
  */
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

  /**
  * adds a car at a specific location with a specific controller
  * @param desiredLoc location where to add car
  * @param actualController controller that will decide where the car moves
  * when in drive
  * @return true if car can be added and is added, false if not added
  */
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

  /**
  * adds a rider to the grid, specifically gridInfo
  * @param rider the rider to add to the grid
  * @return true if rider can be added and is added, false if not added
  */
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

  /**
  * adds a rider to the grid at a specific location
  * @param desiredLoc location where a new rider is added to the grid
  * @return true if rider can be added and is added, false if not added
  */
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

  /**
  * adds a rider to the grid at a random location
  * @return true if rider can be added and is added, false if not added
  */
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

  /**
  * adds an obstacle to the grid at specific location
  * @param desiredLoc location where a new obstacle is added to the grid
  * @return true if obstacle can be added and is added, false if not added
  */
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

  /**
  * adds an obstacle to the grid at a random location
  * @return true if obstacle can be added and is added, false if not added
  */
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

  /**
  * Overrides toString to print out a grid with a boundary
  */
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
