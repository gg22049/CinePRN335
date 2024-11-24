package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tipo_sala", schema = "public")
@NamedQueries({
    /**⬇️ Necesita ser invocada
     * orden de retorno
     * (Integer idTipoSala, String nombre, Boolean activo, String comentarios, String expresionRegular)*/
    @NamedQuery(name = "TipoSala.findAll", query = "SELECT t FROM TipoSala t"),

    /**⬇️ Necesita idTipoSala(PK)[Integer] de la tabla (TipoSala)
     * orden de retorno
     * (Integer idTipoSala, String nombre, Boolean activo, String comentarios, String expresionRegular)*/
    @NamedQuery(name = "TipoSala.findByIdTipoSala", query = "SELECT t FROM TipoSala t WHERE t.idTipoSala = :idTipoSala"),

    /**⬇️ Necesita nombre(columna)[String] de la tabla (TipoSala)
     * orden de retorno
     * (Integer idTipoSala, String nombre, Boolean activo, String comentarios, String expresionRegular)*/
    @NamedQuery(name = "TipoSala.findByNombre", query = "SELECT t FROM TipoSala t WHERE t.nombre = :nombre"),

    /**⬇️ Necesita activo(columna)[Boolean] de la tabla (TipoSala)
     * orden de retorno
     * (Integer idTipoSala, String nombre, Boolean activo, String comentarios, String expresionRegular)*/
    @NamedQuery(name = "TipoSala.findByActivo", query = "SELECT t FROM TipoSala t WHERE t.activo = :activo"),

    /**⬇️ Necesita comentarios(columna)[String] de la tabla (TipoSala)
     * orden de retorno
     * (Integer idTipoSala, String nombre, Boolean activo, String comentarios, String expresionRegular)*/
    @NamedQuery(name = "TipoSala.findByComentarios", query = "SELECT t FROM TipoSala t WHERE t.comentarios = :comentarios"),

    /**⬇️ Necesita expresionRegular(columna)[String] de la tabla (TipoSala)
     * orden de retorno
     * (Integer idTipoSala, String nombre, Boolean activo, String comentarios, String expresionRegular)*/
    @NamedQuery(name = "TipoSala.findByExpresionRegular", query = "SELECT t FROM TipoSala t WHERE t.expresionRegular = :expresionRegular")})

public class TipoSala implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_sala", nullable = false)
    private Integer idTipoSala;

    /** *Relacion: TipoSala/SalaCaracteristica
     * TipoSala (id)(M) <-> (fk)(1) SalaCaracteristica
     */
    @OneToMany(mappedBy = "idTipoSala", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SalaCaracteristica> salaCaracteristicaList;

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

    //TipoSala
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

    //SalaCaracteristica
    public List<SalaCaracteristica> getSalaCaracteristicaList() {
        return salaCaracteristicaList;
    }

    public void setSalaCaracteristicaList(List<SalaCaracteristica> salaCaracteristicaList) {
        this.salaCaracteristicaList = salaCaracteristicaList;
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

}