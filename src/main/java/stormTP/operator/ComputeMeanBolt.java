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
import stormTP.core.RunnerMean;
import stormTP.core.TortoiseManager;

import java.util.Map;

public class ComputeMeanBolt extends BaseWindowedBolt{


    @Override
    public void execute(TupleWindow tupleWindow) {
        int rank = 0;
        int i = 0;
        Runner runner = null;
        for(Tuple tuple : tupleWindow.get()){
            i ++;
            rank += Integer.valueOf(((Runner) tuple.getValueByField("giveRank")).getRang().replace("ex", ""));
            runner = (Runner) tuple.getValueByField("giveRank");
        }
        runner.setMean(rank/i);
        collector.emit(new Values(new RunnerMean(runner, rank / i)));
    }


    /* (non-Javadoc)
     * @see backtype.storm.topology.IComponent#declareOutputFields(backtype.storm.topology.OutputFieldsDeclarer)
     */
    public void declareOutputFields(OutputFieldsDeclarer arg0) {
        arg0.declare(new Fields("mean"));
    }

    private OutputCollector collector;
    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector){
        this.collector = collector;
    }
}