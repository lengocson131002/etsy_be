package com.app.commerce.annotations;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractDoubleSerializer extends JsonDeserializer<Double> {

    private static final String DOUBLE_REGEX_PATTERN = "(-?[0-9]+(?:[.][0-9]+)?)";

    @Override
    public Double deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
        ObjectCodec oc = p.getCodec();
        JsonNode node = oc.readTree(p);
        if (node.isNull()) {
            return null;
        }
        if (node.isTextual()) {
            Pattern pattern = Pattern.compile(DOUBLE_REGEX_PATTERN);
            Matcher matcher = pattern.matcher(node.asText());
            String doubleString = "";
            if (matcher.find() && !((doubleString = matcher.group(0)).isEmpty())) {
                return Double.parseDouble(doubleString);
            }
        } else if (node.isNumber()) {
            return node.asDouble();
        }

        return null;
    }


}
