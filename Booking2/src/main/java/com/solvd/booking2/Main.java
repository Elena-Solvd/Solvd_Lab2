package com.solvd.booking2;

import com.solvd.booking2.dao.CustomerDAO;
import com.solvd.booking2.dao.ICustomerDAO;
import com.solvd.booking2.models.Property;
import com.solvd.booking2.utilities.PropertiesParser;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    public static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args)

            LOGGER.info("DOM Parsing Properties");

            List<Property> properties = PropertiesParser.parseProperties("src/main/java/com.solvd.booking/resources/properties.xml");

            if (properties.isEmpty()) {
                LOGGER.error("No property elements found in properties.xml");
            } else
            {LOGGER.info("DOM parsing completed successfully. Number of properties parsed: {}", properties.size());
            }


        ICustomerDAO customerDAO = new CustomerDAO();
}