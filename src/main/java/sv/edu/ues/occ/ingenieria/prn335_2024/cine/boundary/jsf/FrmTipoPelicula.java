package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.interceptor.Interceptors;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoPeliculaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoPelicula;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class FrmTipoPelicula extends AbstractFrm implements Serializable {

    @Inject
    TipoPeliculaBean dataBean;

    @Inject
    FacesContext facesContext;

    @Override
    public int getCount() {
        if (dataBean!=null){
            try {
                return dataBean.count();
            }catch (Exception e){
                FacesMessage mensaje = new FacesMessage();
                mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
                mensaje.setSummary("Error al consultar los registros:"+e.getMessage());
                facesContext.addMessage(null, mensaje);
            }
        }else {
            FacesMessage mensaje = new FacesMessage();
            mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            mensaje.setSummary("Error al acceder a la base de datos");
            facesContext.addMessage(null, mensaje);
        }
        return 0;
    }

    @Override
    public List llenarModelo(int desde, int max) {
        try {
            if (desde >= 0 && max >= desde) {
                return dataBean.findByRange(desde, max);
            }
        }catch (Exception e){
            FacesMessage mensaje = new FacesMessage();
            mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            mensaje.setSummary("Error al cargar los registros:"+e.getMessage());
            facesContext.addMessage(null, mensaje);
        }
        return List.of();
    }

    @Override
    public String obtenerID(Object objeto) {
        if (objeto!=null){
            try {
                TipoPelicula parse = (TipoPelicula) objeto;
                return parse.getIdTipoPelicula().toString();
            }catch (Exception e){
                FacesMessage mensaje = new FacesMessage();
                mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
                mensaje.setSummary("Error no se puedo obtener el ID:"+e.getMessage());
                facesContext.addMessage(null, mensaje);
            }
        }
        return "";
    }

    //Botones

    @Override
    public void createNew() {
        TipoPelicula nuevo = new TipoPelicula();
        nuevo.setActivo(true);
        nuevo.setExpresionRegular(".");
        this.registro = nuevo;
    }

    @Override
    public void saveNew(){
        if(dataBean!=null){
            try {
                TipoPelicula parse = (TipoPelicula) registro;
                dataBean.create(parse);
            }catch (Exception e){
                FacesMessage mensaje = new FacesMessage();
                mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
                mensaje.setSummary("Error al convertir" + e.getMessage());
                facesContext.addMessage(null, mensaje);
            }
        }
    }
    @Override
    public void updateNew(){
        if(dataBean!=null){
            try {
                TipoPelicula parse = (TipoPelicula) registro;
                dataBean.update(parse);
            }catch (Exception e){
                FacesMessage mensaje = new FacesMessage();
                mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
                mensaje.setSummary("Error al actualizar" + e.getMessage());
                facesContext.addMessage(null, mensaje);
            }
        }
    }

    @Override
    public void deleteNew(){
        if(dataBean!=null){
            try {
                TipoPelicula parse = (TipoPelicula) registro;
                dataBean.delete(parse);
            }catch (Exception e){
                FacesMessage mensaje = new FacesMessage();
                mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
                mensaje.setSummary("Error al actualizar" + e.getMessage());
                facesContext.addMessage(null, mensaje);
            }
        }
    }

}