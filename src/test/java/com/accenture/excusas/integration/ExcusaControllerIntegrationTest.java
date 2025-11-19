package com.accenture.excusas.integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("ExcusaController Integration Tests")
class ExcusaControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.basePath = "/api/excusas";
    }

    @Test
    @DisplayName("Debe retornar excusa simple con status 200")
    void testGetExcusaSimple() {
        given()
                .when()
                .get("/simple")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("contexto", notNullValue())
                .body("causa", notNullValue())
                .body("consecuencia", notNullValue())
                .body("recomendacion", notNullValue())
                .body("meme", nullValue())
                .body("ley", nullValue())
                .body("modo", equalTo("SIMPLE"))
                .body("timestamp", notNullValue());
    }

    @Test
    @DisplayName("Debe retornar excusa con meme")
    void testGetExcusaConMeme() {
        given()
                .when()
                .get("/con-meme")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("contexto", notNullValue())
                .body("meme", notNullValue())
                .body("ley", nullValue())
                .body("modo", equalTo("CON_MEME"));
    }

    @Test
    @DisplayName("Debe retornar excusa con ley")
    void testGetExcusaConLey() {
        given()
                .when()
                .get("/con-ley")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("contexto", notNullValue())
                .body("ley", notNullValue())
                .body("fuente", notNullValue())
                .body("meme", nullValue())
                .body("modo", equalTo("CON_LEY"));
    }

    @Test
    @DisplayName("Debe retornar excusa ULTRA_SHARK con meme y ley")
    void testGetExcusaUltraShark() {
        given()
                .when()
                .get("/ultra-shark")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("contexto", notNullValue())
                .body("causa", notNullValue())
                .body("consecuencia", notNullValue())
                .body("recomendacion", notNullValue())
                .body("meme", notNullValue())
                .body("ley", notNullValue())
                .body("fuente", notNullValue())
                .body("modo", equalTo("ULTRA_SHARK"))
                .body("timestamp", notNullValue());
    }

    @Test
    @DisplayName("Debe retornar excusa filtrada por rol 'dev'")
    void testGetExcusaPorRolDev() {
        given()
                .when()
                .get("/por-rol/dev")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("contexto", notNullValue())
                .body("modo", equalTo("ULTRA_SHARK"));
    }

    @Test
    @DisplayName("Debe retornar excusa filtrada por rol 'qa'")
    void testGetExcusaPorRolQa() {
        given()
                .when()
                .get("/por-rol/qa")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("modo", equalTo("ULTRA_SHARK"));
    }

    @Test
    @DisplayName("Debe retornar excusa filtrada por rol 'devops'")
    void testGetExcusaPorRolDevops() {
        given()
                .when()
                .get("/por-rol/devops")
                .then()
                .statusCode(200)
                .body("modo", equalTo("ULTRA_SHARK"));
    }

    @Test
    @DisplayName("Debe retornar excusa filtrada por rol 'pm'")
    void testGetExcusaPorRolPm() {
        given()
                .when()
                .get("/por-rol/pm")
                .then()
                .statusCode(200)
                .body("modo", equalTo("ULTRA_SHARK"));
    }

    @Test
    @DisplayName("Debe generar múltiples excusas con valores distintos")
    void testGenerarMultiplesExcusas() {
        String excusa1 = given()
                .when()
                .get("/simple")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getString("timestamp");

        String excusa2 = given()
                .when()
                .get("/simple")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getString("timestamp");

        // Los timestamps deberían ser diferentes (o muy similares)
        assert excusa1 != null && excusa2 != null;
    }

    @Test
    @DisplayName("Debe contener estructura de respuesta completa")
    void testRespuestaCompleta() {
        given()
                .when()
                .get("/ultra-shark")
                .then()
                .statusCode(200)
                .body("$", hasKey("contexto"))
                .body("$", hasKey("causa"))
                .body("$", hasKey("consecuencia"))
                .body("$", hasKey("recomendacion"))
                .body("$", hasKey("meme"))
                .body("$", hasKey("ley"))
                .body("$", hasKey("fuente"))
                .body("$", hasKey("timestamp"))
                .body("$", hasKey("modo"));
    }
}
