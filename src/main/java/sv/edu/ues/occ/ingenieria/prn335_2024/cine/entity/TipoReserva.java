package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tipo_reserva", schema = "public")
@NamedQueries({
    /**⬇️ Necesita ser invocada
     * orden de retorno
     * (Integer idTipoReserva, String nombre, Boolean activo, String comentarios) {*/
    @NamedQuery(name = "TipoReserva.findAll", query = "SELECT t FROM TipoReserva t"),

    /**⬇️ Necesita idTipoReserva(PK)[Integer] de la tabla (TipoReserva)
     * orden de retorno
     * (Integer idTipoReserva, String nombre, Boolean activo, String comentarios)*/
    @NamedQuery(name = "TipoReserva.findByIdTipoReserva", query = "SELECT t FROM TipoReserva t WHERE t.idTipoReserva = :idTipoReserva"),

    /**⬇️ Necesita nombre(Columna)[String] de la tabla (TipoReserva)
     * orden de retorno
     * (Integer idTipoReserva, String nombre, Boolean activo, String comentarios)*/
    @NamedQuery(name = "TipoReserva.findByNombre", query = "SELECT t FROM TipoReserva t WHERE t.nombre = :nombre"),

    /**⬇️ Necesita activo(Columna)[Boolean] de la tabla (TipoReserva)
     * orden de retorno
     * (Integer idTipoReserva, String nombre, Boolean activo, String comentarios)*/
    @NamedQuery(name = "TipoReserva.findByActivo", query = "SELECT t FROM TipoReserva t WHERE t.activo = :activo"),

    /**⬇️ Necesita comentarios(Columna)[String] de la tabla (TipoReserva)
     * orden de retorno
     * (Integer idTipoReserva, String nombre, Boolean activo, String comentarios)*/
    @NamedQuery(name = "TipoReserva.findByComentarios", query = "SELECT t FROM TipoReserva t WHERE t.comentarios = :comentarios")})

public class TipoReserva implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_reserva", nullable = false)
    private Integer idTipoReserva;

    /** *Relacion: TipoReserva/Reserva
     * TipoReserva (id)(M) <-> (fk)(1) Reserva
     */
    @OneToMany(mappedBy = "idTipoReserva", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reserva> ReservaList;

    @NotBlank(message = "Debe ingresar un nombre valido")
    @Size(min=3, max = 155, message = "El nombre debe tener entre 3 y 155 caracteres")
    @Column(name = "nombre", length = 155)
    private String nombre;

    @Column(name = "activo")
    private Boolean activo;

    @Lob
    @Column(name = "comentarios")
    private String comentarios;

    //TipoReserva
    public TipoReserva() {
    }

    public TipoReserva(Integer idTipoReserva) {
        this.idTipoReserva = idTipoReserva;
    }

    public TipoReserva(Integer idTipoReserva, String nombre, Boolean activo, String comentarios) {
        this.idTipoReserva = idTipoReserva;
        this.nombre = nombre;
        this.activo = activo;
        this.comentarios = comentarios;
    }

    //Reserva
    public List<Reserva> getReservaList() {
        return ReservaList;
    }

    public void setReservaList(List<Reserva> reservaList) {
        ReservaList = reservaList;
    }

    public Integer getIdTipoReserva() {
        return idTipoReserva;
    }

    public void setIdTipoReserva(Integer idTipoReserva) {
        this.idTipoReserva = idTipoReserva;
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