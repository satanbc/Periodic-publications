package org.app.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void writeJson(HttpServletResponse response, Object object) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            objectMapper.writeValue(response.getWriter(), object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
