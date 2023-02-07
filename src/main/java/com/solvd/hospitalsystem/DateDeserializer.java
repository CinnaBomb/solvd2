package com.solvd.hospitalsystem;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.solvd.hospitalsystem.dao.mysql.AppointmentDiagnosisDAO;

public class DateDeserializer extends StdDeserializer<Date> {

	final Logger logger = LogManager.getLogger(AppointmentDiagnosisDAO.class.getName());

	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	public DateDeserializer() {
		this(null);
	}

	public DateDeserializer(Class<Date> t) {
		super(t);
	}

	@Override
	public Date deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		String date = parser.getText();
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			logger.info(e);
		}
		return null;
	}
}
