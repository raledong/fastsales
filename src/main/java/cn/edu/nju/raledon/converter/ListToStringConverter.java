package cn.edu.nju.raledon.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by rale on 7/9/17.
 * List转换为String
 */
@Converter
public class ListToStringConverter implements AttributeConverter<List, String>{
    @Override
    public String convertToDatabaseColumn(List attribute) {
        if (attribute==null || attribute.size()==0 ){
            return "";
        }
        StringBuilder s = new StringBuilder();
        Iterator i = attribute.iterator();
        while (i.hasNext()){
            s.append(i.next());
            s.append(":");
        }
        return s.toString();
    }

    @Override
    public List convertToEntityAttribute(String dbData) {
        if(dbData==null || dbData.isEmpty()){
            return new ArrayList();
        }
        return Arrays.asList(dbData.split(":"));
    }
}
