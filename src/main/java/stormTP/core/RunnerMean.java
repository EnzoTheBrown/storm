package stormTP.core;

public class RunnerMean {
    Runner runner = null;
    double mean = 0;

    public RunnerMean(Runner runner_, double mean_){
            runner = runner_;
            mean = mean_;
    }

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }
}
