package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
@Table(name = "sala_caracteristica", schema = "public")
@NamedQueries(
        {
                @NamedQuery(name="SalaCaracteristica.ListBySelected", query="select sc from SalaCaracteristica sc where sc.idSala.idSala = :idSala order by sc.idSalaCaracteristica asc"),
                @NamedQuery(name="SalaCaracteristica.cantidadPaginador", query="select count(sc) from SalaCaracteristica sc where sc.idSala.idSala = :idSala "),
                @NamedQuery(name="SalaCaracteristica.tiposByIdSala", query="select sc from SalaCaracteristica sc where sc.idSala.idSala = :idSala order by sc.idSala.idSala asc ")
        }
)
public class SalaCaracteristica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sala_caracteristica", nullable = false)
    private Long idSalaCaracteristica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_sala")
    private TipoSala idTipoSala;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sala")
    private Sala idSala;

    @Lob
    @NotBlank(message = "Debe ingresar un valor valido")
    @Size(min=3, max = 155, message = "El valor debe tener entre 3 y 155 caracteres")
    @Column(name = "valor")
    private String valor;

    public SalaCaracteristica() {
    }

    public SalaCaracteristica(Long idSalaCaracteristica, TipoSala idTipoSala, Sala idSala, String valor) {
        this.idSalaCaracteristica = idSalaCaracteristica;
        this.idTipoSala = idTipoSala;
        this.idSala = idSala;
        this.valor = valor;
    }

    public Long getIdSalaCaracteristica() {
        return idSalaCaracteristica;
    }

    public void setIdSalaCaracteristica(Long idSalaCaracteristica) {
        this.idSalaCaracteristica = idSalaCaracteristica;
    }

    public TipoSala getIdTipoSala() {
        return idTipoSala;
    }

    public void setIdTipoSala(TipoSala idTipoSala) {
        this.idTipoSala = idTipoSala;
    }

    public Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(Sala idSala) {
        this.idSala = idSala;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}