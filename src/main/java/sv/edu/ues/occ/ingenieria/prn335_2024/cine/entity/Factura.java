package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "factura", schema = "public")
@NamedQueries({
        @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
        @NamedQuery(name = "Factura.findByIdFactura", query = "SELECT f FROM Factura f WHERE f.idFactura = :idFactura"),
        @NamedQuery(name = "Factura.findByCliente", query = "SELECT f FROM Factura f WHERE f.cliente = :cliente"),
        @NamedQuery(name = "Factura.findByDui", query = "SELECT f FROM Factura f WHERE f.dui = :dui"),
        @NamedQuery(name = "Factura.findByFecha", query = "SELECT f FROM Factura f WHERE f.fecha = :fecha"),
        @NamedQuery(name = "Factura.findByComentarios", query = "SELECT f FROM Factura f WHERE f.comentarios = :comentarios")})
public class Factura implements Serializable {
    @Id
    @Column(name = "id_factura", nullable = false)
    private Long idFactura;

    @OneToMany(mappedBy = "idFactura", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FacturaDetalleProducto> facturaDetalleProductoList;

    @OneToMany(mappedBy = "idFactura", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FacturaDetalleSala> facturaDetalleSalaList;

    @OneToMany(mappedBy = "idFactura", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Pago> pagoList;

    @Size(max = 255)
    @Column(name = "cliente")
    private String cliente;

    @Size(max = 155)
    @Column(name = "dui", length = 155)
    private String dui;

    @Column(name = "fecha")
    private OffsetDateTime fecha;

    @Lob
    @Column(name = "comentarios")
    private String comentarios;

    //Factura
    public Factura() {
    }

    public Factura(Long idFactura) {
        this.idFactura = idFactura;
    }

    public Factura(Long idFactura, String cliente, String dui, OffsetDateTime fecha, String comentarios) {
        this.idFactura = idFactura;
        this.cliente = cliente;
        this.dui = dui;
        this.fecha = fecha;
        this.comentarios = comentarios;
    }

    public List<FacturaDetalleProducto> getFacturaDetalleProductoList() {
        return facturaDetalleProductoList;
    }

    public void setFacturaDetalleProductoList(List<FacturaDetalleProducto> facturaDetalleProductoList) {
        this.facturaDetalleProductoList = facturaDetalleProductoList;
    }

    public List<FacturaDetalleSala> getFacturaDetalleSalaList() {
        return facturaDetalleSalaList;
    }

    public void setFacturaDetalleSalaList(List<FacturaDetalleSala> facturaDetalleSalaList) {
        this.facturaDetalleSalaList = facturaDetalleSalaList;
    }

    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
    }

    public Long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public OffsetDateTime getFecha() {
        return fecha;
    }

    public void setFecha(OffsetDateTime fecha) {
        this.fecha = fecha;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

}