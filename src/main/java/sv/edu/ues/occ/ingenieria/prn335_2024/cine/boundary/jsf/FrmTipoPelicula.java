package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoPeliculaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoPelicula;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoProducto;

import java.io.Serializable;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class FrmTipoPelicula extends AbstractFrm<TipoPelicula> implements Serializable {

    @Inject
    TipoPeliculaBean bean;

    @Inject
    FacesContext fc;

    @Override
    public AbstractDataPersistence<TipoPelicula> getDataPersist() {
        return this.bean;
    }

    @Override
    public FacesContext getFacesContext() {
        return this.fc;
    }

    @Override
    public String getIdObjeto(TipoPelicula object) {
        if (object != null && object.getIdTipoPelicula()!=null) {
            return object.getIdTipoPelicula().toString();
        }
        return null;
    }

    @Override
    public TipoPelicula getObjeto(String id) {
        if (id!=null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r->r.getIdTipoPelicula().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    @Override
    public void instanciarRegistro() {
        this.registro = new TipoPelicula();
    }

    @Override
    public String getTituloPagina(){
        return TipoProducto.class.getSimpleName().replaceAll("([a-z])([A-Z])", "$1 de $2");
    }
}