package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AsientoCaracteristicaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoAsientoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Asiento;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.AsientoCaracteristica;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoAsiento;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Named
@Dependent
public class FrmAsientoCaracteristica extends AbstractFrm<AsientoCaracteristica> implements Serializable {

    @Inject
    AsientoCaracteristicaBean acBean;

    @Inject
    TipoAsientoBean taBean;

    @Inject
    FacesContext fc;

    //Instancias
    protected  List<AsientoCaracteristica> caracteristicasAsiento;
    protected Asiento asientoSeleccionado;
    protected List<TipoAsiento> tipoAsientoList;

    @PostConstruct
    @Override
    public void init(){
        super.init();
        try{
            tipoAsientoList = taBean.findRange(0,Integer.MAX_VALUE);
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Override
    public AbstractDataPersistence<AsientoCaracteristica> getDataPersist() {
        return this.acBean;
    }

    @Override
    public FacesContext getFacesContext() {
        return this.fc;
    }

    @Override
    public String getIdObjeto(AsientoCaracteristica object) {
        if (object != null && object.getIdAsientoCaracteristica()!=null) {
            return object.getIdAsientoCaracteristica().toString();
        }
        return null;
    }

    @Override
    public AsientoCaracteristica getObjeto(String id) {
        if (id!=null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r->r.getIdAsientoCaracteristica().toString().equals(id)).findFirst().orElse(null);
        }
        return null;
    }

    @Override
    public String getTituloPagina() {
        return AsientoCaracteristica.class.getSimpleName().replaceAll("([a-z])([A-Z])", "$1 de $2");
    }

    @Override
    public int contar(){
        try {
            if (asientoSeleccionado.getIdAsiento()!=null && acBean !=null) {
                return acBean.countAsiento(this.asientoSeleccionado.getIdAsiento());
            }
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
        return 0;
    }

    @Override
    public List<AsientoCaracteristica> cargarDatos(int findFrist,int findMax){
        try {
            if (asientoSeleccionado.getIdAsiento()!=null && acBean !=null) {
                return acBean.caracteristicaSelected(this.asientoSeleccionado.getIdAsiento(), findFrist, findMax);
            }
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
        return null;
    }

    //BOTONES

    @Override
    public void instanciarRegistro() {
        this.registro = new AsientoCaracteristica();
        this.registro.setIdAsiento(asientoSeleccionado);
    }

    @Override
    public void btnGuardarHandler(ActionEvent actionEvent){
        super.btnGuardarHandler(actionEvent);
        cargarAsientoCaracteristicas();
    }

    @Override
    public void btnModificarHandler(ActionEvent actionEvent) {
        super.btnModificarHandler(actionEvent);
        cargarAsientoCaracteristicas();
    }

    @Override
    public void btnEliminarHandler(ActionEvent actionEvent) {
        super.btnEliminarHandler(actionEvent);
        cargarAsientoCaracteristicas();
    }

    public void validarVailador(FacesContext fc, UIComponent component, Object valor){
        UIInput input= (UIInput) component;
        if (registro!=null && this.registro.getIdTipoAsiento()!=null) {
            String nuevo = valor.toString();
            Pattern pattern=Pattern.compile(this.registro.getIdTipoAsiento().getExpresionRegular());
            Matcher matcher=pattern.matcher(nuevo);
            if (matcher.find()) {
                input.setValue(true);
                return;
            }
        }
        input.setValue(false);
    }

    public void cargarAsientoCaracteristicas(){
        if (asientoSeleccionado.getIdAsiento()!=null && acBean !=null) {
            try {
                this.caracteristicasAsiento = acBean.caracteristicasByIdAsiento(asientoSeleccionado.getIdAsiento());
            }catch (Exception e) {
                this.caracteristicasAsiento = List.of();
                Logger.getLogger(FrmAsientoCaracteristica.class.getName()).log(Level.SEVERE, null, e);
                FacesMessage mensaje = new FacesMessage();
                mensaje.setSummary("Error al procesar el asiento seleccionado");
                mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
                fc.addMessage(null, mensaje);
            }
        }
    }

//    Propiedades Sintericas
    public Integer getCaracteristicaByTipo() {
        if (this.registro != null && this.registro.getIdTipoAsiento().getIdTipoAsiento() != null && !caracteristicasAsiento.isEmpty()) {
            return this.registro.getIdTipoAsiento().getIdTipoAsiento();
        }
        return -1;
    }

    public void setCaracteristicaByTipo(final Integer idTipo) {
        if (!this.caracteristicasAsiento.isEmpty()){
            this.registro = this.caracteristicasAsiento.stream().filter(r->r.getIdTipoAsiento().getIdTipoAsiento().equals(idTipo)).findFirst().orElse(new AsientoCaracteristica());
        }
    }

    public Integer getIdTipoAsientoSeleccionado() {
        if (registro!=null && this.registro.getIdTipoAsiento()!=null) {
            return this.registro.getIdTipoAsiento().getIdTipoAsiento();
        }
        return -1;
    }

    public void setIdTipoAsientoSeleccionado(final Integer idAsientoSeleccionado) {
        if ( idAsientoSeleccionado !=null && idAsientoSeleccionado>0 && !this.tipoAsientoList.isEmpty()){
            this.registro.setIdTipoAsiento(tipoAsientoList.stream().filter(r->r.getIdTipoAsiento().equals(idAsientoSeleccionado)).findFirst().orElse(new TipoAsiento()));
        }
    }

    //Getter & Setter
    public List<AsientoCaracteristica> getCaracteristicasAsiento() {
        return caracteristicasAsiento;
    }

    public void setCaracteristicasAsiento(List<AsientoCaracteristica> caracteristicasAsiento) {
        this.caracteristicasAsiento = caracteristicasAsiento;
    }

    public Asiento getAsientoSeleccionado() {
        return asientoSeleccionado;
    }

    public void setAsientoSeleccionado(Asiento asientoSeleccionado) {
        this.asientoSeleccionado = asientoSeleccionado;
    }

    public List<TipoAsiento> getTipoAsientoList() {
        return tipoAsientoList;
    }

    public void setTipoAsientoList(List<TipoAsiento> tipoAsientoList) {
        this.tipoAsientoList = tipoAsientoList;
    }
}