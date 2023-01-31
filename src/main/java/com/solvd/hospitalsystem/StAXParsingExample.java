package com.solvd.hospitalsystem;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.helpers.DefaultHandler;

import com.solvd.hospitalsystem.services.HospitalRunner;

import java.io.FileInputStream;

public class StAXParsingExample extends DefaultHandler {
	
	final static Logger logger = LogManager.getLogger(HospitalRunner.class.getName());

	public static void main(String[] args) {
		try {
			XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader reader = factory.createXMLEventReader(new FileInputStream("C:\\Users\\House Games\\eclipse-workspace\\hospitalsystem\\src\\main\\resources\\Appointment.xml"));
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {
                	logger.info(event.toString());
                } else if (event.isEndElement()) {
                	logger.info(event.toString());
                } else if (event.isCharacters()) {
                    //do something 
                }
            }
		} catch (Exception e) {
			logger.info(e);        }
	}
}
