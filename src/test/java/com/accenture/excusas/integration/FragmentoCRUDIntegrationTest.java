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
@DisplayName("Fragment CRUD Integration Tests")
class FragmentoCRUDIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.basePath = "/api/fragmentos";
    }

    @Test
    @DisplayName("Debe crear contexto y retornar 201")
    void testCrearContexto() {
        String payload = """
                {
                    "texto": "Contexto de prueba integración",
                    "role": "dev",
                    "tags": "test,integration"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contextos")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("texto", equalTo("Contexto de prueba integración"))
                .body("role", equalTo("dev"))
                .body("tags", equalTo("test,integration"));
    }

    @Test
    @DisplayName("Debe crear causa y retornar 201")
    void testCrearCausa() {
        String payload = """
                {
                    "texto": "Causa de prueba integración",
                    "role": "qa",
                    "tags": "bug,integration"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/causas")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("texto", equalTo("Causa de prueba integración"))
                .body("role", equalTo("qa"));
    }

    @Test
    @DisplayName("Debe crear consecuencia y retornar 201")
    void testCrearConsecuencia() {
        String payload = """
                {
                    "texto": "Consecuencia de prueba integración",
                    "role": "devops",
                    "tags": "incident,integration"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/consecuencias")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("texto", equalTo("Consecuencia de prueba integración"));
    }

    @Test
    @DisplayName("Debe crear recomendación y retornar 201")
    void testCrearRecomendacion() {
        String payload = """
                {
                    "texto": "Recomendación de prueba integración",
                    "role": "pm",
                    "tags": "advice,integration"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/recomendaciones")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("texto", equalTo("Recomendación de prueba integración"));
    }

    @Test
    @DisplayName("Debe crear meme y retornar 201")
    void testCrearMeme() {
        String payload = """
                {
                    "texto": "Meme de prueba integración",
                    "fuente": "dev-memes",
                    "tags": "humor,integration"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/memes")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("texto", equalTo("Meme de prueba integración"))
                .body("fuente", equalTo("dev-memes"));
    }

    @Test
    @DisplayName("Debe crear ley y retornar 201")
    void testCrearLey() {
        String payload = """
                {
                    "texto": "Ley de prueba integración",
                    "fuente": "Murphy",
                    "role": "dev",
                    "tags": "law,integration"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/leyes")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("texto", equalTo("Ley de prueba integración"))
                .body("fuente", equalTo("Murphy"));
    }

    @Test
    @DisplayName("Debe retornar error 400 si contexto no tiene texto")
    void testCrearContextoSinTextoRetorna400() {
        String payload = """
                {
                    "role": "dev"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contextos")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Debe retornar error 400 si ley no tiene fuente")
    void testCrearLeySinFuenteRetorna400() {
        String payload = """
                {
                    "texto": "Ley sin fuente",
                    "role": "dev"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/leyes")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Debe permitir crear fragmentos sin campos opcionales")
    void testCrearFragmentosConCamposMinimos() {
        String contexto = """
                {
                    "texto": "Contexto mínimo"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(contexto)
                .when()
                .post("/contextos")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("texto", equalTo("Contexto mínimo"));
    }

    @Test
    @DisplayName("Debe retornar JSON en todas las respuestas")
    void testRespuestasJSON() {
        String payload = """
                {
                    "texto": "Test JSON",
                    "role": "dev"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contextos")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON);
    }
}
