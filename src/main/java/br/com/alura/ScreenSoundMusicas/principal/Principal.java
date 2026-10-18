package br.com.alura.ScreenSoundMusicas.principal;

import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);


    public void exibeMenu(){
        var opcao =-1;
        while(opcao !=0){
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




    }

    private void cadastrarArtistas(){

    }

    private void cadastrarMusicas(){

    }

    private void listarMusicas(){

    }

    private void  buscaMusicasPorArtistas(){

    }

    private void  pesquisarArtista(){

    }

}
