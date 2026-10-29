package br.com.biblioteca.livros;

import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.biblioteca.livros.model.Livro;
import br.com.biblioteca.livros.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LivroControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private LivroRepository repository;

    @BeforeEach
    void limparBanco() {
        repository.deleteAll();
    }

    private Livro livroExemplo() {
        return new Livro("Dom Casmurro", "Machado de Assis", "Romance", 1899, true);
    }

    @Test
    void deveCadastrarLivro() throws Exception {
        mockMvc.perform(post("/api/livros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(livroExemplo())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.titulo").value("Dom Casmurro"));
    }

    @Test
    void deveListarLivros() throws Exception {
        repository.save(livroExemplo());
        mockMvc.perform(get("/api/livros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].autor").value("Machado de Assis"));
    }

    @Test
    void deveConsultarLivroPorId() throws Exception {
        Livro salvo = repository.save(livroExemplo());
        mockMvc.perform(get("/api/livros/" + salvo.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.genero").value("Romance"));
    }

    @Test
    void deveAtualizarLivro() throws Exception {
        Livro salvo = repository.save(livroExemplo());
        Livro atualizado = new Livro("Dom Casmurro - edição nova", "Machado de Assis", "Clássico", 1899, true);
        mockMvc.perform(put("/api/livros/" + salvo.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(atualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Dom Casmurro - edição nova"));
    }

    @Test
    void deveExcluirLivro() throws Exception {
        Livro salvo = repository.save(livroExemplo());
        mockMvc.perform(delete("/api/livros/" + salvo.getId()))
                .andExpect(status().isNoContent());
        mockMvc.perform(get("/api/livros/" + salvo.getId()))
                .andExpect(status().isNotFound());
    }
}
