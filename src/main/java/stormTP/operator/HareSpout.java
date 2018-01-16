/**
 * 
 */
package stormTP.operator;

import java.util.List;

import java.util.Map;
import java.util.Random;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;
import stormTP.core.Stream;

/**
 * @author Lumineau
 *
 */

public class HareSpout implements IRichSpout {

	private static final long serialVersionUID = -299357684149378960L;
	private SpoutOutputCollector collector;
	private long msgId = 0;
    private long initTimestamp = 0;
    private Stream stream = null;
   
	
    public HareSpout(long initts){
    	this.initTimestamp = initts;
    	this.msgId = 0;
    	String[] rabbits = new String[10];
    	rabbits[0] = "RogerRabbit";
    	rabbits[1] = "BugsBunny";
    	rabbits[2] = "Panpan";
    	rabbits[3] = "Caerbannog";
    	rabbits[4] = "Oswald";
    	rabbits[5] = "Jojo";
    	rabbits[6] = "Coco";
    	rabbits[7] = "JudyHopps";
    	rabbits[8] = "LapinBlanc";
    	rabbits[9] = "Basil";
    	this.stream = new Stream( rabbits ); 
    }
    
	
	/* (non-Javadoc)
	 * @see org.apache.storm.spout.ISpout#nextTuple()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void nextTuple() {
		
		String msg = this.stream.getMessage( this.msgId );
			    
		collector.emit(new Values(msg), ++this.msgId);
		
		
	    Utils.sleep(100);
	}

	
	
	/* (non-Javadoc)
	 * @see org.apache.storm.spout.ISpout#open(java.util.Map, org.apache.storm.task.TopologyContext, org.apache.storm.spout.SpoutOutputCollector)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
       
	}



	/* (non-Javadoc)
	 * @see org.apache.storm.spout.ISpout#close()
	 */
	@Override
	public void close() {
		
	}

	/* (non-Javadoc)
	 * @see org.apache.storm.spout.ISpout#activate()
	 */
	@Override
	public void activate() {
		
	}

	/* (non-Javadoc)
	 * @see org.apache.storm.spout.ISpout#deactivate()
	 */
	@Override
	public void deactivate() {
		
	}

	

	/* (non-Javadoc)
	 * @see org.apache.storm.spout.ISpout#ack(java.lang.Object)
	 */
	@Override
	public void ack(Object msgId) {
	}

	/* (non-Javadoc)
	 * @see org.apache.storm.spout.ISpout#fail(java.lang.Object)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void fail(Object msgId) {
		System.out.println("[PodiumFail] Failure (msg num:"+ msgId +") after " + (System.currentTimeMillis() - this.initTimestamp) + " ms" );
	
	}


	/* (non-Javadoc)
	 * @see org.apache.storm.topology.IComponent#declareOutputFields(org.apache.storm.topology.OutputFieldsDeclarer)
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		 declarer.declare(new Fields("json"));
		
	}

	/* (non-Javadoc)
	 * @see org.apache.storm.topology.IComponent#getComponentConfiguration()
	 */
	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}
}