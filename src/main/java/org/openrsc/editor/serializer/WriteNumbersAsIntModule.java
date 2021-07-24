package org.openrsc.editor.serializer;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class WriteNumbersAsIntModule extends SimpleModule {
    public WriteNumbersAsIntModule() {
        super("CustomNumberSerializer");
        final CustomNumberSerializer serializer = new CustomNumberSerializer();
        addSerializer(Number.class, serializer);
        addSerializer(Double.class, serializer);
        addSerializer(double.class, serializer);
        addSerializer(Float.class, serializer);
        addSerializer(float.class, serializer);
    }
}
