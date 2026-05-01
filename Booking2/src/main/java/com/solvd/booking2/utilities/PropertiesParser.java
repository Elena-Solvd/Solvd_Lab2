package com.solvd.booking2.utilities;

public class PropertiesParser {
    private static final Logger LOGGER = LogManager.getLogger(PropertiesParser.class);

    public static List<Property> parseProperties(String filePath) {
        List<Property> properties = new ArrayList<>();

        try {
            File file = new File(filePath);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            document.getDocumentElement().normalize();

            NodeList propertyNodes = document.getElementsByTagName("property");

            for (int i = 0; i < propertyNodes.getLength(); i++) {
                Node node = propertyNodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    Property property = new Property();
                    property.setId(Long.parseLong(getTagValue(element, "propertyId")));
                    property.setTitle(getTagValue(element, "title"));
                    property.setPropertyType(getTagValue(element, "propertyType"));
                    property.setPricePerNight(Float.parseFloat(getTagValue(element, "pricePerNight")));
                    property.setMaxGuests(Integer.parseInt(getTagValue(element, "maxGuests")));
                    property.setAddress(parseAddress(element));
                    property.setHost(parseHost(element));
                    property.setPhotos(parsePhotos(element));

                    properties.add(property);
                }
            }

        } catch (Exception e) {
            LOGGER.error("Failed to parse property from XML file: {}", filePath, e);
        }

        return properties;
    }

    private static Address parseAddress(Element propertyElement) {
        Element addressElement = (Element) propertyElement
                .getElementsByTagName("address").item(0);

        Address address = new Address();
        address.setId(Long.parseLong(getTagValue(addressElement, "addressId")));
        address.setCity(getTagValue(addressElement, "city"));
        address.setStreet(getTagValue(addressElement, "street"));
        address.setZipcode(getTagValue(addressElement, "zipcode"));
        address.setCountry(parseCountry(addressElement));

        return address;
    }

    private static Country parseCountry(Element addressElement) {
        Element countryElement = (Element) addressElement
                .getElementsByTagName("country").item(0);

        Country country = new Country();
        country.setId(Long.parseLong(getTagValue(countryElement, "countryId")));
        country.setCode(getTagValue(countryElement, "code"));
        country.setName(getTagValue(countryElement, "name"));
        country.setCallingCode(Integer.parseInt(getTagValue(countryElement, "callingCode")));

        return country;
    }

    private static Host parseHost(Element propertyElement) {
        Element hostElement = (Element) propertyElement
                .getElementsByTagName("host").item(0);

        Host host = new Host();
        host.setId(Long.parseLong(getTagValue(hostElement, "hostId")));

        return host;
    }

    private static List<Photo> parsePhotos(Element propertyElement) {
        List<Photo> photos = new ArrayList<>();
        NodeList photoNodes = propertyElement.getElementsByTagName("photo");

        for (int i = 0; i < photoNodes.getLength(); i++) {
            Element photoElement = (Element) photoNodes.item(i);

            Photo photo = new Photo();
            photo.setId(Long.parseLong(getTagValue(photoElement, "photoId")));
            photo.setUrl(getTagValue(photoElement, "url"));

            photos.add(photo);
        }

        return photos;
    }

    private static String getTagValue(Element parent, String tagName) {
        return parent.getElementsByTagName(tagName).item(0).getTextContent();
    }
}