package com.app.commerce.util;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class NumberUtils {

    private static final String DOUBLE_REGEX_PATTERN = "(-?[0-9]+(?:[.][0-9]+)?)";

    public static Double extractDoubleFromString(String string) {
        try {
            Pattern pattern = Pattern.compile(DOUBLE_REGEX_PATTERN);
            Matcher matcher = pattern.matcher(string);
            String doubleString = "";
            if (matcher.find() && !((doubleString = matcher.group(0)).isEmpty())) {
                return Double.parseDouble(doubleString);
            }
            return null;
        } catch (Exception ex) {
            log.error("Parse error: {}", ex.getMessage());
        }
        return null;
    }
}
