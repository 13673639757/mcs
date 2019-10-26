package com.mcs.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @ClassName StringToJsonSerializer
 * @Description 序列化时将string转为json格式,避免出现\转义符号
 * @Author szq
 * @Date 2019/9/24 9:44
 * @Version 1.0
 */
public class JsonObjectSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Object o = mapper.readValue(s, Object.class);
        jsonGenerator.writeObject(o);
    }


}
