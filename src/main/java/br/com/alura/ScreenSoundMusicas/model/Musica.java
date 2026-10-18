package br.com.alura.ScreenSoundMusicas.model;


import jakarta.persistence.*;

@Entity
@Table(name = "musicas")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToOne
    private Artista artista;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString(){
        return "Músicas: '"+titulo+'\''+", artista: "+artista;
    }

}
