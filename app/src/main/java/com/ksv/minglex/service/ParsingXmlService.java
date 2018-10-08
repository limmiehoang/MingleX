package com.ksv.minglex.service;

import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import com.ksv.minglex.model.User;

@Service
public class ParsingXmlService {

    public User XmlToUser (String file) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(User.class);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        XMLInputFactory xif = XMLInputFactory.newFactory();
        xif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, true);
        XMLStreamReader xsr = xif.createXMLStreamReader(new StreamSource(file));
        User user = (User) unmarshaller.unmarshal(xsr);

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        System.out.println(user.toString());
        return user;
    }
}