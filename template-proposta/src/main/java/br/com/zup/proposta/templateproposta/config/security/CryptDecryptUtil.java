package br.com.zup.proposta.templateproposta.config.security;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class CryptDecryptUtil {

    private final String ALGORITHM = "AES/ECB/PKCS5Padding";
    @Value("${encryptor.salt}")
    private String KEY;

    public String encode(String value) {
        Key key = new SecretKeySpec(KEY.getBytes(), "AES");
        try {
            final Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, key);
            return new String(Base64.encodeBase64(c.doFinal(value.getBytes())), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String decode(String value) {
        Key key = new SecretKeySpec(KEY.getBytes(), "AES");
        try {
            final Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, key);
            return new String(c.doFinal(Base64.decodeBase64(value.getBytes(StandardCharsets.UTF_8))));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
