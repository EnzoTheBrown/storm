package stormTP.operator;


	import java.io.StringReader;
	import java.util.Map;
	import java.util.logging.Logger;

	import javax.json.Json;
	import javax.json.JsonObject;
	import javax.json.JsonObjectBuilder;
	import javax.json.JsonReader;

	import org.apache.storm.task.OutputCollector;
	import org.apache.storm.task.TopologyContext;
	import org.apache.storm.topology.IRichBolt;
	import org.apache.storm.topology.OutputFieldsDeclarer;
	import org.apache.storm.tuple.Fields;
	import org.apache.storm.tuple.Tuple;
	import org.apache.storm.tuple.Values;
	import org.apache.storm.utils.Utils;

import stormTP.core.TortoiseManager;
import stormTP.observer.ObserverHook;


public class ComputePodiumBolt implements IRichBolt {

		private static final long serialVersionUID = 4262369370788456843L;
		private OutputCollector collector;
		private static Logger logger = Logger.getLogger("ComputePodiumBolt");
		
			
		/* (non-Javadoc)
		 * @see org.apache.storm.topology.IRichBolt#execute(org.apache.storm.tuple.Tuple)
		 */
		public void execute(Tuple t) {
		
			 logger.info("[ComputePodiumBolt] EXEC");
					
			/* récupération du message */
			String n = t.getValueByField("json").toString();
			
			String res = TortoiseManager.getPodium( n );

			  //Utils.sleep(100);
			  logger.info("[ComputePodiumBolt] " + res);
			  collector.emit(t, new Values(res));
			  collector.ack(t);
		}
				
			
		
		/* (non-Javadoc)
		 * @see org.apache.storm.topology.IComponent#declareOutputFields(org.apache.storm.topology.OutputFieldsDeclarer)
		 */
		public void declareOutputFields(OutputFieldsDeclarer arg0) {
			arg0.declare( new Fields("json"));
			
		}
			

		/* (non-Javadoc)
		 * @see org.apache.storm.topology.IComponent#getComponentConfiguration()
		 */
		public Map<String, Object> getComponentConfiguration() {
			return null;
		}

		/* (non-Javadoc)
		 * @see org.apache.storm.topology.IBasicBolt#cleanup()
		 */
		public void cleanup() {
			
		}
		
		
		
		/* (non-Javadoc)
		 * @see org.apache.storm.topology.IRichBolt#prepare(java.util.Map, org.apache.storm.task.TopologyContext, org.apache.storm.task.OutputCollector)
		 */
		@SuppressWarnings("rawtypes")
		public void prepare(Map arg0, TopologyContext context, OutputCollector collector) {
			this.collector = collector;
			context.addTaskHook(new ObserverHook());
			
		}
	}