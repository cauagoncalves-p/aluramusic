package br.com.aluramusic.aluramusic.model;
import jakarta.persistence.*;
@Entity
@Table(name = "music")

public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String titulo;

    @ManyToOne
    private Artista artista;

    public Musica(){}

    public Musica(String nomeMusica) {
        this.titulo = nomeMusica;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return
                "Música='" + titulo + '\'' +
                        ", artista=" + artista.getNome();
    }
}
