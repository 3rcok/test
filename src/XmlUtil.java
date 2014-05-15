

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.odisei.provisioning.ExtensionInfoListType;
import com.odisei.provisioning.ExtensionInfoType;
import com.odisei.provisioning.MasterExtensionInfoListType;
import com.odisei.provisioning.MasterExtensionInfoType;
import com.odisei.provisioning.ObjectFactory;
import com.odisei.provisioning.SlaveExtensionInfoListType;
import com.odisei.provisioning.SlaveExtensionInfoType;


public class XmlUtil {
    public static String convertToXml(Object source, Class... type) {
        String result;
        StringWriter sw = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(type);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);  

            marshaller.marshal(source, sw);
            result = sw.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
    
 
      public static Object convertToObject(String xml, Class... type) {
          
       	 try {
       		StringReader sr = new StringReader(xml);      		 
     		JAXBContext jaxbContext = JAXBContext.newInstance(type);
      
     		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
     		Object obj =  jaxbUnmarshaller.unmarshal(sr);
     		System.out.println(obj);
     		return obj;
      
     	  } catch (JAXBException e) {
     		e.printStackTrace();
     	  }
       	 return null;
     	}
      
      public static void writeToFile(String fileName, String xml) {
    	  Writer writer = null;

    	  try {
    	      writer = new BufferedWriter(new OutputStreamWriter(
    	            new FileOutputStream(fileName), "utf-8"));
    	      writer.write(xml);
    	  } catch (IOException ex) {
    	    // report
    		  ex.printStackTrace();
    	  } finally {
    	     try {writer.close();} catch (Exception ex) {}
    	  }
      }
      
      public static void main(String[] args) {
		System.out.println("xml " + convertToXml(getExtensionInfoType(), ExtensionInfoListType.class));
		System.out.println("xml " );
	}
      
  	public static JAXBElement<ExtensionInfoListType> getExtensionInfoType() {
//  		String iPbxName = System.getProperty("com.odisei.system.process");
  		String iPbxName = "com.odisei.system.process";
  		
  		
		ObjectFactory of = new ObjectFactory();
		ExtensionInfoListType infoList =of.createExtensionInfoListType();
		List list = new ArrayList();
		boolean isMaster = false;
		SlaveExtensionInfoListType slaveList =  of.createSlaveExtensionInfoListType();
		infoList.setSlaveExtensionInfoList(slaveList);
		MasterExtensionInfoListType masterList =  of.createMasterExtensionInfoListType();
		infoList.setMasterExtensionInfoList(masterList);

		for(int i=0;i<4;i++) {
//				if(null == addr) {
//					continue;
//				}
//				System.out.println("address: " + addr.getName());				
				ExtensionInfoType info = of.createExtensionInfoType();

				if(isMaster) {

					info = of.createSlaveExtensionInfoType();
					((SlaveExtensionInfoType)info).setMasterExt(""+1000+i);

					slaveList.getSlaveExtensionInfo().add((SlaveExtensionInfoType)info);
					
				} else {
					info = of.createMasterExtensionInfoType();
					String[] members = {"slave1", "slave2"};
					((MasterExtensionInfoType)info).getSlaveMembers().addAll(Arrays.asList(members));

					masterList.getMasterExtensionInfo().add((MasterExtensionInfoType)info);
				}
				isMaster = !isMaster;
//				PhysicalTerminal term = null;
//				try {
//					term = (PhysicalTerminal)cceProvider.getTerminal(addr.getName());
//				} catch (Exception e) {
//					LOG.trace(e);
//				}
//				if(null != term) {
//					int state = term.getPhysicalState();
//					if(state == PhysicalTerminal.IN_SERVICE) {
//						info.setOnline(true);
//					}
//					System.out.println("address: " + addr.getName() + " physical state:"+state);
//				}
//
//				CfgTerminal terminal = addr.getCfgTerminals()[0];
//				CfgIPGateway cfgIPGateway = terminal.getOwningIPGateway();
//				String macAddress = cfgIPGateway.getMacAddress();
//				String mediaControllerName = cfgIPGateway.getMediaControllerName();
				info.setExtension("111" + i);
				info.setMacAddress("macAddress"+i);
				info.setDeviceModel("mediaControllerName"+i);
				info.setBranch("branch" + i);
				info.setPbx(iPbxName);
/*
				com.netergynet.cce.TerminalImpl cceTerm = null;
				try {
					cceTerm = (com.netergynet.cce.TerminalImpl)cceProvider.getTerminal(addr.getName());
					com.odisei.media.TerminalImpl mediaTerm = (com.odisei.media.TerminalImpl)cceTerm.getMedia();
					com.netergynet.protocolhandler.sip.Handler ph = (com.netergynet.protocolhandler.sip.Handler)mediaTerm.getProtocolHandler();	
					int state = cceTerm.getPhysicalState();
					if(state == PhysicalTerminal.IN_SERVICE) {
						info.setOnline(true);
					}
					System.out.println("address: " + addr.getName() + " physical state:"+state);
					
					String publicIPAddress = ph.getPublicIPAddress();
					int publicPort = ph.getPublicPort();
					
					System.out.println(" Lei: " + publicIPAddress);
					info.setPublicIpAddress(publicIPAddress);
					info.setPublicPort(publicPort);
					*/

//				} catch (InvalidArgumentException e) {
//					// TODO Auto-generated catch block
//					System.out.println(e.getMessage());
//				}


		}//end of for
		return of.createExtensionInfoList(infoList);
		
	}
}
