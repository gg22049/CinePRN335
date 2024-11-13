package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "sala_caracteristica", schema = "public")
public class SalaCaracteristica implements Serializable {
    @Id
    @Column(name = "id_sala_caracteristica", nullable = false)
    private Long idSalaCaracteristica;

    /** *Relacion: SalaCaracterista/TipoSala
     * henry(hp19021):
     * SalaCaracterisca (fk)(M) <-> (1)(id) TipoSala
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_sala")
    private TipoSala idTipoSala;

    /** *Relacion: SalaCaracterista/Sala
     * henry(hp19021):
     * SalaCaracterisca (fk)(M) <-> (1)(id) Sala
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sala")
    private Sala idSala;

    @Lob
    @Column(name = "valor")
    private String valor;

    //SalaCaracteristica
    public SalaCaracteristica() {}

    public SalaCaracteristica(Long idSalaCaracteristica) {
        this.idSalaCaracteristica = idSalaCaracteristica;
    }

    public SalaCaracteristica(Long idSalaCaracteristica, TipoSala idTipoSala, Sala idSala, String valor) {
        this.idSalaCaracteristica = idSalaCaracteristica;
        this.idTipoSala = idTipoSala;
        this.idSala = idSala;
        this.valor = valor;
    }

    //Sala
    public Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(Sala idSala) {
        this.idSala = idSala;
    }

    //TipoSala
    public TipoSala getIdTipoSala() {
        return idTipoSala;
    }

    public void setIdTipoSala(TipoSala idTipoSala) {
        this.idTipoSala = idTipoSala;
    }


    public Long getIdSalaCaracteristica() {
        return idSalaCaracteristica;
    }

    public void setIdSalaCaracteristica(Long idSalaCaracteristica) {
        this.idSalaCaracteristica = idSalaCaracteristica;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}