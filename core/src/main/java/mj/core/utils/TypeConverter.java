package mj.core.utils;

import mj.core.domain.Sex;
import org.springframework.core.convert.converter.Converter;

public class TypeConverter {
    public Sex toSex(String source) {
        return Sex.valueOf(source.toUpperCase());
    }
}
