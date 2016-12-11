package xvzh.wechat.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

public class XMLUtil {
	public static String jaxb2XML(Object object) throws JAXBException {
		String result = "";
		JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());  
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();  
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);   
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        jaxbMarshaller.setProperty("com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler",
                new CharacterEscapeHandler() {
            @Override
            public void escape(char[] ch, int start,int length, boolean isAttVal, Writer writer) throws IOException {
                writer.write(ch, start, length);
            }

        });
        
        StringWriter writer = new StringWriter();
        jaxbMarshaller.marshal(object, writer);
        result = writer.toString();
        return result;
	}
	
	public static Object XML2Jaxb(String xml, Class jabxClass) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(jabxClass);  
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		
		return jaxbUnmarshaller.unmarshal(new StringReader(xml));	
	}
}
