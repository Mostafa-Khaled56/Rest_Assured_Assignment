package data.Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Payload;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
//Class to read the JSON file
public class DataReader
{
    //Method to read the JSON file
    public static List<Payload> loadUserRequests(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IOException("File not found in classpath: " + fileName);
            }
            return mapper.readValue(inputStream, new TypeReference<>() {});
        }
    }
}
