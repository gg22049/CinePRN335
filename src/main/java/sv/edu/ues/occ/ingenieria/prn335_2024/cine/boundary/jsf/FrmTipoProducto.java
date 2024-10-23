package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoProductoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoProducto;

import java.io.Serializable;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class FrmTipoProducto extends AbstractFrm<TipoProducto> implements Serializable {

    @Inject
    TipoProductoBean bean;

    @Inject
    FacesContext fc;

    @Override
    public AbstractDataPersistence<TipoProducto> getDataPersist() {
        return this.bean;
    }

    @Override
    public FacesContext getFacesContext() {
        return this.fc;
    }

    @Override
    public String getIdObjeto(TipoProducto object) {
        if (object != null && object.getIdTipoProducto()!=null) {
            return object.getIdTipoProducto().toString();
        }
        return null;
    }

    @Override
    public TipoProducto getObjeto(String id) {
        if (id!=null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r->r.getIdTipoProducto().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    @Override
    public void instanciarRegistro() {
        this.registro = new TipoProducto();
    }

    @Override
    public String getTituloPagina(){
        return TipoProducto.class.getSimpleName().replaceAll("([a-z])([A-Z])", "$1 de $2");
    }
}