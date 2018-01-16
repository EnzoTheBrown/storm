package stormTP.core;

import java.io.Serializable;
import java.util.Random;

public class Stream implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private int nbRunners = 10;
	private int nbCells = 254;
	
	private String[] runners = null;
	private String[] names = null;
	private int[] runnersPos = null; 
	public static final String STREAMFILE = "./.streams/runnersRacer";
	
	

	public Stream(){
		this.nbCells = 254;
	}
	
	
	public Stream(String[] runnerNames){
		this();
		this.names = runnerNames;
		this.nbRunners = this.names.length;
		this.runners =  new String[this.nbRunners];
		this.runnersPos = new int[this.nbRunners];
			
	}
	
	

	
	
	public String getMessage(long top){
		String res = "{ \"rabbits\":[ \n";
		
		this.runners = this.getNewRow(top);
		
		for( int i = 0 ; i < this.runners.length - 1 ;  i++){
			res += this.runners[i] + ", \n";
		}
		res += this.runners[this.runners.length - 1] + "\n";
		res += " ] }";
		return res;
	}
	
	
	public String[] getNewRow(long top){
		
		int cpta, cptb = 0;
		Runner rabbits = null;
		String tmp = "";

		Random alea = new Random(System.currentTimeMillis());

		// affectation des nouvelles positions (soit +1 soit rien)
				for(int i = 0 ; i < this.nbRunners ; i++){
					if(runnersPos[i] == this.nbCells) continue;
					
					runnersPos[i] = ( runnersPos[i] + alea.nextInt(10)) % this.nbCells ;
				}
				
		// affectation des rangs
				for(int i = 0 ; i < this.nbRunners ; i++){
					
					if(runnersPos[i] == this.nbCells) continue;
					
					cpta = 0;
					cptb = 0;
					
					for(int j = 0 ; j < this.nbRunners  ; j++){
						
						
						if( runnersPos[i] < runnersPos[j] ){
							cptb++;
						}
						
						if( runnersPos[i] > runnersPos[j] ){
							
							cpta++;
						}
						
					}
					
					
					// construction du row pour le runner i
					rabbits = new Runner(i, this.names[i], cptb, cpta, this.nbRunners, runnersPos[i], top);
					tmp = rabbits.getJSON_V1();
					this.runners[i] = tmp;
					//System.out.println(tmp);
					
				}
				return this.runners;
		
	}
	
	
	

}
