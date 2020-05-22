package ca.flearning.restfulgloom.rest;

import org.hibernate.collection.internal.PersistentBag;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;


public class JacksonIgnoreNullFalseNegOrZeroFilter extends SimpleBeanPropertyFilter{

	private boolean ignoreZero;
	
	public JacksonIgnoreNullFalseNegOrZeroFilter(boolean ignoreZero) {
		super();
		this.ignoreZero = ignoreZero;
	}
	@Override
	public void serializeAsField (
			Object pojo, JsonGenerator jgen, 
			SerializerProvider provider, PropertyWriter writer) 
			throws Exception {
		
		if (include(writer)) {
			
			Object writerValue = null;
			try {
				writerValue = writer.getMember().getValue(pojo);
				
				if(writerValue instanceof Integer) {
					if(ignoreZero) if (((Integer) writerValue).intValue() <= 0) return;
					else if (((Integer) writerValue).intValue() < 0) return;
				}else if(writerValue instanceof Boolean) {
					if (!(Boolean)writerValue) return;
				}else if(writerValue instanceof String) {
					if(((String)writerValue).length() <= 0) return;
				}else if(writerValue == null) {
					return;
				}else if(writerValue instanceof PersistentBag) {
					System.out.println("  >> PersistentBag: " + writer.getName());
					//if(((PersistentBag)writerValue).empty()) return;
				}else {
					System.out.println("  >> " + writerValue.getClass().getSimpleName());
				}
				
			}catch(Exception e){e.printStackTrace();} // Ignoring exceptions is wanted behavior here
			
			writer.serializeAsField(pojo, jgen, provider);
			
			
		} else if (!jgen.canOmitFields()) { // since 2.3
			writer.serializeAsOmittedField(pojo, jgen, provider);
		}
	}
	
	@Override
	protected boolean include(BeanPropertyWriter writer) {
		return true;
	}
	
	@Override
	protected boolean include(PropertyWriter writer) {
		return true;
	}
}