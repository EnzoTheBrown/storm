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
import stormTP.core.RunnerEvolution;
import stormTP.core.RunnerMean;
import stormTP.core.TortoiseManager;

import java.util.Map;

public class RankEvolutionBolt extends BaseWindowedBolt{


    @Override
    public void execute(TupleWindow tupleWindow) {
        TortoiseManager tortoiseManager = new TortoiseManager(5, "Candy-Lebrun");
        RunnerMean runner1 = (RunnerMean) tupleWindow.get().get(0).getValueByField("mean");
        if(tupleWindow.get().size() == 1) {
            collector.emit(new Values(new RunnerEvolution(runner1.getRunner(), "no result")));
            return;
        }
        else{
            RunnerMean runner2 = (RunnerMean) tupleWindow.get().get(1).getValueByField("mean");
            collector.emit(new Values(new RunnerEvolution(runner2.getRunner(), tortoiseManager.giveRankEvolution(runner2.getMean(), runner1.getMean()))));
        }
    }


    /* (non-Javadoc)
     * @see backtype.storm.topology.IComponent#declareOutputFields(backtype.storm.topology.OutputFieldsDeclarer)
     */
    public void declareOutputFields(OutputFieldsDeclarer arg0) {
        arg0.declare(new Fields("evolution"));
    }

    private OutputCollector collector;
    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector){
        this.collector = collector;
    }
}