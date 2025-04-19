package org.app.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {

    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {
        if (value != null) {
            out.value(value.toString()); // ISO формат YYYY-MM-DD
        } else {
            out.nullValue();
        }
    }

    @Override
    public LocalDate read(JsonReader in) throws IOException {
        String date = in.nextString();
        return LocalDate.parse(date);
    }
}
