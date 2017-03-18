/**
 * ManualController lets you decide which direction the player will move on a grid
 * @author Antonio Martorana, Aleksandr Bgatov
 * @version March 16, 2017
 */

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.lang.*;

public class ManualController extends CarController implements KeyListener
{
	Grid actualGrid;

public ManualController(Grid mainGrid)
{
		 super(mainGrid);
		 actualGrid = mainGrid;
}

public void keyPressed(KeyEvent evt) {

    int key = evt.getKeyCode();

    if (key == KeyEvent.VK_H) {
        if (getDirection() == EAST) { direction = NORTH; }
				else if (getDirection() == NORTH) { direction = WEST; }
				else if (getDirection() == WEST) { direction = SOUTH; }
				else { direction = EAST; }
    }

    else if (key == KeyEvent.VK_L) {
			if (getDirection() == EAST) { direction = SOUTH; }
			else if (getDirection() == NORTH) { direction = EAST; }
			else if (getDirection() == WEST) { direction = NORTH; }
			else { direction = WEST; }
    }

    else if (key == KeyEvent.VK_SPACE) {
        actualGrid.driveReal();
    }
}

public void keyReleased(KeyEvent evt)
{
}
public void keyTyped(KeyEvent evt)
{
}

 // return the direction when roaming
 public Coord roam(Coord current)
 {
		return direction;
 }

 // return the direction when driving
 public Coord drive(Coord current, Coord goal)
 {
	 return direction;
 }
 public Coord getDirection()
 {
	 return direction;
 }

 public void setDefaultDirection()
 {
   direction = EAST;
 }
}