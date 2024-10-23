package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoReservaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoReserva;

import java.io.Serializable;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class FrmTipoReserva extends AbstractFrm<TipoReserva> implements Serializable {

    @Inject
    TipoReservaBean bean;

    @Inject
    FacesContext fc;

    @Override
    public AbstractDataPersistence<TipoReserva> getDataPersist() {
        return this.bean;
    }

    @Override
    public FacesContext getFacesContext() {
        return this.fc;
    }

    @Override
    public String getIdObjeto(TipoReserva object) {
        if (object != null && object.getIdTipoReserva()!=null) {
            return object.getIdTipoReserva().toString();
        }
        return null;
    }

    @Override
    public TipoReserva getObjeto(String id) {
        if (id!=null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r->r.getIdTipoReserva().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    @Override
    public void instanciarRegistro() {
        this.registro = new TipoReserva();
    }

    @Override
    public String getTituloPagina(){
        return TipoReserva.class.getSimpleName().replaceAll("([a-z])([A-Z])", "$1 de $2");
    }
}
