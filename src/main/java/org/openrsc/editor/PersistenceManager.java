package org.openrsc.editor;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class PersistenceManager {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> Optional<T> load(InputStream stream, Class<T> type) {
        try {
            BufferedInputStream is = new BufferedInputStream(stream);
            return Optional.of(objectMapper.readValue(is, type));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return Optional.empty();
    }

    public static void write(File file, Object o) {
        try {
            //TODO: Persist files
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
