import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

import bmeier.City;
import bmeier.Population;
import bmeier.Tour;
import bmeier.World;
import bmeier.util.InputCatcher;

/**
 * This class implements the Traveling Salesman problem as a Java applet.
 */

public class TravelingSalesman extends Applet implements Runnable
{

    private static final long serialVersionUID = 1L;

    protected int cityCount;                // How many cities to use.
    protected int populationSize;           // How many chromosomes to use.
    protected int matingPopulationSize;     // The part of the population eligible for mating.
    protected int selectedParents;          // The part of the population selected for mating.
    protected int generation;               // The current generation

    protected World world;   
    protected Population population;
    
    protected Thread worker = null;         // The background worker thread.
    protected boolean started = false;      // Is the thread started.
    
    private Button ctrlStart;               // The Start button.
    private TextField ctrlCities;           // The TextField that holds the number of cities.
    private TextField ctrlPopulationSize;   // The TextField for the population size.
    private Panel ctrlButtons;              // Holds the buttons and other controls, forms a strip across the bottom of the applet.
    private String status = "";             // The current status, which is displayed just above the controls.

    public void init()
    {
        setLayout(new BorderLayout());

        // setup the controls
        ctrlButtons = new Panel();
        ctrlStart = new Button("Start");
        ctrlButtons.add(ctrlStart);
        ctrlButtons.add(new Label("# Cities:"));
        ctrlButtons.add(ctrlCities = new TextField(5));
        ctrlButtons.add(new Label("Population Size:"));
        ctrlButtons.add(ctrlPopulationSize = new TextField(5));
        this.add(ctrlButtons, BorderLayout.SOUTH);

        // set the default values
        ctrlPopulationSize.setText("1000");
        ctrlCities.setText("50");

        // add an action listener for the START button
        ctrlStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0)
            {
                startThread();
            }
        });

        started = false;
        update();
    }

    /**
     * Start the background thread.
     */
    public void startThread()
    {
        cityCount = InputCatcher.toInt(ctrlCities.getText(), 50);
        populationSize = InputCatcher.toInt(ctrlPopulationSize.getText(), 1000);        

        FontMetrics fm = getGraphics().getFontMetrics();
        int bottom = ctrlButtons.getBounds().y - fm.getHeight() - 2;
        
        world = new World(getBounds().width - 10, bottom - 10, cityCount); 
        
        population = new Population(populationSize, world);
        
        started = true;

        generation = 0;

        if (worker != null) worker = null;
        worker = new Thread(this);
        worker.start();
    }

    public void update()
    {
    	
        Image img = createImage(getBounds().width, getBounds().height);
        Graphics g = img.getGraphics();
        FontMetrics fm = g.getFontMetrics();

        int width = getBounds().width;
        int bottom = ctrlButtons.getBounds().y - fm.getHeight() - 2;

        g.setColor(Color.black);
        g.fillRect(0, 0, width, bottom);

        if (started && (world != null))
        {
            g.setColor(Color.green);
            
            for(City city : world.cities)
            {
                int xpos = (int) city.getX();
                int ypos = (int) city.getY();
                g.fillOval(xpos - 5, ypos - 5, 10, 10);
            }

            g.setColor(Color.white);
            
            Tour bestTour = population.top();
            
            City last = bestTour.getCity(0);
            for (int i = 1; i < world.numcities; i++)
            {
                City city = bestTour.getCity(i);
                
                g.drawLine(
                    (int)last.getX(), (int)last.getY(),
                    (int)city.getX(), (int)city.getY()
                );
                
                last = city;
            }

        }

        g.drawString(status, 0, bottom);

        getGraphics().drawImage(img, 0, 0, this);
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void run()
    {
        update();

        while (generation < 1000)
        {

            generation++;

            population = new Population(population, world);
            
            Tour best = population.top();
            
            setStatus("Generation " + generation + " Cost " + (int) best.getCost());

            update();

        }
        setStatus("Solution found after " + generation + " generations.");
    }

    public void paint(Graphics g)
    {
        update();
    }
}
