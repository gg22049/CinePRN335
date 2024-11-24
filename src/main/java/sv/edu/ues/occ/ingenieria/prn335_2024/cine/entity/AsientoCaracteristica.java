package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "asiento_caracteristica", schema = "public")
@NamedQueries({
    @NamedQuery(name="AsientoCaracteristica.ListBySelected",query = "select a from AsientoCaracteristica a where a.idAsiento = :idAsiento order by a.idAsientoCaracteristica asc"),
    @NamedQuery(name="AsientoCaracteristica.cantidadPaginador",query = "select count(a) from AsientoCaracteristica a where a.idAsiento = :idAsiento"),
    /** hp19021: ⬇️ Necesita ser invocada
     * orden de retorno
     * (Long idAsientoCaracteristica, Asiento idAsiento, TipoAsiento idTipoAsiento, String valor)*/
    @NamedQuery(name = "AsientoCaracteristica.findAll", query = "SELECT a FROM AsientoCaracteristica a"),

    /** hp19021: ⬇️ Necesita idAsientoCaracteristica(PK)[Long] de la tabla (AsientoCaracteristica)
     * orden de retorno
     * (Long idAsientoCaracteristica, Asiento idAsiento, TipoAsiento idTipoAsiento, String valor)*/
    @NamedQuery(name = "AsientoCaracteristica.findByIdAsientoCaracteristica", query = "SELECT a FROM AsientoCaracteristica a WHERE a.idAsientoCaracteristica = :idAsientoCaracteristica"),

    /** hp19021: ⬇️ Necesita valor(columna)[String] de la tabla (AsientoCaracteristica)
     * orden de retorno
     * (Long idAsientoCaracteristica, Asiento idAsiento, TipoAsiento idTipoAsiento, String valor)*/
    @NamedQuery(name = "AsientoCaracteristica.findByValor", query = "SELECT a FROM AsientoCaracteristica a WHERE a.valor = :valor")})

public class AsientoCaracteristica implements Serializable {
    @Id
    @Column(name = "id_asiento_caracteristica", nullable = false)
    private Long idAsientoCaracteristica;

    /** *Relacion: AsientoCaracterisca/Asiento
     * henry(hp19021):
     * AsientoCaracterisca (fk)(M) <-> (1)(id) Asiento
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_asiento")
    private Asiento idAsiento;

    /** *Relacion: AsientoCaracterisca/TipoAsiento
     * henry(hp19021):
     * AsientoCaracterisca (fk)(M) <-> (1)(id) TipoAsiento
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_asiento")
    private TipoAsiento idTipoAsiento;

    @Lob
    @Column(name = "valor")
    private String valor;

    //AsientoCaracteristica
    public AsientoCaracteristica() {
    }

    public AsientoCaracteristica(Long idAsientoCaracteristica) {
        this.idAsientoCaracteristica = idAsientoCaracteristica;
    }

    public AsientoCaracteristica(Long idAsientoCaracteristica, Asiento idAsiento, TipoAsiento idTipoAsiento, String valor) {
        this.idAsientoCaracteristica = idAsientoCaracteristica;
        this.idAsiento = idAsiento;
        this.idTipoAsiento = idTipoAsiento;
        this.valor = valor;
    }

    //Asiento
    public Asiento getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(Asiento idAsiento) {
        this.idAsiento = idAsiento;
    }

    //TipoAsiento
    public TipoAsiento getIdTipoAsiento() {
        return idTipoAsiento;
    }

    public void setIdTipoAsiento(TipoAsiento idTipoAsiento) {
        this.idTipoAsiento = idTipoAsiento;
    }


    public Long getIdAsientoCaracteristica() {
        return idAsientoCaracteristica;
    }

    public void setIdAsientoCaracteristica(Long idAsientoCaracteristica) {
        this.idAsientoCaracteristica = idAsientoCaracteristica;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}