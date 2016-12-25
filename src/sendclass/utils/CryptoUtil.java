package sendclass.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class CryptoUtil {
	private static final String ENCODE = "UTF-8";

	public static String encryptAES(String data, String key) {
		try {
			return encrypt(data, key, "AES", "AES/ECB/PKCS5Padding");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	public static String decryptAES(String data, String key) {
		try {
			return decrypt(data, key, "AES", "AES/ECB/PKCS5Padding");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 加密
	 * @param content
	 * @param key
	 * @param algorithm
	 * @param transformation
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String content, String key, String algorithm, String transformation) throws Exception {
		try {
			Cipher aesECB = Cipher.getInstance(transformation);
			SecretKeySpec secretKeySpec = new SecretKeySpec(key(key), algorithm);
			aesECB.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			byte[] result = aesECB.doFinal(content.getBytes(ENCODE));
			return new String(Base64.encodeBase64(result));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 解密
	 * @param content 密文
	 * @param key 盐
	 * @param algorithm  解密类型
	 * @param transformation  创建密码器
	 * @return 
	 * @throws Exception
	 */
	public static String decrypt(String content, String key, String algorithm, String transformation) throws Exception {
		if (content == null) {
			throw new IllegalArgumentException();
		}
		try {
			Cipher cipher = Cipher.getInstance(transformation);// 创建密码器
			SecretKeySpec secretKeySpec = new SecretKeySpec(key(key), algorithm);
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);// 初始化
			byte[] result = Base64.decodeBase64(content.getBytes(ENCODE));
			return new String(cipher.doFinal(result), ENCODE); // 解密
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * 再次处理key，防止别人拿到原来的key
	 * @param key
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static byte[] key(String key) throws UnsupportedEncodingException {
		byte[] shaBytes = sha256(key);
		byte[] key_ = new byte[8];
		System.arraycopy(shaBytes, 0, key_, 0, 8);
		return new String(Hex.encodeHex(key_)).getBytes(ENCODE);
	}

	public static byte[] sha256(String str) {
		return digest(str, "SHA-256");
	}

	public static byte[] digest(String str, String algorithm) {
		if (str == null) {
			return null;
		}
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(algorithm);
			byte[] hash = digest.digest(str.getBytes(ENCODE));
			return hash;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
