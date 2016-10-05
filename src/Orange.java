public class Orange {
    public enum State {// sets up the different states an orange can be (number)= seconds for delay
        Fetched(15),
        Peeled(38),
        Squeezed(29),
        Bottled(17),
        Processed(1);

        final int timeToComplete;

        private State(int timeToComplete) {
            this.timeToComplete = timeToComplete;//sets the number next to each state as a variable
        
        }
        public State nextState(){
        	if (this==Processed){
        		return this;//returns the state processed 
        	}
        	return State.values()[this.ordinal()+1];//increment state by 1
        }
    }
    private State state;

    public Orange() {
        state = State.Fetched;// sets the orange to first state
        doWork();// calls the delay
    }

    public State getState() {//allows for the orange to report its state
        return state;
    }

    public void runProcess() { 
        if (state == State.Processed) {// prevent the process of an already completed orange
            throw new IllegalStateException("This orange has already been processed");
        }
        doWork();//calls the delay
        state = state.nextState();//orange moves onto next state
    }

    private void doWork() {// Sleep for the amount of time necessary to do the work
        try {
            Thread.sleep(state.timeToComplete);
        } catch (InterruptedException e) {
            System.err.println("Orange was not completly Processesed");
        }
    }
}