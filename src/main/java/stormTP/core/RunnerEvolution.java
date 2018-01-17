package stormTP.core;

public class RunnerEvolution {
    Runner runner = null;
    String evolution;

    public RunnerEvolution(Runner runner_, String evolution_){
        runner = runner_;
        evolution = evolution_;
    }

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public String getEvolution() {
        return evolution;
    }

    public void setEvolution(String evolution) {
        this.evolution = evolution;
    }
}
