public class Plant implements Runnable {
    // How long do we want to run the juice processing
    public static final long PROCESSING_TIME = 5 * 1000;

    public static void main(String[] args) {
        // Startup 
    	int TotalPlant = 5;
    	Plant [] p = new Plant[TotalPlant];
    	
    	for (int i = 0; i<TotalPlant; i++){
    		p[i] = new Plant();
    		p[i].startPlant();
    	}

        int totalProvided = 0;
        int totalProcessed = 0;
        int totalBottles = 0;
        int totalWaste = 0;

        // Give the plants time to do work,
        delay(PROCESSING_TIME, "Plant malfunction");

        // Stop the plant, and wait for it to shutdown
        for (int i = 0; i<TotalPlant; i++){
        	p[i].stopPlant();
        }
        //and wait for it to shutdown
        for (int i = 0; i<TotalPlant; i++){
        	p[i].waitToStop();	
        }
         
        for (int i = 0; i<TotalPlant; i++){
        	totalProvided = totalProvided + p[i].getProvidedOranges(); 
        	totalProcessed = totalProcessed + p[i].getProcessedOranges(); 
        	totalBottles = totalBottles +p[i].getBottles();
        	totalWaste = totalWaste + p[i].getWaste();
        	
        }

        // Summarize the results
        System.out.println("Total provided/processed = " + (totalProvided) + "/" + (totalProcessed));
        System.out.println("Created " + (totalBottles) +
                           ", wasted " + (totalWaste) + " oranges");
    }

    private static void delay(long time, String errMsg) {
        long sleepTime = Math.max(1, time);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            System.err.println(errMsg);
        }
    }

    public final int ORANGES_PER_BOTTLE = 3;

    private final Thread thread;
    private int orangesProvided;
    private int orangesProcessed;
    private volatile boolean timeToWork;

    Plant() {
        orangesProvided = 0;
        orangesProcessed = 0;
        thread = new Thread(this, "Plant");
    }

    public void startPlant() {
        timeToWork = true;
        thread.start();
    }

    public void stopPlant() {
        timeToWork = false;
    }

    public void waitToStop() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.err.println(thread.getName() + " stop malfunction");
        }
    }

    public void run() {
        System.out.print(Thread.currentThread().getName() + " Processing oranges");
        while (timeToWork) {
            processEntireOrange(new Orange());
            orangesProvided++;
            System.out.print(".");
        }
        System.out.println("");
    }

    public void processEntireOrange(Orange o) {
        while (o.getState() != Orange.State.Bottled) {
            o.runProcess();
        }
        orangesProcessed++;
    }

    public int getProvidedOranges() {
        return orangesProvided;
    }

    public int getProcessedOranges() {
        return orangesProcessed;
    }

    public int getBottles() {
        return orangesProcessed / ORANGES_PER_BOTTLE;
    }

    public int getWaste() {
        return orangesProcessed % ORANGES_PER_BOTTLE;
    }
}