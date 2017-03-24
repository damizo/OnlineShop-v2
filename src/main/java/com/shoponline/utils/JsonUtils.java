package com.shoponline.utils;

import com.shoponline.model.dto.ProductCriteriaDTO;
import org.apache.commons.lang.SerializationUtils;

import java.io.Serializable;
import java.util.Base64;

/**
 * Created by Damian on 2017-03-24.
 */
public class JsonUtils {

    public static <T extends Serializable> String encodeBase64(T t) {
        byte[] bytes = SerializationUtils.serialize(t);
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static <T extends Serializable> T decodeBase64(String encodedJson) {
        byte[] decoded = Base64.getDecoder().decode(encodedJson);
        return (T) SerializationUtils.deserialize(decoded);
    }
}
