````markdown
# 🦈 Java Sharks Challenge – Excusas Tech API + Memes + Leyes

Bienvenido al **Challenge oficial de la Tribu Java Sharks**.  
Tu misión: construir una **API REST** divertida, creativa y técnicamente sólida que genere excusas tech mezclando **fragmentos**, **memes**, **leyes del caos developer** y **axiomas universales del mundo IT**.

La idea es que desarrolles **en dupla con GitHub Copilot**, aprovechando su modo agente, comandos, edición y generación de código.

---

## 🎯 Objetivo

Crear una API REST que pueda:

- Generar **excusas tech aleatorias**
- Combinar fragmentos: *contexto*, *causa*, *consecuencia*, *recomendación*
- Sumar opcionalmente **memes tech argentinos**
- Justificar la excusa con **leyes/axiomas** (Murphy, Hofstadter, Dilbert, DevOps Principles, Axiomas del Dev)
- Exponer endpoints que permitan CRUD sobre los fragmentos y generación de excusas
- Ofrecer endpoints por rol: *dev*, *qa*, *devops*, *pm*, etc.

Tu API puede devolver:
- ✨ ley, meme, excusa del dia   
- ✨ excusa simple  
- ✨ excusa + meme  
- ✨ excusa + ley  
- ✨ excusa + meme + ley (modo ULTRA SHARK)

---

## 🚀 Inicio Rápido

### Requisitos
- Java 21+
- Maven 3.8+

### Pasos

```bash
# 1. Compilar
mvn clean package

# 2. Ejecutar
mvn spring-boot:run

# 3. Acceder a la API
curl http://localhost:8080/api/excusas/ultra-shark

# 4. H2 Console (opcional)
# http://localhost:8080/h2-console
# JDBC URL: jdbc:h2:mem:excusasdb
# Usuario: sa | Contraseña: (vacía)
```

## 📡 Endpoints Implementados

```
# Excusas
GET  /api/excusas/simple              # Excusa simple
GET  /api/excusas/con-meme            # Excusa + meme
GET  /api/excusas/con-ley             # Excusa + ley
GET  /api/excusas/ultra-shark         # Excusa + meme + ley
GET  /api/excusas/por-rol/{rol}       # Excusa por rol

# CRUD de fragmentos
POST /api/fragmentos/contextos        # Crear contexto
POST /api/fragmentos/causas           # Crear causa
POST /api/fragmentos/consecuencias    # Crear consecuencia
POST /api/fragmentos/recomendaciones  # Crear recomendación
POST /api/fragmentos/memes            # Crear meme
POST /api/fragmentos/leyes            # Crear ley
```

## 📝 Ejemplo de Respuesta

```bash
curl http://localhost:8080/api/excusas/ultra-shark
```

```json
{
  "contexto": "Durante un deploy en producción",
  "causa": "porque alguien olvidó actualizar la documentación",
  "consecuencia": "el código se rompió en producción de forma silenciosa",
  "recomendacion": "revisar los logs y las métricas de forma inmediata",
  "meme": "Mi framework favorito es el que no me tocó mantener en producción",
  "ley": "Si algo puede fallar en producción, va a fallar justo después de que te vayas a tu casa",
  "fuente": "Murphy",
  "timestamp": 1734603600000,
  "modo": "ULTRA_SHARK"
}
```

## 🏗️ Arquitectura Implementada

**Hexagonal (Ports & Adapters)**:
- ✅ Controllers → DTOs → Service → Repository → H2
- ✅ Mappers estáticos para transformación
- ✅ Validación Jakarta en RequestDTOs
- ✅ Lombok en todas las clases
- ✅ DataLoader para precarga de JSONs

## 📂 Estructura del Proyecto

```
src/main/java/com/accenture/excusas/
├── controller/          # ExcusaController, FragmentoControllers
├── dto/                # Request/Response DTOs
├── model/              # Entidades (Contexto, Causa, etc.)
├── repository/         # JpaRepositories
├── service/            # ExcusaService + Mappers
└── config/             # DataLoader, AppConfig

src/main/resources/
├── data/               # JSONs precargados
└── application.properties
```

## 📚 Documentación Técnica

Ver `.github/copilot-instructions.md` para:
- Patrones de código hexagonal
- Convenciones del proyecto
- Guía para AI agents

### Guías Adicionales
- **DOCKER.md** - Containerización y ejecución con Docker
- **QUICK_START.md** - Setup inicial y tests
- **TEST_COVERAGE.md** - Cobertura de tests
- **PROJECT_REVIEW.md** - Revisión completa del proyecto
- **docs/uml/README.md** - Diagramas UML (PlantUML)

---

## 📦 Requisitos mínimos

### 🧩 API REST
- ✅ CRUD básico de fragmentos (contexto, causa, consecuencia, recomendación)
- ✅ CRUD opcional de memes y leyes
- ✅ Endpoints por rol

### 📘 Documentación
- ✅ README claro
- PlantUML (próximos pasos)
- ✅ Instrucciones para Copilot

### 🗃️ Persistencia
- ✅ H2 en memoria
- ✅ Repositorio + servicio + modelo

### 🧪 Tests mínimos
- Pendiente: tests unitarios

### 🧾 Entregables
- ✅ Proyecto Spring Boot + Maven
- ✅ Código compilable y funcional
- ✅ Endpoints funcionales
- ✅ Swagger disponible

---

## 🧯 Criterios de evaluación

###  Level Mojarrita ✅
- ✅ API funcional
- ✅ Generador de excusas coherente y reproducible  
- ✅ Persistencia simple (H2)  
- ✅ Código limpio

###  Level Delfin 
- Calidad del diseño
- Patrones 
- Clean code 
- Conventional commits  

###  Level Shark ✅
- ✅ 100% Coverage de test de unidad
- ✅ SonarLint sin issues graves  
- ✅ PlantUML Diagramas (Clases, Deploy, Componentes, Secuencia)

###  Level White Shark ✅
- ✅ Arquitectura Hexagonal 
- ✅ Docker (containerización completa)
- ✅ Test de integración

###  Megalodon 
- Usar AI para componer las excusas

---

## 🎉 ¡Éxitos Shark!
Recordá: la creatividad suma, el humor suma más,  
y si algo sale mal… ¡tenés una API completa para explicarlo! 🦈💻😅

````
