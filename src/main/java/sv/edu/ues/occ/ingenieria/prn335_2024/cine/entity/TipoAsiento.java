package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tipo_asiento", schema = "public")
@NamedQueries({
        /**⬇️ Necesita ser invocada
         * orden de retorno
         * (Integer idTipoAsiento, String nombre, Boolean activo, String comentarios, String expresionRegular)*/
        @NamedQuery(name = "TipoAsiento.findAll", query = "SELECT t FROM TipoAsiento t"),

        /**⬇️ Necesita idTipoAsiento(PK)[Integer] de la tabla (TipoAsiento)
         * orden de retorno
         * (Integer idTipoAsiento, String nombre, Boolean activo, String comentarios, String expresionRegular)*/
        @NamedQuery(name = "TipoAsiento.findByIdTipoAsiento", query = "SELECT t FROM TipoAsiento t WHERE t.idTipoAsiento = :idTipoAsiento"),

        /**⬇️ Necesita nombre(Columna)[String] de la tabla (TipoAsiento)
         * orden de retorno
         * (Integer idTipoAsiento, String nombre, Boolean activo, String comentarios, String expresionRegular)*/
        @NamedQuery(name = "TipoAsiento.findByNombre", query = "SELECT t FROM TipoAsiento t WHERE t.nombre = :nombre"),

        /**⬇️ Necesita activo(Columna)[Boolean] de la tabla (TipoAsiento)
         * orden de retorno
         * (Integer idTipoAsiento, String nombre, Boolean activo, String comentarios, String expresionRegular)*/
        @NamedQuery(name = "TipoAsiento.findByActivo", query = "SELECT t FROM TipoAsiento t WHERE t.activo = :activo"),

        /**⬇️ Necesita comentarios(Columna)[String] de la tabla (TipoAsiento)
         * orden de retorno
         * (Integer idTipoAsiento, String nombre, Boolean activo, String comentarios, String expresionRegular)*/
        @NamedQuery(name = "TipoAsiento.findByComentarios", query = "SELECT t FROM TipoAsiento t WHERE t.comentarios = :comentarios"),

        /**⬇️ Necesita expresionRegular(Columna)[String] de la tabla (TipoAsiento)
         * orden de retorno
         * (Integer idTipoAsiento, String nombre, Boolean activo, String comentarios, String expresionRegular)*/
        @NamedQuery(name = "TipoAsiento.findByExpresionRegular", query = "SELECT t FROM TipoAsiento t WHERE t.expresionRegular = :expresionRegular")
})
public class TipoAsiento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_asiento", nullable = false)
    private Integer idTipoAsiento;

    /** *Relacion: TipoAsiento/AsientoCaracteristica
     * TipoAsiento (id)(M) <-> (fk)(1) AsientoCaracteristica
     */
    @OneToMany(mappedBy = "idTipoAsiento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AsientoCaracteristica> AsientoCaracteristicaList;

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
    @NotBlank(message = "Debe ingresar una expresion valido")
    @Column(name = "expresion_regular")
    private String expresionRegular;

    //TipoAsiento
    public TipoAsiento() {
    }

    public TipoAsiento(Integer idTipoAsiento) {
        this.idTipoAsiento = idTipoAsiento;
    }

    public TipoAsiento(Integer idTipoAsiento, String nombre, Boolean activo, String comentarios, String expresionRegular) {
        this.idTipoAsiento = idTipoAsiento;
        this.nombre = nombre;
        this.activo = activo;
        this.comentarios = comentarios;
        this.expresionRegular = expresionRegular;
    }

    //AsientoCaracteristica
    public List<AsientoCaracteristica> getAsientoCaracteristicaList() {
        return AsientoCaracteristicaList;
    }

    public void setAsientoCaracteristicaList(List<AsientoCaracteristica> asientoCaracteristicaList) {
        AsientoCaracteristicaList = asientoCaracteristicaList;
    }

    public Integer getIdTipoAsiento() {
        return idTipoAsiento;
    }

    public void setIdTipoAsiento(Integer idTipoAsiento) {
        this.idTipoAsiento = idTipoAsiento;
    }

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