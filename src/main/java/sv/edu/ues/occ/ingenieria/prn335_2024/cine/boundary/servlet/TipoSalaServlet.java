package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.servlet;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoSalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoSala;

import java.io.IOException;

@WebServlet(name = "TipoSalaServlet", urlPatterns = "TipoSala")
public class TipoSalaServlet extends HttpServlet {
    @Inject
    TipoSalaBean tsBean;

/*    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/TipoSala.xhtml").forward(req, resp);
    }*/

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        if (req.getParameter("nombre") != null) {
            TipoSala nuevo = new TipoSala();
            nuevo.setNombre(req.getParameter("nombre"));
            nuevo.setActivo(Boolean.valueOf(req.getParameter("activo")));
            nuevo.setComentarios(req.getParameter("comentarios"));
            nuevo.setExpresionRegular(req.getParameter("expresionRegular"));
            try {
                tsBean.create(nuevo);
            }catch (Exception e) {
                e.printStackTrace();
                resp.setStatus(500);
                return;
            }
        }else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}