package stormTP.topology;

import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.topology.base.BaseWindowedBolt;
import org.apache.storm.tuple.Fields;
import stormTP.operator.*;

public class TopologyT6 {
    public static void main(String[] args) throws Exception {
        int nbExecutors = 1;
        int portINPUT = 9001;
        int portOUTPUT = 9002;
        String ipmINPUT = "224.0.0." + args[0];
        String ipmOUTPUT = "225.0." + args[0] + "." + args[1];
        MasterInputStreamSpout spout = new MasterInputStreamSpout(portINPUT, ipmINPUT);
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("masterStream", spout);
        builder.setBolt("nofilter", new NothingBolt(), nbExecutors).shuffleGrouping("masterStream");
        builder.setBolt("exit", new MyTortoiseBolt(portOUTPUT, ipmOUTPUT), nbExecutors).shuffleGrouping("nofilter");
        builder.setBolt("exit2", new GiveRankBolt(portOUTPUT, ipmOUTPUT), nbExecutors).fieldsGrouping("exit", new Fields("myTortoise"));
        builder.setBolt("exit3", new ComputeMeanBolt().withWindow(new BaseWindowedBolt.Count(3), new BaseWindowedBolt.Count(3)), nbExecutors).fieldsGrouping("exit2", new Fields("giveRank"));
        builder.setBolt("exit4", new RankEvolutionBolt().withWindow(new BaseWindowedBolt.Count(2), new BaseWindowedBolt.Count(1)), nbExecutors).fieldsGrouping("exit3", new Fields("mean"));
        builder.setBolt("exit5", new Exit6Bolt(portOUTPUT, ipmOUTPUT), nbExecutors).fieldsGrouping("exit4", new Fields("evolution"));
        StormSubmitter.submitTopology("topoT6", new Config(), builder.createTopology());
    }
}