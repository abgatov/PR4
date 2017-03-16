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
    
   }

   // return the direction when driving 
   public Coord drive(Coord current, Coord goal)
   {
      
   }

   public Coord getDirection()
   {
      return direction;
   }
}