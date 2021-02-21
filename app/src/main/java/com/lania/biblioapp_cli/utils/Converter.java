package com.lania.biblioapp_cli.utils;

import androidx.databinding.InverseMethod;

public class Converter {

    @InverseMethod("integer2String")
    public static String string2Integer(Integer newValue) {
        if (newValue == null) return "";
        if (newValue == 0) return "";
        return newValue.toString();
    }

    public static Integer integer2String(String newValue) {
        if (newValue == null) return 0;
        if (newValue.isEmpty()) return 0;
        return Integer.parseInt(newValue);
    }


    @InverseMethod("float2String")
    public static String string2Float(Float newValue) {
        if (newValue == null) return "";
        if (newValue == 0) return "";
        return newValue.toString();
    }

    public static Float float2String(String newValue) {
        if (newValue == null) return 0f;
        if (newValue.isEmpty()) return 0f;
        return Float.parseFloat(newValue);
    }


}
