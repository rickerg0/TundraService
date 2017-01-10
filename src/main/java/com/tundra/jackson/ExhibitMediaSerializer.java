package com.tundra.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.tundra.entity.ExhibitTagMedia;

public class ExhibitMediaSerializer extends StdSerializer<ExhibitTagMedia> {

    public ExhibitMediaSerializer() {
        this(null);
    }
   
    public ExhibitMediaSerializer(Class<ExhibitTagMedia> t) {
        super(t);
    }
 
    @Override
    public void serialize(ExhibitTagMedia value, JsonGenerator jgen, SerializerProvider provider) 
      throws IOException, JsonProcessingException {
    	
        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getId());
        
        jgen.writeStringField("mimeType", value.getMimeType());
        // test the mime type to figure out what we should serialize
        // TODO: note the same type of logic needs to be implemented by the client
        if ("text/plain".equals(value.getMimeType())) {
        	jgen.writeStringField("content", new String(value.getContent()));
        } else {
        	jgen.writeBinaryField("content", value.getContent());
        }
        jgen.writeEndObject();
    }
}