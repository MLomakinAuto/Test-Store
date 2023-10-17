package junit;

import com.google.gson.JsonParseException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import parser.JsonParser;
import parser.NoSuchFileException;
import shop.Cart;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class JsonParserTest {

    private JsonParser jsonParser;
    private File andrewCartFile;
    private File eugenCartFile;
    private Cart andrewCart;
    private Cart eugenCart;

    @BeforeEach
    void setUp() {
        jsonParser = new JsonParser();
        andrewCart = new Cart("andrew-cart");
        eugenCart = new Cart("eugen-cart");
    }

    @AfterEach
    void tearDown() throws IOException {
        if (andrewCartFile != null) {
            Files.deleteIfExists(andrewCartFile.toPath());
        }
        if (eugenCartFile != null) {
            Files.deleteIfExists(eugenCartFile.toPath());
        }
    }

    @Disabled
    @Test
    void testWriteAndReadAndrewCart() {
        jsonParser.writeToFile(andrewCart);
        andrewCartFile = new File("src/main/resources/andrew-cart.json");
        assertTrue(andrewCartFile.exists());

        Cart readCart = jsonParser.readFromFile(andrewCartFile);
        assertNotNull(readCart);
        assertEquals(andrewCart.getCartName(), readCart.getCartName());
    }

    @Test
    void testWriteAndReadEugenCart() {
        jsonParser.writeToFile(eugenCart);
        eugenCartFile = new File("src/main/resources/eugen-cart.json");
        assertTrue(eugenCartFile.exists());

        Cart readCart = jsonParser.readFromFile(eugenCartFile);
        assertNotNull(readCart);
        assertEquals(eugenCart.getCartName(), readCart.getCartName());
    }

    @Test
    void testReadFromNonExistentFile() {
        assertThrows(NoSuchFileException.class, () -> jsonParser.readFromFile(new File("nonexistent.json")));
    }

    @ParameterizedTest
    @MethodSource("jsonFileParameters")
    void testReadFromMalformedJsonFile(String fileName, String jsonContent) {
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

    private static Stream<Arguments> jsonFileParameters() {
        return Stream.of(
                Arguments.of("malformed1.json", "{ \"key\": \"value\""),
                Arguments.of("malformed2.json", "{ key: {{\"value\" }"),
                Arguments.of("malformed3.json", "{ \"array\": {1,2,3}"),
                Arguments.of("malformed4.json", "{ \"key1\": \"value1\" \"key2\": \"value2\" }"),
                Arguments.of("malformed5.json", "{ \"key\": /value/ }")
        );
    }
}