package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.SucursalBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Sucursal;

import java.io.Serializable;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class FrmSucursal extends AbstractFrm<Sucursal> implements Serializable {

    @Inject
    SucursalBean bean;

    @Inject
    FacesContext fc;

    @Override
    public AbstractDataPersistence<Sucursal> getDataPersist() {
        return this.bean;
    }

    @Override
    public FacesContext getFacesContext() {
        return this.fc;
    }

    @Override
    public String getIdObjeto(Sucursal object) {
        if (object != null && object.getIdSucursal()!=null) {
            return object.getIdSucursal().toString();
        }
        return null;
    }

    @Override
    public Sucursal getObjeto(String id) {
        if (id!=null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r->r.getIdSucursal().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    @Override
    public void instanciarRegistro() {
        this.registro = new Sucursal();
        this.registro.setActivo(true);
    }

    @Override
    public String getTituloPagina(){
        return Sucursal.class.getSimpleName().replaceAll("([a-z])([A-Z])", "$1 de $2");
    }

}