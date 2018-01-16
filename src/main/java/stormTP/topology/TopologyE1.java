package stormTP.topology;



import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;

import stormTP.operator.HareSpout;
import stormTP.operator.ComputePodiumBolt;
import stormTP.operator.ExitInLogBolt;

public class TopologyE1 {
	
	public static void main(String[] args) throws Exception {
		
	int nbExecutors = 1;
		
    	HareSpout spout = new HareSpout(System.currentTimeMillis());
    	
    	/**
         * Declaration of the linear topology
         */
        TopologyBuilder builder = new TopologyBuilder();
        
        builder.setSpout("localBigStream", spout);
        
        builder.setBolt("podium", new ComputePodiumBolt(), nbExecutors).shuffleGrouping("localBigStream");
        
        builder.setBolt("exit", new ExitInLogBolt(), nbExecutors).shuffleGrouping("podium");
           
        /**
         * Configuration of metadata of the topology
         */
        Config config = new Config();
        config.setDebug(true);
        config.setNumWorkers(1);
		
	/**
	 * Call to the topology submitter for storm
	 */
		
        StormSubmitter.submitTopology("topoE1", config, builder.createTopology());
        
      
		
	}
}
