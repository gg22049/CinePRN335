//package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.rest.server;
//
//import jakarta.inject.Inject;
//import jakarta.ws.rs.*;
//import jakarta.ws.rs.core.Context;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//import jakarta.ws.rs.core.UriInfo;
//import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoSalaBean;
//import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoSala;
//
//import java.io.Serializable;
//import java.util.List;
//import java.util.logging.Logger;
//import java.util.logging.Level;
//
//@Path("tiposala")
//public class TipoSalaResource implements Serializable {
//
//    @Inject
//    TipoSalaBean tsBean;
//
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response findRange(
//            @QueryParam("first")
//            @DefaultValue("0")
//            int firstResult,
//            @QueryParam("max")
//            @DefaultValue("50")
//            int maxResult
//    ) {
//        try{
//            if (firstResult >= 0 || maxResult > 0 || maxResult<=50) {
//                List<TipoSala> encontrados = tsBean.findRange(firstResult, maxResult);
//               // Long total = tsBean.count();
////                Response.ResponseBuilder builder = Response.ok(encontrados)
////                        .header("Total-Records", total)
////                        .type(MediaType.APPLICATION_JSON);
////                return builder.build();
//                return null;
//            }else{
//                return Response.status(422).header("Wrong-Parameter", "first"+firstResult+"max"+maxResult).build();
//            }
//        }catch (Exception e){
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
//            return Response.status(500).entity(e.getMessage()).build();
//        }
//    }
//
//    @GET
//    @Path("/{id}")
//    public Response findById(@PathParam("id") Integer id){
//        if (id!=null){
//            try {
//                TipoSala encontrados = tsBean.findById(id);
//                if (encontrados!=null){
//                    Response.ResponseBuilder builder = Response.ok(encontrados).type(MediaType.APPLICATION_JSON);
//                    return builder.build();
//                }
//            }catch (Exception e){
//                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
//                return Response.status(500).entity(e.getMessage()).build();
//            }
//        }
//        return Response.status(422).header("Wrong-Parameter", "id"+id).build();
//    }
//
//    @POST
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_JSON})
//    public Response create(TipoSala tipoSala,
//        @Context UriInfo UriInfo
//    ){
//        if (tipoSala!=null && tipoSala.getIdTipoSala()==null){
//            tsBean.create(tipoSala);
//            if (tipoSala.getIdTipoSala()!=null){
//              //UriBuilder uriBuilder = UriInfo.getAbsolutePathBuilder();
//                //uriBuilder.path(String.valueOf(tipoSala.getIdTipoSala()));
//            }
//        }
//        return null;
//    }
//}
