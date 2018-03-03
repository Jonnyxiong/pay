package com.ucpaas.sms.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JacksonUtil {
    private  static ObjectMapper mapper = new ObjectMapper(); // create once, reuse

    public static  String toJSON(Object o) throws JsonProcessingException {
        return  mapper.writeValueAsString(o);
    }

}
