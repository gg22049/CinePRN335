package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tipo_pelicula", schema = "public")
@NamedQueries({
    /** hp19021: ⬇️ Necesita ser invocada
     * orden de retorno
     * (Integer idTipoPelicula, String nombre, Boolean activo, String comentarios, String expresionRegular) {*/
    @NamedQuery(name = "TipoPelicula.findAll", query = "SELECT t FROM TipoPelicula t"),

    /** hp19021: ⬇️ Necesita idTipoPelicula(PK)[Integer] de la tabla (TipoPelicula)
     * orden de retorno
     * (Integer idTipoPelicula, String nombre, Boolean activo, String comentarios, String expresionRegular)*/
    @NamedQuery(name = "TipoPelicula.findByIdTipoPelicula", query = "SELECT t FROM TipoPelicula t WHERE t.idTipoPelicula = :idTipoPelicula"),

    /** hp19021: ⬇️ Necesita nombre(Columna)[String] de la tabla (TipoPelicula)
     * orden de retorno
     * (Integer idTipoPelicula, String nombre, Boolean activo, String comentarios, String expresionRegular)*/
    @NamedQuery(name = "TipoPelicula.findByNombre", query = "SELECT t FROM TipoPelicula t WHERE t.nombre = :nombre"),

    /** hp19021: ⬇️ Necesita activo(Columna)[Boolean] de la tabla (TipoPelicula)
     * orden de retorno
     * (Integer idTipoPelicula, String nombre, Boolean activo, String comentarios, String expresionRegular)*/
    @NamedQuery(name = "TipoPelicula.findByActivo", query = "SELECT t FROM TipoPelicula t WHERE t.activo = :activo"),

    /** hp19021: ⬇️ Necesita comentarios(Columna)[String] de la tabla (TipoPelicula)
     * orden de retorno
     * (Integer idTipoPelicula, String nombre, Boolean activo, String comentarios, String expresionRegular)*/
    @NamedQuery(name = "TipoPelicula.findByComentarios", query = "SELECT t FROM TipoPelicula t WHERE t.comentarios = :comentarios"),

    /** hp19021: ⬇️ Necesita expresionRegular(Columna)[String] de la tabla (TipoPelicula)
     * orden de retorno
     * (Integer idTipoPelicula, String nombre, Boolean activo, String comentarios, String expresionRegular)*/
    @NamedQuery(name = "TipoPelicula.findByExpresionRegular", query = "SELECT t FROM TipoPelicula t WHERE t.expresionRegular = :expresionRegular")})

public class TipoPelicula implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_pelicula", nullable = false)
    private Integer idTipoPelicula;

    /** *Relacion: TipoPelicula/PeliculaCaracteristica
     * henry(hp19021)
     * TipoPelicula (id)(M) <-> (fk)(1) PeliculaCaracteristica
     */
    @OneToMany(mappedBy = "idTipoPelicula", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PeliculaCaracteristica> PeliculaCaracteristicasList;

    @NotBlank(message = "Debe ingresar un nombre valido")
    @Size(min=3, max = 155, message = "El nombre debe tener entre 3 y 155 caracteres")
    @Column(name = "nombre", length = 155)
    private String nombre;

    @Column(name = "activo")
    private Boolean activo;

    @Lob
    @Column(name = "comentarios")
    private String comentarios;

    @Lob
    @Column(name = "expresion_regular")
    private String expresionRegular;

    //TipoPelicula
    public TipoPelicula() {
    }

    public TipoPelicula(Integer idTipoPelicula) {
        this.idTipoPelicula = idTipoPelicula;
    }

    public TipoPelicula(Integer idTipoPelicula, String nombre, Boolean activo, String comentarios, String expresionRegular) {
        this.idTipoPelicula = idTipoPelicula;
        this.nombre = nombre;
        this.activo = activo;
        this.comentarios = comentarios;
        this.expresionRegular = expresionRegular;
    }

    //PeliculaCaracteristica

    public List<PeliculaCaracteristica> getPeliculaCaracteristicasList() {
        return PeliculaCaracteristicasList;
    }

    public void setPeliculaCaracteristicasList(List<PeliculaCaracteristica> peliculaCaracteristicasList) {
        PeliculaCaracteristicasList = peliculaCaracteristicasList;
    }

    public Integer getIdTipoPelicula() {
        return idTipoPelicula;
    }

    public void setIdTipoPelicula(Integer idTipoPelicula) { this.idTipoPelicula = idTipoPelicula;}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getExpresionRegular() {
        return expresionRegular;
    }

    public void setExpresionRegular(String expresionRegular) {
        this.expresionRegular = expresionRegular;
    }

}