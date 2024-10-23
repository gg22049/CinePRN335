package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoPagoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoPago;

import java.io.Serializable;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class FrmTipoPago extends AbstractFrm<TipoPago> implements Serializable {

    @Inject
    TipoPagoBean bean;

    @Inject
    FacesContext fc;

    @Override
    public AbstractDataPersistence<TipoPago> getDataPersist() {
        return this.bean;
    }

    @Override
    public FacesContext getFacesContext() {
        return this.fc;
    }

    @Override
    public String getIdObjeto(TipoPago object) {
        if (object != null && object.getIdTipoPago()!=null) {
            return object.getIdTipoPago().toString();
        }
        return null;
    }

    @Override
    public TipoPago getObjeto(String id) {
        if (id!=null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r->r.getIdTipoPago().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    @Override
    public void instanciarRegistro() {
        this.registro = new TipoPago();
    }

    @Override
    public String getTituloPagina(){
        return TipoPago.class.getSimpleName().replaceAll("([a-z])([A-Z])", "$1 de $2");
    }
}