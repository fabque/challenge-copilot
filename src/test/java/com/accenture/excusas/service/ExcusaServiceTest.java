package com.accenture.excusas.service;

import com.accenture.excusas.dto.*;
import com.accenture.excusas.model.*;
import com.accenture.excusas.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ExcusaService Tests")
class ExcusaServiceTest {

    @Mock
    private ContextoRepository contextoRepository;

    @Mock
    private CausaRepository causaRepository;

    @Mock
    private ConsecuenciaRepository consecuenciaRepository;

    @Mock
    private RecomendacionRepository recomendacionRepository;

    @Mock
    private MemeRepository memeRepository;

    @Mock
    private LeyRepository leyRepository;

    @InjectMocks
    private ExcusaService excusaService;

    @BeforeEach
    void setUp() {
        // Configurar mocks comunes
        when(contextoRepository.count()).thenReturn(1L);
        when(causaRepository.count()).thenReturn(1L);
        when(consecuenciaRepository.count()).thenReturn(1L);
        when(recomendacionRepository.count()).thenReturn(1L);
        when(memeRepository.count()).thenReturn(1L);
        when(leyRepository.count()).thenReturn(1L);

        when(contextoRepository.findById(anyLong()))
                .thenReturn(Optional.of(Contexto.builder().id(1L).texto("Contexto test").build()));
        when(causaRepository.findById(anyLong()))
                .thenReturn(Optional.of(Causa.builder().id(1L).texto("Causa test").build()));
        when(consecuenciaRepository.findById(anyLong()))
                .thenReturn(Optional.of(Consecuencia.builder().id(1L).texto("Consecuencia test").build()));
        when(recomendacionRepository.findById(anyLong()))
                .thenReturn(Optional.of(Recomendacion.builder().id(1L).texto("Recomendación test").build()));
        when(memeRepository.findById(anyLong()))
                .thenReturn(Optional.of(Meme.builder().id(1L).texto("Meme test").fuente("dev-memes").build()));
        when(leyRepository.findById(anyLong()))
                .thenReturn(Optional.of(Ley.builder().id(1L).texto("Ley test").fuente("Murphy").build()));
    }

    @Test
    @DisplayName("Debe generar excusa simple correctamente")
    void testGenerarExcusaSimple() {
        // Act
        ExcusaResponseDTO response = excusaService.generarExcusaSimple();

        // Assert
        assertNotNull(response);
        assertEquals("SIMPLE", response.getModo());
        assertNotNull(response.getContexto());
        assertNotNull(response.getCausa());
        assertNotNull(response.getConsecuencia());
        assertNotNull(response.getRecomendacion());
        assertNull(response.getMeme());
        assertNull(response.getLey());
        assertNotNull(response.getTimestamp());
    }

    @Test
    @DisplayName("Debe generar excusa con meme correctamente")
    void testGenerarExcusaConMeme() {
        // Act
        ExcusaResponseDTO response = excusaService.generarExcusaConMeme();

        // Assert
        assertNotNull(response);
        assertEquals("CON_MEME", response.getModo());
        assertNotNull(response.getContexto());
        assertNotNull(response.getMeme());
        assertNull(response.getLey());
    }

    @Test
    @DisplayName("Debe generar excusa con ley correctamente")
    void testGenerarExcusaConLey() {
        // Act
        ExcusaResponseDTO response = excusaService.generarExcusaConLey();

        // Assert
        assertNotNull(response);
        assertEquals("CON_LEY", response.getModo());
        assertNotNull(response.getContexto());
        assertNotNull(response.getLey());
        assertNotNull(response.getFuente());
        assertNull(response.getMeme());
    }

    @Test
    @DisplayName("Debe generar excusa ULTRA_SHARK correctamente")
    void testGenerarExcusaUltraShark() {
        // Act
        ExcusaResponseDTO response = excusaService.generarExcusaUltraShark();

        // Assert
        assertNotNull(response);
        assertEquals("ULTRA_SHARK", response.getModo());
        assertNotNull(response.getContexto());
        assertNotNull(response.getMeme());
        assertNotNull(response.getLey());
        assertNotNull(response.getFuente());
    }

    @Test
    @DisplayName("Debe generar excusa por rol correctamente")
    void testGenerarExcusaPorRol() {
        // Act
        ExcusaResponseDTO response = excusaService.generarExcusaPorRol("dev");

        // Assert
        assertNotNull(response);
        assertEquals("ULTRA_SHARK", response.getModo());
        assertNotNull(response.getContexto());
    }

    @Test
    @DisplayName("Debe crear contexto correctamente")
    void testCrearContexto() {
        // Arrange
        ContextoRequestDTO dto = ContextoRequestDTO.builder()
                .texto("Nuevo contexto")
                .role("dev")
                .tags("test")
                .build();

        Contexto entity = Contexto.builder()
                .id(1L)
                .texto("Nuevo contexto")
                .role("dev")
                .tags("test")
                .build();

        when(contextoRepository.save(any(Contexto.class))).thenReturn(entity);

        // Act
        ContextoResponseDTO response = excusaService.crearContexto(dto);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Nuevo contexto", response.getTexto());
        verify(contextoRepository, times(1)).save(any(Contexto.class));
    }

    @Test
    @DisplayName("Debe crear causa correctamente")
    void testCrearCausa() {
        // Arrange
        CausaRequestDTO dto = CausaRequestDTO.builder()
                .texto("Nueva causa")
                .role("qa")
                .tags("test")
                .build();

        Causa entity = Causa.builder()
                .id(1L)
                .texto("Nueva causa")
                .role("qa")
                .tags("test")
                .build();

        when(causaRepository.save(any(Causa.class))).thenReturn(entity);

        // Act
        CausaResponseDTO response = excusaService.crearCausa(dto);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Nueva causa", response.getTexto());
        verify(causaRepository, times(1)).save(any(Causa.class));
    }

    @Test
    @DisplayName("Debe crear consecuencia correctamente")
    void testCrearConsecuencia() {
        // Arrange
        ConsecuenciaRequestDTO dto = ConsecuenciaRequestDTO.builder()
                .texto("Nueva consecuencia")
                .build();

        Consecuencia entity = Consecuencia.builder()
                .id(1L)
                .texto("Nueva consecuencia")
                .build();

        when(consecuenciaRepository.save(any(Consecuencia.class))).thenReturn(entity);

        // Act
        ConsecuenciaResponseDTO response = excusaService.crearConsecuencia(dto);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        verify(consecuenciaRepository, times(1)).save(any(Consecuencia.class));
    }

    @Test
    @DisplayName("Debe crear recomendación correctamente")
    void testCrearRecomendacion() {
        // Arrange
        RecomendacionRequestDTO dto = RecomendacionRequestDTO.builder()
                .texto("Nueva recomendación")
                .build();

        Recomendacion entity = Recomendacion.builder()
                .id(1L)
                .texto("Nueva recomendación")
                .build();

        when(recomendacionRepository.save(any(Recomendacion.class))).thenReturn(entity);

        // Act
        RecomendacionResponseDTO response = excusaService.crearRecomendacion(dto);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        verify(recomendacionRepository, times(1)).save(any(Recomendacion.class));
    }

    @Test
    @DisplayName("Debe crear meme correctamente")
    void testCrearMeme() {
        // Arrange
        MemeRequestDTO dto = MemeRequestDTO.builder()
                .texto("Nuevo meme")
                .fuente("dev-memes")
                .build();

        Meme entity = Meme.builder()
                .id(1L)
                .texto("Nuevo meme")
                .fuente("dev-memes")
                .build();

        when(memeRepository.save(any(Meme.class))).thenReturn(entity);

        // Act
        MemeResponseDTO response = excusaService.crearMeme(dto);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Nuevo meme", response.getTexto());
        verify(memeRepository, times(1)).save(any(Meme.class));
    }

    @Test
    @DisplayName("Debe crear ley correctamente")
    void testCrearLey() {
        // Arrange
        LeyRequestDTO dto = LeyRequestDTO.builder()
                .texto("Nueva ley")
                .fuente("Dilbert")
                .role("devops")
                .build();

        Ley entity = Ley.builder()
                .id(1L)
                .texto("Nueva ley")
                .fuente("Dilbert")
                .role("devops")
                .build();

        when(leyRepository.save(any(Ley.class))).thenReturn(entity);

        // Act
        LeyResponseDTO response = excusaService.crearLey(dto);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Nueva ley", response.getTexto());
        verify(leyRepository, times(1)).save(any(Ley.class));
    }

    @Test
    @DisplayName("Debe manejar excusas cuando no hay datos disponibles")
    void testGenerarExcusaSinDatos() {
        // Arrange
        when(contextoRepository.count()).thenReturn(0L);

        // Act
        ExcusaResponseDTO response = excusaService.generarExcusaSimple();

        // Assert
        assertNotNull(response);
        assertEquals("Fragmento no disponible", response.getContexto());
    }

    @Test
    @DisplayName("Debe manejar memes cuando no hay datos disponibles")
    void testGenerarMemeSinDatos() {
        // Arrange
        when(memeRepository.count()).thenReturn(0L);

        // Act
        ExcusaResponseDTO response = excusaService.generarExcusaConMeme();

        // Assert
        assertNotNull(response);
        assertNull(response.getMeme());
    }

    @Test
    @DisplayName("Debe manejar leyes cuando no hay datos disponibles")
    void testGenerarLeySinDatos() {
        // Arrange
        when(leyRepository.count()).thenReturn(0L);

        // Act
        ExcusaResponseDTO response = excusaService.generarExcusaConLey();

        // Assert
        assertNotNull(response);
        assertNotNull(response.getLey()); // Retorna Ley vacía
    }
}
