package com.pawelwlazlo.util;

import androidx.room.TypeConverter;

public class BmiResultTypeConverter {

    @TypeConverter
    public BMITypeResult toBmiResultType(String value) {
        return BMITypeResult.valueOf(value);
    }

    @TypeConverter
    public String fromBmiResultType(BMITypeResult value) {
        return value.name();
    }
}
