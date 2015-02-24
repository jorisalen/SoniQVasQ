package api;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class BandcampApi implements Api{

	public void doCall(String query) throws ApiException {
		/*
		 * 
		 * this method calls a python script on the server that executes a scraper script	 
		 * , the script uses google scraper to get data from bandcamp, we use this script because bandcamp
		 * has no public api available (this might change in the future)
		 *
		 */
		
		 String user = "bandcamp";
		 String pass = "scrape";
//		 String host = "naboo";
		 String host = "193.191.187.13";
//		 int port=22;
		 int port=11127;
	    
		    try
		    {
		        JSch jsch = new JSch();
		        
		        java.util.Properties config = new java.util.Properties(); 
		        config.put("StrictHostKeyChecking", "no");
		        Session session = jsch.getSession(user, host, port);
		        session.setConfig(config);

		        session.setPassword(pass);
		        session.connect();

		        ChannelExec channelExec = (ChannelExec)session.openChannel("exec");
		        
		        channelExec.setCommand("cd dianoga ;sh script.sh " + query );

		        channelExec.connect();
		      
		        channelExec.disconnect();
		        session.disconnect();
		    }
		    catch(Exception e)
		    {
		    	throw new ApiException("failed to acces bandcamp");
		    }
		    
	}


}
