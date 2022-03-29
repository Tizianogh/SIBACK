package fr.dev.resources;

import fr.dev.dao.RoleDAO;
import fr.dev.model.Role;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("roles")
public class RoleResource {
    private final RoleDAO roleDAO = new RoleDAO();

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Role createMarque(Role role) {
        return roleDAO.createRole(role);
    }
}
