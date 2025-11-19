package com.accenture.excusas.service;

import com.accenture.excusas.dto.*;
import com.accenture.excusas.model.*;
import com.accenture.excusas.repository.*;
import com.accenture.excusas.service.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ExcusaService {
    private final ContextoRepository contextoRepository;
    private final CausaRepository causaRepository;
    private final ConsecuenciaRepository consecuenciaRepository;
    private final RecomendacionRepository recomendacionRepository;
    private final MemeRepository memeRepository;
    private final LeyRepository leyRepository;

    private final Random random = new Random();

    public ExcusaResponseDTO generarExcusaSimple() {
        return construirExcusa(false, false, null);
    }

    public ExcusaResponseDTO generarExcusaConMeme() {
        return construirExcusa(true, false, null);
    }

    public ExcusaResponseDTO generarExcusaConLey() {
        return construirExcusa(false, true, null);
    }

    public ExcusaResponseDTO generarExcusaUltraShark() {
        return construirExcusa(true, true, null);
    }

    public ExcusaResponseDTO generarExcusaPorRol(String rol) {
        return construirExcusa(true, true, rol);
    }

    private ExcusaResponseDTO construirExcusa(boolean conMeme, boolean conLey, String rol) {
        String contexto = obtenerFragmento(contextoRepository.count(), rol, "contexto");
        String causa = obtenerFragmento(causaRepository.count(), rol, "causa");
        String consecuencia = obtenerFragmento(consecuenciaRepository.count(), rol, "consecuencia");
        String recomendacion = obtenerFragmento(recomendacionRepository.count(), rol, "recomendacion");

        String meme = null;
        String ley = null;
        String fuente = null;

        if (conMeme) {
            meme = obtenerMemeAleatorio();
        }

        if (conLey) {
            Ley leyObj = obtenerLeyAleatoria(rol);
            ley = leyObj.getTexto();
            fuente = leyObj.getFuente();
        }

        String modo = determinaModo(conMeme, conLey);

        return ExcusaResponseDTO.builder()
                .contexto(contexto)
                .causa(causa)
                .consecuencia(consecuencia)
                .recomendacion(recomendacion)
                .meme(meme)
                .ley(ley)
                .fuente(fuente)
                .timestamp(System.currentTimeMillis())
                .modo(modo)
                .build();
    }

    private String obtenerFragmento(long total, String rol, String tipo) {
        if (total == 0) return "Fragmento no disponible";

        long randomId = random.nextLong(1, total + 1);

        return switch (tipo) {
            case "contexto" -> contextoRepository.findById(randomId)
                    .map(Contexto::getTexto)
                    .orElse("Contexto no encontrado");
            case "causa" -> causaRepository.findById(randomId)
                    .map(Causa::getTexto)
                    .orElse("Causa no encontrada");
            case "consecuencia" -> consecuenciaRepository.findById(randomId)
                    .map(Consecuencia::getTexto)
                    .orElse("Consecuencia no encontrada");
            case "recomendacion" -> recomendacionRepository.findById(randomId)
                    .map(Recomendacion::getTexto)
                    .orElse("Recomendación no encontrada");
            default -> "Tipo desconocido";
        };
    }

    private String obtenerMemeAleatorio() {
        long total = memeRepository.count();
        if (total == 0) return null;

        long randomId = random.nextLong(1, total + 1);
        return memeRepository.findById(randomId)
                .map(Meme::getTexto)
                .orElse(null);
    }

    private Ley obtenerLeyAleatoria(String rol) {
        long total = leyRepository.count();
        if (total == 0) return new Ley();

        long randomId = random.nextLong(1, total + 1);
        return leyRepository.findById(randomId)
                .orElse(new Ley());
    }

    private String determinaModo(boolean conMeme, boolean conLey) {
        if (conMeme && conLey) return "ULTRA_SHARK";
        if (conMeme) return "CON_MEME";
        if (conLey) return "CON_LEY";
        return "SIMPLE";
    }

    // CRUD para fragmentos
    public ContextoResponseDTO crearContexto(ContextoRequestDTO dto) {
        Contexto contexto = ContextoMapper.toEntity(dto);
        Contexto saved = contextoRepository.save(contexto);
        return ContextoMapper.toResponse(saved);
    }

    public CausaResponseDTO crearCausa(CausaRequestDTO dto) {
        Causa causa = CausaMapper.toEntity(dto);
        Causa saved = causaRepository.save(causa);
        return CausaMapper.toResponse(saved);
    }

    public ConsecuenciaResponseDTO crearConsecuencia(ConsecuenciaRequestDTO dto) {
        Consecuencia consecuencia = ConsecuenciaMapper.toEntity(dto);
        Consecuencia saved = consecuenciaRepository.save(consecuencia);
        return ConsecuenciaMapper.toResponse(saved);
    }

    public RecomendacionResponseDTO crearRecomendacion(RecomendacionRequestDTO dto) {
        Recomendacion recomendacion = RecomendacionMapper.toEntity(dto);
        Recomendacion saved = recomendacionRepository.save(recomendacion);
        return RecomendacionMapper.toResponse(saved);
    }

    public MemeResponseDTO crearMeme(MemeRequestDTO dto) {
        Meme meme = MemeMapper.toEntity(dto);
        Meme saved = memeRepository.save(meme);
        return MemeMapper.toResponse(saved);
    }

    public LeyResponseDTO crearLey(LeyRequestDTO dto) {
        Ley ley = LeyMapper.toEntity(dto);
        Ley saved = leyRepository.save(ley);
        return LeyMapper.toResponse(saved);
    }
}
