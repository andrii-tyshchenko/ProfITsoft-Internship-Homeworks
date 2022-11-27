package task2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@JacksonXmlRootElement(localName = "fines_statistics")
public class FinesStatistics {
    private static final ObjectMapper JSON_MAPPER;
    private static final XmlMapper XML_MAPPER;
    static {
        JSON_MAPPER = new ObjectMapper()
                .findAndRegisterModules()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .enable(SerializationFeature.INDENT_OUTPUT);

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


    /**
     * Creates List of Fines (every Fine has unique "type" field, and its "amount" field is a sum of all appeared
     * values of this field for this unique "type" value), descending sorted by "amount" field,
     * and sets it to a "uniqueFinesWithTotalAmount" field.
     */
    public void setUniqueFinesWithTotalAmount() {
        Map<String, Double> uniqueTypesAndTotalAmount = fines.stream()
                .collect(Collectors.toMap(Fine::getType, Fine::getAmount, Double::sum));

        uniqueFinesWithTotalAmount = uniqueTypesAndTotalAmount.entrySet().stream()
                .map(entry -> new Fine(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(Fine::getAmount).reversed())
                .toList();
    }

    /**
     * Reads json-file and returns list of parsed Fines.
     * @param source - json-file, which contains a list of objects with traffic fines information for certain year.
     * @return list of Fines for certain year.
     * @throws IOException
     */
    public List<Fine> getFinesFromJson(File source) throws IOException {
        return JSON_MAPPER.readValue(source, new TypeReference<>(){});
    }

    /**
     * Writes information about unique types of traffic fines and sums of fine amounts for them to xml-file.
     * @param destination - xml-file for writing corresponding data.
     * @throws IOException
     */
    public void writeStatisticsToXml(File destination) throws IOException {
        this.setUniqueFinesWithTotalAmount();

        XML_MAPPER.writeValue(destination, this);
    }
}