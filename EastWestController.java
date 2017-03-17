/**
 * EastWestController determines which direction SharedCar should move on a grid
 * @author Antonio Martorana, Aleksandr Bgatov
 * @version March 16, 2017
 */

public class EastWestController extends CarController
{
    Grid actualGrid;

	public EastWestController(Grid mainGrid)
	{
       super(mainGrid);
       actualGrid = mainGrid;
	}

   // return the direction when roaming
   public Coord roam(Coord current)
   {
      if(actualGrid.coordFree(current))
      {
         return direction;
      }
      else
      {
         if(getDirection() == EAST)
         {
            return WEST;
         }
         else
         {
            return EAST;
         }
      }
   }

   public void setDefaultDirection()
   {
      direction = EAST;
   }

   // return the direction when driving
   public Coord drive(Coord current, Coord goal)
   {
      Coord distToTarget = current.dist(goal);
      int distX = distToTarget.row;
      int distY = distToTarget.col;
      int minDistance = distX + distY;
      int countTravel = 0;

      if(current.col > goal.col)
      {
         if(actualGrid.coordFree(current.add(WEST)))
         {
            return WEST;
         }
         else
         {
            if(current.row > goal.row)
            {
               if(actualGrid.coordFree(current.add(NORTH)))
               {
               	  return NORTH;
               }
               else if(actualGrid.coordFree(current.add(SOUTH)))
               {
               	  return SOUTH;
               }
               else
               {
               	  return EAST;
               }
            }
            else
            {
               if(actualGrid.coordFree(current.add(SOUTH)))
               {
                  return SOUTH;
               }
               else if(actualGrid.coordFree(current.add(NORTH)))
               {
                  return NORTH;
               }
               else
               {
               	  return EAST;
               }
            }
         }

      }
      else if(current.col < goal.col)
      {
         if(actualGrid.coordFree(current.add(EAST)))
         {
            return EAST;
         }
         else
         {
            if(current.row > goal.row)
            {
               if(actualGrid.coordFree(current.add(NORTH)))
               {
               	  return NORTH;
               }
               else if(actualGrid.coordFree(current.add(SOUTH)))
               {
               	  return SOUTH;
               }
               else
               {
               	  return WEST;
               }
            }
            else
            {
               if(actualGrid.coordFree(current.add(SOUTH)))
               {
                  return SOUTH;
               }
               else if(actualGrid.coordFree(current.add(NORTH)))
               {
                  return NORTH;
               }
               else
               {
               	  return WEST;
               }
            }
         }
      }
      else
      {
         if(current.row > goal.row)
         {
            if(actualGrid.coordFree(current.add(NORTH)))
            {
               return NORTH;
            }
            else
            {
               if(actualGrid.coordFree(current.add(EAST)))
               {
                  return EAST;
               }
               else if(actualGrid.coordFree(current.add(WEST)))
               {
               	  return WEST;
               }
               else
               {
               	  return SOUTH;
               }
            }
         }
         else if(current.row < goal.row)
         {
         	if(actualGrid.coordFree(current.add(SOUTH)))
         	{
               return SOUTH;
            }
            else
            {
               if(actualGrid.coordFree(current.add(EAST)))
               {
                  return EAST;
               }
               else if(actualGrid.coordFree(current.add(WEST)))
               {
               	  return WEST;
               }
               else
               {
               	  return NORTH;
               }
            }
         }
         else
         {
            return new Coord(0,0);
         }
      }
   }

   public Coord getDirection()
   {
      return direction;
   }
}
