package stormTP.stream;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class StreamBuffer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Queue<String> fifo = null;
	private int port = -1;
	String ipM = "";

	
	public StreamBuffer(int port, String ip){
			this.port = port;
			this.ipM = ip;
			this.fifo = new ConcurrentLinkedQueue<String>();

		
	}
	
	
	public void listenStream()
    {
		
		   
		    MulticastSocket mcSocket = null;
		    InetAddress mcIPAddress = null;
		    		
		    try{
		    mcIPAddress = InetAddress.getByName(this.ipM);
		    mcSocket = new MulticastSocket(this.port);
		    //System.out.println("Multicast Receiver running at:"+ mcSocket.getLocalSocketAddress());
		    mcSocket.joinGroup(mcIPAddress);

		    DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

		      String msg = null;
		
        	mcSocket.receive(packet);
 		    msg = new String(packet.getData(), packet.getOffset(), packet.getLength());
 		    this.fifo.add( msg );
 		    
		    }catch(Exception e){e.printStackTrace();}
		    finally{
		    	try{
		    	mcSocket.leaveGroup(mcIPAddress);
		    	mcSocket.close();
		    	}catch(IOException ioe){ ioe.printStackTrace();} 
		    }
	    
    }
	
	
	 public String readTuple() throws Exception {
	      
		 return  this.fifo.poll();
		  
		   
	  }
	
	
	
	 
		

}
