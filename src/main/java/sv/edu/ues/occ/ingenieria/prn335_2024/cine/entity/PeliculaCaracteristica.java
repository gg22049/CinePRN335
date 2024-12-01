package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "pelicula_caracteristica", schema = "public")
@NamedQueries({
        @NamedQuery(name="PeliculaCaracteristica.ListBySelected", query="SELECT pc FROM PeliculaCaracteristica pc where pc.idPelicula.idPelicula = :idPelicula order by pc.idTipoPelicula.nombre asc "),
        @NamedQuery(name="PeliculaCaracteristica.cantidadPaginador", query ="SELECT count(pc) FROM PeliculaCaracteristica pc where pc.idPelicula.idPelicula = :idPelicula")
}
)
public class PeliculaCaracteristica implements Serializable {
    @Id
    @Column(name = "id_pelicula_caracteristica", nullable = false)
    private Long idPeliculaCaracteristica;

    /** *Relacion: PeliculaCaracteristica/TipoPelicula
     * henry(hp19021)
     * PeliculaCaracteristica (M)(id) <-> (fk)(1) TipoPelicula
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_pelicula")
    private TipoPelicula idTipoPelicula;

    /** *Relacion: PeliculaCaracteristica/Pelicula
     * henry(hp19021)
     * PeliculaCaracteristica (id)(M) <-> (fk)(1) Pelicula
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pelicula")
    private Pelicula idPelicula;

    @Lob
    @Column(name = "valor")
    private String valor;

    //PeliculaCaracteristica
    public PeliculaCaracteristica() {
    }

    public PeliculaCaracteristica(Long idPeliculaCaracteristica) {
        this.idPeliculaCaracteristica = idPeliculaCaracteristica;
    }

    public PeliculaCaracteristica(Long idPeliculaCaracteristica, TipoPelicula idTipoPelicula, Pelicula idPelicula, String valor) {
        this.idPeliculaCaracteristica = idPeliculaCaracteristica;
        this.idTipoPelicula = idTipoPelicula;
        this.idPelicula = idPelicula;
        this.valor = valor;
    }

    //TipoPelicula
    public TipoPelicula getIdTipoPelicula() {
        return idTipoPelicula;
    }

    public void setIdTipoPelicula(TipoPelicula idTipoPelicula) {
        this.idTipoPelicula = idTipoPelicula;
    }

    //Pelicula
    public Pelicula getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Pelicula idPelicula) {
        this.idPelicula = idPelicula;
    }


    public Long getIdPeliculaCaracteristica() {
        return idPeliculaCaracteristica;
    }

    public void setIdPeliculaCaracteristica(Long idPeliculaCaracteristica) {
        this.idPeliculaCaracteristica = idPeliculaCaracteristica;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}