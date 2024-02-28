package server;

import java.util.ArrayList;

import com.memory.JsonPayloadOperations;
import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.RemoteDevice;
import com.serotonin.bacnet4j.RemoteObject;
import com.serotonin.bacnet4j.event.DeviceEventListener;
import com.serotonin.bacnet4j.exception.BACnetServiceException;
import com.serotonin.bacnet4j.npdu.ip.IpNetwork;
import com.serotonin.bacnet4j.obj.AnalogInputObject;
import com.serotonin.bacnet4j.obj.BACnetObject;
import com.serotonin.bacnet4j.service.Service;
import com.serotonin.bacnet4j.transport.DefaultTransport;
import com.serotonin.bacnet4j.type.constructed.Address;
import com.serotonin.bacnet4j.type.constructed.Choice;
import com.serotonin.bacnet4j.type.constructed.DateTime;
import com.serotonin.bacnet4j.type.constructed.PropertyValue;
import com.serotonin.bacnet4j.type.constructed.SequenceOf;
import com.serotonin.bacnet4j.type.constructed.TimeStamp;
import com.serotonin.bacnet4j.type.enumerated.EngineeringUnits;
import com.serotonin.bacnet4j.type.enumerated.EventState;
import com.serotonin.bacnet4j.type.enumerated.EventType;
import com.serotonin.bacnet4j.type.enumerated.MessagePriority;
import com.serotonin.bacnet4j.type.enumerated.NotifyType;
import com.serotonin.bacnet4j.type.enumerated.PropertyIdentifier;
import com.serotonin.bacnet4j.type.notificationParameters.NotificationParameters;
import com.serotonin.bacnet4j.type.primitive.CharacterString;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;

import util.BacnetUtils;

public class BacnetServerObject {
	
	private static BacnetServerObject instance;
	
	private LocalDevice localDevice;
	private IpNetwork network; 
	private DefaultTransport transport;
    public ArrayList<BACnetObject> bacnetObjectList; 
	
	
	
	private BacnetServerObject() {
		  network = BacnetUtils.getIpNetwork("192.168.0.68", "255.255.255.255", 24, 47808);
	      int localDeviceID = 11111;
	      transport = new DefaultTransport(network);
	      localDevice = new LocalDevice(localDeviceID, transport);
	      System.out.println("Local device is running with device id " + localDeviceID);
	      localDevice.writePropertyInternal(PropertyIdentifier.objectName, new CharacterString("Bacnet4j Slave Device01"));
	}
	
	 public static BacnetServerObject getInstance() {
	        // Lazy initialization: create the instance if it doesn't exist yet
	        if (instance == null) {
	            instance = new BacnetServerObject();
	        }
	        return instance;
	 }
	 
	 public LocalDevice getLocalDevice() {
			return localDevice;
	 }
	 
	 public  void eventListener() {
	  
		 localDevice.getEventHandler().addListener(new DeviceEventListener() {
	
	          @Override
	          public void textMessageReceived(ObjectIdentifier textMessageSourceDevice, Choice messageClass, MessagePriority messagePriority, CharacterString message) {
	          }
	
	          @Override
	          public void listenerException(Throwable e) {
	              System.out.println("1");
	          }
	
	          @Override
	          public void iAmReceived(RemoteDevice d) {
	          	System.out.println("Test");
	//              Encodable e = null;
	//              try {
	//                  e = RequestUtils.sendReadPropertyAllowNull(localDevice, d, d.getObjectIdentifier(),
	//                          PropertyIdentifier.objectList);
	//              } catch (BACnetException ex) {
	//                  ex.printStackTrace();
	//              }
	//              System.out.println(e);
	//              System.out.println("DiscoveryTest iAmReceived");
	          }
	
	          @Override
	          public boolean allowPropertyWrite(Address from, BACnetObject obj, PropertyValue pv) {
	              localDevice.writePropertyInternal(pv.getPropertyIdentifier(), pv.getValue());
	             // System.out.println(obj + " " + pv.getPropertyIdentifier() + " " + pv.getValue());
	              System.out.println("Object ID :"+obj.getInstanceId() + 
	                                 "Object Local Device: " + obj.getLocalDevice()+
	                                 "Object Name: " + obj.getObjectName()+  
	                                 "Value: "+ pv.getValue().toString());
	              
	              
	              JsonPayloadOperations.writePayload(obj.getInstanceId(),pv.getValue().toString());
	              
	              
	              return true;
	          }
	
	          @Override
	          public void propertyWritten(Address from, BACnetObject obj, PropertyValue pv) {
	              System.out.println("--");
	          }
	
	          @Override
	          public void iHaveReceived(RemoteDevice d, RemoteObject o) {
	              System.out.println("3");
	          }
	
	          @Override
	          public void covNotificationReceived(UnsignedInteger subscriberProcessIdentifier, ObjectIdentifier initiatingDeviceIdentifier, ObjectIdentifier monitoredObjectIdentifier, UnsignedInteger timeRemaining, SequenceOf<PropertyValue> listOfValues) {
	              System.out.println("-----------------");
	              System.out.println(listOfValues.toString());
	              System.out.println("-----------------");
	          }
	
	          @Override
	          public void eventNotificationReceived(UnsignedInteger processIdentifier, ObjectIdentifier initiatingDeviceIdentifier, ObjectIdentifier eventObjectIdentifier, TimeStamp timeStamp, UnsignedInteger notificationClass, UnsignedInteger priority, EventType eventType, CharacterString messageText, NotifyType notifyType, com.serotonin.bacnet4j.type.primitive.Boolean ackRequired, EventState fromState, EventState toState, NotificationParameters eventValues) {
	              System.out.println(processIdentifier);
	          }
	
	          @Override
	          public void synchronizeTime(Address from, DateTime dateTime, boolean utc) {
	              System.out.println(dateTime);
	          }
	
	          @Override
	          public void requestReceived(Address from, Service service) {
	              System.out.println(service.toString());
	          }
	
	      });
	 }
	 
	 public void start() throws Exception {
       localDevice.initialize();
       localDevice.startRemoteDeviceDiscovery();
       localDevice.sendGlobalBroadcast(localDevice.getIAm());
       localDevice.sendLocalBroadcast(localDevice.getIAm());
	 }
	 
	 public void addObject(int value) throws BACnetServiceException {
		 bacnetObjectList.add(new AnalogInputObject(localDevice, 0, "inputTest0", value, EngineeringUnits.noUnits, java.lang.Boolean.TRUE) );
	 }
	 
	 

}
