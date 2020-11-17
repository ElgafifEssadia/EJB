package com.project.businesslayer;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.BytesMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Message-Driven Bean implementation class for: MDB
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "projetDestination"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "jms/projetDestination")
public class MDB implements MessageListener {

    /**
     * Default constructor. 
     */
    public MDB() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        // TODO Auto-generated method stub
    	BytesMessage bm = (BytesMessage)message;
		File file = new File(bm);
		FileOutputStream fos = new FileOutputStream(file);
		BufferedOutputStream outBuf = new BufferedOutputStream(fos);
		int i;
		while((i=bm.readInt())!=-1){
		   outBuf.write(i);
		}
		outBuf.close();
		fos.close();
        
    }

}
