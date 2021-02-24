package br.com.zup.proposta.templateproposta.config.security;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;

public class CryptographyAttributeConverter implements AttributeConverter<String, String> {
    @Autowired
    CryptDecryptUtil cryptDecryptUtil;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return cryptDecryptUtil.encode(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return cryptDecryptUtil.decode(dbData);
    }
}
