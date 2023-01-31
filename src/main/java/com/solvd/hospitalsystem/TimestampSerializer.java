package com.solvd.hospitalsystem;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class TimestampSerializer extends StdSerializer<Timestamp> {

    private static final long serialVersionUID = 1L;
    private SimpleDateFormat formatter = 
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    public TimestampSerializer() {
        this(null);
    }

    public TimestampSerializer(Class<Timestamp> t) {
        super(t);
    }

    @Override
    public void serialize(Timestamp value, JsonGenerator gen, 
                         SerializerProvider provider) throws IOException {
        gen.writeString(formatter.format(value));
    }
}

