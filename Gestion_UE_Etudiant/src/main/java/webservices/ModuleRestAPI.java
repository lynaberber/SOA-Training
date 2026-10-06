package webservices;
//JAX-RS

import entities.Module;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/module")
public class ModuleRestAPI {

    static ModuleBusiness helper = new ModuleBusiness();

    //list
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListModules() {
        return Response.status(200).entity(helper.getAllModules()).build();
    }

    //add
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addModule(Module module) {

        if (module == null || module.getUniteEnseignement() == null) {
            return Response.status(400).entity("Erreur").build();
        }
        if (helper.addModule(module)) {
            return Response.status(201).entity("Succes").build();
        } else {

            return Response.status(400).entity("Erreur").build();
        }
    }

    //matricule
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{matricule}")
    public Response getModuleByMatricule(@PathParam("matricule") String matricule) {
        return Response.status(200).entity(helper.getModuleByMatricule(matricule)).build();
    }




    // update?matricule=M101
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateModule(@QueryParam("matricule") String matricule, Module module) {

        if (helper.updateModule(matricule, module)) {
            return Response.status(200).entity("Succes").build();
        }
        else {
            return Response.status(400).entity("Erreur").build();
        }
    }

    //delete?matricule=M101
    @DELETE
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteModule(@QueryParam("matricule") String matricule) {

        if (helper.deleteModule(matricule)) {
            return Response.status(200).entity("Succes").build();
        }
        else {
            return Response.status(400).entity("Erreur").build();
        }
    }
}