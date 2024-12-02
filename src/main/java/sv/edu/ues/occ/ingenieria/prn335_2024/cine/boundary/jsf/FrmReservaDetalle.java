package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.ReservaDetalleBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.ReservaDetalle;

import java.io.Serializable;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class FrmReservaDetalle extends AbstractFrm<ReservaDetalle> implements Serializable {

    @Inject
    ReservaDetalleBean rdbean;

    @Inject
    FacesContext fc;

    @Override
    public AbstractDataPersistence<ReservaDetalle> getDataPersist() {
        return this.rdbean;
    }

    @Override
    public FacesContext getFacesContext() {
        return this.fc;
    }

    @Override
    public String getIdObjeto(ReservaDetalle object) {
        if (object != null && object.getIdReservaDetalle()!=null) {
            return object.getIdReservaDetalle().toString();
        }
        return null;
    }

    @Override
    public ReservaDetalle getObjeto(String id) {
        if (id!=null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r->r.getIdReservaDetalle().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    /**
     * Para crear las reservas detalle donde irán nuestros asientos reservados
     */
    @Override
    public void instanciarRegistro() {
        this.registro = new ReservaDetalle();
        this.registro.setEstado("CREADO");
    }

    @Override
    public String getTituloPagina() {
        return ReservaDetalle.class.getSimpleName().replaceAll("([a-z])([A-Z])", "$1 de $2");
    }
}
