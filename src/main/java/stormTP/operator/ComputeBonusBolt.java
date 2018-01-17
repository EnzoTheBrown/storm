package stormTP.operator;

import org.apache.storm.state.State;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.IStatefulBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseWindowedBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.apache.storm.windowing.TupleWindow;
import stormTP.core.Runner;
import stormTP.core.TortoiseManager;

import java.util.Map;

public class ComputeBonusBolt extends BaseWindowedBolt{


    @Override
    public void execute(TupleWindow tupleWindow) {
        TortoiseManager tortoiseManager = new TortoiseManager(5, "Candy-Lebrun");
        Runner runner = null;
        Runner first = null;
        int i = 0;
        // getting the last runner position of the window
        for(Tuple tuple : tupleWindow.get()){
            if(i == 0)
                first = (Runner) tuple.getValueByField("giveRank");
            i ++;
            runner = (Runner) tuple.getValueByField("giveRank");
        }

        runner.setPoints(tortoiseManager.computePoints(runner.getRang(), runner.getTotal()) + first.getPoints());

        collector.emit(new Values(runner));
    }


    /* (non-Javadoc)
     * @see backtype.storm.topology.IComponent#declareOutputFields(backtype.storm.topology.OutputFieldsDeclarer)
     */
    public void declareOutputFields(OutputFieldsDeclarer arg0) {
        arg0.declare(new Fields("points"));
    }

    private OutputCollector collector;
    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector){
        this.collector = collector;
    }
}