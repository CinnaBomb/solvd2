package com.solvd.hospitalsystem;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListDeserializer extends StdDeserializer<List> {

    public ListDeserializer() {
        this(null);
    }

    public ListDeserializer(Class<List> t) {
        super(t);
    }

    @Override
    public List deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        List<Object> list = new ArrayList<>();
        JsonNode node = p.getCodec().readTree(p);
        Iterator<JsonNode> elements = node.elements();
        while (elements.hasNext()) {
            list.add(elements.next());
        }
        return list;
    }
}


