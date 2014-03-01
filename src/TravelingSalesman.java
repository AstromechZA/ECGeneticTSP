import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

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

    protected City[] cities;                // The list of cities.
    protected Chromosome[] chromosomes;     // The list of chromosomes.
    
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
        
        // try parse city count input, otherwise 50 cities
        try
        {
            cityCount = Integer.parseInt(ctrlCities.getText());
        }
        catch (NumberFormatException e)
        {
            cityCount = 50;
        }

        // try parse population count input, otherwise 1000 individuals
        try
        {
            populationSize = Integer.parseInt(ctrlPopulationSize.getText());
        }
        catch (NumberFormatException e)
        {
            populationSize = 1000;
        }

        FontMetrics fm = getGraphics().getFontMetrics();
        int bottom = ctrlButtons.getBounds().y - fm.getHeight() - 2;

        // create a random list of cities
        cities = new City[cityCount];
        for (int i = 0; i < cityCount; i++)
        {
            cities[i] = new City(
                    (int) (Math.random() * (getBounds().width - 10)),
                    (int) (Math.random() * (bottom - 10))
            );
        }

        // create the initial population of chromosomes

        // TO DO

        // start up the background thread

        started = true;

        generation = 0;

        if (worker != null) worker = null;
        worker = new Thread(this);
        //  worker.setPriority(Thread.MIN_PRIORITY);
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

        if (started && (cities != null))
        {
            g.setColor(Color.green);
            for (int i = 0; i < cityCount; i++)
            {
                int xpos = cities[i].getx();
                int ypos = cities[i].gety();
                g.fillOval(xpos - 5, ypos - 5, 10, 10);
            }

            g.setColor(Color.white);
            for (int i = 0; i < cityCount; i++)
            {
                int icity = chromosomes[0].getCity(i);
                if (i != 0)
                {
                    int last = chromosomes[0].getCity(i - 1);
                    g.drawLine(cities[icity].getx(), cities[icity].gety(),
                            cities[last].getx(), cities[last].gety());
                }
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

        double thisCost = 500.0;

        update();

        while (generation < 1000)
        {

            generation++;

            // TO DO

            Chromosome.sortChromosomes(chromosomes, matingPopulationSize);

            double cost = chromosomes[0].getCost();
            cost = Math.abs(cost - thisCost);
            thisCost = cost;

            NumberFormat nf = NumberFormat.getInstance();
            nf.setMinimumFractionDigits(2);
            nf.setMinimumFractionDigits(2);

            setStatus("Generation " + generation + " Cost " + (int) thisCost);

            update();

        }
        setStatus("Solution found after " + generation + " generations.");
    }

    public void paint(Graphics g)
    {
        update();
    }
}
