package mj.core.utils;

import mj.core.domain.Sex;
import org.springframework.core.convert.converter.Converter;

public class TypeConverter implements Converter<String, Sex> {
    @Override
    public Sex convert(String source) {
        return Sex.valueOf(source.toUpperCase());
    }
}
