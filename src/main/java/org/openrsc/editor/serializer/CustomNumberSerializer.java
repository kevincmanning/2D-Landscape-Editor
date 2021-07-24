package org.openrsc.editor.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CustomNumberSerializer extends JsonSerializer<Number> {
    @Override
    public void serialize(
            Number value,
            JsonGenerator generator,
            SerializerProvider provider
    ) throws IOException {
        generator.writeNumber(value.intValue());
    }
}