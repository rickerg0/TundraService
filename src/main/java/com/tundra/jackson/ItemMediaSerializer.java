package com.tundra.jackson;

import java.io.IOException;
import java.util.Base64;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.tundra.entity.ItemTagMedia;

import antlr.StringUtils;

public class ItemMediaSerializer extends StdSerializer<ItemTagMedia> {

    public ItemMediaSerializer() {
        this(null);
    }
   
    public ItemMediaSerializer(Class<ItemTagMedia> t) {
        super(t);
    }
 
    @Override
    public void serialize(ItemTagMedia value, JsonGenerator jgen, SerializerProvider provider) 
      throws IOException, JsonProcessingException {
    	
        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getId());
        
        jgen.writeStringField("mimeType", value.getMimeType());
        
        // send along the description
        if (value.getItemTag() != null && value.getItemTag().getDescription() != null) {
        	jgen.writeStringField("description", value.getItemTag().getDescription());
        } else {
        	jgen.writeStringField("description", "");
        }
        
        // test the mime type to figure out what we should serialize
        // TODO: note the same type of logic needs to be implemented by the client
        if ("text/plain".equals(value.getMimeType())) {
        	jgen.writeStringField("content", new String(value.getContent()));
        } else {
        	jgen.writeStringField("content", new String(Base64.getEncoder().encode(value.getContent())));
        }
        jgen.writeEndObject();
    }
}