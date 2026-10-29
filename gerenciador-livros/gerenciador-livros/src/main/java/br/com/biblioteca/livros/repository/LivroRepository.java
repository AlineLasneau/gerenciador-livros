package br.com.biblioteca.livros.repository;

import br.com.biblioteca.livros.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
