package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Named
@SessionScoped
public class SesionUsuario implements Serializable {

    @Inject
    FacesContext fc;

    //Instancias
    Map<String, Locale> idiomas = new HashMap<>();
    String idiomaSeleccionado;

    //Getter & Setter
    public String getIdiomaSeleccionado() {
        return idiomaSeleccionado;
    }

    public void setIdiomaSeleccionado(String idiomaSeleccionado) {
        this.idiomaSeleccionado = idiomaSeleccionado;
    }

    public Map<String, Locale> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Map<String, Locale> idiomas) {
        this.idiomas = idiomas;
    }

    @PostConstruct
    public void init() {
        idiomas.put("English", new Locale.Builder().setLanguageTag("en").build());
        idiomas.put("Español", new Locale.Builder().setLanguageTag("es").build());
    }

    public void cambiarIdioma(ValueChangeEvent ev) {
        String idioma = ev.getNewValue().toString();
        for (Map.Entry<String, Locale> entry : idiomas.entrySet()) {
            if (entry.getKey().equals(idioma)) {
                fc.getViewRoot().setLocale(entry.getValue());
            }
        }
    }
}
