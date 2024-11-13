package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.TabChangeEvent;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.PeliculaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Pelicula;

import java.io.Serializable;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class FrmPelicula extends AbstractFrm<Pelicula> implements Serializable {

    @Inject
    PeliculaBean bean;

    @Inject
    FacesContext fc;

    @Inject
    FrmPeliculaCaracteristica frmPeliculaCaracteristica;

    @Override
    public AbstractDataPersistence<Pelicula> getDataPersist() {
        return this.bean;
    }

    @Override
    public FacesContext getFacesContext() {
        return this.fc;
    }

    @Override
    public String getIdObjeto(Pelicula object) {
        if (object != null && object.getIdPelicula()!=null) {
            return object.getIdPelicula().toString();
        }
        return null;
    }

    @Override
    public Pelicula getObjeto(String id) {
        if (id!=null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r->r.getIdPelicula().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;    }

    @Override
    public void instanciarRegistro() {
        this.registro = new Pelicula();
    }

    @Override
    public String getTituloPagina() {
        return Pelicula.class.getSimpleName().replaceAll("([a-z])([A-Z])", "$1 de $2");
    }

    public FrmPeliculaCaracteristica getFrmPeliculaCaracteristica() {
        return frmPeliculaCaracteristica;
    }

    public void cambiarTab(TabChangeEvent tce){
        if (tce.getTab().getTitle().equals("Tipos")){
            if(this.registro!=null && this.frmPeliculaCaracteristica !=null){
                this.frmPeliculaCaracteristica.setIdPelicula(this.registro.getIdPelicula());
            }
        }
    }
}
