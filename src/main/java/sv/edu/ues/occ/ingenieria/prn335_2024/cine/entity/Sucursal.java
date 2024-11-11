package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "sucursal", schema = "public")
public class Sucursal implements Serializable {
    @Id
    @Column(name = "id_sucursal", nullable = false)
    private Integer idSala;

    @Size(max = 155)
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

    public Sucursal() {
    }

    public Sucursal(Integer idSala, String nombre, Double longitud, Double latitud, String comentarios, Boolean activo) {
        this.idSala = idSala;
        this.nombre = nombre;
        this.longitud = longitud;
        this.latitud = latitud;
        this.comentarios = comentarios;
        this.activo = activo;
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


    //    public List<Sala> getSalaList() {
//        return salaList;
//    }
//
//    public void setSalaList(List<Sala> salaList) {
//        this.salaList = salaList;
//    }


}