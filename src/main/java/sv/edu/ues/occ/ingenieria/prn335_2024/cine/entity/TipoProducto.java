package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tipo_producto", schema = "public")
@NamedQueries({
    /**⬇️ Necesita ser invocada
     * orden de retorno
     * (Integer idTipoProducto, String nombre, Boolean activo, String comentarios) {*/
    @NamedQuery(name = "TipoProducto.findAll", query = "SELECT t FROM TipoProducto t"),

    /**⬇️ Necesita idTipoProducto(PK)[Integer] de la tabla (TipoProducto)
     * orden de retorno
     * (Integer idTipoProducto, String nombre, Boolean activo, String comentarios)*/
    @NamedQuery(name = "TipoProducto.findByIdTipoProducto", query = "SELECT t FROM TipoProducto t WHERE t.idTipoProducto = :idTipoProducto"),

    /**⬇️ Necesita nombre(Columna)[String] de la tabla (TipoProductos)
     * orden de retorno
     * (Integer idTipoProducto, String nombre, Boolean activo, String comentarios)*/
    @NamedQuery(name = "TipoProducto.findByNombre", query = "SELECT t FROM TipoProducto t WHERE t.nombre = :nombre"),

    /**⬇️ Necesita activo(Columna)[Boolean] de la tabla (TipoPelicula)
     * orden de retorno
     * (Integer idTipoProducto, String nombre, Boolean activo, String comentarios)*/
    @NamedQuery(name = "TipoProducto.findByActivo", query = "SELECT t FROM TipoProducto t WHERE t.activo = :activo"),

    /**⬇️ Necesita comentarios(Columna)[String] de la tabla (TipoPelicula)
     * orden de retorno
     * (Integer idTipoProducto, String nombre, Boolean activo, String comentarios)*/
    @NamedQuery(name = "TipoProducto.findByComentarios", query = "SELECT t FROM TipoProducto t WHERE t.comentarios = :comentarios")})

public class TipoProducto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_producto", nullable = false)
    private Integer idTipoProducto;

    @NotBlank(message = "Debe ingresar un nombre valido")
    @Size(min=3, max = 155, message = "El nombre debe tener entre 3 y 155 caracteres")
    @Column(name = "nombre", length = 155)
    private String nombre;

    @Column(name = "activo")
    private Boolean activo;

    @Lob
    @Column(name = "comentarios")
    private String comentarios;

    public TipoProducto() {
    }

    public TipoProducto(Integer idTipoProducto, String nombre, Boolean activo, String comentarios) {
        this.idTipoProducto = idTipoProducto;
        this.nombre = nombre;
        this.activo = activo;
        this.comentarios = comentarios;
    }

    public Integer getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(Integer idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
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

}