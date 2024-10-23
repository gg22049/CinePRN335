package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tipo_pago", schema = "public")
public class TipoPago implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_pago", nullable = false)
    private Integer idTipoPago;

//    @OneToMany(mappedBy = "pago", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Pago> pagoList;

    @NotBlank(message = "Debe ingresar un nombre valido")
    @Size(min=3, max = 155, message = "El nombre debe tener entre 3 y 155 caracteres")
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

//    public List<Pago> getPagoList() {
//        return pagoList;
//    }
//
//    public void setPagoList(List<Pago> pagoList) {
//        this.pagoList = pagoList;
//    }
}