package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "programacion", schema = "public")
public class Programacion implements Serializable {
    @Id
    @Column(name = "id_programacion", nullable = false)
    private Long idProgramacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sala")
    private Sala idSala;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pelicula")
    private Pelicula idPelicula;

    @OneToMany(mappedBy = "reserva", fetch = FetchType.LAZY)
    private List<Reserva> reservas;

    @Column(name = "desde")
    private OffsetDateTime desde;

    @Column(name = "hasta")
    private OffsetDateTime hasta;

    @Lob
    @Column(name = "comentarios")
    private String comentarios;

    public Programacion() {
    }

    public Programacion(Long idProgramacion, Sala idSala, Pelicula idPelicula, OffsetDateTime desde, OffsetDateTime hasta, String comentarios) {
        this.idProgramacion = idProgramacion;
        this.idSala = idSala;
        this.idPelicula = idPelicula;
        this.desde = desde;
        this.hasta = hasta;
        this.comentarios = comentarios;
    }

    public Long getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(Long idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    public Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(Sala idSala) {
        this.idSala = idSala;
    }

    public Pelicula getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Pelicula idPelicula) {
        this.idPelicula = idPelicula;
    }

    public OffsetDateTime getDesde() {
        return desde;
    }

    public void setDesde(OffsetDateTime desde) {
        this.desde = desde;
    }

    public OffsetDateTime getHasta() {
        return hasta;
    }

    public void setHasta(OffsetDateTime hasta) {
        this.hasta = hasta;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }
}