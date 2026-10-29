package br.com.biblioteca.livros.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório.")
    private String titulo;

    @NotBlank(message = "O autor é obrigatório.")
    private String autor;

    @NotBlank(message = "O gênero é obrigatório.")
    private String genero;

    @NotNull(message = "O ano de publicação é obrigatório.")
    @Min(value = 0, message = "O ano deve ser maior ou igual a zero.")
    @Max(value = 2100, message = "Informe um ano válido.")
    private Integer anoPublicacao;

    @NotNull(message = "Informe se o livro foi lido.")
    private Boolean lido;

    public Livro() {}

    public Livro(String titulo, String autor, String genero, Integer anoPublicacao, Boolean lido) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.anoPublicacao = anoPublicacao;
        this.lido = lido;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public Integer getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(Integer anoPublicacao) { this.anoPublicacao = anoPublicacao; }
    public Boolean getLido() { return lido; }
    public void setLido(Boolean lido) { this.lido = lido; }
}
