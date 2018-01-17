package stormTP.operator;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseWindowedBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.apache.storm.windowing.TupleWindow;
import stormTP.core.Runner;
import stormTP.core.TortoiseManager;

import java.util.Map;

public class SpeedBolt extends BaseWindowedBolt{

    @Override
    public void execute(TupleWindow tupleWindow) {
        Runner firstRunner = null;
        Runner lastRunner = null;
        TortoiseManager tortoiseManager = new TortoiseManager(5, "Candy-Lebrun");
        int i = 0;
        for(Tuple tuple : tupleWindow.get()) {
            if (i == 0)
                firstRunner = (Runner) tuple.getValueByField("myTortoise");
            lastRunner = (Runner) tuple.getValueByField("myTortoise");
        }
        double speed = tortoiseManager.computeSpeed(
                firstRunner.getTop(),
                lastRunner.getTop(),
                firstRunner.getNbDevant(),
                lastRunner.getNbDevant()
        );
        lastRunner.setSpeed(speed);

        collector.emit(new Values(lastRunner));
    }
    public void declareOutputFields(OutputFieldsDeclarer arg0) {
        arg0.declare(new Fields("speed"));
    }

    private OutputCollector collector;
    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector){
        this.collector = collector;
    }
}