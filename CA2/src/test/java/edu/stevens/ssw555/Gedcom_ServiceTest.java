package edu.stevens.ssw555;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class Gedcom_ServiceTest {
    @Test
    public void testReadAndParseFile() throws IOException {
        String fileName = "src/test/java/test_inputs/gedcom_test_family.ged";

        Gedcom_Service.readAndParseFile(fileName);
    }

    @Test
    public void testReadAndParseIndividualsOnlyFile() throws IOException {
        String fileName = "src/test/java/test_inputs/individuals_only.ged";

        Gedcom_Service.readAndParseFile(fileName);
    }

    @Test
    public void testReadAndParseFamiliesOnlyFile() throws IOException {
        String fileName = "src/test/java/test_inputs/families_only.ged";

        Gedcom_Service.readAndParseFile(fileName);
    }

    @Test
    public void testReadAndParseEmptyFile() throws IOException {
        String fileName = "src/test/java/test_inputs/empty_file.ged";

        Gedcom_Service.readAndParseFile(fileName);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testReadAndParseIndexOutOfBoundsIndividualsFile() throws IOException {
        String fileName = "src/test/java/test_inputs/index_out_of_bounds_individuals.ged";

        Gedcom_Service.readAndParseFile(fileName);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testReadAndParseIndexOutOfBoundsFamiliesFile() throws IOException {
        String fileName = "src/test/java/test_inputs/index_out_of_bounds_families.ged";

        Gedcom_Service.readAndParseFile(fileName);
    }

    @Test
    public void testBirthBeforeDeath() throws IOException, ParseException {
        HashMap<String, Individual> individuals = new HashMap<>();
        Individual indi1 = new Individual("I1");
        indi1.setBirth("01/01/1980");
        indi1.setDeath("12/31/1979");
        individuals.put("I1", indi1);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Gedcom_Service.birthBeforeDeath(individuals);
        String expectedOutput = "ERROR:INDIVIDUAL: User Story US03: Birth Before Death \n" +
                "Individual: I1 - null was born after death\n" +
                "DOB: 01/01/1980 DOD: 12/31/1979";
        assertEquals(expectedOutput.replaceAll("\\s", ""), outContent.toString().replaceAll("\\s", ""));
    }

    @Test
    public void testMarriageBeforeDivorce() throws IOException, ParseException {
        HashMap<String, Individual> individuals = new HashMap<>();
        HashMap<String, Family> families = new HashMap<>();

        Individual indi1 = new Individual("I1");
        Individual indi2 = new Individual("I2");
        individuals.put("I1", indi1);
        individuals.put("I2", indi2);

        Family fam1 = new Family("F1");
        fam1.setHusb("I1");
        fam1.setWife("I2");
        fam1.setMarriage("01/01/2000");
        fam1.setDivorce("12/31/1999");
        families.put("F1", fam1);

        // Add more families with marriage and divorce dates to the map

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Gedcom_Service.Marriagebeforedivorce(individuals, families);
        String expectedOutput = "ERROR:FAMILY: User Story US04: Marriage Before Divorce \n" +
                "Family: F1\n" +
                "Individual: I1: nullI2: null marriage date is before divorce date.\n" +
                "Marriage Date: 01/01/2000 Divorce Date: 12/31/1999";
        assertEquals(expectedOutput.replaceAll("\\s", ""), outContent.toString().replaceAll("\\s", ""));
    }

    @Test
    public void testBirthBeforeParentMarriage() throws IOException, ParseException {
        HashMap<String, Individual> individuals = new HashMap<>();
        HashMap<String, Family> families = new HashMap<>();

        Individual indi1 = new Individual("I1");
        Individual indi2 = new Individual("I2");
        Individual indi3 = new Individual("I3");
        individuals.put("I1", indi1);
        individuals.put("I2", indi2);
        individuals.put("I3", indi3);

        Family fam1 = new Family("F1");
        fam1.setHusb("I1");
        fam1.setWife("I2");
        fam1.setMarriage("01/01/2000");
        fam1.setChild(new ArrayList<>(Arrays.asList("I3")));
        individuals.put("I3", new Individual("I3"));
        individuals.get("I3").setBirth("12/31/1999");
        families.put("F1", fam1);

        // Add more families with children and marriage dates to the map

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Gedcom_Service.birthbeforemarriageofparent(individuals, families);
        String expectedOutput = "ERROR: User Story US08: Birth Before Marriage Date \n" +
                "Family ID: F1\n" +
                "Individual: I3: null Has been born before parents' marriage\n" +
                "DOB: 12/31/1999 Parents Marriage Date: 01/01/2000\n";
        assertEquals(expectedOutput.replaceAll("\\s", ""), outContent.toString().replaceAll("\\s", ""));
    }

    @Test
    public void testBirthBeforeParentMarriageWithDivorce() throws IOException, ParseException {
        HashMap<String, Individual> individuals = new HashMap<>();
        HashMap<String, Family> families = new HashMap<>();

        Individual indi1 = new Individual("I1");
        Individual indi2 = new Individual("I2");
        Individual indi3 = new Individual("I3");
        individuals.put("I1", indi1);
        individuals.put("I2", indi2);
        individuals.put("I3", indi3);

        Family fam1 = new Family("F1");
        fam1.setHusb("I1");
        fam1.setWife("I2");
        fam1.setMarriage("01/01/1998");
        fam1.setChild(new ArrayList<>(Arrays.asList("I3")));
        individuals.put("I3", new Individual("I3"));
        individuals.get("I3").setBirth("12/31/2000");
        families.put("F1", fam1);
        fam1.setDivorce("12/31/1999");

        // Add more families with children and marriage dates to the map

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Gedcom_Service.birthbeforemarriageofparent(individuals, families);
        String expectedOutput = "ERROR: User Story US08: Birth After Divorce Date\n" +
                "Family ID: F1\n" +
                "Individual: I3: null Has been born after parents' divorce\n" +
                "DOB: 12/31/2000 Parents Divorce Date: 12/31/1999";
        assertEquals(expectedOutput.replaceAll("\\s", ""), outContent.toString().replaceAll("\\s", ""));
    }

    @Test
    public void testMaleLastName() throws IOException, ParseException {
        HashMap<String, Individual> individuals = new HashMap<>();
        HashMap<String, Family> families = new HashMap<>();

        Individual ind1 = new Individual("I1");
        ind1.setName("John Smith");
        ind1.setSex("M");
        individuals.put("I1", ind1);

        Individual ind2 = new Individual("I2");
        ind2.setName("Jane Smith");
        ind2.setSex("F");
        individuals.put("I2", ind2);

        Family fam1 = new Family("F1");
        fam1.setHusb("I1");
        fam1.setWife("I2");
        fam1.setChild(new ArrayList<>(Arrays.asList("I3")));
        families.put("F1", fam1);

        Individual ind3 = new Individual("I3");
        ind3.setName("Jack L");
        ind3.setSex("M");
        ind3.setChildOf("F1");
        individuals.put("I3", ind3);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Gedcom_Service.Malelastname(families, individuals);
        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testMaleLastNameWithChildren() throws IOException, ParseException {
        HashMap<String, Individual> individuals = new HashMap<>();
        HashMap<String, Family> families = new HashMap<>();

        Individual ind1 = new Individual("I1");
        ind1.setName("John Smith");
        ind1.setSex("M");
        individuals.put("I1", ind1);

        Individual ind2 = new Individual("I2");
        ind2.setName("Jane Smith");
        ind2.setSex("F");
        individuals.put("I2", ind2);

        Individual ind3 = new Individual("I3");
        ind3.setName("Jack L");
        ind3.setSex("M");
        ind3.setChildOf("F1");
        individuals.put("I3", ind3);

        Individual ind4 = new Individual("I4");
        ind4.setName("Sam L");
        ind4.setSex("M");
        ind4.setChildOf("F1");
        individuals.put("I4", ind4);

        Family fam1 = new Family("F1");
        fam1.setHusb("I1");
        fam1.setWife("I2");
        fam1.setChild(new ArrayList<>(Arrays.asList("I3", "I4")));


        Individual ind5 = new Individual("I5");
        ind5.setName("Ali A");
        ind5.setSex("M");
        ind5.setChildOf("F2");
        individuals.put("I5", ind5);

        Individual ind6 = new Individual("I6");
        ind6.setName("Am A");
        ind6.setSex("F");
        ind6.setChildOf("F2");
        individuals.put("I6", ind6);

        Individual ind7 = new Individual("I7");
        ind7.setName("Am Auuu");
        ind7.setSex("M");
        ind7.setChildOf("F2");
        individuals.put("I7", ind7);

        Individual ind8 = new Individual("I8");
        ind8.setName("LL KFF");
        ind8.setSex("M");
        ind8.setChildOf("F2");
        individuals.put("I8", ind8);

        Family fam2 = new Family("F2");
        fam2.setHusb("I5");
        fam2.setWife("I6");
        fam2.setChild(new ArrayList<>(Arrays.asList("I7", "I8")));
        families.put("F2", fam2);
        families.put("F1", fam1);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Gedcom_Service.Malelastname(families, individuals);
        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString());
    }


    @Test
    public void testUniqueFamilyNameBySpouses() throws IOException, ParseException {
        HashMap<String, Individual> individuals = new HashMap<>();
        HashMap<String, Family> families = new HashMap<>();
        Individual ind1 = new Individual("I1");
        Individual ind2 = new Individual("I2");
        Individual ind3 = new Individual("I3");
        individuals.put("I1", ind1);
        individuals.put("I2", ind2);
        individuals.put("I3", ind3);
        Family fam1 = new Family("F1");
        fam1.setHusb("I1");
        fam1.setWife("I2");
        fam1.setMarriage("01/01/2000");
        families.put("F1", fam1);
        Family fam2 = new Family("F2");
        fam2.setHusb("I3");
        fam2.setWife("I2");
        fam2.setMarriage("01/01/2000");
        families.put("F2", fam2);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Gedcom_Service.uniqueFamilynameBySpouses(individuals, families);
        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testAuntsandUnclesname() throws ParseException, IOException {
        HashMap<String, Family> families = new HashMap<>();
        HashMap<String, Individual> individuals = new HashMap<>();

        Family fam1 = new Family("F1");
        fam1.setHusb("I1");
        fam1.setWife("I2");
        Family fam2 = new Family("F2");
        fam2.setHusb("I3");
        fam2.setWife("I4");
        Family fam3 = new Family("F3");
        fam3.setHusb("I5");
        fam3.setWife("I6");
        Individual ind1 = new Individual("I1");
        Individual ind2 = new Individual("I2");
        Individual ind3 = new Individual("I3");
        Individual ind4 = new Individual("I4");
        Individual ind5 = new Individual("I5");
        Individual ind6 = new Individual("I6");
        Individual ind7 = new Individual("I7");
        Individual ind8 = new Individual("I8");

        fam1.setChild(new ArrayList<>(Arrays.asList("I7")));
        fam2.setChild(new ArrayList<>(Arrays.asList("I3", "I4")));
        fam3.setChild(new ArrayList<>(Arrays.asList("I5", "I6")));

        ind1.setSpouseOf("F1");
        ind3.setSpouseOf("F2");
        ind5.setSpouseOf("F3");

        families.put("F1", fam1);
        families.put("F2", fam2);
        families.put("F3", fam3);
        individuals.put("I1", ind1);
        individuals.put("I2", ind2);
        individuals.put("I3", ind3);
        individuals.put("I4", ind4);
        individuals.put("I5", ind5);
        individuals.put("I6", ind6);
        individuals.put("I7", ind7);
        individuals.put("I8", ind8);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Gedcom_Service.AuntsandUnclesname(families, individuals);

        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testRunServiceBirthAndDivorceErrors() throws IOException, ParseException {
        String inputFilePath = "src/test/java/test_inputs/gedcom_test_family.ged";

        ByteArrayInputStream inContent = new ByteArrayInputStream(inputFilePath.getBytes());
        System.setIn(inContent);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Gedcom_Service.main(null);

        String output = outContent.toString();

        String error1 = "ERROR:INDIVIDUAL: User Story US03: Birth Before Death " +
                "Individual: @I2@ - Bob Williams was born after death " +
                "DOB: 10/19/1997 DOD: 05/12/1995";

        assertTrue(output.replaceAll("\\s", "").contains(error1.replaceAll("\\s", "")));

        // Reset the System.in and System.out
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    public void testRunServiceWithDuplicatedIndividual() throws IOException, ParseException {
        String inputFilePath = "src/test/java/test_inputs/gedcom_test_family_with_dup_ind_id.ged";

        ByteArrayInputStream inContent = new ByteArrayInputStream(inputFilePath.getBytes());
        System.setIn(inContent);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Gedcom_Service.main(null);

        String output = outContent.toString();

        String error1 = "ERROR:INDIVIDUAL: User Story US03: Birth Before Death " +
                "Individual: @I2@ - Bob Williams was born after death " +
                "DOB: 10/19/1997 DOD: 05/12/1995";

        assertTrue(output.replaceAll("\\s", "").contains(error1.replaceAll("\\s", "")));

        // Reset the System.in and System.out
        System.setIn(System.in);
        System.setOut(System.out);
    }


}
