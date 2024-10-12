package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoReservaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoReserva;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class FrmTipoReserva extends AbstractFrm implements Serializable {

    @Inject
    TipoReservaBean dataBean;

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
                TipoReserva parse = (TipoReserva) objeto;
                return parse.getIdTipoReserva().toString();
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
        TipoReserva nuevo = new TipoReserva();
        nuevo.setActivo(true);
        this.registro = nuevo;
    }

    @Override
    public void saveNew(){
        if(dataBean!=null){
            try {
                TipoReserva parse = (TipoReserva) registro;
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
                TipoReserva parse = (TipoReserva) registro;
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
                TipoReserva parse = (TipoReserva) registro;
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
