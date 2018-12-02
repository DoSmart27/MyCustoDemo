package com.vvc.ourcustoapp.common.webservices;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.vvc.ourcustoapp.utils.AndroidUtils;
import com.vvc.ourcustoapp.utils.Constants;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Calendar;

public class DateGsonTypeAdapter implements JsonDeserializer<Calendar>, JsonSerializer<Calendar> {
    @Override
    public Calendar deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            return AndroidUtils.getDate(jsonElement.getAsString(), Constants.APP_DATE_FORMAT);
        } catch (ParseException ignore) {
            return Calendar.getInstance();
        }
    }

    @Override
    public JsonElement serialize(Calendar calendar, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(AndroidUtils.getDateInStringFormat(calendar, Constants.APP_DATE_FORMAT));
    }
}
