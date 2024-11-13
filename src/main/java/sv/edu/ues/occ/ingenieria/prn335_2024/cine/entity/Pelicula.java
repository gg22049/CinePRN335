package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "pelicula", schema = "public")
public class Pelicula implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula", nullable = false)
    private Long idPelicula;

//    @OneToMany(mappedBy = "programacion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Programacion> programacionList;
//
//    @OneToMany(mappedBy = "pelicula_caracteristica", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<PeliculaCaracteristica> peliculaCaracteristicaList;

    @NotBlank(message = "Debe ingresar un nombre valido")
    @Size(min=3, max = 155, message = "El nombre debe tener entre 3 y 155 caracteres")
    @Column(name = "nombre")
    private String nombre;

    @Lob
    @Column(name = "sinopsis")
    private String sinopsis;

    public Pelicula() {
    }

    public Pelicula(Long idPelicula) {
        this.idPelicula = idPelicula;
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

//    public List<Programacion> getProgramacionList() {
//        return programacionList;
//    }
//
//    public void setProgramacionList(List<Programacion> programacionList) {
//        this.programacionList = programacionList;
//    }
//
//    public List<PeliculaCaracteristica> getPeliculaCaracteristicaList() {
//        return peliculaCaracteristicaList;
//    }
//
//    public void setPeliculaCaracteristicaList(List<PeliculaCaracteristica> peliculaCaracteristicaList) {
//        this.peliculaCaracteristicaList = peliculaCaracteristicaList;
//    }
}