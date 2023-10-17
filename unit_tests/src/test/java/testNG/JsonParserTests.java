package testNG;

import com.google.gson.JsonParseException;
import org.testng.annotations.*;

import parser.JsonParser;
import parser.NoSuchFileException;
import shop.Cart;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import static org.testng.Assert.*;

public class JsonParserTests {

    private JsonParser jsonParser;
    private File andrewCartFile;
    private File eugenCartFile;
    private Cart andrewCart;
    private Cart eugenCart;
    private File tempDir;

    @BeforeClass
    public void setUp() {
        jsonParser = new JsonParser();
        andrewCart = new Cart("andrew-cart");
        eugenCart = new Cart("eugen-cart");
        tempDir = new File("src/main/resources/");
    }

    @AfterMethod
    public void tearDown() throws IOException {
        File[] filesInTempDir = tempDir.listFiles();
        if (filesInTempDir != null) {
            for (File file : filesInTempDir) {
                Files.deleteIfExists(file.toPath());
            }
        }
    }

    @Test(enabled = false)
    public void testWriteAndReadAndrewCart() {
        jsonParser.writeToFile(andrewCart);
        andrewCartFile = new File("src/main/resources/andrew-cart.json");
        assertTrue(andrewCartFile.exists());

        Cart readCart = jsonParser.readFromFile(andrewCartFile);
        assertNotNull(readCart);
        assertEquals(andrewCart.getCartName(), readCart.getCartName());
    }

    @Test
    public void testWriteAndReadEugenCart() {
        jsonParser.writeToFile(eugenCart);
        eugenCartFile = new File("src/main/resources/eugen-cart.json");
        assertTrue(eugenCartFile.exists());

        Cart readCart = jsonParser.readFromFile(eugenCartFile);
        assertNotNull(readCart);
        assertEquals(eugenCart.getCartName(), readCart.getCartName());
    }

    @Test
    public void testReadFromNonExistentFile() {
        assertThrows(NoSuchFileException.class, () -> jsonParser.readFromFile(new File("nonexistent.json")));
    }

    @Test(dataProvider = "malformedJsonData")
    public void testReadFromMalformedJsonFile(String fileName, String jsonContent) {
        jsonParser = new JsonParser();

        File tempDir = new File("src/main/resources/");
        File malformedJsonFile = new File(tempDir.toString(), fileName);
        writeMalformedJsonToFile(malformedJsonFile, jsonContent);
        assertThrows(JsonParseException.class, () -> jsonParser.readFromFile(malformedJsonFile));
    }

    private void writeMalformedJsonToFile(File file, String jsonContent) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(jsonContent);
        } catch (IOException e) {
            throw new RuntimeException("Error writing malformed JSON to file", e);
        }
    }

    @DataProvider(name = "malformedJsonData")
    public Object[][] malformedJsonData() {
        return new Object[][]{
                {"malformed1.json", "{ \"key\": \"value\""},
                {"malformed2.json", "{ key: {{\"value\" }"},
                {"malformed3.json", "{ \"array\": {1,2,3}"},
                {"malformed4.json", "{ \"key1\": \"value1\" \"key2\": \"value2\" }"},
                {"malformed5.json", "{ \"key\": /value/ }"}
        };
    }


}
