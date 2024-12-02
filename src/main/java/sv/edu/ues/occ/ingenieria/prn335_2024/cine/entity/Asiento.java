package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import javax.xml.namespace.QName;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "asiento", schema = "public")
@NamedQueries({
        @NamedQuery(name="Asiento.cantidadPaginador", query ="select count(a) from Asiento a where a.idSala.idSala = :idSala "),
        @NamedQuery(name="Asiento.ListBySelected", query = "select a from Asiento a where a.idSala.idSala = :idSala order by a.idAsiento asc"),
    /**⬇️ Necesita ser invocada
     * orden de retorno
     * (Long idAsiento, Sala idSala, String nombre, Boolean activo)*/
        @NamedQuery(name = "Asiento.findAll", query = "SELECT a FROM Asiento a"),

    /**⬇️ Necesita idAsiento(PK)[Long] de la tabla (Asiento)
     * orden de retorno
     * (Long idAsiento, Sala idSala, String nombre, Boolean activo)*/
        @NamedQuery(name = "Asiento.findByIdAsiento", query = "SELECT a FROM Asiento a WHERE a.idAsiento = :idAsiento"),

    /**⬇️ Necesita nombre(columna)[String] de la tabla (Asiento)
     * orden de retorno
     * (Long idAsiento, Sala idSala, String nombre, Boolean activo)*/
        @NamedQuery(name = "Asiento.findByNombre", query = "SELECT a FROM Asiento a WHERE a.nombre = :nombre"),

    /**⬇️ Necesita activo(columna)[Boolean] de la tabla (Asiento)
     * orden de retorno
     * (Long idAsiento, Sala idSala, String nombre, Boolean activo)*/
        @NamedQuery(name = "Asiento.findByActivo", query = "SELECT a FROM Asiento a WHERE a.activo = :activo")})

public class Asiento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asiento", nullable = false)
    private Long idAsiento;

    /** *Relacion: Asiento/Sala
     * Asiento (fk)(M) <-> (1)(id) Sala
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sala")
    private Sala idSala;

    /** *Relacion: Asiento/AsientoCaracteristica
     * Asiento (id)(M) <-> (fk)(1) AsientoCaracteristica
     */
    @OneToMany(mappedBy = "idAsiento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AsientoCaracteristica> AsientoCaracteristicaList;

    @NotBlank(message = "Debe ingresar un nombre valido")
    @Size(min=3, max = 155, message = "El nombre debe tener entre 3 y 155 caracteres")
    @Column(name = "nombre", length = 155)
    private String nombre;

    @Column(name = "activo")
    private Boolean activo;

    /** *Relacion: Asiento/AsientoCaracteristica
     * Asiento (id)(M) <-> (fk)(1) ReservaCarateristica
     */
    @OneToMany(mappedBy = "idAsiento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReservaDetalle> ReservaDetalleList;

    public List<ReservaDetalle> getReservaDetalleList() {
        return ReservaDetalleList;
    }

    public void setReservaDetalleList(List<ReservaDetalle> reservaDetalleList) {
        ReservaDetalleList = reservaDetalleList;
    }

    public Asiento() {
    }

    public Asiento(Long idAsiento) {
        this.idAsiento = idAsiento;
    }

    public Asiento(Long idAsiento, Sala idSala, String nombre, Boolean activo) {
        this.idAsiento = idAsiento;
        this.idSala = idSala;
        this.nombre = nombre;
        this.activo = activo;
    }

    //Sala
    public Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(Sala idSala) {
        this.idSala = idSala;
    }

    //AsientoCaracteristica
    public List<AsientoCaracteristica> getAsientoCaracteristicaList() {
        return AsientoCaracteristicaList;
    }

    public void setAsientoCaracteristicaList(List<AsientoCaracteristica> asientoCaracteristicaList) {
        AsientoCaracteristicaList = asientoCaracteristicaList;
    }

    public Long getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(Long idAsiento) {
        this.idAsiento = idAsiento;
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

}