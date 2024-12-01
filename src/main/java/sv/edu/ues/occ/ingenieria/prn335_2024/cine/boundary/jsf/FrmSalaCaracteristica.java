package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.SalaCaracteristicaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoSalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Sala;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.SalaCaracteristica;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoSala;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Named
@Dependent
public class FrmSalaCaracteristica extends AbstractFrm<SalaCaracteristica> implements Serializable {

    //Bean Dependiente
    @Inject
    SalaCaracteristicaBean bean;

    //Bean Independiente
    @Inject
    TipoSalaBean tsBean;

    @Inject
    FacesContext fc;

    protected List<TipoSala> tipoSalaList;
    protected Sala salaSeleccionada;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        try {
            this.tipoSalaList = tsBean.findRange(0,Integer.MAX_VALUE);
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
    }

    @Override
    public AbstractDataPersistence<SalaCaracteristica> getDataPersist() {
        return this.bean;
    }

    @Override
    public FacesContext getFacesContext() {
        return this.fc;
    }

    @Override
    public String getIdObjeto(SalaCaracteristica object) {
        if (object != null && object.getIdSalaCaracteristica()!=null) {
            return object.getIdSalaCaracteristica().toString();
        }
        return null;
    }

    @Override
    public SalaCaracteristica getObjeto(String id) {
        if (id!=null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r->r.getIdSalaCaracteristica().toString().equals(id)).findFirst().orElse(null);
        }
        return null;
    }

    @Override
    public void instanciarRegistro() {
        this.registro = new SalaCaracteristica();
        this.registro.setIdSala(salaSeleccionada);
    }

    @Override
    public String getTituloPagina() {
        return SalaCaracteristica.class.getSimpleName().replaceAll("([a-z])([A-Z])", "$1 de $2");
    }

    @Override
    public int contar(){
        try {
            if (this.salaSeleccionada.getIdSala()!=null && bean!=null) {
                return bean.countSala(this.salaSeleccionada.getIdSala().longValue());
            }
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
        return 0;
    }

    @Override
    public List<SalaCaracteristica> cargarDatos(int findFrist,int findMax){
        try {
            if (salaSeleccionada.getIdSala()!=null && bean!=null) {
                return bean.caracteristicaSelected(this.salaSeleccionada.getIdSala().longValue(), findFrist, findMax);
            }
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
        return List.of();
    }

    public void validarVailador(FacesContext fc, UIComponent component, Object valor){
        UIInput input= (UIInput) component;
        if (registro!=null && this.registro.getIdTipoSala()!=null) {
            String nuevo = valor.toString();
            Pattern pattern=Pattern.compile(this.registro.getIdTipoSala().getExpresionRegular());
            Matcher matcher=pattern.matcher(nuevo);
            if (matcher.find()) {
                input.setValue(true);
                return;
            }
        }
        input.setValue(false);
    }

    public Integer getIdTipoSalaSeleccionado() {
        if (this.registro!=null && this.registro.getIdTipoSala()!=null) {
            return this.registro.getIdTipoSala().getIdTipoSala();
        }
        return null;
    }

    public void setIdTipoSalaSeleccionado(final Integer idTipoSala) {
        if (this.registro!=null && this.tipoSalaList!=null && !this.tipoSalaList.isEmpty()){
            this.registro.setIdTipoSala(this.tipoSalaList.stream().filter(r->r.getIdTipoSala().equals(idTipoSala)).findFirst().orElse(null));
        }
    }

    //Getter & Setter
    public List<TipoSala> getTipoSalaList() {
        return tipoSalaList;
    }

    public void setTipoSalaList(List<TipoSala> tipoSalaList) {
        this.tipoSalaList = tipoSalaList;
    }

    public Sala getSalaSeleccionada() {
        return salaSeleccionada;
    }

    public void setSalaSeleccionada(Sala salaSeleccionada) {
        this.salaSeleccionada = salaSeleccionada;
    }
}