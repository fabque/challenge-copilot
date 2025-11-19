# 🐳 Docker Setup - Excusas Tech API

Esta guía te enseña cómo ejecutar la **Excusas Tech API** usando Docker.

---

## 📋 Requisitos Previos

- Docker 20.10+ instalado
- Docker Compose 2.0+ instalado

### Verificar instalación

```bash
docker --version
docker-compose --version
```

---

## 🚀 Ejecución Rápida

### Opción 1: Docker Compose (Recomendado)

```bash
# Navega al directorio del proyecto
cd challenge-copilot

# Construir e iniciar el contenedor
docker-compose up --build

# La API estará disponible en: http://localhost:8080
```

### Opción 2: Docker Manual

```bash
# Construir la imagen
docker build -t excusas-api:1.0.0 .

# Ejecutar el contenedor
docker run -p 8080:8080 \
  --name excusas-api \
  excusas-api:1.0.0

# La API estará disponible en: http://localhost:8080
```

---

## 🛑 Detener los Contenedores

### Con Docker Compose

```bash
# Detener (sin eliminar volúmenes)
docker-compose down

# Detener y eliminar volúmenes
docker-compose down -v
```

### Con Docker Manual

```bash
# Detener el contenedor
docker stop excusas-api

# Eliminar el contenedor
docker rm excusas-api
```

---

## 📡 Acceso a la API

Una vez que el contenedor está corriendo:

### Endpoints

```bash
# Excusas
curl http://localhost:8080/api/excusas/simple
curl http://localhost:8080/api/excusas/con-meme
curl http://localhost:8080/api/excusas/con-ley
curl http://localhost:8080/api/excusas/ultra-shark
curl http://localhost:8080/api/excusas/por-rol/dev

# Crear fragmentos
curl -X POST http://localhost:8080/api/fragmentos/contextos \
  -H "Content-Type: application/json" \
  -d '{"texto":"Nuevo contexto"}'
```

### Swagger UI

Abre en tu navegador:
```
http://localhost:8080/swagger-ui.html
```

### OpenAPI JSON

```
http://localhost:8080/v3/api-docs
```

---

## 🔍 Inspeccionar Contenedores

### Ver logs

```bash
# Con docker-compose
docker-compose logs -f excusas-api

# Con docker
docker logs -f excusas-api
```

### Entrar al contenedor

```bash
# Con docker-compose
docker-compose exec excusas-api /bin/sh

# Con docker
docker exec -it excusas-api /bin/sh
```

### Ver estado

```bash
# Con docker-compose
docker-compose ps

# Con docker
docker ps
```

---

## 🏗️ Estructura del Dockerfile

### Multi-stage Build

El `Dockerfile` usa construcción multi-etapa para optimizar el tamaño de la imagen:

**Stage 1 - Builder**: Maven 3.9.8 con JDK 21
- Copia `pom.xml` y `src/`
- Compila el proyecto
- Genera `excusas-api-1.0.0.jar`

**Stage 2 - Runtime**: Alpine JRE 21
- Copia solo el JAR compilado
- Tamaño final: ~200-300 MB (optimizado)
- Ejecuta la aplicación

### Health Check

El contenedor incluye un health check:

```dockerfile
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3
```

---

## ⚙️ Variables de Entorno

Puedes personalizar el comportamiento con variables de entorno:

```bash
# Con docker-compose
docker-compose up \
  -e JAVA_OPTS="-Xmx1024m -Xms512m" \
  -e SPRING_PROFILES_ACTIVE="production"

# Con docker
docker run -p 8080:8080 \
  -e JAVA_OPTS="-Xmx1024m -Xms512m" \
  excusas-api:1.0.0
```

---

## 🔧 Configuración del docker-compose.yml

```yaml
services:
  excusas-api:
    build:
      context: .           # Directorio del Dockerfile
      dockerfile: Dockerfile
    container_name: excusas-api
    ports:
      - "8080:8080"       # Puerto expuesto
    environment:
      JAVA_OPTS: "-Xmx512m -Xms256m"  # Memoria JVM
    healthcheck:          # Verificación de salud
      test: ["CMD", "wget", "--no-verbose", "--tries=1", "--spider", "http://localhost:8080/actuator/health"]
      interval: 30s
      timeout: 3s
      retries: 3
      start_period: 10s
    restart: unless-stopped  # Reinicio automático
```

---

## 📊 Tamaño de Imagen

Gracias al multi-stage build:

| Layer | Tamaño |
|-------|--------|
| Maven (Builder) | ~500 MB |
| Alpine JRE (Final) | ~150 MB |
| **Total Imagen** | **~200-300 MB** |

---

## 🚨 Troubleshooting

### El contenedor no inicia

```bash
# Ver logs
docker-compose logs excusas-api

# Verificar que el puerto 8080 no está en uso
netstat -an | grep 8080
lsof -i :8080
```

### Error de memoria

```bash
# Aumentar memoria en docker-compose.yml
environment:
  JAVA_OPTS: "-Xmx1024m -Xms512m"

# O pasar por línea de comandos
docker run -e JAVA_OPTS="-Xmx1024m -Xms512m" ...
```

### API lenta

```bash
# Verificar recursos del contenedor
docker stats excusas-api

# Aumentar límites si es necesario
docker-compose.yml → agregar recursos
```

---

## 📈 Optimizaciones

### Cache de Maven

```bash
# Reutilizar cache de Maven entre builds
docker build --build-arg MAVEN_CACHE=/cache \
  -t excusas-api:1.0.0 .
```

### Ejecutable optimizado

```bash
# Habilitar startup más rápido
-e JAVA_OPTS="-XX:TieredStopAtLevel=1 -XX:+UseSerialGC"
```

---

## 🔐 Seguridad

### Imagen base confiable

- ✅ Usa `eclipse-temurin` (oficial, mantenida)
- ✅ Alpine para tamaño mínimo
- ✅ Basado en Red Hat UBI para producción

### Buenas prácticas

```dockerfile
# ✅ Bueno: usuario no-root
USER app

# ✅ Bueno: read-only filesystem donde sea posible
--read-only

# ✅ Bueno: resource limits
docker run --memory="512m" --cpus="1" ...
```

---

## 📝 Verificar Funcionamiento

```bash
# 1. Ver estado del contenedor
docker-compose ps

# 2. Revisar logs
docker-compose logs

# 3. Probar health check
curl http://localhost:8080/actuator/health

# 4. Probar endpoint de API
curl http://localhost:8080/api/excusas/simple

# 5. Acceder a Swagger UI
# http://localhost:8080/swagger-ui.html
```

---

## 🎯 Próximos Pasos

- [ ] Agregar Kubernetes manifests
- [ ] Configurar registry privado (Docker Hub, ECR)
- [ ] Agregar CI/CD pipeline (GitHub Actions, GitLab CI)
- [ ] Implementar Prometheus metrics
- [ ] Agregar logging centralizado (ELK Stack)

---

## 📞 Support

Para problemas, consulta:
- `.github/copilot-instructions.md` - Guía de desarrollo
- `QUICK_START.md` - Inicio rápido
- `README.md` - Documentación general
