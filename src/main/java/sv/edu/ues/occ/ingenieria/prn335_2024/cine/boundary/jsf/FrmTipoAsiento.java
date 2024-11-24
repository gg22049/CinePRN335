package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoAsientoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoAsiento;

import java.io.Serializable;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class FrmTipoAsiento extends AbstractFrm<TipoAsiento> implements Serializable {

    @Inject
    TipoAsientoBean bean;

    @Inject
    FacesContext fc;

    @Override
    public AbstractDataPersistence<TipoAsiento> getDataPersist() {
        return this.bean;
    }

    @Override
    public FacesContext getFacesContext() {
        return this.fc;
    }

    @Override
    public String getIdObjeto(TipoAsiento object) {
        if (object != null && object.getIdTipoAsiento()!=null) {
            return object.getIdTipoAsiento().toString();
        }
        return null;
    }

    @Override
    public TipoAsiento getObjeto(String id) {
        if (id!=null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r->r.getIdTipoAsiento().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    @Override
    public void instanciarRegistro() {
        this.registro = new TipoAsiento();
    }

    @Override
    public String getTituloPagina(){
        return TipoAsiento.class.getSimpleName().replaceAll("([a-z])([A-Z])", "$1 de $2");
    }
}