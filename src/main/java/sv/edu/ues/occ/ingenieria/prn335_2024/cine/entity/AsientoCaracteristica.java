package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
@Table(name = "asiento_caracteristica", schema = "public")
@NamedQueries({
        @NamedQuery(name="AsientoCaracteristica.ListBySelected",query = "select a from AsientoCaracteristica a where a.idAsiento = :idAsiento order by a.idAsientoCaracteristica asc"),
        @NamedQuery(name="AsientoCaracteristica.cantidadPaginador",query = "select count(a) from AsientoCaracteristica a where a.idAsiento = :idAsiento"),
        @NamedQuery(name="AsientoCaracteristica.TiposAsientosByIdAsiento", query = "select ac.idTipoAsiento from AsientoCaracteristica ac where ac.idAsiento.idAsiento = :idAsiento order by ac.idAsientoCaracteristica asc")
})
public class AsientoCaracteristica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asiento_caracteristica", nullable = false)
    private Long idAsientoCaracteristica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_asiento")
    private Asiento idAsiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_asiento")
    private TipoAsiento idTipoAsiento;

    @Lob
    @NotBlank(message = "Debe ingresar un nombre valido")
    @Size(min=3, max = 155, message = "El nombre debe tener entre 3 y 155 caracteres")
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