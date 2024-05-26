package com.auth.authoriation.config;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
@NoArgsConstructor
public class GenerateSecretKey {
    public String keySecretGenerator() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] secretKey = new byte[64]; // 512 bits
        secureRandom.nextBytes(secretKey);
        //System.out.println(bytesToHex(secretKey));
        return bytesToHex(secretKey);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
