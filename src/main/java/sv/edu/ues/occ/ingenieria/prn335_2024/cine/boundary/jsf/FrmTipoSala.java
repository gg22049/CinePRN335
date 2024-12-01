package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoSalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoSala;

import java.io.Serializable;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class FrmTipoSala extends AbstractFrm<TipoSala> implements Serializable {

    @Inject
    TipoSalaBean bean;

    @Inject
    FacesContext fc;

    @Override
    public AbstractDataPersistence<TipoSala> getDataPersist() {
        return this.bean;
    }

    @Override
    public FacesContext getFacesContext() {
        return this.fc;
    }

    @Override
    public String getIdObjeto(TipoSala object) {
        if (object != null && object.getIdTipoSala()!=null) {
            return object.getIdTipoSala().toString();
        }
        return null;
    }

    @Override
    public TipoSala getObjeto(String id) {
        if (id!=null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r->r.getIdTipoSala().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    @Override
    public void instanciarRegistro() {
        this.registro = new TipoSala();
        this.registro.setNombre("Nombre de la Sala");
        this.registro.setExpresionRegular(".");
        this.registro.setActivo(true);
        this.registro.setComentarios("Comentarios aquí.");
    }

    @Override
    public String getTituloPagina(){
        return TipoSala.class.getSimpleName().replaceAll("([a-z])([A-Z])", "$1 de $2");
    }

    /**
     * Metodo para crear nuevo pero creo no sirve :v
     */

    public void nuevoRegistro(){
        this.registro.setNombre(registro.getNombre());
        this.registro.setExpresionRegular(registro.getExpresionRegular());
        this.registro.setActivo(registro.getActivo());
        this.registro.setComentarios(registro.getComentarios());
    }
}