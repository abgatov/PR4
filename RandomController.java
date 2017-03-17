import java.util.Random;

public class RandomController extends CarController
{
	Grid actualGrid;

	public RandomController(Grid mainGrid)
	{
       super(mainGrid);
       actualGrid = mainGrid;
	}

   // return the direction when roaming
   public Coord roam(Coord current)
   {
		  Random rand = new Random();
			int randDir = rand.nextInt(4)+1; //generates random number
			if ((randDir == 1) && actualGrid.coordFree(current.add(WEST))) //checks if coord taken
			{
				return WEST;
			}
			else if ((randDir == 2) && actualGrid.coordFree(current.add(EAST)))
			{
				return EAST;
			}
			else if ((randDir == 3) && actualGrid.coordFree(current.add(SOUTH)))
			{
				return SOUTH;
			}
			else if ((randDir == 4) && actualGrid.coordFree(current.add(NORTH)))
			{
				return NORTH;
			}
			else
      {
         if(actualGrid.coordFree(current.add(EAST)) != true)
         {
					 randDir = rand.nextInt(3)+1;
					 switch ( randDir )
					 {
						 case 1:
							 return WEST;
						 case 2:
							 return SOUTH;
						 case 3:
							 return NORTH;
           }
				 }
				 else if(actualGrid.coordFree(current.add(WEST)) != true)
         {
					 randDir = rand.nextInt(3)+1;
					 switch ( randDir )
					 {
						 case 1:
							 return EAST;
						 case 2:
							 return SOUTH;
						 case 3:
							 return NORTH;
           }
				 }
				 else if(actualGrid.coordFree(current.add(SOUTH)) != true)
         {
					 randDir = rand.nextInt(3)+1;
					 switch ( randDir )
					 {
						 case 1:
							 return WEST;
						 case 2:
							 return EAST;
						 case 3:
							 return NORTH;
					 }
         }
         else
         {
					 randDir = rand.nextInt(3)+1;
					 switch ( randDir )
					 {
						 case 1:
						 	return WEST;
						 case 2:
						 	return EAST;
						 case 3:
							return SOUTH;
           }
         }
			}
   }



   public void setDefaultDirection()
   {
		 	direction = WEST;
   }

   // return the direction when driving
   public Coord drive(Coord current, Coord goal)
   {
		  Coord distToTarget = current.dist(goal);
		  int distx = distToTarget.col;
			int disty = distToTarget.row;
			if (distx <= disty) //checks if y distance is bigger, goes N/S respectively
			{
				if ((current.col < goal.col) && (current.row < goal.row)) //C1 = NW of goal
				{
			  	Coord temp1 = new Coord(current.row+1,current.col);
					if (coordFree(temp1)) //checks if coordinate is free
					{
						return SOUTH;
					}
					else if(coordFree(temp1.add(EAST))) //if not free checks surrounding spaces
					{
						return EAST;
					}
					else if(coordFree(temp1.add(WEST)))
					{
						return WEST;
					}
					else
					{
						return NORTH;
					}
			  }
				else if ((current.col > goal.col) && (current.row < goal.row)) //C3 = NE of goal
				{
			  	Coord temp1 = new Coord(current.row+1,current.col);
					if (coordFree(temp1))
					{
						return SOUTH;
					}
					else if(coordFree(temp1.add(WEST)))
					{
						return WEST;
					}
					else if(coordFree(temp1.add(EAST)))
					{
						return EAST;
					}
					else
					{
						return NORTH;
					}
			  }
				if ((current.col < goal.col) && (current.row > goal.row)) //C5 = SW of goal
				{
			  	Coord temp1 = new Coord(current.row-1,current.col);
					if (coordFree(temp1))
					{
						return NORTH;
					}
					else if(coordFree(temp1.add(EAST)))
					{
						return EAST;
					}
					else if(coordFree(temp1.add(WEST)))
					{
						return WEST;
					}
					else
					{
						return SOUTH;
					}
			  }
				if ((current.col > goal.col) && (current.row > goal.row)) //C7 = SE of goal
				{
			  	Coord temp1 = new Coord(current.row-1,current.col);
					if (coordFree(temp1))
					{
						return NORTH;
					}
					else if(coordFree(temp1.add(WEST)))
					{
						return WEST;
					}
					else if(coordFree(temp1.add(EAST)))
					{
						return EAST;
					}
					else
					{
						return SOUTH;
					}
			  }
			}
			else //means that x distance is bigger, goes W/E respectively
			{
				if ((current.col < goal.col) && (current.row < goal.row)) //C2 = NW
				{
			  	Coord temp1 = new Coord(current.row,current.col+1);
					if (coordFree(temp1))
					{
						return EAST;
					}
					else if(coordFree(temp1.add(SOUTH)))
					{
						return SOUTH;
					}
					else if(coordFree(temp1.add(NORTH)))
					{
						return NORTH;
					}
					else
					{
						return WEST;
					}
			  }
				else if ((current.col > goal.col) && (current.row < goal.row)) //C4 = NE
				{
			  	Coord temp1 = new Coord(current.row,current.col-1);
					if (coordFree(temp1))
					{
						return WEST;
					}
					else if(coordFree(temp1.add(SOUTH)))
					{
						return SOUTH;
					}
					else if(coordFree(temp1.add(NORTH)))
					{
						return NORTH;
					}
					else
					{
						return EAST;
					}
			  }
				if ((current.col < goal.col) && (current.row > goal.row)) //C6 = SW
				{
			  	Coord temp1 = new Coord(current.row,current.col+1);
					if (coordFree(temp1))
					{
						return WEST;
					}
					else if(coordFree(temp1.add(NORTH)))
					{
						return NORTH;
					}
					else if(coordFree(temp1.add(SOUTH)))
					{
						return SOUTH;
					}
					else
					{
						return EAST;
					}
			  }
				if ((current.col > goal.col) && (current.row > goal.row)) //C8 = SE
				{
			  	Coord temp1 = new Coord(current.row,current.col-1);
					if (coordFree(temp1))
					{
						return EAST;
					}
					else if(coordFree(temp1.add(NORTH)))
					{
						return NORTH;
					}
					else if(coordFree(temp1.add(SOUTH)))
					{
						return SOUTH;
					}
					else
					{
						return WEST;
					}
			  }
			}
   }

   public Coord getDirection()
   {
      return direction;
   }
}
