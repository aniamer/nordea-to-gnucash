package priv.nordea.csv;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.MappingStrategy;

public class CsvToNordeaBean extends CsvToBean<NordeaCSVRow>{

@Override
protected NordeaCSVRow processLine(MappingStrategy<NordeaCSVRow> mapper,
		String[] line) throws IllegalAccessException,
		InvocationTargetException, InstantiationException,
		IntrospectionException {
	NordeaCSVRow bean = mapper.createBean();
    for (int col = 0; col < line.length; col++) {
        PropertyDescriptor prop = mapper.findDescriptor(col);
        if (null != prop) {
            //String value = super.checkForTrim(line[col], prop);
            Object obj = convertValue(line[col], prop);
            prop.getWriteMethod().invoke(bean, obj);
        }
    }
     	
 
    return bean;
}


}
