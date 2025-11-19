package com.accenture.excusas.config;

import com.accenture.excusas.model.*;
import com.accenture.excusas.repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final ContextoRepository contextoRepository;
    private final CausaRepository causaRepository;
    private final ConsecuenciaRepository consecuenciaRepository;
    private final RecomendacionRepository recomendacionRepository;
    private final MemeRepository memeRepository;
    private final LeyRepository leyRepository;

    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) {
        log.info("Iniciando carga de datos...");
        
        cargarMemes();
        cargarLeyes();
        cargarContextos();
        
        log.info("Datos cargados exitosamente!");
    }

    private void cargarMemes() {
        if (memeRepository.count() > 0) {
            log.info("Memes ya cargados, omitiendo...");
            return;
        }

        try {
            cargarDesdeJSON("data/dev-memes.json", meme -> {
                Meme m = Meme.builder()
                        .texto((String) meme.get("text"))
                        .fuente("dev-memes")
                        .tags(String.join(",", (List<String>) meme.get("tags")))
                        .build();
                memeRepository.save(m);
            });

            cargarDesdeJSON("data/argento-memes.json", meme -> {
                Meme m = Meme.builder()
                        .texto((String) meme.get("text"))
                        .fuente("argento-memes")
                        .tags(String.join(",", (List<String>) meme.get("tags")))
                        .build();
                memeRepository.save(m);
            });

            log.info("Memes cargados: {}", memeRepository.count());
        } catch (Exception e) {
            log.error("Error cargando memes", e);
        }
    }

    private void cargarLeyes() {
        if (leyRepository.count() > 0) {
            log.info("Leyes ya cargadas, omitiendo...");
            return;
        }

        try {
            cargarLeyesDesdeJSON("data/murphy.json", "Murphy");
            cargarLeyesDesdeJSON("data/hofstadter.json", "Hofstadter");
            cargarLeyesDesdeJSON("data/dilbert.json", "Dilbert");
            cargarLeyesDesdeJSON("data/devops_principles.json", "DevOpsPrinciple");
            cargarLeyesDesdeJSON("data/dev_axioms.json", "DevAxioms");

            log.info("Leyes cargadas: {}", leyRepository.count());
        } catch (Exception e) {
            log.error("Error cargando leyes", e);
        }
    }

    private void cargarContextos() {
        // Cargar contextos básicos para las excusas
        if (contextoRepository.count() == 0) {
            contextoRepository.save(Contexto.builder()
                    .texto("Durante el desarrollo de una nueva feature")
                    .role("dev")
                    .tags("feature,dev")
                    .build());

            contextoRepository.save(Contexto.builder()
                    .texto("En una revisión de código")
                    .role("dev")
                    .tags("review,code")
                    .build());

            contextoRepository.save(Contexto.builder()
                    .texto("Durante un deploy en producción")
                    .role("devops")
                    .tags("deploy,prod,devops")
                    .build());

            contextoRepository.save(Contexto.builder()
                    .texto("En una prueba de carga")
                    .role("qa")
                    .tags("testing,qa,performance")
                    .build());

            contextoRepository.save(Contexto.builder()
                    .texto("En una reunión de planificación")
                    .role("pm")
                    .tags("planning,pm")
                    .build());
        }

        // Cargar causas básicas
        if (causaRepository.count() == 0) {
            causaRepository.save(Causa.builder()
                    .texto("porque alguien olvidó actualizar la documentación")
                    .role("dev")
                    .tags("doc,oversight")
                    .build());

            causaRepository.save(Causa.builder()
                    .texto("porque el ambiente de testing no es igual a producción")
                    .role("devops")
                    .tags("env,config")
                    .build());

            causaRepository.save(Causa.builder()
                    .texto("porque nadie leyó los requirements completamente")
                    .role("qa")
                    .tags("requirements,communication")
                    .build());
        }

        // Cargar consecuencias básicas
        if (consecuenciaRepository.count() == 0) {
            consecuenciaRepository.save(Consecuencia.builder()
                    .texto("el código se rompió en producción de forma silenciosa")
                    .role("dev")
                    .tags("prod,bugs")
                    .build());

            consecuenciaRepository.save(Consecuencia.builder()
                    .texto("los logs no mostraban nada útil para debuggear")
                    .role("devops")
                    .tags("logs,monitoring")
                    .build());

            consecuenciaRepository.save(Consecuencia.builder()
                    .texto("la base de datos se bloqueó sin razón aparente")
                    .role("qa")
                    .tags("database,performance")
                    .build());
        }

        // Cargar recomendaciones básicas
        if (recomendacionRepository.count() == 0) {
            recomendacionRepository.save(Recomendacion.builder()
                    .texto("revisar los logs y las métricas de forma inmediata")
                    .role("dev")
                    .tags("debugging,logs")
                    .build());

            recomendacionRepository.save(Recomendacion.builder()
                    .texto("hacer un rollback y analizar lo que pasó")
                    .role("devops")
                    .tags("rollback,incident")
                    .build());

            recomendacionRepository.save(Recomendacion.builder()
                    .texto("ejecutar un test load en un ambiente controlado")
                    .role("qa")
                    .tags("testing,performance")
                    .build());
        }

        log.info("Contextos cargados: {} Causas: {} Consecuencias: {} Recomendaciones: {}",
                contextoRepository.count(), causaRepository.count(),
                consecuenciaRepository.count(), recomendacionRepository.count());
    }

    private void cargarLeyesDesdeJSON(String path, String fuente) throws Exception {
        ClassPathResource resource = new ClassPathResource(path);
        try (InputStream is = resource.getInputStream()) {
            List<Map<String, Object>> leyes = objectMapper.readValue(is, new TypeReference<>() {});
            leyes.forEach(ley -> {
                String texto = (String) ley.get("text");
                String role = (String) ley.get("role");
                List<String> tags = (List<String>) ley.get("tags");
                
                Ley l = Ley.builder()
                        .texto(texto)
                        .fuente(fuente)
                        .role(role)
                        .tags(tags != null ? String.join(",", tags) : "")
                        .build();
                leyRepository.save(l);
            });
        }
    }

    private void cargarDesdeJSON(String path, LambdaConsumer consumer) throws Exception {
        ClassPathResource resource = new ClassPathResource(path);
        try (InputStream is = resource.getInputStream()) {
            List<Map<String, Object>> items = objectMapper.readValue(is, new TypeReference<>() {});
            items.forEach(consumer::accept);
        }
    }

    @FunctionalInterface
    private interface LambdaConsumer {
        void accept(Map<String, Object> map);
    }
}
