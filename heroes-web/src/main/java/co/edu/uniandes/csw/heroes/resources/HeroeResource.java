package co.edu.uniandes.csw.heroes.resources;


import co.edu.uniandes.csw.heroes.dtos.HeroeDetailDTO;
import co.edu.uniandes.csw.heroes.ejb.HeroeLogic;
import co.edu.uniandes.csw.heroes.entities.HeroeEntity;
import co.edu.uniandes.csw.heroes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.heroes.mappers.BusinessLogicExceptionMapper;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

@Path("heroes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class HeroeResource {
    
    @Inject
    private HeroeLogic heroeLogic;

    
    @POST
    public HeroeDetailDTO createHeroe(HeroeDetailDTO heroe) throws BusinessLogicException {
        HeroeEntity heroeEntity = heroe.toEntity();
        heroeEntity = heroeLogic.createHeroe(heroeEntity); 
        return new HeroeDetailDTO(heroeEntity);
    }


    @GET
    @Path("{id: \\d+}")
    public HeroeDetailDTO getHeroe(@PathParam("id") Long id) {
        HeroeEntity heroe = heroeLogic.getHeroe(id);
        if (heroe == null) 
            throw new WebApplicationException("El heroe no existe");
        return new HeroeDetailDTO(heroe); 
    }
    
 
}
