package com.lawencon.admin.config;

public class JwtConfig {
    private static final ThreadLocal<String> JWT = new ThreadLocal<>();

    public static String getToken(){
        final String jwtToken = JWT.get();
        return jwtToken;
    }

    public static void setToken(String factory){
        final String jwtToken = factory;
        JWT.set(jwtToken);
    }
}
