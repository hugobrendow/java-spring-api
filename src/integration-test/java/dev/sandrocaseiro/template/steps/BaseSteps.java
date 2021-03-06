package dev.sandrocaseiro.template.steps;

import dev.sandrocaseiro.template.MockServer;
import dev.sandrocaseiro.template.states.TestState;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.LogConfig.logConfig;
import static io.restassured.config.RestAssuredConfig.config;

@CucumberContextConfiguration
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = {"isTest=true"}
)
@ActiveProfiles("test")
public abstract class BaseSteps {
    @LocalServerPort
    public int port;

    @Autowired
    public MockServer mockServer;
    @Autowired
    public TestState state;
    @Autowired
    public Flyway flyway;

    public void setupRestAssured() {
        RestAssured.config = config()
            .encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))
            .logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails().and().enablePrettyPrinting(true));
        RestAssured.port = port;
    }

    public void rebuildDbData() {
        flyway.clean();
        flyway.migrate();
    }
}
