/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.estacionamento.rest;

import br.com.senai.estacionamento.dao.UsuarioDAO;
import br.com.senai.estacionamento.model.Usuario;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Victor Matheus
 */

@Stateless
@Path("usuarios")
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {
    @Inject
    private UsuarioDAO usuarioDAO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Usuario insert(Usuario usuario) {
        usuarioDAO.insere(usuario);
        return usuario;
    }

    @GET
    public List<Usuario> list() {
        return usuarioDAO.lista();
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        usuarioDAO.excluir(id);
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Usuario update(@PathParam("id") Long id,
            Usuario usuario) {
        if (!Objects.equals(id, usuario.getId())) {
            throw new WebApplicationException
                            (Response.Status.BAD_REQUEST);
        }
        return usuarioDAO.atualizar(usuario);
    }
    
    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Long id) {
        final Usuario usuario = usuarioDAO.buscar(id);
        if (usuario == null) {
            throw new EntityNotFoundException();
        }
        return Response.ok(usuario).build();
    }  
}
