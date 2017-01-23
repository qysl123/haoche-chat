package com.haoche.chat.utils;

import com.haoche.chat.comm.ClientContext;
import com.haoche.chat.comm.TokenGenerator;

/**
 * Created by Ken on 2017/1/23.
 */
public class TokenUtils {
    private static final TokenGenerator tokenGenerator;

    static {
        tokenGenerator = new TokenGenerator(ClientContext.getInstance());
    }

    public static String getToken() {
        return tokenGenerator.request(true);
    }
}
