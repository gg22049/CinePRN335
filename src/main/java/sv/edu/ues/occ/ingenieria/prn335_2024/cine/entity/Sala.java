package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "sala", schema = "public")
public class Sala implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sala", nullable = false)
    private Integer idSala;

    /** *Relacion: Sala/Sucursal
     * henry(hp19021)
     * Sala (fk)(M) <-> (1)(id) Sucursal
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sucursal")
    private Sucursal idSucursal;

    /** *Relacion: Sala/SalaCaracteristica
     * henry(hp19021)
     * Sala (id)(M) <-> (fk)(1) SalaCaracteristica
     */
    @OneToMany(mappedBy = "idSala", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SalaCaracteristica> salaCaracteristicaList;

    /** *Relacion: Sala/Asiento
     * henry(hp19021)
     * Sala (id)(M) <-> (fk)(1) Programacion
     */
    @OneToMany(mappedBy = "idSala", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Asiento> AsientoList;

    /** *Relacion: Sala/Programacion
     * henry(hp19021)
     * Sala (id)(M) <-> (fk)(1) SalaCaracteristica
     */
    @OneToMany(mappedBy = "idSala", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Programacion> ProgramacionList;

    @NotBlank(message = "Debe ingresar un nombre valido")
    @Size(min=3, max = 155, message = "El nombre debe tener entre 3 y 155 caracteres")
    @Column(name = "nombre", length = 155)
    private String nombre;

    @Column(name = "activo")
    private Boolean activo;

    @Lob
    @Column(name = "observaciones")
    private String observaciones;

    //Sala
    public Sala() {}

    public Sala(Integer idSala) {
        this.idSala = idSala;
    }

    public Sala(Integer idSala, Sucursal idSucursal, String nombre, Boolean activo, String observaciones) {
        this.idSala = idSala;
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.activo = activo;
        this.observaciones = observaciones;
    }

    //Sucursal
    public Sucursal getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursal idSucursal) {
        this.idSucursal = idSucursal;
    }

    //SalaCaracteristica
    public List<SalaCaracteristica> getSalaCaracteristicaList() {
        return salaCaracteristicaList;
    }

    public void setSalaCaracteristicaList(List<SalaCaracteristica> salaCaracteristicaList) {
        this.salaCaracteristicaList = salaCaracteristicaList;
    }
    //Asiento
    public List<Asiento> getAsientoList() {
        return AsientoList;
    }

    public void setAsientoList(List<Asiento> asientoList) {
        AsientoList = asientoList;
    }

    //Programacion
    public List<Programacion> getProgramacionList() {
        return ProgramacionList;
    }

    public void setProgramacionList(List<Programacion> programacionList) {
        ProgramacionList = programacionList;
    }

    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer idSala) {
        this.idSala = idSala;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }


}