package task1;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String path = "hw2/src/main/resources/";

        File fromFile = new File(path + "persons.xml");
        File toFile = new File(path + "persons_edited.xml");

        makeCopyWithMergedNameAndSurname(fromFile, toFile);
    }

    /**
     * Receives .xml file with <person> tags, which contain "name" and "surname" attributes (among other attributes).
     * Then reads and transforms it - deletes "surname" attribute and replaces "name" attribute value
     * with 'name surname' value (for every person).
     * NB. It is considered for this task, that every person has both name and surname attributes
     * (order of appearance doesn't matter) and each attribute appears only once for one person
     * (one person - one 'name' and one 'surname' attribute).
     * Regex:
     * - namePattern - checks, if string has attribute 'name' (there should be one or more whitespaces
     * (including line terminator) before the word 'name'; also there can be zero or more whitespaces between
     * the word 'name', equal sign and quotes. Value can contain any character except quotes).
     * Has 1 group, which represents value for 'name' attribute;
     * - surnamePattern - checks, if string has attribute 'surname' (there should be one or more whitespaces
     * (including line terminator) before the word 'surname'; also there can be zero or more whitespaces between
     * the word 'surname', equal sign and quotes, and also after the value of attribute. Value can contain
     * any character except quotes).
     * Has 2 groups: group 1 represents attribute 'surname' (including its value and possible whitespaces after it),
     * group 2 represents value for 'surname' attribute.
     * @param fromFile - .xml file with input data.
     * @param toFile - .xml file for writing transformed data.
     */
    public static void makeCopyWithMergedNameAndSurname(File fromFile, File toFile) {
        try(BufferedReader br = new BufferedReader(new FileReader(fromFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(toFile, false))) {
            String inputString;
            StringBuilder outputStringBuilder = new StringBuilder();

            Pattern namePattern = Pattern.compile("\\s+name\\s*=\\s*\"([^\"]*)\"");
            Pattern surnamePattern = Pattern.compile("\\s+(surname\\s*=\\s*\"([^\"]*)\"\\s*)");

            Matcher nameMatcher;
            Matcher surnameMatcher;

            String name;
            String surname;

            while((inputString = br.readLine()) != null) {
                outputStringBuilder.append(inputString).append("\n");

                if(inputString.endsWith(">")) {
                    surnameMatcher = surnamePattern.matcher(outputStringBuilder);
                    if(surnameMatcher.find()) {
                        surname = surnameMatcher.group(2);
                        outputStringBuilder.replace(surnameMatcher.start(1), surnameMatcher.end(1), "");

                        nameMatcher = namePattern.matcher(outputStringBuilder);
                        if (nameMatcher.find()) {
                            name = nameMatcher.group(1) + " " + surname;
                            outputStringBuilder.replace(nameMatcher.start(1), nameMatcher.end(1), name);
                        }
                    }

                    bw.write(outputStringBuilder.toString());

                    outputStringBuilder = new StringBuilder();
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}