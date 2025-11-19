# 📊 PlantUML Diagrams - Excusas Tech API

Esta carpeta contiene diagramas UML generados con PlantUML que documentan la arquitectura, diseño y flujos de la API.

## 📁 Diagrams Incluidos

### 1. **01_class_diagram.puml** 🏗️
**Diagrama de Clases - Arquitectura Completa**

Visualiza la estructura de clases completa del proyecto:

- **Controllers**: ExcusaController, ContextoController, CausaController, etc.
- **DTOs**: Request/Response DTOs para cada fragmento
- **Entities**: Modelos JPA (Contexto, Causa, Consecuencia, etc.)
- **Repositories**: Interfaces JpaRepository
- **Service**: ExcusaService con toda la lógica de negocio
- **Mappers**: Transformadores Entity ↔ DTO en `service/mapper/`
- **Config**: DataLoader, AppConfig, SwaggerConfig

**Relaciones mostradas**:
- Inyección de dependencias
- Transformación de datos
- Persistencia con repositorios
- Gestión de configuración

### 2. **02_sequence_diagram.puml** 🔄
**Diagrama de Secuencia - Flujo Ultra-Shark**

Detalla el flujo completo cuando se genera una excusa en modo ULTRA-SHARK:

```
Client → Controller → Service → Repositories → Database
                      ↓
              (Selección aleatoria)
                      ↓
              Mappers (Entity → DTO)
                      ↓
              ExcusaResponseDTO
                      ↓
              Response (JSON)
```

**Pasos incluidos**:
1. Request GET /api/excusas/ultra-shark
2. Contar fragmentos disponibles
3. Seleccionar fragmentos aleatorios (contexto, causa, consecuencia, recomendación, meme, ley)
4. Mapear entidades a DTOs
5. Construir ExcusaResponseDTO con todos los campos
6. Retornar Response 200 con JSON

**Características**:
- Ejecución en paralelo de consultas a BD
- Timestamps en milisegundos
- Modo ULTRA_SHARK con meme y ley incluidos

### 3. **03_deployment_diagram.puml** 🚀
**Diagrama de Despliegue - Infraestructura**

Muestra la arquitectura de despliegue del proyecto:

**Componentes**:
- **Developer Machine**: Código fuente, Maven build, generación de JAR
- **Docker Registry**: Almacenamiento de imagen Docker
- **Production Environment**: 
  - Contenedores Docker (configurables con docker-compose)
  - JVM para ejecutar Spring Boot
  - Aplicación con propiedades de configuración
  - H2 Database (in-memory)
- **Load Balancer**: nginx/traefik para ruteo (opcional)
- **Client Machines**: Navegadores y clientes REST

**Flujo de despliegue**:
1. Código fuente en Git
2. Maven compila y genera JAR
3. Docker construye imagen
4. Imagen se sube al registry
5. Production ambiente pullea la imagen
6. docker-compose levanta contenedores
7. Clientes acceden vía load balancer

### 4. **04_component_diagram.puml** 🧩
**Diagrama de Componentes - Arquitectura Hexagonal**

Visualiza la arquitectura Hexagonal (Ports & Adapters) completa:

**Capas (de fuera hacia adentro)**:

```
┌─────────────────────────────────────────────────────────┐
│ External Layer (HTTP)                                   │
│ - REST API endpoints                                    │
└──────────────────┬──────────────────────────────────────┘
                   │
┌──────────────────┴──────────────────────────────────────┐
│ Adapter Layer (Controllers)                             │
│ - ExcusaController, ContextoController, etc.           │
└──────────────────┬──────────────────────────────────────┘
                   │
┌──────────────────┴──────────────────────────────────────┐
│ DTO Layer (Data Transfer)                               │
│ - Request/Response DTOs                                │
└──────────────────┬──────────────────────────────────────┘
                   │
╔══════════════════╩═══════════════════════════════════════╗
║ DOMAIN CORE (Hexagon Center)                            ║
║ - ExcusaService (Business Logic)                        ║
║ - No dependencies on frameworks                         ║
╚══════════════════╦═══════════════════════════════════════╝
                   │
┌──────────────────┴──────────────────────────────────────┐
│ Mapper Layer (Transformation)                           │
│ - ContextoMapper, CausaMapper, etc.                    │
└──────────────────┬──────────────────────────────────────┘
                   │
┌──────────────────┴──────────────────────────────────────┐
│ Data Access Layer (Repository)                          │
│ - JpaRepository implementations                         │
│ - Spring Data abstracts details                         │
└──────────────────┬──────────────────────────────────────┘
                   │
                   ▼
              H2 Database
```

**Ventajas de esta arquitectura**:
- ✅ Aislamiento: Dominio independiente de frameworks
- ✅ Testabilidad: Fácil mockar dependencias
- ✅ Mantenibilidad: Cambios en BD no afectan lógica
- ✅ Reusabilidad: Servicio puede usar diferentes adaptadores

## 🖥️ Cómo Generar Imágenes PNG/SVG

### Opción 1: Online Editor
1. Ir a https://www.plantuml.com/plantuml/uml
2. Copiar contenido del archivo .puml
3. Pegar en el editor
4. Click "Export"

### Opción 2: CLI (Local)

#### Instalar PlantUML
```bash
# Windows (con Chocolatey)
choco install plantuml

# macOS (con Homebrew)
brew install plantuml

# Linux (con apt)
sudo apt install plantuml
```

#### Generar Imágenes
```bash
# Generar PNG para todos los diagramas
plantuml docs/uml/*.puml

# Generar SVG (mejor para web)
plantuml -tsvg docs/uml/*.puml

# Generar PDF
plantuml -tpdf docs/uml/*.puml

# Un archivo específico
plantuml docs/uml/01_class_diagram.puml -o ../images/
```

#### Resultados
```
docs/
├── uml/
│   ├── 01_class_diagram.puml
│   ├── 01_class_diagram.png  ← Generado
│   ├── 02_sequence_diagram.puml
│   ├── 02_sequence_diagram.png  ← Generado
│   ├── 03_deployment_diagram.puml
│   ├── 03_deployment_diagram.png  ← Generado
│   ├── 04_component_diagram.puml
│   ├── 04_component_diagram.png  ← Generado
│   └── README.md  ← Este archivo
└── images/
    ├── 01_class_diagram.png
    ├── 02_sequence_diagram.png
    ├── 03_deployment_diagram.png
    └── 04_component_diagram.png
```

### Opción 3: Visual Studio Code

Instalar extensión "PlantUML" (jebbs.plantuml)

```bash
ext install jebbs.plantuml
```

Luego, con el archivo .puml abierto:
- Click derecho → "Export Current File"
- O usar shortcut: Alt+D

## 📖 Interpretación de Diagramas

### Clase Diagram
- **Rectángulos**: Clases
- **Líneas sólidas**: Relaciones/dependencias
- **Líneas punteadas**: Usos opcionales
- **Flechas**: Dirección de dependencia

### Sequence Diagram
- **Actores/Participantes**: Líneas verticales
- **Flechas sólidas**: Llamadas síncronas
- **Flechas punteadas**: Retornos/respuestas
- **Cuadros**: Actividad/procesamiento

### Deployment Diagram
- **Nodos**: Máquinas/servidores
- **Artefactos**: Componentes de software
- **Conexiones**: Comunicación entre nodos

### Component Diagram
- **Componentes**: Rectángulos redondeados
- **Puertos**: Conexiones (in/out)
- **Packages**: Agrupación de componentes
- **Bases de datos**: Cilindros especiales

## 🔍 Detalles Técnicos

### Color Scheme
- 🔵 Azul (#01579B): Tema principal
- 🟦 Azul claro (#E1F5FF): Fondos
- 🟨 Amarillo (#FFF9C4): Notas
- 🟠 Naranja (#F57C00): Énfasis

### Relaciones Documentadas

**Controllers → Service**
```
ExcusaController --uses--> ExcusaService
```

**Service → Repositories**
```
ExcusaService --uses--> ContextoRepository
ExcusaService --uses--> CausaRepository
... (6 repositorios)
```

**Mappers → DTOs & Entities**
```
ContextoMapper --transforms--> ContextoRequestDTO
ContextoMapper --transforms--> Contexto
ContextoMapper --produces--> ContextoResponseDTO
```

## 📝 Notas Importantes

1. **Hexagonal Architecture**: El diseño sigue el patrón de puertos y adaptadores
2. **Separación de Capas**: Cada capa tiene responsabilidad única
3. **Independencia del Dominio**: ExcusaService no depende de Spring
4. **Testabilidad**: Todas las dependencias son inyectadas
5. **Data Initialization**: DataLoader precarga datos en startup

## 🔗 Relación con Docker

Los diagramas de deployment muestran cómo la aplicación se containeriza:
- **Dockerfile**: Construye imagen con JAR + dependencias
- **docker-compose.yml**: Orquesta contenedores
- **H2 Database**: En-memoria en cada contenedor
- **Puertos**: 8080 para REST API

## 📚 Referencias

- [PlantUML Documentation](https://plantuml.com/)
- [UML Class Diagrams](https://www.lucidchart.com/blog/class-diagram-relationships)
- [Hexagonal Architecture](https://alistair.cockburn.us/hexagonal-architecture/)
- [ArchiMate (Deployment)](https://pubs.opengroup.org/architecture/archimate3-doc/)

---

**Generado**: November 19, 2025  
**Proyecto**: Excusas Tech API  
**Versión**: 1.0.0
