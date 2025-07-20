package br.com.aluramusic.aluramusic.repositorio;

import br.com.aluramusic.aluramusic.model.Artista;
import br.com.aluramusic.aluramusic.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista,Long> {

    Optional<Artista> findByNomeContainingIgnoreCase(String nomeArtista);

    @Query("SELECT m FROM Artista a JOIN a.musicas m WHERE a.nome ILIKE %:nome%")
    List<Musica> buscaMusicasPorArtista(String nome);
}
