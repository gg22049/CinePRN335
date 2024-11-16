package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.ReservaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoReservaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Reserva;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoReserva;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class FrmReserva extends AbstractFrm<Reserva> implements Serializable {

    @Inject
    ReservaBean bean;

    @Inject
    FacesContext fc;

    @Override
    public AbstractDataPersistence<Reserva> getDataPersist() {
        return this.bean;
    }

    @Override
    public FacesContext getFacesContext() {
        return this.fc;
    }

    @Override
    public String getIdObjeto(Reserva object) {
        if (object != null && object.getIdReserva()!=null) {
            return object.getIdReserva().toString();
        }
        return null;
    }

    @Override
    public Reserva getObjeto(String id) {
        if (id!=null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r->r.getIdReserva().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    /**
     * Para crear el objeto reserva
     */
    @Override
    public void instanciarRegistro() {
        this.registro = new Reserva();
        this.registro.setEstado("CREADO");
    }

    @Override
    public String getTituloPagina(){
        return Reserva.class.getSimpleName().replaceAll("([a-z])([A-Z])", "$1 de $2");
    }

    /**
     * Para traer lista de tipo de reserva
     */
    @Inject
    TipoReservaBean trbean;

    private List<TipoReserva> tiposReserva;

    @PostConstruct
    public void init() {
        // Obtener los tipos de reserva únicos
        tiposReserva = trbean.obtenerTodos();
    }

    public List<TipoReserva> getTiposReserva() {
        return tiposReserva;
    }

}