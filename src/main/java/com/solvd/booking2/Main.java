package com.solvd.booking2;

import com.solvd.booking2.dao.CustomerDAO;
import com.solvd.booking2.dao.ICustomerDAO;
import com.solvd.booking2.models.Property;
import com.solvd.booking2.utilities.JaxbUtil;
import com.solvd.booking2.utilities.PropertiesParser;
import jakarta.xml.bind.JAXBException;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    public static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args);

            LOGGER.info("DOM Parsing Properties");

            List<Property> properties = PropertiesParser.parseProperties("src/main/java/com.solvd.booking/resources/properties.xml");

            if (properties.isEmpty()) {
                LOGGER.error("No property elements found in properties.xml");
            } else
            {LOGGER.info("DOM parsing completed successfully. Number of properties parsed: {}", properties.size());
            }

LOGGER.info("JAXB Parsing Properties");

    String filePath = "src/main/resources/propertiesJAXB.xml";

    Property property = new Property();
        property.setId(4L);
        property.setTitle("Seaside Studio");
        property.setPropertyType("STUDIO");
        property.setPricePerNight(new BigDecimal("75.00"));
        property.setMaxGuests(2);
        property.setAddress(address);
        property.setHost(host);
        property.setPhotos(List.of(photo1, photo2));

        try {
        JaxbUtil.marshal(property, filePath);
        LOGGER.info("Property marshalled to XML");

        Property unmarshalledProperty = JaxbUtil.unmarshal(filePath, Property.class);
        LOGGER.info("Unmarshalled property: {}", unmarshalledProperty);

    } catch (
    JAXBException e) {
        LOGGER.error("JAXB error occurred", e);
    }

        ICustomerDAO customerDAO = new CustomerDAO();
}