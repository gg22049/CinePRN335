package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
@Table(name = "tipo_pago", schema = "public")
public class TipoPago implements Serializable {
    @Id
    @Column(name = "id_tipo_pago", nullable = false)
    private Integer idTipoPago;

    @Size(max = 155)
    @Column(name = "nombre", length = 155)
    private String nombre;

    @Column(name = "activo")
    private Boolean activo;

    public TipoPago() {
    }

    public TipoPago(Integer idTipoPago, String nombre, Boolean activo) {
        this.idTipoPago = idTipoPago;
        this.nombre = nombre;
        this.activo = activo;
    }

    public Integer getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(Integer idTipoPago) {
        this.idTipoPago = idTipoPago;
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