package br.com.aluramusic.aluramusic.principal;

import br.com.aluramusic.aluramusic.model.Artista;
import br.com.aluramusic.aluramusic.model.Musica;
import br.com.aluramusic.aluramusic.model.TipoArtista;
import br.com.aluramusic.aluramusic.repositorio.ArtistaRepository;
import br.com.aluramusic.aluramusic.service.BuscaChatGPT;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class principal {
    private final ArtistaRepository repositorio;
    private Scanner leitura = new Scanner(System.in);

    public principal(ArtistaRepository repositorio) {
        this.repositorio = repositorio;
    }


    public void exibeMenu() {
        var opcao = -1;

        while (opcao!= 9) {
            var menu = """
                    *** Screen Sound Músicas ***                    
                                        
                    1- Cadastrar artistas
                    2- Cadastrar músicas
                    3- Listar músicas
                    4- Buscar músicas por artistas
                                    
                    9 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 9:
                    System.out.println("Encerrando a aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarArtistas() {
        System.out.println("Informe o nome desse artista");
        var nomeArtista = leitura.nextLine();
        System.out.println("Informe o tipo desse arista: (Solo/Dupla/Banda)");
        var tipo = leitura.nextLine();
        TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
        Artista artista = new Artista(tipoArtista, nomeArtista);
        repositorio.save(artista);
        System.out.println("Arista cadastro com sucesso!");
    }

    private void cadastrarMusicas() {
        System.out.println("Cadastrar musica de qual artista?");
        var nomeArtista = leitura.nextLine();

        Optional<Artista> artista = repositorio.findByNomeContainingIgnoreCase(nomeArtista);
        if (artista.isPresent()){
            System.out.println("Informe o titulo da musica");
            var musicaArtista = leitura.nextLine();
            Musica musica = new Musica(musicaArtista);
            musica.setArtista(artista.get());
            artista.get().getMusicas().add(musica);
            repositorio.save(artista.get());
        }
        else{
            System.out.println("Artista não encontrado!");
        }
    }

    private void listarMusicas() {
        List<Artista> artistas = repositorio.findAll();
        artistas.forEach(a -> a.getMusicas().forEach(System.out::println));
    }

    private void buscarMusicasPorArtista() {
        System.out.println("Buscar musica de qual artista? ");
        var nome = leitura.nextLine();
        List<Musica> musicas = repositorio.buscaMusicasPorArtista(nome);
        musicas.forEach(System.out::println);
    }
}
