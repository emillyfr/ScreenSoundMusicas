package br.com.alura.ScreenSoundMusicas.principal;

import br.com.alura.ScreenSoundMusicas.model.Artista;
import br.com.alura.ScreenSoundMusicas.model.Musica;
import br.com.alura.ScreenSoundMusicas.model.TipoArtista;
import br.com.alura.ScreenSoundMusicas.repository.ArtistaRepository;
import br.com.alura.ScreenSoundMusicas.service.ConsultaChatGPT;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private final ArtistaRepository repositorio;
    private Scanner leitura = new Scanner(System.in);

    public Principal(ArtistaRepository repositorio) {
        this.repositorio = repositorio;
    }


    public void exibeMenu(){
        var opcao =-1;
        while(opcao !=9){
            var menu = """
                    1- Cadastrar artistas               
                    2- Cadastrar músicas                   
                    3- Listar músicas                   
                    4- Buscar músicas por artistas                    
                    5- Pesquisar dados sobre um artista                    
                    9- Sair                                                           
                    """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();
            switch (opcao){
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
                    buscaMusicasPorArtistas();
                    break;
                case 5:
                    pesquisarArtista();
                    break;
                case 9:
                    System.out.println("Saindo ...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        }
        exibeMenu();



    }

    private void cadastrarArtistas(){
        var cadastrarNovo ="S";
        while (cadastrarNovo.equalsIgnoreCase("s")) {
            System.out.println("Informe o nome desse artista: ");
            var nome = leitura.nextLine();
            System.out.println("Informe o tipo desse artista: (solo, dupla ou banda)");
            var tipo = leitura.nextLine();
            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
            Artista artista = new Artista(nome, tipoArtista);
            repositorio.save(artista);
            System.out.println("Cadastrar novo artista? (S/N)");
            cadastrarNovo = leitura.nextLine();
        }
    }

    private void cadastrarMusicas(){
        System.out.println("Cadastrar música de que artista? ");
        var nome = leitura.nextLine();
        Optional<Artista> artista = repositorio.findByNomeContainingIgnoreCase(nome);
        if (artista.isPresent()){
            System.out.println("Informe titulo da musica: ");
            var nomemusica = leitura.nextLine();
            Musica musica = new Musica(nomemusica);
            musica.setArtista(artista.get());
            artista.get().getMusicas().add(musica);
            repositorio.save(artista.get());

        }else{
            System.out.println("Artista não encontrado");
        }
    }

    private void listarMusicas(){
        List<Artista> artistas = repositorio.findAll();
        artistas.forEach(a-> a.getMusicas().forEach(System.out::println));
    }

    private void  buscaMusicasPorArtistas(){
        System.out.println("Buscar musicas de que artista? ");
        var nome = leitura.nextLine();
        List<Musica> musicas = repositorio.buscaMusicasPorArtistas(nome);
        musicas.forEach(System.out::println);

    }

    private void  pesquisarArtista(){
        System.out.println("Pesquisar dados sobre qual artista? ");
        var nome = leitura.nextLine();
        var resposta = ConsultaChatGPT.obterInformacao(nome);
        System.out.println(resposta.trim());
    }

}
