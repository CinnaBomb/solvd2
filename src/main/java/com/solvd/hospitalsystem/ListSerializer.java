package com.solvd.hospitalsystem;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.List;

public class ListSerializer extends StdSerializer<List> {

    public ListSerializer() {
        this(null);
    }

    public ListSerializer(Class<List> t) {
        super(t);
    }

    @Override
    public void serialize(List value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartArray();
        for (Object obj : value) {
            gen.writeObject(obj);
        }
        gen.writeEndArray();
    }
}

