package com.lawencon.util;

import java.util.UUID;

public class GeneratorUtil {
    public static String generateCode() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5);
    }
    
}
