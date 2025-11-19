# 📝 Convenciones de Commits Git

Guía de convenciones de commits para el proyecto **Excusas Tech API** usando **Conventional Commits** adaptado al español.

---

## 🎯 Objetivo

Mantener un historial de commits **legible**, **automatizable** y **semánticamente significativo** que facilite:
- 📖 Lectura clara del historial
- 🔄 Generación automática de changelogs
- 📊 Análisis de cambios
- 🔍 Búsqueda en el historial

---

## 📋 Formato General

```
<tipo>(<alcance>): <descripción>

<cuerpo>

<pie de página>
```

### Componentes

| Componente | Descripción | Obligatorio |
|-----------|-------------|-----------|
| **tipo** | Categoría del cambio | ✅ Sí |
| **alcance** | Área del código afectada | ❌ No |
| **descripción** | Resumen en imperativo | ✅ Sí |
| **cuerpo** | Explicación detallada | ❌ No |
| **pie de página** | Cierre de issues | ❌ No |

---

## 🏷️ Tipos de Commits

### ✨ `feat` – Nueva Funcionalidad

Introduce una **nueva característica** o capacidad.

```bash
git commit -m "feat(excusa): agregar generador de excusas por rol"
git commit -m "feat(api): crear endpoint ultra-shark con meme y ley"
git commit -m "feat(validation): validar fragmentos con Jakarta"
```

**Casos de uso:**
- ✅ Nuevo endpoint REST
- ✅ Nueva característica de negocio
- ✅ Nuevo servicio
- ✅ Nueva entidad

---

### 🐛 `fix` – Corrección de Defecto

Corrige un **bug** o comportamiento incorrecto.

```bash
git commit -m "fix(service): corregir selección aleatoria de fragmentos"
git commit -m "fix(controller): validar rol en endpoint por-rol"
git commit -m "fix(mapper): manejar nulls correctamente"
```

**Casos de uso:**
- ✅ Corrección de lógica defectuosa
- ✅ Solución de error en validación
- ✅ Reparación de transformación de datos

---

### 📚 `docs` – Documentación

Cambios **solo de documentación** (sin código de aplicación).

```bash
git commit -m "docs: actualizar README con ejemplos"
git commit -m "docs(api): documentar endpoints en Swagger"
git commit -m "docs(architecture): agregar diagramas UML"
git commit -m "docs(guide): crear guía de quick start"
```

**Casos de uso:**
- ✅ Actualizar README
- ✅ Crear/actualizar guías
- ✅ Agregar comentarios en documentación
- ✅ Crear diagramas
- ✅ Actualizar comentarios en código
- ✅ Agregar Javadoc

---

### 🎨 `style` – Estilo de Código

Cambios que **no afectan la lógica** (formato, imports, whitespace).

```bash
git commit -m "style: formatear código según conventions"
git commit -m "style(imports): organizar imports automáticamente"
git commit -m "style: remover whitespace innecesario"
```

**Casos de uso:**
- ✅ Reformateo de código
- ✅ Organización de imports
- ✅ Corrección de espacios en blanco
- ✅ Ajuste de indentación
- ❌ NO: cambios de lógica

---

### ♻️ `refactor` – Refactorización

Reestructura de código **sin cambiar funcionalidad**.

```bash
git commit -m "refactor(service): extraer lógica de construcción a método privado"
git commit -m "refactor(mapper): mover mappers a subdirectorio"
git commit -m "refactor(controller): simplificar parámetros de métodos"
```

**Casos de uso:**
- ✅ Renombrar variables/métodos
- ✅ Extracción de métodos
- ✅ Reorganización de código
- ✅ Mejora de estructura
- ❌ NO: cambios de comportamiento

---

### ⚡ `perf` – Mejora de Rendimiento

Cambios que **mejoran rendimiento o eficiencia**.

```bash
git commit -m "perf(repository): agregar índices en H2"
git commit -m "perf(service): optimizar búsqueda de fragmentos"
git commit -m "perf(cache): implementar caché de memes"
```

**Casos de uso:**
- ✅ Optimización de algoritmos
- ✅ Reducción de complejidad
- ✅ Implementación de cachés
- ✅ Mejora de queries

---

### 🧪 `test` – Tests

Agregación o modificación de **casos de prueba**.

```bash
git commit -m "test: agregar tests unitarios para ExcusaService"
git commit -m "test(mappers): crear 30 casos de test para mappers"
git commit -m "test(integration): agregar tests con RestAssured"
```

**Casos de uso:**
- ✅ Agregar tests unitarios
- ✅ Agregar tests de integración
- ✅ Actualizar fixtures de test
- ✅ Mejorar cobertura

---

### 🔧 `chore` – Tareas de Mantenimiento

Cambios que **no afectan código productivo** (dependencias, configuración, build).

```bash
git commit -m "chore(pom): actualizar Spring Boot a 3.4.5"
git commit -m "chore(maven): agregar plugin SpringDoc OpenAPI"
git commit -m "chore(docker): crear Dockerfile multi-stage"
git commit -m "chore(config): configurar docker-compose"
```

**Casos de uso:**
- ✅ Actualizar dependencias
- ✅ Cambiar configuración de build
- ✅ Crear archivos Docker
- ✅ Configurar CI/CD
- ✅ Actualizar .gitignore

---

### 🚀 `ci` – Integración Continua

Cambios en configuración de **CI/CD** o scripts de build.

```bash
git commit -m "ci: crear workflow de GitHub Actions"
git commit -m "ci: configurar SonarQube"
git commit -m "ci: agregar validación de tests en push"
```

**Casos de uso:**
- ✅ Workflows de GitHub Actions
- ✅ Configuración de SonarQube
- ✅ Scripts de deployment
- ✅ Configuración de linters

---

### 🔐 `sec` – Seguridad

Cambios relacionados con **seguridad** o vulnerabilidades.

```bash
git commit -m "sec: validar entrada de usuario"
git commit -m "sec: agregar @Valid en RequestDTOs"
git commit -m "sec: parchear vulnerabilidad de dependencia"
```

**Casos de uso:**
- ✅ Corrección de vulnerabilidades
- ✅ Mejora de validación
- ✅ Actualización de dependencias vulnerables

---

## 📌 Alcances (Scope)

El alcance debe indicar **qué parte del proyecto** se afecta:

```
feat(excusa): ...          # Controlador de excusas
feat(fragmento): ...       # Manejo de fragmentos
feat(mapper): ...          # Transformación de datos
feat(test): ...            # Tests
feat(config): ...          # Configuración
feat(docker): ...          # Docker
feat(api): ...             # API en general
feat(validation): ...      # Validación
```

---

## ✍️ Descripción

### Reglas

1. **Imperativo**: Usa verbo en imperativo ("agregar", "crear", "corregir")
   - ✅ `agregar validación`
   - ❌ `agregada validación`
   - ❌ `agrega validación`

2. **Minúscula**: Primera letra en minúscula
   - ✅ `feat: crear endpoint`
   - ❌ `feat: Crear endpoint`

3. **Sin punto**: No termines con punto al final
   - ✅ `feat: agregar validación`
   - ❌ `feat: agregar validación.`

4. **Conciso**: Máximo 50 caracteres

### Ejemplos

```
feat(excusa): crear endpoint ultra-shark
fix(service): corregir selección aleatoria
docs: actualizar README con ejemplos
refactor(mapper): simplificar lógica de transformación
test: agregar 50 casos de test
```

---

## 📄 Cuerpo del Commit (Opcional)

Proporciona **contexto detallado** separado de la descripción por una línea en blanco.

### Contenido

- **¿Qué?** - Cambio realizado
- **¿Por qué?** - Motivo del cambio
- **¿Cómo?** - Implementación
- **Impacto** - Efectos esperados

### Ejemplo

```
feat(excusa): agregar generador de excusas por rol

Implementa filtrado de fragmentos según rol del usuario (dev, qa, devops, pm).

El endpoint GET /api/excusas/por-rol/{rol} retorna excusas personalizadas
seleccionando solo fragmentos etiquetados con ese rol.

Cambios:
- Agregar parámetro 'role' a fragmentos
- Extender ExcusaService con lógica de filtrado
- Validar rol contra lista permitida
- Agregar 40+ tests

Cierra #123
```

---

## 🔗 Pie de Página (Opcional)

Usa para **cerrar issues** o agregar referencias.

### Formato

```
Cierra #123
Soluciona #456
Relacionado con #789
Co-authored-by: Nombre <email@example.com>
```

### Ejemplos

```
feat(api): agregar endpoint ultra-shark

Cierra #42
```

```
fix(service): corregir bug en selección aleatoria

Soluciona #15
Relacionado con #10
```

---

## 📋 Ejemplos Prácticos

### Ejemplo 1: Nueva Funcionalidad

```
feat(excusa): crear generador de excusas por rol

Implementa filtrado de fragmentos según rol del usuario.

Endpoints nuevos:
- GET /api/excusas/por-rol/{rol}

Cambios realizados:
- Agregar campo 'role' a fragmentos
- Extender ExcusaService con obtenerFragmentoPorRol()
- Validar rol contra lista permitida (dev, qa, devops, pm)
- Agregar 40 casos de test

Cierra #123
```

### Ejemplo 2: Bug Fix

```
fix(service): corregir selección aleatoria de fragmentos

La función Random.nextLong() no respetaba el rango máximo,
causando IndexOutOfBoundsException cuando el repositorio
estaba vacío.

Cambios:
- Validar count() > 0 antes de acceder
- Usar nextLong(1, count+1) con rango correcto
- Retornar fragmento vacío si no hay datos

Soluciona #56
```

### Ejemplo 3: Refactorización

```
refactor(mapper): extraer lógica de transformación

Mejora legibilidad moviendo lógica null-safe a métodos privados.

Cambios:
- Crear método privado validateAndTransform()
- Aplicar patrón Builder de forma más clara
- Reducir complejidad ciclomática

No hay cambios de comportamiento.
```

### Ejemplo 4: Tests

```
test(excusa): agregar 20 casos de test para ExcusaService

Cobertura completa de todos los modos de generación:
- generarExcusaSimple()
- generarExcusaConMeme()
- generarExcusaConLey()
- generarExcusaUltraShark()
- generarExcusaPorRol()

Incluye tests de casos edge (repositorios vacíos, datos nulos).
```

### Ejemplo 5: Documentación

```
docs: crear guía de quick start

Documenta:
- Requisitos (Java 21, Maven 3.8+)
- Pasos de compilación y ejecución
- Ejemplos de curl para endpoints
- Acceso a H2 console
```

### Ejemplo 6: Dependencias

```
chore(pom): actualizar Spring Boot a 3.4.5

Cambios:
- Spring Boot: 3.4.4 → 3.4.5
- SpringDoc OpenAPI: 2.5.0 → 2.6.0
- Mockito: 5.5.1 → 5.6.0

Fixes:
- Mejora de rendimiento en DataLoader
- Corrige vulnerabilidad en Jackson
```

---

## 🚫 Anti-patrones a Evitar

```bash
# ❌ Commits muy genéricos
git commit -m "update"
git commit -m "fix"
git commit -m "cambios"

# ❌ Descripciones sin contexto
git commit -m "feat: cambios varios"
git commit -m "fix: arreglar cosas"

# ❌ Tipo incorrecto
git commit -m "feature: ..."      # ❌ usar "feat"
git commit -m "bugfix: ..."       # ❌ usar "fix"
git commit -m "testing: ..."      # ❌ usar "test"

# ❌ Commits con múltiples cambios no relacionados
git commit -m "feat: agregar excusa + refactor mapper + test nuevo"

# ❌ Sin alcance cuando es necesario
git commit -m "feat: agregar validación"  # ¿En qué módulo?
git commit -m "fix: corregir error"       # ¿Dónde está el error?
```

---

## ✅ Checklist Pre-Commit

Antes de hacer commit, verifica:

- [ ] El tipo es correcto (feat, fix, docs, etc.)
- [ ] El alcance está definido (o se omite si es global)
- [ ] La descripción está en imperativo
- [ ] La descripción no tiene punto al final
- [ ] La descripción tiene máximo 50 caracteres
- [ ] El commit contiene cambios relacionados
- [ ] Los tests pasan (si aplica)
- [ ] El código sigue las convenciones del proyecto
- [ ] He incluído `Cierra #issue` si es aplicable

---

## 🔗 Integración con Herramientas

### Git Hook (pre-commit)

Puedes crear un git hook para validar commits automáticamente.

Crea `.git/hooks/commit-msg`:

```bash
#!/bin/bash

# Validar formato de commit message
COMMIT_MSG=$(cat $1)

# Patrón: tipo(alcance): descripción
if ! echo "$COMMIT_MSG" | grep -qE "^(feat|fix|docs|style|refactor|perf|test|chore|ci|sec)(\(.+\))?: .+"; then
    echo "❌ Commit message inválido"
    echo "Formato: <tipo>(<alcance>): <descripción>"
    exit 1
fi

exit 0
```

Hazlo ejecutable:
```bash
chmod +x .git/hooks/commit-msg
```

---

## 📚 Referencias

- **Conventional Commits**: https://www.conventionalcommits.org/en/v1.0.0/
- **Angular Contributing**: https://github.com/angular/angular/blob/master/CONTRIBUTING.md
- **Semantic Versioning**: https://semver.org/

---

## 🎯 Resumen Rápido

| Tipo | Uso | Ejemplo |
|------|-----|---------|
| `feat` | Nueva funcionalidad | `feat(excusa): agregar endpoint por rol` |
| `fix` | Corrección de bug | `fix(service): corregir selección aleatoria` |
| `docs` | Documentación | `docs: actualizar README` |
| `style` | Formato de código | `style: formatear imports` |
| `refactor` | Reorganización | `refactor(mapper): extraer método privado` |
| `perf` | Optimización | `perf(cache): agregar caché de memes` |
| `test` | Tests | `test: agregar 20 casos de test` |
| `chore` | Mantenimiento | `chore(pom): actualizar Spring Boot` |
| `ci` | CI/CD | `ci: crear workflow GitHub Actions` |
| `sec` | Seguridad | `sec: validar entrada de usuario` |

---

**¡Commits claros = Historial limpio = Proyecto profesional! 🚀**
