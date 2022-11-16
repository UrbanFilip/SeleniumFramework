package org.selenium.utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;

public class JsonFile {
        public static <T> T deserializeJson(String fileName, Class<T> T) throws IOException {
            JsonReader reader = new JsonReader(new FileReader("./src/main/resources/" + fileName));

            return new Gson().fromJson(reader, T);
        }
}
