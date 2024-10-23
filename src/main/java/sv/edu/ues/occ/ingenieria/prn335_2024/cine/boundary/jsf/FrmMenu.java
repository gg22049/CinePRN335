package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;

import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class FrmMenu implements Serializable {

    @Inject
    FacesContext fc;

    DefaultMenuModel model;

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();
        DefaultSubMenu tipos = DefaultSubMenu.builder()
                .label("Tipos")
                .expanded(true)
                .build();
        DefaultMenuItem item = DefaultMenuItem.builder()
                .value("Asiento")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoAsiento.jsf')}")
                .build();
        tipos.getElements().add(item);
        item = DefaultMenuItem.builder()
                .value("Pago")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoPago.jsf')}")
                .build();
        tipos.getElements().add(item);
        item = DefaultMenuItem.builder()
                .value("Pelicula")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoPelicula.jsf')}")
                .build();
        tipos.getElements().add(item);
        item = DefaultMenuItem.builder()
                .value("Producto")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoProducto.jsf')}")
                .build();
        tipos.getElements().add(item);
        item = DefaultMenuItem.builder()
                .value("Reserva")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoReserva.jsf')}")
                .build();
        tipos.getElements().add(item);
        item = DefaultMenuItem.builder()
                .value("Sala")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoSala.jsf')}")
                .build();
        tipos.getElements().add(item);
        model.getElements().add(tipos);
    }

    public void navegar(String formulario) throws IOException {
            fc.getExternalContext().redirect(formulario);
    }

    public DefaultMenuModel getModel() {
        return model;
    }

    public void setModel(DefaultMenuModel model) {
        this.model = model;
    }
}
