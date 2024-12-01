package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "sucursal", schema = "public")
@NamedQueries({
    /**⬇️ Necesita ser invocada
     * orden de retorno
     * (Integer idSucursal, String nombre, Double longitud, Double latitud, String comentarios, Boolean activo)*/
    @NamedQuery(name = "Sucursal.findAll", query = "SELECT s FROM Sucursal s"),

    /**⬇️ Necesita idSucursal(PK)[Long] de la tabla (Sucursal)
     * orden de retorno
     * (Integer idSucursal, String nombre, Double longitud, Double latitud, String comentarios, Boolean activo)*/
    @NamedQuery(name = "Sucursal.findByIdSucursal", query = "SELECT s FROM Sucursal s WHERE s.idSucursal = :idSucursal"),

    /**⬇️ Necesita nombre(columna)[String] de la tabla (Sucursal)
     * orden de retorno
     * (Integer idSucursal, String nombre, Double longitud, Double latitud, String comentarios, Boolean activo)*/
    @NamedQuery(name = "Sucursal.findByNombre", query = "SELECT s FROM Sucursal s WHERE s.nombre = :nombre"),

    /**⬇️ Necesita logitud(Columna)[Double] de la tabla (Sucursal)
     * orden de retorno
     * (Integer idSucursal, String nombre, Double longitud, Double latitud, String comentarios, Boolean activo)*/
    @NamedQuery(name = "Sucursal.findByLongitud", query = "SELECT s FROM Sucursal s WHERE s.longitud = :longitud"),

    /**⬇️ Necesita latitud(Columna)[Double] de la tabla (Sucursal)
     * orden de retorno
     * (Integer idSucursal, String nombre, Double longitud, Double latitud, String comentarios, Boolean activo)*/
    @NamedQuery(name = "Sucursal.findByLatitud", query = "SELECT s FROM Sucursal s WHERE s.latitud = :latitud"),

    /**⬇️ Necesita comentarios(Columna)[String] de la tabla (Sucursal)
     * orden de retorno
     * (Integer idSucursal, String nombre, Double longitud, Double latitud, String comentarios, Boolean activo)*/
    @NamedQuery(name = "Sucursal.findByComentarios", query = "SELECT s FROM Sucursal s WHERE s.comentarios = :comentarios"),

    /**⬇️ Necesita activo(Columna)[Boolean] de la tabla (Sucursal)
     * orden de retorno
     * (Integer idSucursal, String nombre, Double longitud, Double latitud, String comentarios, Boolean activo)*/
    @NamedQuery(name = "Sucursal.findByActivo", query = "SELECT s FROM Sucursal s WHERE s.activo = :activo")})

public class Sucursal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal", nullable = false)
    private Integer idSucursal;

    @NotBlank(message="El nombre no puede estar vacio")
    @Size(min=3, max = 155, message = "EL nombre debe contener entre 3 y 155 caracteres")
    @Column(name = "nombre", length = 155)
    private String nombre;

    @Column(name = "longitud")
    private Double longitud;

    @Column(name = "latitud")
    private Double latitud;

    @Lob
    @Column(name = "comentarios")
    private String comentarios;

    @Column(name = "activo")
    private Boolean activo;

    /**
     * Relacion: Sucursal/Sala
     * Sucursal (1)(id) <-> (fk)(M)Sala
     */
    @OneToMany(mappedBy = "idSucursal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Sala> SalaList;

    //Sucursal
    public Sucursal() {}

    public Sucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Sucursal(Integer idSucursal, String nombre, Double longitud, Double latitud, String comentarios, Boolean activo) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.longitud = longitud;
        this.latitud = latitud;
        this.comentarios = comentarios;
        this.activo = activo;
    }

    //Sala
    public List<Sala> getSalaList() {
        return SalaList;
    }

    public void setSalaList(List<Sala> listSala) {
        this.SalaList = listSala;
    }

    //Sucursal

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

}