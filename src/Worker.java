public class Worker {
    private final Plant plant;

    public Worker(Plant plant) {
        this.plant = plant;
    }

    public void entireOrange(Orange o) {
        do {
            processOrange(o);
        } while (o.getState() != Orange.State.Bottled);//while the orange is not done being processed
        plant.completeOrange(o);
    }

    public void processOrange(Orange o) {
        o.runProcess();//worker runs their threads over in orange
    }
}