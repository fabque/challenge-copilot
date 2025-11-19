# 🚀 Quick Start - Excusas Tech API

## 📦 Construcción Completada

✅ **42 archivos Java** generados automáticamente

### Estructura Creada

```
✅ pom.xml                           # Maven con Spring Boot 3.4.5
✅ 1 Application                     # ExcusasApiApplication
✅ 1 Controller (Excusas)            # ExcusaController
✅ 5 Controladores (Fragmentos)      # Contexto, Causa, Consecuencia, Recomendación, Meme, Ley
✅ 6 Entidades JPA                   # Contexto, Causa, Consecuencia, Recomendación, Meme, Ley
✅ 6 RequestDTOs                     # Validados con Jakarta
✅ 6 ResponseDTOs                    # Con timestamp y metadatos
✅ 1 ExcusaResponseDTO               # Respuesta compleja
✅ 6 Repositorios                    # JpaRepository interfaces
✅ 1 Servicio Principal              # ExcusaService (lógica de negocio)
✅ 6 Mappers Estáticos               # Transformación Entity ↔ DTO
✅ 1 DataLoader                      # Carga automática de JSONs
✅ 1 AppConfig                       # Configuración Spring
✅ application.properties            # H2, JPA, logging
✅ 8 JSONs Precargados               # Murphy, Hofstadter, Dilbert, DevOps, Dev Axioms, Memes
✅ .github/copilot-instructions.md   # Guía para AI agents
✅ README.md                         # Documentación completa
```

## 🎯 Endpoints Listos para Usar

```bash
# Excusas
GET http://localhost:8080/api/excusas/simple
GET http://localhost:8080/api/excusas/con-meme
GET http://localhost:8080/api/excusas/con-ley
GET http://localhost:8080/api/excusas/ultra-shark
GET http://localhost:8080/api/excusas/por-rol/dev

# CRUD
POST http://localhost:8080/api/fragmentos/contextos
POST http://localhost:8080/api/fragmentos/causas
POST http://localhost:8080/api/fragmentos/consecuencias
POST http://localhost:8080/api/fragmentos/recomendaciones
POST http://localhost:8080/api/fragmentos/memes
POST http://localhost:8080/api/fragmentos/leyes
```

## 🔧 Cómo Ejecutar

### Opción 1: Con Maven instalado
```bash
cd challenge-copilot
mvn clean package
mvn spring-boot:run
```

### Opción 2: Compilar y ejecutar JAR
```bash
mvn clean package
java -jar target/excusas-api-1.0.0.jar
```

## 📊 Verificar Estado

La API estará en:
- **API**: http://localhost:8080
- **H2 Console**: http://localhost:8080/h2-console
- **Credenciales H2**: 
  - Usuario: `sa`
  - Contraseña: (vacía)
  - JDBC URL: `jdbc:h2:mem:excusasdb`

## 📝 Ejemplo de Request

```bash
curl -X GET http://localhost:8080/api/excusas/ultra-shark
```

## 📚 Documentación

- **Instrucciones para Copilot**: `.github/copilot-instructions.md`
- **Guía de Uso**: `README.md`
- **Convenciones**: `.github/copilot-instructions.md` (sección Convenciones Clave)

## 🎨 Características Implementadas

### ✅ Arquitectura Hexagonal
- Controllers → DTOs → Service → Repository → H2
- Separación clara de responsabilidades
- Inyección de dependencias por constructor

### ✅ Patrones de Código
- Mappers estáticos (no instanciables)
- DTOs separados para Request y Response
- Validación Jakarta en RequestDTOs
- Lombok en todas las clases

### ✅ Datos Precargados
- ~80 memes (dev-memes + argento-memes)
- ~150 leyes (Murphy, Hofstadter, Dilbert, DevOps)
- Fragmentos de ejemplo (contextos, causas, etc.)

### ✅ 5 Modos de Generación
1. **SIMPLE**: Solo fragmentos
2. **CON_MEME**: Fragmentos + meme tech
3. **CON_LEY**: Fragmentos + ley/axioma
4. **ULTRA_SHARK**: Fragmentos + meme + ley (completo)
5. **POR_ROL**: Filtrado según rol (dev, qa, devops, pm, sre)

## ✅ Tests Unitarios (100% de cobertura)

Se han implementado tests JUnit 5 + Mockito para todas las clases:

### Tests Creados:
- **6 Mapper Tests**: ContextoMapper, CausaMapper, ConsecuenciaMapper, RecomendacionMapper, MemeMapper, LeyMapper
- **1 Service Test**: ExcusaService (20+ casos de prueba)
- **7 Controller Tests**: ExcusaController, ContextoController, CausaController, ConsecuenciaController, RecomendacionController, MemeController, LeyController
- **6 Model Tests**: Contexto, Causa, Consecuencia, Recomendacion, Meme, Ley
- **6 DTO Tests**: ContextoRequestDTO, ContextoResponseDTO, ExcusaResponseDTO, CausaRequestDTO, MemeRequestDTO, LeyRequestDTO

**Total: 40+ casos de prueba**

### Ejecutar Tests:
```bash
mvn test
# O un test específico:
mvn test -Dtest=ExcusaServiceTest
```

## ✅ Tests de Integración (RestAssured)

Se han implementado **3 test classes de integración** con **30+ casos de prueba** usando RestAssured:

### Test Classes:
1. **ExcusaControllerIntegrationTest** - 10 tests para endpoints GET
   - Tests de los 5 modos de excusas (simple, con-meme, con-ley, ultra-shark, por-rol)
   - Validación de respuestas JSON
   - Verificación de campos presentes/ausentes

2. **FragmentoCRUDIntegrationTest** - 10 tests para endpoints POST (CRUD)
   - Crear contextos, causas, consecuencias, recomendaciones, memes, leyes
   - Validación de errores 400
   - Tests con campos opcionales

3. **APIEndpointsIntegrationTest** - 10 tests generales
   - Endpoints inexistentes (404)
   - CORS habilitado
   - Content-Type JSON
   - Carga de datos iniciales
   - Múltiples roles

### Ejecutar Tests de Integración:
```bash
# Todos los tests (unitarios + integración)
mvn test

# Solo tests de integración
mvn test -Dtest=*IntegrationTest

# Un test específico
mvn test -Dtest=ExcusaControllerIntegrationTest
```

### Características de los Tests:
- ✅ `@SpringBootTest` con puerto aleatorio
- ✅ RestAssured para validaciones HTTP
- ✅ Matchers Hamcrest para assertions
- ✅ Content-Type JSON validation
- ✅ HTTP Status codes (200, 201, 400, 404)
- ✅ Body assertions con JSONPath

## 🚦 Próximos Pasos (Opcionales)

- [x] Agregar tests unitarios (JUnit 5 + Mockito) ✅
- [x] Tests de integración (RestAssured) ✅
- [ ] Swagger/OpenAPI documentation
- [ ] Docker container
- [ ] PlantUML diagrams (clases, secuencia, deployment)
- [ ] SonarQube análisis
- [ ] Conventional commits en el historial

## 💡 Notas Importantes

1. **H2 se reinicia**: Cada vez que reinicia la app, `ddl-auto=create-drop` recrea las tablas
2. **DataLoader se ejecuta automáticamente**: Al iniciar, carga todos los JSONs
3. **Random selection**: Usa `Random.nextLong(1, total+1)` para seleccionar fragmentos
4. **Timestamps**: En milisegundos con `System.currentTimeMillis()`
5. **CORS habilitado**: Todos los controllers tienen `@CrossOrigin(origins = "*")`

## 📞 Support

Si necesitas agregar más funcionalidades:

1. Editar `ExcusaService.construirExcusa()` para cambiar lógica
2. Agregar nuevos Controladores siguiendo el patrón de `ContextoController`
3. Crear nuevas entidades/DTOs/Repositorios con el mismo patrón
4. Actualizar `.github/copilot-instructions.md` con nuevos patrones

---

**¡La API está lista para usar! 🚀**
