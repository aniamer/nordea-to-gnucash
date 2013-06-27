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
//private static final String pattern1 = "([0-9]*)\\.([0-9]*),([0-9]*)";
//private static final String pattern2 = "([0-9])\\.";
//private static final String pattern3 = ",([0-9])";

private static Pattern regex1= Pattern.compile(pattern1);
private static Pattern regex2= Pattern.compile(pattern2);
private static Pattern regex3= Pattern.compile(pattern3);
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
    
    String valueString = bean.getAmount();
    if(null != valueString){
    	if(regex1.matcher(valueString).find())
    		bean.setAmount(valueString.replaceAll(pattern1, "$1,$2.$3"));
    	else if(regex2.matcher(valueString).find())
    		bean.setAmount(valueString.replaceAll(pattern2, "$1,"));
    	else if(regex3.matcher(valueString).find())
    		bean.setAmount(valueString.replaceAll(pattern3, ".$1"));
    }
    	
 
    return bean;
}


}
