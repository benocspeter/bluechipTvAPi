package hu.unideb.inf.benocspeter.jaxb;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.OutputStream;

public class JAXBUtil {


    public static void toXML(Object o, OutputStream os) throws JAXBException {
        try {
            JAXBContext context = JAXBContext.newInstance(o.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(o, os);
        } catch(JAXBException e) {
            throw e;
        }
    }


}
