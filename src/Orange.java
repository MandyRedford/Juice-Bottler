public class Orange {
    public enum State {
        Fetched(15),
        Peeled(38),
        Squeezed(29),
        Bottled(17),
        Processed(1);

        final int timeToComplete;

        private State(int timeToComplete) {
            this.timeToComplete = timeToComplete;
        
        }
        public State nextState(){
        	if (this==Processed){
        		return this;
        	}
        	return State.values()[this.ordinal()+1];
        }
    }
    private State state;

    public Orange() {
        state = State.Fetched;
        doWork();
    }

    public State getState() {
        return state;
    }

    public void runProcess() {
        // Don't attempt to process an already completed orange
        if (state == State.Processed) {
            throw new IllegalStateException("This orange has already been processed");
        }
        doWork();
        state = state.nextState();//orange moves onto next state
    }

    private void doWork() {
        // Sleep for the amount of time necessary to do the work
        try {
            Thread.sleep(state.timeToComplete);
        } catch (InterruptedException e) {
            System.err.println("Orange was not completly Processesed");
        }
    }
}