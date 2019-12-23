package com.example.demo;

import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class Controller {

	@RequestMapping(value = "/accountStatusXML", method = RequestMethod.GET,
			produces ="application/xml")
	public CustomerDetail getAccountStatusRequestXML(@RequestParam("jsonInp") String jsonInp) {
		ObjectMapper mapper = new ObjectMapper();
		CustomerDetail cd=null;
		try {
			cd = mapper.readValue(jsonInp, CustomerDetail.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cd;
	}
	
	@RequestMapping(value = "/accountStatusJSON", method = RequestMethod.GET,
			produces ="application/json")
	public CustomerDetail getAccountStatusRequestJSON(@RequestParam("xmlInp") String xmlInp) 
	{
		CustomerDetail cd=null;
        try {
        	JAXBContext jaxbContext = JAXBContext.newInstance(CustomerDetail.class);  
 		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller(); 
			cd= (CustomerDetail) jaxbUnmarshaller.unmarshal(new StringReader(xmlInp));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        return cd;
	}
}
