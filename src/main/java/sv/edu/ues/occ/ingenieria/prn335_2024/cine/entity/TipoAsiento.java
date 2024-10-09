package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tipo_asiento", schema = "public")
public class TipoAsiento implements Serializable {
    @Id
    @Column(name = "id_tipo_asiento", nullable = false)
    private Integer idTipoAsiento;

//    @OneToMany(mappedBy = "asiento_caracteristica", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<AsientoCaracteristica> asientoCaracteristicaList;

    @Size(max = 155)
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

    public TipoAsiento() {
    }

    public TipoAsiento(Integer idTipoAsiento, String nombre, Boolean activo, String comentarios, String expresionRegular) {
        this.idTipoAsiento = idTipoAsiento;
        this.nombre = nombre;
        this.activo = activo;
        this.comentarios = comentarios;
        this.expresionRegular = expresionRegular;
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

//    public List<AsientoCaracteristica> getAsientoCaracteristicaList() {
//        return asientoCaracteristicaList;
//    }
//
//    public void setAsientoCaracteristicaList(List<AsientoCaracteristica> asientoCaracteristicaList) {
//        this.asientoCaracteristicaList = asientoCaracteristicaList;
//    }
}