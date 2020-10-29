package com.jesse.sample.networkchat.util;

import okhttp3.MediaType;

public class CommonUtil {

    public static final MediaType JSONMEDIATYPE= MediaType.parse("application/json; charset=utf-8");

    public static final boolean isStringNotEmpty(String s) {

        return s !=null && !s.trim().equals("");
    }
}
