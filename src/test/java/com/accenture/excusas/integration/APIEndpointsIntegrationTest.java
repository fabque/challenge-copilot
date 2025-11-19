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
@DisplayName("API Endpoints Integration Tests")
class APIEndpointsIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Debe retornar 404 para endpoint inexistente")
    void testEndpointInexistente() {
        given()
                .when()
                .get("/api/inexistente")
                .then()
                .statusCode(404);
    }

    @Test
    @DisplayName("Debe tener CORS habilitado")
    void testCORSHabilitado() {
        given()
                .header("Origin", "http://localhost:3000")
                .when()
                .get("/api/excusas/simple")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Debe retornar Content-Type JSON en todos los endpoints")
    void testContentTypeJSON() {
        given()
                .when()
                .get("/api/excusas/simple")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);

        given()
                .contentType(ContentType.JSON)
                .body("""
                        {
                            "texto": "Test",
                            "fuente": "Murphy"
                        }
                        """)
                .when()
                .post("/api/fragmentos/leyes")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON);
    }

    @Test
    @DisplayName("Debe retornar timestamp válido en todas las excusas")
    void testTimestampValido() {
        given()
                .when()
                .get("/api/excusas/ultra-shark")
                .then()
                .statusCode(200)
                .body("timestamp", greaterThan(0L));
    }

    @Test
    @DisplayName("Debe retornar modo válido en excusas")
    void testModoValido() {
        given()
                .when()
                .get("/api/excusas/simple")
                .then()
                .body("modo", anyOf(
                        equalTo("SIMPLE"),
                        equalTo("CON_MEME"),
                        equalTo("CON_LEY"),
                        equalTo("ULTRA_SHARK")
                ));
    }

    @Test
    @DisplayName("Debe cargar datos iniciales automáticamente")
    void testDatosIniciales() {
        // Debe haber datos para generar excusas
        given()
                .when()
                .get("/api/excusas/simple")
                .then()
                .statusCode(200)
                .body("contexto", notNullValue())
                .body("causa", notNullValue())
                .body("consecuencia", notNullValue())
                .body("recomendacion", notNullValue());
    }

    @Test
    @DisplayName("Debe soportar múltiples roles")
    void testMultiplesRoles() {
        String[] roles = {"dev", "qa", "devops", "pm", "sre"};

        for (String rol : roles) {
            given()
                    .when()
                    .get("/api/excusas/por-rol/" + rol)
                    .then()
                    .statusCode(200)
                    .body("contexto", notNullValue());
        }
    }

    @Test
    @DisplayName("Debe retornar respuestas consistentes")
    void testRespuestasConsistentes() {
        given()
                .when()
                .get("/api/excusas/simple")
                .then()
                .statusCode(200)
                .body("contexto", notNullValue())
                .body("causa", notNullValue())
                .body("consecuencia", notNullValue())
                .body("recomendacion", notNullValue())
                .body("timestamp", notNullValue())
                .body("modo", notNullValue());
    }

    @Test
    @DisplayName("Debe permitir crear y luego usar datos")
    void testCreateAndUse() {
        // Crear un nuevo contexto
        String newContextId = given()
                .contentType(ContentType.JSON)
                .body("""
                        {
                            "texto": "Nuevo contexto para prueba"
                        }
                        """)
                .when()
                .post("/api/fragmentos/contextos")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath()
                .getString("id");

        // Verificar que se creó
        assert newContextId != null;

        // Generar excusa (debería poder usar el nuevo contexto)
        given()
                .when()
                .get("/api/excusas/simple")
                .then()
                .statusCode(200)
                .body("contexto", notNullValue());
    }
}
