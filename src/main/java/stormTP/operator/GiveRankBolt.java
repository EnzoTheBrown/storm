package stormTP.operator;


import java.util.Map;
//import java.util.logging.Logger;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import stormTP.core.Runner;
import stormTP.stream.StreamEmiter;
import stormTP.core.TortoiseManager;

public class GiveRankBolt implements IRichBolt {

    private static final long serialVersionUID = 4262369370788107342L;
    //private static Logger logger = Logger.getLogger("ExitBolt");
    private OutputCollector collector;
    String ipM = "";
    int port = -1;
    StreamEmiter semit = null;

    public GiveRankBolt (int port, String ip) {
        this.port = port;
        this.ipM = ip;
        this.semit = new StreamEmiter(this.port,this.ipM);

    }

    /* (non-Javadoc)
     * @see backtype.storm.topology.IRichBolt#execute(backtype.storm.tuple.Tuple)
     */
    public void execute(Tuple t) {

        Runner runner = (Runner) t.getValueByField("myTortoise");
        TortoiseManager tm = new TortoiseManager(5, "candy-lebrun");
        Runner runner2 = tm.computeRank(runner.getId(), runner.getTop(), runner.getNom(), runner.getNbDevant(), runner.getNbDerriere(), runner.getTotal());
        collector.ack(t);
        collector.emit(new Values(runner2));

        return;

    }



    /* (non-Javadoc)
     * @see backtype.storm.topology.IComponent#declareOutputFields(backtype.storm.topology.OutputFieldsDeclarer)
     */
    public void declareOutputFields(OutputFieldsDeclarer arg0) {
        arg0.declare(new Fields("giveRank"));
    }


    /* (non-Javadoc)
     * @see backtype.storm.topology.IComponent#getComponentConfiguration()
     */
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

    /* (non-Javadoc)
     * @see backtype.storm.topology.IBasicBolt#cleanup()
     */
    public void cleanup() {

    }

    /* (non-Javadoc)
     * @see backtype.storm.topology.IRichBolt#prepare(java.util.Map, backtype.storm.task.TopologyContext, backtype.storm.task.OutputCollector)
     */
    @SuppressWarnings("rawtypes")
    public void prepare(Map arg0, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }
}
