package task1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "fines_statistics")
public class FinesStatistics {
    private static final XmlMapper XML_MAPPER;
    static {
        XML_MAPPER = new XmlMapper();
        XML_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @JsonIgnore
    private List<Fine> fines;
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "fine")
    private List<Fine> uniqueFinesWithTotalAmount;

    public List<Fine> getFines() {
        return fines;
    }

    public void setFines(List<Fine> fines) {
        this.fines = fines;
    }

    public List<Fine> getUniqueFinesWithTotalAmount() {
        return uniqueFinesWithTotalAmount;
    }

    public void setUniqueFinesWithTotalAmount(List<Fine> uniqueFinesWithTotalAmount) {
        this.uniqueFinesWithTotalAmount = uniqueFinesWithTotalAmount;
    }

    /**
     * Reads json-file line-by-line and returns list of parsed Fines.
     * @param source - json-file, which contains a list of objects with traffic fines information for certain year.
     * @return list of Fines for certain year.
     */
    public List<Fine> getFinesFromJson(File source) {
        List<Fine> fines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(source))) {
            String inputString;
            StringBuilder jsonStringBuilder = new StringBuilder();

            JsonFactory jsonFactory = new JsonFactory();

            Fine fine;

            while ((inputString = br.readLine()) != null) {
                if (inputString.trim().equals("[") || inputString.trim().equals("]")) {
                    continue;
                }

                jsonStringBuilder.append(inputString);

                if (inputString.contains("}")) {
                    try (JsonParser jsonParser = jsonFactory.createParser(jsonStringBuilder.toString())) {
                        fine = new Fine();

                        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                            String fieldname = jsonParser.getCurrentName();

                            if ("type".equals(fieldname)) {
                                jsonParser.nextToken();
                                fine.setType(jsonParser.getText());
                            }

                            if ("fine_amount".equals(fieldname)) {
                                jsonParser.nextToken();
                                fine.setAmount(jsonParser.getDoubleValue());
                            }
                        }

                        fines.add(fine);
                    }

                    jsonStringBuilder = new StringBuilder();
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return fines;
    }

    /**
     * Writes information about unique types of traffic fines and sums of fine amounts for them to xml-file.
     * @param destination - xml-file for writing corresponding data.
     * @throws IOException
     */
    public void writeStatisticsToXml(File destination) throws IOException {
        XML_MAPPER.writeValue(destination, this);
    }
}