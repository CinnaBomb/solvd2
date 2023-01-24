package com.solvd.hospitalsystem;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TimestampAdapter extends XmlAdapter<String, Timestamp> {
    private SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Timestamp unmarshal(String timestamp) throws Exception {
        return new Timestamp(timestampFormat.parse(timestamp).getTime());
    }

    @Override
    public String marshal(Timestamp timestamp) throws Exception {
        return timestampFormat.format(timestamp);
    }
}

