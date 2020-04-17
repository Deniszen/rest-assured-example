package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import transport.Transport;

import java.nio.charset.StandardCharsets;

import static io.restassured.config.DecoderConfig.decoderConfig;
import static io.restassured.config.EncoderConfig.encoderConfig;

public abstract class BaseTest {

    protected Transport transport = new Transport();

    protected String animalsPath = "/animals";
    protected String animalsListPath = "/animals/list";

    @BeforeAll
    public static void setUp() {
        RestAssured.config()
                .encoderConfig(encoderConfig().defaultContentCharset(StandardCharsets.UTF_8))
                .decoderConfig(decoderConfig().defaultContentCharset(StandardCharsets.UTF_8));
    }
}
