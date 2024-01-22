package com.luwh.we.app.core.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author lu.wh
 * @date 2023/09/11 16/57/48
 * @description
 */
public class AESUtil {
    private static final String ALGORITHM_NAME = "AES";
    private static final String CIPHER_MODE = "AES/CBC/PKCS5Padding";

    private byte[] sessionKey;

//    public static void main(String[] args) throws Exception {
//        sessionKey = Base64.getDecoder().decode("bPCNlSicwWX/FtrA2k+tOQ==");
//        String s = decryptData("3xhLIzscd5TsqkRj/H2odu0JaryK32GWMd83JZRH9ehxiQ6mHtOoe5t4+7PqymW+DAQlIMCy/rgBKYoP4v49+VqfN8w0MuTynH7qD07JO3sVFO8iYLYZFY0JuJ64b8uu/MkzYW2Is9UlSJoYDTx9+5x9/eVtnQpi5vck6u1FVCZ9JHPsGd8aNpbf6rdstsb5lyGS7udHhBCoyA05dO7n2g==",
//                "J+uRz84znxnDA02L+NnUJA==");
//        System.out.println(s);
//    }

    public AESUtil build(String sessionKey){
        this.sessionKey = Base64.getDecoder().decode(sessionKey);
        return this;
    }

    public String decryptData(String encryptedData, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_MODE);
        SecretKeySpec secretKeySpec = new SecretKeySpec(sessionKey, ALGORITHM_NAME);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(Base64.getDecoder().decode(iv));
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}
