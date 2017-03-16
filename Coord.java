/**
 * @author Antonio Martorana, Alex Bgatov
 * @version March 8, 2017
 */

/**
 * | x | means that absolute value of x
 * x^2 means x * x
 * sign(x) means -1 if x < 0, 1 otherwise
 * iff means "if and only if"
 * Implementation notes:
 * compareTo -- compares the dist2(Origin) of the each Coord instance. a null instance is infinitely far
 * from the Origin
 * equals() compares row == row and col == col for the two instances
 * toString format is Coord:(row=%f,col=%f)
 */

import java.lang.Math;

/**
 * Coord class describes coordinates on a grid
 */
public class Coord implements Comparable<Coord>
{
  public final int row;
  public final int col;


    //Constructors
    public Coord ()
    {
       this.row = 0;
       this.col = 0;
   }

  public Coord(Coord other)
  {
    this.row = other.row;
    this.col = other.col;
  }

  public Coord(int row1, int col1)
  {
    this.row = row1;
    this.col = col1;
  }

  //methods
  public Coord dist(Coord b)
  {
    if (b == null) { return null; }
    else {
      return new Coord(Math.abs(row - b.row),Math.abs(col - b.col));
    }
  }

  public Coord diff(Coord b)
  {
    if (b == null) { return null; }
    else {
      return new Coord((row - b.row),(col - b.col));
    }
  }

  public int dist2(Coord b)
  {
    if (b == null) { return Integer.MAX_VALUE; }
    else
    {
    	Double secondDistance = Math.pow((dist(b).row), 2) + Math.pow((dist(b).col), 2);
    	int adjustedDistance = secondDistance.intValue();
    	return adjustedDistance;
    }
  }

  public Coord unit ()
  {
   if ((row < 0) && (col < 0)) { return (new Coord(-1,-1)); }
   else if ((row >= 0) && (col >= 0)) { return (new Coord(1,1)); }
   else if ((row >= 0) && (col < 0)) { return (new Coord(1,-1)); }
   else if ((row < 0) && (col >= 0)) { return (new Coord(-1,1)); }
   else { return (new Coord(0,0)); }
  }

  public Coord add(Coord b)
  {
    if (b == null) { return null; }
    else { return (new Coord(row + b.row,col + b.col)); }
  }

  public int compareTo (Coord other)
  {
     Coord origin = new Coord();
     int comparison1 = 0;
     int comparison2 = 0;

     comparison1 = dist2(origin);
     comparison2 = dist2(other);

     if(comparison2 == Integer.MAX_VALUE) { return Integer.MIN_VALUE; }

     if(comparison1 > comparison2)
     {
     	return 1;
     }
     else if(comparison1 < comparison2)
     {
     	return -1;
     }
     else
     {
     	return 0;
     }
  }

  @Override
  public boolean equals (Object obj)
  {
     if(obj == null) { return false; }

     if(obj instanceof Coord)
     {
       Coord other = (Coord) obj;
       if(other.row == this.row && other.col == this.col) { return true; }
       else { return false; }
     }
     else {  return false;  }
  }

  @Override
  public String toString ()
  {
    return ("Coord:(row=" + row + ",col=" + col + ")");
  }

}
