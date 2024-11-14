package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "asiento_caracteristica", schema = "public")
@NamedQueries({
        @NamedQuery(name="AsientoCaracteristica.ListBySelected",query = "select a from AsientoCaracteristica a where a.idAsiento = :idAsiento order by a.idAsientoCaracteristica asc"),
        @NamedQuery(name="AsientoCaracteristica.cantidadPaginador",query = "select count(a) from AsientoCaracteristica a where a.idAsiento = :idAsiento")
})
public class AsientoCaracteristica implements Serializable {
    @Id
    @Column(name = "id_asiento_caracteristica", nullable = false)
    private Long idAsientoCaracteristica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_asiento")
    private Asiento idAsiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_asiento")
    private TipoAsiento idTipoAsiento;

    @Lob
    @Column(name = "valor")
    private String valor;

    public AsientoCaracteristica() {
    }

    public AsientoCaracteristica(Long idAsientoCaracteristica, Asiento idAsiento, TipoAsiento idTipoAsiento, String valor) {
        this.idAsientoCaracteristica = idAsientoCaracteristica;
        this.idAsiento = idAsiento;
        this.idTipoAsiento = idTipoAsiento;
        this.valor = valor;
    }

    public Long getIdAsientoCaracteristica() {
        return idAsientoCaracteristica;
    }

    public void setIdAsientoCaracteristica(Long idAsientoCaracteristica) {
        this.idAsientoCaracteristica = idAsientoCaracteristica;
    }

    public Asiento getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(Asiento idAsiento) {
        this.idAsiento = idAsiento;
    }

    public TipoAsiento getIdTipoAsiento() {
        return idTipoAsiento;
    }

    public void setIdTipoAsiento(TipoAsiento idTipoAsiento) {
        this.idTipoAsiento = idTipoAsiento;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}