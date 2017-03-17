/**
 * Obstacle represents a non-movable object on a grid
 * @author Antonio Martorana, Aleksandr Bgatov
 * @version March 16, 2017
 */


import java.awt.Color;

public class Obstacle extends GridObject
{
  /* Directions that GridObjects can point */
  public static final int NONE = 0;
  public static final int UP = 1;
  public static final int DOWN = 2;
  public static final int LEFT =  3;
  public static final int RIGHT =  4;
  protected String [] dirSymbols = {"#","^","v","<",">"};

  protected Coord location;
  protected String symbol = null;
  protected Color color;
  protected int dir = NONE;

  public Obstacle(Coord loc)
  {
    color = Color.BLACK;
    location = new Coord(loc);
  }
}


//vim:ts=4:sw=4:et
