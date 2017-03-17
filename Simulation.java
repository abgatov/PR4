import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.lang.*;

public class Simulation extends JFrame implements Runnable, ActionListener, ChangeListener
{
   private JFrame newFrame;
   private JPanel centerPanel;
   private JPanel topPanel;
   private JPanel bottomPanel;
   private JLabel ridersPlayerLoaded;
   private JButton newGameButton;
   private JButton pauseButton;
   private JSlider speedSlider;
   private int rows;
   private final static int NEXT_LEVEL = 10;
   private int cols;
   private Grid masterGrid;
   private TimeTick thread1;
   private GraphicsGrid screen;
   private int updateTime = 100;
   private final int numPixels = 40;
   private GridSetup setup;
   private int playerLoaded;
   private int robotLoaded;
   private int countPickups = playerLoaded + robotLoaded;
   private boolean runningGame = false;
   private boolean usingFile = false;
   private Coord [] tempHold;
   private String [] secondaryHold;
   private SharedCar freshCar;
   private EastWestController control1 = new EastWestController(masterGrid);
   private NorthSouthController control2 = new NorthSouthController(masterGrid);
   private RandomController control3 = new RandomController(masterGrid);
   private Coord [] obstacleHold;

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

   public void update()
   {
      masterGrid.update();
   }

   public static void main(String [] args)
   {
      Simulation newGame = new Simulation(args);
      SwingUtilities.invokeLater(newGame);
   }
}