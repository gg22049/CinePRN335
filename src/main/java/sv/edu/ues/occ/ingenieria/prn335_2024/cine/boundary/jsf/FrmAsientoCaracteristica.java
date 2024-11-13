package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AsientoCaracteristicaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoAsientoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.AsientoCaracteristica;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoAsiento;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Named
@Dependent
public class FrmAsientoCaracteristica extends AbstractFrm<AsientoCaracteristica> implements Serializable {

    @Inject
    AsientoCaracteristicaBean bean;

    @Inject
    TipoAsientoBean tpBean;

    @Inject
    FacesContext fc;

    List<TipoAsiento> tipoAsientoList;
    Long idAsiento;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        try {
            this.tipoAsientoList=tpBean.findRange(0, Integer.MAX_VALUE);
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
    }

    @Override
    public AbstractDataPersistence<AsientoCaracteristica> getDataPersist() {
        return this.bean;
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
            return this.modelo.getWrappedData().stream().filter(r->r.getIdAsientoCaracteristica().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    @Override
    public void instanciarRegistro() {
        this.registro = new AsientoCaracteristica();
    }

    @Override
    public String getTituloPagina() {
        return AsientoCaracteristica.class.getSimpleName().replaceAll("([a-z])([A-Z])", "$1 de $2");
    }

    @Override
    public int contar(){
        try {
            if (idAsiento!=null && bean!=null) {
                return bean.countAsiento(this.idAsiento);
            }
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
        return 0;
    }

    @Override
    public List<AsientoCaracteristica> cargarDatos(int findFrist,int findMax){
        try {
            if (idAsiento!=null && bean!=null) {
                return bean.caracteristicaSelected(this.idAsiento, findFrist, findMax);
            }
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
        return null;
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

    public Integer getIdTipoAsientoSeleccionado() {
        if (this.registro!=null && this.registro.getIdTipoAsiento()!=null) {
            return this.registro.getIdTipoAsiento().getIdTipoAsiento();
        }
        return null;
    }

    public void setIdTipoAsientoSeleccionado(final Integer idTipoAsiento) {
        if (this.registro!=null && this.tipoAsientoList!=null && !this.tipoAsientoList.isEmpty()){
            this.registro.setIdTipoAsiento(this.tipoAsientoList.stream().filter(r->r.getIdTipoAsiento().equals(idTipoAsiento)).findFirst().orElse(null));
        }
    }

    //Getter & Setter

    public List<TipoAsiento> getTipoAsientoList() {
        return tipoAsientoList;
    }

    public void setTipoAsientoList(List<TipoAsiento> tipoAsientoList) {
        this.tipoAsientoList = tipoAsientoList;
    }

    public Long getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(Long idAsiento) {
        this.idAsiento = idAsiento;
    }
}