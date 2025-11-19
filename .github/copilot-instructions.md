# Instrucciones para GitHub Copilot - Excusas Tech API

## Arquitectura y Contexto

**Excusas Tech API**: Generador de excusas técnicas aleatorias con fragmentos combinables (contexto, causa, consecuencia, recomendación), memes argentinos y leyes/axiomas (Murphy, Hofstadter, Dilbert, DevOps Principles).

Construido con **Spring Boot 3.4.5** y **Java 21**, arquitectura **Hexagonal (Ports & Adapters)** con **H2** en memoria.

### Principios
- **Hexagonal**: Dominio independiente de frameworks
- **Clean Code + SOLID**: Aplicados en toda la arquitectura
- **Lombok**: En TODAS las entidades y DTOs
- **JPA**: Spring Data JPA para persistencia

## Estructura de Paquetes

```
com.accenture.excusas/
├── controller/           # Adaptadores REST (ExcusaController, FragmentoControllers)
├── dto/                 # Request/Response DTOs (@Data, @Builder con validación Jakarta)
├── model/               # Entidades JPA (@Entity con @Getter, @Setter, @Builder)
├── repository/          # JpaRepository interfaces (sin implementación)
├── service/             # Lógica de negocio + Mappers estáticos (ExcusaService, *Mapper)
└── config/              # Configuración (DataLoader, AppConfig)
```

### Data Flow
```
GET /api/excusas/ultra-shark
    ↓
ExcusaController → ExcusaService.generarExcusaUltraShark()
    ↓
ExcusaService combina fragmentos aleatorios (Contexto, Causa, Consecuencia, Recomendación, Meme, Ley)
    ↓
Repositorios obtienen datos de H2 → ExcusaResponseDTO → JSON
```

## Core Features

### 1. Generación de Excusas por Modo
```java
// En ExcusaController:
GET /api/excusas/simple          // Solo fragmentos
GET /api/excusas/con-meme        // Fragmentos + meme
GET /api/excusas/con-ley         // Fragmentos + ley
GET /api/excusas/ultra-shark     // Fragmentos + meme + ley (la mejor)
GET /api/excusas/por-rol/{rol}   // Filtrada por rol (dev, qa, devops, pm, etc.)
```

### 2. Fragmentos Base (Entidades)
- **Contexto**: Situación inicial ("Durante un deploy en prod...")
- **Causa**: Razón del problema ("porque alguien olvidó...")
- **Consecuencia**: Resultado ("el código se rompió...")
- **Recomendación**: Solución ("revisar los logs...")
- **Meme**: Humor tech (dev-memes, argento-memes)
- **Ley**: Justificación (Murphy, Hofstadter, Dilbert, DevOps, DevAxioms)

### 3. CRUD de Fragmentos
```java
POST /api/fragmentos/contextos        // Crear contexto
POST /api/fragmentos/causas           // Crear causa
POST /api/fragmentos/consecuencias    // Crear consecuencia
POST /api/fragmentos/recomendaciones  // Crear recomendación
POST /api/fragmentos/memes            // Crear meme
POST /api/fragmentos/leyes            // Crear ley
```

## Patrones Críticos

### 1. Mappers Estáticos (Service Layer)
```java
public class ContextoMapper {
    private ContextoMapper() {} // Nunca se instancia
    
    public static Contexto toEntity(ContextoRequestDTO dto) { ... }
    public static ContextoResponseDTO toResponse(Contexto entity) { ... }
}
```

### 2. ExcusaService - Lógica Principal
```java
@Service
@RequiredArgsConstructor
public class ExcusaService {
    private final ContextoRepository contextoRepository;
    // ... otros repos ...
    
    // Métodos de generación
    public ExcusaResponseDTO generarExcusaSimple() { }
    public ExcusaResponseDTO generarExcusaConMeme() { }
    public ExcusaResponseDTO generarExcusaConLey() { }
    public ExcusaResponseDTO generarExcusaUltraShark() { }
    public ExcusaResponseDTO generarExcusaPorRol(String rol) { }
    
    // Métodos CRUD
    public ContextoResponseDTO crearContexto(ContextoRequestDTO dto) { }
    // ... otros CRUD ...
}
```

**Key**: El método `construirExcusa()` privado selecciona fragmentos aleatorios de cada repositorio combinándolos en `ExcusaResponseDTO`.

### 3. DataLoader - Carga de JSONs
```java
@Component
public class DataLoader implements CommandLineRunner {
    @Override
    public void run(String... args) {
        cargarMemes();      // dev-memes.json, argento-memes.json
        cargarLeyes();      // murphy.json, hofstadter.json, dilbert.json, etc.
        cargarContextos();  // Valores iniciales básicos
    }
}
```

**Ubicación de JSONs**: `src/main/resources/data/` (Murphy, Hofstadter, Dilbert, DevOps, etc.)

### 4. DTOs con Validación Jakarta
```java
// ContextoRequestDTO
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ContextoRequestDTO {
    @NotBlank(message = "El texto es requerido")
    private String texto;
    private String role;
    private String tags;
}
```

### 5. Controllers Simples - Una Responsabilidad
```java
@RestController
@RequestMapping("/api/excusas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ExcusaController {
    private final ExcusaService excusaService;
    
    @GetMapping("/simple")
    public ResponseEntity<ExcusaResponseDTO> generarExcusaSimple() {
        return ResponseEntity.ok(excusaService.generarExcusaSimple());
    }
}
```

## Entidades Clave

### ExcusaResponseDTO
```java
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ExcusaResponseDTO {
    private String contexto;
    private String causa;
    private String consecuencia;
    private String recomendacion;
    private String meme;           // null si modo SIMPLE/CON_LEY
    private String ley;            // null si modo SIMPLE/CON_MEME
    private String fuente;         // Murphy, Hofstadter, etc.
    private Long timestamp;        // System.currentTimeMillis()
    private String modo;           // SIMPLE, CON_MEME, CON_LEY, ULTRA_SHARK
}
```

## Comandos Críticos

```bash
# Compilar
mvn clean package

# Ejecutar
mvn spring-boot:run

# Ejecutar tests
mvn test

# Ejecutar test específico
mvn test -Dtest=ExcusaServiceTest

# H2 Console
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:excusasdb
User: sa | Password: (vacía)
```

## Testing

### Estructura de Tests
```
src/test/java/com/accenture/excusas/
├── service/mapper/  # 6 tests de Mappers
├── service/         # 1 test de ExcusaService (20+ casos)
├── controller/      # 7 tests de Controllers
├── model/           # 6 tests de Entidades
└── dto/             # 6 tests de DTOs
```

### Casos de Prueba Cubiertos
- **Mappers**: Conversión Entity↔DTO, manejo de nulls
- **Service**: Todos los modos (SIMPLE, CON_MEME, CON_LEY, ULTRA_SHARK), CRUD, datos faltantes
- **Controllers**: HTTP status (201 Created, 200 OK), mock service calls
- **Models**: Builder pattern, getters/setters, campos múltiples
- **DTOs**: Constructor, builder, validaciones

### Ejecución de Tests
```bash
# Todos los tests
mvn test

# Con cobertura
mvn test jacoco:report

# Solo tests de service
mvn test -Dtest=*Service*
```

## Extensiones Comunes

### Agregar nuevo rol
1. Crear fragmentos en DTOs/Controllers con `role="nombre_rol"`
2. Pasar rol a `generarExcusaPorRol(rol)` → filtra fragmentos por rol

### Agregar nueva ley/meme
1. Crear JSON en `docs/docs/json/`
2. Copiar a `src/main/resources/data/`
3. Agregar carga en `DataLoader.java`
4. Reiniciar app (recarga H2 con `ddl-auto=create-drop`)

### Extender generación
- Editar `construirExcusa()` privado en ExcusaService
- Agregar new modo (ej: `CON_LEY_SIN_MEME`)
- Crear endpoint correspondiente

## Convenciones Clave

- **Inyección**: Por constructor, `@RequiredArgsConstructor` de Lombok
- **Responses HTTP**: `ResponseEntity` con códigos (201 Created, 200 OK)
- **Validación**: Solo en RequestDTOs con `@Valid`
- **Timestamps**: `System.currentTimeMillis()` para generación
- **Random**: `Random.nextLong(1, total+1)` para seleccionar IDs
