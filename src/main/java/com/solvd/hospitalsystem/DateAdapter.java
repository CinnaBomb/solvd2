package com.solvd.hospitalsystem;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends XmlAdapter<String, Date> {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

    public Date unmarshal(String date) throws Exception {
        return dateFormat.parse(date);
    }

    public String marshal(Date date) throws Exception {
        return dateFormat.format(date);
    }
}

