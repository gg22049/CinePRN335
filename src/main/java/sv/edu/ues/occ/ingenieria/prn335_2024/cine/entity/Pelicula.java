package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "pelicula", schema = "public")
public class Pelicula implements Serializable {
    @Id
    @Column(name = "id_pelicula", nullable = false)
    private Long idPelicula;

    @OneToMany(mappedBy = "programacion", fetch = FetchType.LAZY)
    private List<Programacion> programaciones;

    @OneToMany(mappedBy = "pelicula_caracteristica", fetch = FetchType.LAZY)
    private List<PeliculaCaracteristica> peliculaCaracteristicas;

    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;

    @Lob
    @Column(name = "sinopsis")
    private String sinopsis;

    public Pelicula() {
    }

    public Pelicula(Long idPelicula, String nombre, String sinopsis) {
        this.idPelicula = idPelicula;
        this.nombre = nombre;
        this.sinopsis = sinopsis;
    }

    public Long getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Long idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public List<Programacion> getProgramaciones() {
        return programaciones;
    }

    public List<PeliculaCaracteristica> getPeliculaCaracteristicas() {
        return peliculaCaracteristicas;
    }
}