package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.PeliculaCaracteristicaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoPeliculaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Pelicula;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.PeliculaCaracteristica;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoPelicula;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Named
@Dependent
public class FrmPeliculaCaracteristica extends AbstractFrm<PeliculaCaracteristica> implements Serializable {

    @Inject
    PeliculaCaracteristicaBean bean;

    @Inject
    TipoPeliculaBean tpBean;

    @Inject
    FacesContext fc;

    List<TipoPelicula> tipoPeliculaList;
    Long idPelicula;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        try {
            this.tipoPeliculaList=tpBean.findRange(0, Integer.MAX_VALUE);
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
    }

    @Override
    public AbstractDataPersistence<PeliculaCaracteristica> getDataPersist() {
        return this.bean;
    }

    @Override
    public FacesContext getFacesContext() {
        return this.fc;
    }

    @Override
    public String getIdObjeto(PeliculaCaracteristica object) {
        if (object != null && object.getIdPeliculaCaracteristica()!=null) {
            return object.getIdPeliculaCaracteristica().toString();
        }
        return null;
    }

    @Override
    public PeliculaCaracteristica getObjeto(String id) {
        if (id!=null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r->r.getIdPeliculaCaracteristica().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    @Override
    public void instanciarRegistro() {
        this.registro = new PeliculaCaracteristica();
    }

    @Override
    public String getTituloPagina() {
        return PeliculaCaracteristica.class.getSimpleName().replaceAll("([a-z])([A-Z])", "$1 de $2");
    }

    //Metodos que no funcionan
    @Override
    public int contar(){
        try {
            if (idPelicula!=null && bean!=null) {
                return bean.countPelicula(this.idPelicula);
            }
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
        return 0;
    }

    @Override
    public List<PeliculaCaracteristica> cargarDatos(int findFrist,int findMax){
        try {
            if (idPelicula!=null && bean!=null) {
                return bean.caracteristicaSelected(this.idPelicula, findFrist, findMax);
            }
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
        return null;
    }

    //Clases nuevas y cosas que no funcionan

    public PeliculaCaracteristica crearNuevo(){
        PeliculaCaracteristica pc = new PeliculaCaracteristica();
        if(idPelicula!=null){
            pc.setIdPelicula(new Pelicula(idPelicula));
        }
        if (tipoPeliculaList!=null && tipoPeliculaList.isEmpty()){
            pc.setIdTipoPelicula(tipoPeliculaList.getFirst());
        }
        return pc;
    }

    public Integer getIdTipoPeliculaSeleccionado() {
        if (this.registro!=null && this.registro.getIdTipoPelicula()!=null) {
            return this.registro.getIdTipoPelicula().getIdTipoPelicula();
        }
        return null;
    }

    public void setIdTipoPeliculaSeleccionado(final Integer idTipoPelicula) {
        if (this.registro!=null && this.tipoPeliculaList!=null && !this.tipoPeliculaList.isEmpty()){
            this.registro.setIdTipoPelicula(this.tipoPeliculaList.stream().filter(r->r.getIdTipoPelicula().equals(idTipoPelicula)).findFirst().orElse(null));
        }
    }

    public void validarVailador(FacesContext fc, UIComponent component, Object valor){
        UIInput input= (UIInput) component;
        if (registro!=null && this.registro.getIdTipoPelicula()!=null) {
            String nuevo = valor.toString();
            Pattern pattern=Pattern.compile(this.registro.getIdTipoPelicula().getExpresionRegular());
            Matcher matcher=pattern.matcher(nuevo);
            if (matcher.find()) {
                input.setValue(true);
                return;
            }
        }
        input.setValue(false);
    }

    //Getter & Setter

    public List<TipoPelicula> getTipoPeliculaList() {
        return tipoPeliculaList;
    }

    public void setTipoPeliculaList(List<TipoPelicula> tipoPeliculaList) {
        this.tipoPeliculaList = tipoPeliculaList;
    }

    public Long getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Long idPelicula) {
        this.idPelicula = idPelicula;
    }
}
