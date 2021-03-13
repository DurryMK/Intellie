package com.intellie.user.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/4 16:39
 * @describe:PBE加密解密工具
 */
public class PBEUtil {

    /**
     * JAVA6支持以下任意一种算法
     * PBEWITHMD5ANDDES
     * PBEWITHMD5ANDTRIPLEDES
     * PBEWITHSHAANDDESEDE
     * PBEWITHSHA1ANDRC2_40
     * PBKDF2WITHHMACSHA1
     */
    public static final String ALGORITHM = "PBEWITHMD5andDES";

    /**
     * 迭代次数
     */
    public static final int ITERATION_COUNT = 100;

    public static final byte[] salt = "SALTDURY".getBytes();

    /**
     * 转换密钥
     */
    private static Key toKey(String password) throws Exception {
        //密钥转换
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        //实例化
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        //生成密钥
        SecretKey secretKey = keyFactory.generateSecret(keySpec);

        return secretKey;
    }

    /**
     * 加密
     *
     */
    public static String encrypt(String str, String password) throws Exception {
        byte[] data = str.getBytes();
        //转换密钥
        Key key = toKey(password);
        //实例化PBE参数材料
        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, ITERATION_COUNT);
        //实例化
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        //初始化
        cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
        //执行操作
        return Base64.encodeBase64String(cipher.doFinal(data));
    }

    /**
     * 解密
     * 传入的str 必须是经过Base64.encodeBase64String处理后的字符串
     */
    public static String decrypt(String str, String password) throws Exception {
        byte[] bytes = Base64.decodeBase64(str);
        //转换密钥
        Key key = toKey(password);
        //实例化PBE参数材料
        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, ITERATION_COUNT);
        //实例化
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        //初始化
        cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        //执行操作
        return new String(cipher.doFinal(bytes));
    }

    private static char[] randomStr = "QWERTYUIOPLKJHGFDSAMNBVCXZqwertyuioplkjhgfdsazxcvbnm1234567890".toCharArray();

    private static Random rd = new Random();

    //创建一个随机密钥
    public static String genSecretKey(int length) {
        String key = "";
        while (length-- > 0) {
            key += (char) (randomStr[rd.nextInt(randomStr.length)]);
        }
        return key;
    }
}
