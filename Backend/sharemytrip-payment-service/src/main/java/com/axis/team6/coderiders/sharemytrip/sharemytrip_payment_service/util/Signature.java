package com.axis.team6.coderiders.sharemytrip.sharemytrip_payment_service.util;

import java.security.SignatureException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class Signature {

    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

    public static String calculateRFC2104HMAC(String data, String secret) throws SignatureException {
        String result;
        try {
            SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), HMAC_SHA256_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            result = DatatypeConverter.printHexBinary(rawHmac).toLowerCase();
        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC: " + e.getMessage());
        }
        return result;
    }
}