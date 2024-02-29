package server;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;



public class TestServer {

	
	public static void main(String[] args) throws Exception {
		//BacnetServerObject server = BacnetServerObject.getInstance();
        //server.start();
		////server.addObject(88);
		//LocalDevice device = server.getLocalDevice();
	    
	   // bacnetObjectList.add(new AnalogInputObject(device, 0, "inputTest0", 10, EngineeringUnits.noUnits, java.lang.Boolean.TRUE) );

		
		 ConcurrentHashMap<Integer,String> map = new ConcurrentHashMap<>();
		 map.put(1, "Java");
		 System.out.println(map.get(1));
		 map.put(1, "C++");
		 System.out.println(map.get(1));
		 
		    InetAddress localhost = InetAddress.getLocalHost();
           System.out.println("Local IP Address: " + localhost.getHostAddress());



		
	}

}
