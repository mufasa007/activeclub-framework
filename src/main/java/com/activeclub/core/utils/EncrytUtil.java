package com.activeclub.core.utils;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @date 2020/12/21 23:40
 * @email
 * @descript 加解密工具
 */
//@Bean
public class EncrytUtil {

    @Value("${encry.salt:activeclub}")// ZJKTODO: 椒盐噪声待添加 2021/1/18 20:48
    private String salt;//加密中的椒盐

    public static final String PUBLIC_KEY = "public_key";
    public static final String PRIVATE_KEY = "private_key";

    public static final String AES_ENCRY = "0";//对称加密（默认）
    public static final String PRIVATE_CIPHER_PUBLIC = "1";//私钥加密\公钥解密
    public static final String PUBLIC_CIPHER_PRIVATE = "2";//公钥加密\私钥解密

    private static final String RSA = "RSA";
    private static final String AES = "AES";

    /**
     * 初始化
     *
     * @return 公钥和私钥的map
     * @throws Exception 异常
     */
    public Map<String, String> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA);
        keyPairGen.initialize(2048);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        Map<String, String> keyMap = new HashMap<>();
        // 公钥
        keyMap.put(PUBLIC_KEY, Base64.encodeBase64String(keyPair.getPublic().getEncoded()));
        // 私钥
        keyMap.put(PRIVATE_KEY, Base64.encodeBase64String(keyPair.getPrivate().getEncoded()));
        return keyMap;
    }

    /**
     * 生成密钥对象(对称加密)
     */
    private SecretKey generateKey(byte[] key) throws Exception {
        // 根据指定的 RNG 算法, 创建安全随机数生成器
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        // 设置 密钥key的字节数组 作为安全随机数生成器的种子
        random.setSeed(key);

        // 创建 AES算法生成器
        KeyGenerator gen = KeyGenerator.getInstance("AES");
        // 初始化算法生成器
        gen.init(128, random);

        // 生成 AES密钥对象, 也可以直接创建密钥对象: return new SecretKeySpec(key, ALGORITHM);
        return gen.generateKey();
    }

    /**
     * 加密
     *
     * @param clearString 明文
     * @param keyString   钥
     * @param encodeMode  加密模式:0私钥加密\公钥解密,1公钥加密\私钥解密
     * @return 密文
     * @throws Exception 异常
     */
    public String Encode(String clearString, String encodeMode, String keyString) throws Exception {
        byte[] keyByte;
        if(NullUtil.strIsNull(keyString)){
            keyByte = Base64.decodeBase64(salt);
        }else {
            keyByte = Base64.decodeBase64(keyString);
        }
        byte[] result;
        KeyFactory keyFactory;
        Cipher cipher;

        if (!NullUtil.strIsNull(encodeMode) && encodeMode.equals(PRIVATE_CIPHER_PUBLIC)) {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyByte);
            keyFactory = KeyFactory.getInstance(RSA);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            result = cipher.doFinal(clearString.getBytes());

        } else if (!NullUtil.strIsNull(encodeMode) && encodeMode.equals(PUBLIC_CIPHER_PRIVATE)) {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyByte);
            keyFactory = KeyFactory.getInstance(RSA);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            result = cipher.doFinal(clearString.getBytes());

        } else {
            SecretKey secKey = generateKey(keyByte);
            cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, secKey);
            result = cipher.doFinal(clearString.getBytes());

        }
        return Base64.encodeBase64String(result);
    }

    /**
     * 解密
     *
     * @param cipherString 密文
     * @param keyString    钥
     * @param encodeMode   加密模式:0私钥加密\公钥解密,1公钥加密\私钥解密
     * @return 明文
     * @throws Exception 异常
     */
    public String Decode(String cipherString, String encodeMode, String keyString) throws Exception {
        byte[] cipherByte = Base64.decodeBase64(cipherString.getBytes());//密文
        byte[] keyByte;
        if(NullUtil.strIsNull(keyString)){
            keyByte = Base64.decodeBase64(salt);
        }else {
            keyByte = Base64.decodeBase64(keyString);
        }
        byte[] result;
        KeyFactory keyFactory;
        Cipher cipher;

        if (!NullUtil.strIsNull(encodeMode) && encodeMode.equals(PRIVATE_CIPHER_PUBLIC)) {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyByte);
            keyFactory = KeyFactory.getInstance(RSA);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            result = cipher.doFinal(cipherByte);

        } else if (!NullUtil.strIsNull(encodeMode) && encodeMode.equals(PUBLIC_CIPHER_PRIVATE)) {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyByte);
            keyFactory = KeyFactory.getInstance(RSA);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            result = cipher.doFinal(cipherByte);

        } else {
            SecretKey secKey = generateKey(keyByte);
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secKey);
            result = cipher.doFinal(cipherByte);

        }
        return new String(result);
    }

//    /**
//     * 样例
//     *
//     * @param args
//     * @throws Exception
//     */
//    public static void main(String[] args) throws Exception {
//        EncrytUtil encrytUtil = new EncrytUtil();
//        Map<String, String> map = encrytUtil.initKey();
//        String clear = "hello world! ";
//
//        String cipher1 = encrytUtil.Encode(clear, "0", "active");
//        String cipher2 = encrytUtil.Encode(clear, "1", map.get(PRIVATE_KEY));
//        String cipher3 = encrytUtil.Encode(clear, "2", map.get(PUBLIC_KEY));
//
//        String clear1 = encrytUtil.Decode(cipher1, "0", "active");
//        String clear2 = encrytUtil.Decode(cipher2, "1", map.get(PUBLIC_KEY));
//        String clear3 = encrytUtil.Decode(cipher3, "2", map.get(PRIVATE_KEY));
//
//        System.out.println(cipher1);
//        System.out.println(cipher2);
//        System.out.println(cipher3);
//
//        System.out.println(clear1);
//        System.out.println(clear2);
//        System.out.println(clear3);
//    }

}
