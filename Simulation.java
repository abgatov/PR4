/**
 * @author Antonio Martorana, Aleksandr Bgatov
 * @version March 16, 2017
 */

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.lang.*;

/**
 * class Simulation calls instances of the objects to run the game
 */
public class Simulation extends JFrame implements Runnable, ActionListener, ChangeListener
{
  /**
   * newFrame creates a new JFRame to work with
   */
   private JFrame newFrame;
   /**
    * centerPanel creates a new JPanel that will hold the center portion
    */
   private JPanel centerPanel;
   /**
    * topPanel creates a new JPanel that will hold the top portion
    */
   private JPanel topPanel;
   /**
    * bottomPanel creates a new JPanel that will hold the bottom portion
    */
   private JPanel bottomPanel;
   /**
    * ridersPlayerLoaded keeps track of the riders that have been loaded
    */
   private JLabel ridersPlayerLoaded;
   /**
    * newGameButton creates a new game, it resets the simulation and the values
    */
   private JButton newGameButton;
   /**
    * pauseButton pauses the game, it haults the drive methods
    */
   private JButton pauseButton;
   /**
    * speedSlider adjusts the speed the game proceeds at, specifically the timeticks
    */
   private JSlider speedSlider;
   /**
    * rows keeps track of the rows of the grid created
    */
   private int rows;
   /**
    * NEXT_LEVEL keeps an int that is used to adjust the time of the game
    */
   private final static int NEXT_LEVEL = 10;
   /**
    * cols keeps track of the columns of the grid created
    */
   private int cols;
   /**
    * masterGrid is a Grid object that holds the main grid
    */
   private Grid masterGrid;
   /**
    * thread1 keeps track of the time in a separate Thread
    */
   private TimeTick thread1;
   /**
    * screen is a GraphicsGrid that displays the visual representation
    */
   private GraphicsGrid screen;
   /**
    * updateTime is a variable used to store time of the game
    */
   private int updateTime = 100;
   /**
    * numPixels sets the default number of pixels to 40
    */
   private final int numPixels = 40;
   /**
    * setup stores the objects created within the simulation
    */
   private GridSetup setup;
   /**
    * playerLoaded keeps track of the number of riders picked up by player
    */
   private int playerLoaded;
   /**
    * robotLoaded keeps track of the number of riders picked up by robot
    */
   private int robotLoaded;
   /**
    * countPickups keeps track of the number of riders picked up in total
    */
   private int countPickups = playerLoaded + robotLoaded;
   /**
    * runningGame is a boolean that tells if the game/simulation is not running
    */
   private boolean runningGame = false;
   /**
    * usingFile is a boolean that keeps track of when the simulation is running
    */
   private boolean usingFile = false;
   /**
    * tempHold holds a temporary coordinate
    */
   private Coord [] tempHold;
   /**
    * secondaryHold holds another temporary coordinate
    */
   private String [] secondaryHold;
   /**
    * freshCar creates a new SharedCar instance
    */
   private SharedCar freshCar;
   /**
    * control1 is a EastWestController on masterGrid
    */
   private EastWestController control1 = new EastWestController(masterGrid);
   /**
    * control2 is a NorthSouthController on masterGrid
    */
   private NorthSouthController control2 = new NorthSouthController(masterGrid);
   /**
    * control3 is a RandomController on masterGrid
    */
   private RandomController control3 = new RandomController(masterGrid);
   /**
    * obstacleHold holds a temporary obstacle
    */
   private Coord [] obstacleHold;

   /**
    * Constructor that creates a simulation using a string
    * @param initialize holds the necessary values and commands to
    * begin the simulation
    */
   public Simulation(String [] initialize)
   {
   	  if(initialize.length > 1)
   	  {
         rows = Integer.parseInt(initialize[0]);
         cols = Integer.parseInt(initialize[1]);

         masterGrid = new Grid(rows,cols);
         thread1 = new TimeTick(updateTime,masterGrid,this);

     }
     else if(initialize.length == 1)
     {
        setup = new GridSetup(initialize[0]);

        Coord temp = new Coord(setup.getDimension());

        int rows = temp.row;
        int cols = temp.col;

        masterGrid = new Grid(rows,cols);
        thread1 = new TimeTick(updateTime,masterGrid,this);

        usingFile = true;
     }
   }

   /**
    * actionPerformed keeps track of the action performed when a key that the
    * user presses is pressed
    * @param evt uses KeyListener to take keyboard input
    */
   public void actionPerformed(ActionEvent evt)
   {
      if(evt.getSource() == pauseButton)
      {
         thread1.changeState();
      }
      if(evt.getSource() == newGameButton)
      {
         thread1.stop();
         screen.clearObjects();
         masterGrid = new Grid(rows,cols);
         thread1 = new TimeTick (updateTime,masterGrid,this);
         runningGame = !runningGame;
      }
   }

   /**
    * stateChanged keeps track of the state after a key that the
    * user presses is pressed
    * @param evt uses KeyListener to take keyboard input
    */
   public void stateChanged(ChangeEvent evt)
   {
      int newTime = speedSlider.getValue();

      if(thread1 != null)
      {
         if(newTime <= updateTime)
         {
         	updateTime = newTime;
            thread1.setTicks(newTime);
         }
      }
   }

   /**
    * run creates the necessary objects to create a visual represention of
    * the simulation, such as the panel
    */
   public void run()
   {
   	  newFrame = new JFrame ();
   	  centerPanel = new JPanel ();
   	  topPanel = new JPanel();
   	  bottomPanel = new JPanel ();
   	  ridersPlayerLoaded = new JLabel ("Riders Loaded Player: " + playerLoaded + " Robots: " + robotLoaded);
      newGameButton = new JButton ("New Game");
      pauseButton = new JButton ("Pause");
      speedSlider = new JSlider(0,updateTime);
      speedSlider.setInverted(true);
      screen = new GraphicsGrid(rows,cols,numPixels);

      topPanel.add(ridersPlayerLoaded);
      bottomPanel.add(newGameButton);
      bottomPanel.add(pauseButton);
      bottomPanel.add(speedSlider);
      centerPanel.add(screen);

      newGameButton.addActionListener(this);
      pauseButton.addActionListener(this);
      speedSlider.addChangeListener(this);

      newFrame.add(bottomPanel,BorderLayout.SOUTH);
      newFrame.add(centerPanel,BorderLayout.CENTER);
      newFrame.add(topPanel,BorderLayout.NORTH);

      newFrame.pack();
      newFrame.validate();

      newFrame.setVisible(true);

      screen.addGridObject(masterGrid.humanCar());

      if(usingFile == true)
      {
         tempHold = new Coord [setup.getRobocars().length];
         tempHold = setup.getRobocars();
         secondaryHold = new String [setup.getRobocars().length];
         secondaryHold = setup.getControllers();
         obstacleHold = new Coord [setup.getObstacles().length];
         obstacleHold = setup.getObstacles();

         for(int i = 0; i < tempHold.length; i++) //Check controllers and robocars
         {
            if(secondaryHold[i] == "east")
            {
               if(masterGrid.addCar(control1,tempHold[i]))
               {
               	  screen.addGridObject(new SharedCar(control1,masterGrid));
               }
            }
            if(secondaryHold[i] == "north")
            {
               if(masterGrid.addCar(control2,tempHold[i]))
               {
               	  screen.addGridObject(new SharedCar(control2,masterGrid));
               }
            }
            if(secondaryHold[i] == "random")
            {
               if(masterGrid.addCar(control3,tempHold[i]))
               {
               	  screen.addGridObject(new SharedCar(control3,masterGrid));
               }
            }

         }

         for(int i = 0; i < obstacleHold.length; i++)
         {
            if(masterGrid.addObstacle(obstacleHold[i]))
            {
               screen.addGridObject(new Obstacle(obstacleHold[i]));
            }
         }

      }

      while(runningGame)
      {
         if(countPickups % NEXT_LEVEL == 0)
         {
            updateTime-= NEXT_LEVEL;
         }

      }
   }

   /**
    * update simply progresses the simulation through calling update on
    * masterGrid
    */
   public void update()
   {
      masterGrid.update();
   }

   /**
    * the main class that runs the simulation and thus the package
    * @param args the main String that is passed in to run the sumulation
    * containing necessary commands and values within args
    */
   public static void main(String [] args)
   {
      Simulation newGame = new Simulation(args);
      SwingUtilities.invokeLater(newGame);
   }
}
