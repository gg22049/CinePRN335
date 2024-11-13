package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
@Table(name = "tipo_sala", schema = "public")
public class TipoSala implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_sala", nullable = false)
    private Integer idTipoSala;

//    @OneToMany(mappedBy="sala_caracteristica", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<SalaCaracteristica> salaCaracteristicaList;

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

    public TipoSala() {
    }

    public TipoSala (int idEsperado){
      this.idTipoSala = idEsperado;
    }

    public TipoSala(Integer idTipoSala, String nombre, Boolean activo, String comentarios, String expresionRegular) {
        this.idTipoSala = idTipoSala;
        this.nombre = nombre;
        this.activo = activo;
        this.comentarios = comentarios;
        this.expresionRegular = expresionRegular;
    }

    public Integer getIdTipoSala() {
        return idTipoSala;
    }

    public void setIdTipoSala(Integer idTipoReserva) {
        this.idTipoSala = idTipoReserva;
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

//    public List<SalaCaracteristica> getSalaCaracteristicaList() {
//        return salaCaracteristicaList;
//    }
//
//    public void setSalaCaracteristicaList(List<SalaCaracteristica> salaCaracteristicaList) {
//        this.salaCaracteristicaList = salaCaracteristicaList;
//    }
}