
/**
 * @author Antonio Martorana, Aleksandr Bgatov
 * @version March 8, 2017
 */

import java.lang.Math;

public class Coord extends java.lang.object implements java.lang.Comparable<Coord> 
{
  public final int row;
  public final int col;


  //Constructors
  public Coord() 
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
      return new Coord(Math.abs(row - b.row),Math.abs(col - a.col));
    }
  }

  public Coord diff(Coord b) 
  {
    if (b == null) { return null; }
    else {
      return new Coord((row - b.row),(col - a.col));
    }
  }

  public int dist2(Coord b) 
  {
    if (b = null) { return Integer.MAX_VALUE; }
    else { return ((dist(b).row)*(dist(b).row) +(dist(b).col)*(dist(b).col)); }
  }

  public Coord unit ()
  {
   if ((row < 0) && (col < 0)) { return (new Coord(-1,-1)); }
   else if ((row >= 0) && (col >= 0)) { return (new Coord(1,1)); }
   else if ((row >= 0) && (col < 0)) { return (new Coord(1,-1)); }
   else if ((row < 0) && (col >= 0)) { return (new Coord(-1,1)); }
   else { return null; }
  }

  public Coord add(Coord b) 
  {
    if (b == null) { return null; }
    else { return (new Coord(row + b.row,col + a.col)); }
  }

  public int compareTo (Coord other)
  {
    Coord origin = new Coord();
    return (dist2(origin).compareTo(dist2(other)));
  }

  @Override
  public boolean equals (Object obj)
  {
     final Coord other = (Coord) obj;
     if(other.row == this.row && other.col == this.col) { return true; }
     else {  return false;  }
  }

  @Override
  public java.lang.String toString ()
  {
    String x = "Coord:(row =";
    String y = ",col=";
    String z = ")";
    return x + row + y + col + z;
  }
}

