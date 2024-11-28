package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
@Table(name = "sala_caracteristica", schema = "public")
@NamedQueries({
    @NamedQuery(name="SalaCaracteristica.tiposByIdSala", query="select sc from SalaCaracteristica sc where sc.idSala.idSala = :idSala order by sc.idSala.idSala asc "),
    /**⬇️ Necesita ser invocada
     * orden de retorno
     * (Long idSalaCaracteristica, TipoSala idTipoSala, Sala idSala, String valor)*/
    @NamedQuery(name = "SalaCaracteristica.findAll", query = "SELECT s FROM SalaCaracteristica s"),

    /**⬇️ Necesita idSalaCaracteristica(PK)[Long] de la tabla (SalaCaracteristica)
     * orden de retorno
     * (Long idSalaCaracteristica, TipoSala idTipoSala, Sala idSala, String valor)*/
    @NamedQuery(name = "SalaCaracteristica.findByIdSalaCaracteristica", query = "SELECT s FROM SalaCaracteristica s WHERE s.idSalaCaracteristica = :idSalaCaracteristica"),

    /**⬇️ Necesita valor(columna)[String] de la tabla (SalaCaracteristica)
     * orden de retorno
     * (Long idSalaCaracteristica, TipoSala idTipoSala, Sala idSala, String valor)*/
    @NamedQuery(name = "SalaCaracteristica.findByValor", query = "SELECT s FROM SalaCaracteristica s WHERE s.valor = :valor"),

    /**⬇️ Necesita idSala(PK)[Long] de la tabla(Sala)
     * orden de retorno:
     * (Long idSalaCaracteristica, TipoSala idTipoSala, Sala idSala, String valor),
     * (Integer idSala, Sucursal idSucursal, String nombre, Boolean activo, String observaciones),
     * (Integer idTipoSala, String nombre, Boolean activo, String comentarios, String expresionRegular)*/
    @NamedQuery(name = "SalaCaracteristica.ListBySelected", query="SELECT sc FROM SalaCaracteristica  sc WHERE sc.idSala.idSala = :idSala ORDER BY sc.idTipoSala.nombre ASC"),

    /**⬇️ Necesita idSala(PK)[Integer] de la tabla(Sala)
     * orden de retorno:
     * ('numero de cuentas que cumplen con la relacion')*/
    @NamedQuery(name = "SalaCaracteristica.cantidadPaginador", query="SELECT count(sc) FROM SalaCaracteristica sc WHERE sc.idSala.idSala = :idSala")
    })

public class SalaCaracteristica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sala_caracteristica", nullable = false)
    private Long idSalaCaracteristica;

    /** *Relacion: SalaCaracterista/TipoSala
     * SalaCaracterisca (fk)(M) <-> (1)(id) TipoSala
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_sala")
    private TipoSala idTipoSala;

    /** *Relacion: SalaCaracterista/Sala
     * SalaCaracterisca (fk)(M) <-> (1)(id) Sala
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sala")
    private Sala idSala;

    @Lob
    @NotBlank(message = "Debe ingresar un valor valido")
    @Size(min=3, max = 155, message = "El valor debe tener entre 3 y 155 caracteres")
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