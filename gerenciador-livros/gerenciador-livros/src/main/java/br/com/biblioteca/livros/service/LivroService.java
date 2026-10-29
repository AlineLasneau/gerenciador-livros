package br.com.biblioteca.livros.service;

import br.com.biblioteca.livros.model.Livro;
import br.com.biblioteca.livros.repository.LivroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LivroService {
    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public Livro cadastrar(Livro livro) {
        livro.setId(null);
        return repository.save(livro);
    }

    public List<Livro> listar() {
        return repository.findAll();
    }

    public Livro consultar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado."));
    }

    public Livro atualizar(Long id, Livro dados) {
        Livro livro = consultar(id);
        livro.setTitulo(dados.getTitulo());
        livro.setAutor(dados.getAutor());
        livro.setGenero(dados.getGenero());
        livro.setAnoPublicacao(dados.getAnoPublicacao());
        livro.setLido(dados.getLido());
        return repository.save(livro);
    }

    public void excluir(Long id) {
        Livro livro = consultar(id);
        repository.delete(livro);
    }
}
