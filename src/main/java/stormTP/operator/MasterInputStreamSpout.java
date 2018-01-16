/**
 * 
 */
package stormTP.operator;

import java.io.StringReader;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import stormTP.stream.StreamBuffer;

/**
 * @author lumineau
 *
 */

public class MasterInputStreamSpout implements IRichSpout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -299357684149329360L;
	private static Logger logger = Logger.getLogger("StreamSimSpoutLogger");
	//private String host;
	private String ipM = "";
	private SpoutOutputCollector collector;
	private StreamBuffer sbuff = null;
	private long indextuple = -1; 
	
	
	/**
	 * 
	 */
	public MasterInputStreamSpout(int port, String ip) {
		this.ipM = ip;
		this.sbuff = new StreamBuffer(port, this.ipM);
		this.indextuple = 0;
			
	}
	
	
	/* (non-Javadoc)
	 * @see org.apache.storm.spout.ISpout#nextTuple()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void nextTuple() {
		
		this.sbuff.listenStream();
		
		String json = null;
		
		try{
			
			json = this.sbuff.readTuple();
			 System.out.println("*** In MasterInputStreamSpout : " + json + " is recieved !");
    		    		
    	}
    	catch(NoSuchElementException nsee){ //logger.info("**** I'm waiting !");
	    	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}catch(Exception e){ 
    		System.out.println(e.getMessage());
    	
    	}	
				
		
		this.collector.emit(new Values(json), this.indextuple);
		//this.ack(this.indextuple);
		this.indextuple++;
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
		logger.info("StreamSimSpout " + MasterInputStreamSpout.serialVersionUID + " is being closed.");
	}

	/* (non-Javadoc)
	 * @see org.apache.storm.spout.ISpout#activate()
	 */
	@Override
	public void activate() {
		logger.info("StreamSimSpout " + MasterInputStreamSpout.serialVersionUID + " is being activated.");
	}

	/* (non-Javadoc)
	 * @see org.apache.storm.spout.ISpout#deactivate()
	 */
	@Override
	public void deactivate() {
		logger.info("StreamSimSpout " + MasterInputStreamSpout.serialVersionUID + " is being deactivated.");
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
	
	}

	/* (non-Javadoc)
	 * @see org.apache.storm.topology.IComponent#declareOutputFields(org.apache.storm.topology.OutputFieldsDeclarer)
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare( new Fields("json"));
		
	}

	/* (non-Javadoc)
	 * @see org.apache.storm.topology.IComponent#getComponentConfiguration()
	 */
	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}
}