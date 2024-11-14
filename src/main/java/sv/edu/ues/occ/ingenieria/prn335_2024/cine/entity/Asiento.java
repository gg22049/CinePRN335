package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "asiento", schema = "public")
public class Asiento implements Serializable {
    @Id
    @Column(name = "id_asiento", nullable = false)
    private Long idAsiento;

    /** *Relacion: Asiento/Sala
     * henry(hp19021):
     * Asiento (fk)(M) <-> (1)(id) Sala
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sala")
    private Sala idSala;

    /** *Relacion: Asiento/AsientoCaracteristica
     * henry(hp19021)
     * Asiento (id)(M) <-> (fk)(1) AsientoCaracteristica
     */
    @OneToMany(mappedBy = "idAsiento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AsientoCaracteristica> AsientoCaracteristicaList;

    @Size(max = 155)
    @Column(name = "nombre", length = 155)
    private String nombre;

    @Column(name = "activo")
    private Boolean activo;

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