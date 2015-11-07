/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.estacionamento.rest;

import br.com.senai.estacionamento.dao.MensalistaDAO;
import br.com.senai.estacionamento.model.Mensalista;
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
@Path("mensalistas")
@Produces(MediaType.APPLICATION_JSON)
public class MensalistaResource {
    @Inject
    private MensalistaDAO mensalistaDAO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensalista insert(Mensalista mensalista) {
        mensalistaDAO.insere(mensalista);
        return mensalista;
    }

    @GET
    public List<Mensalista> list() {
        return mensalistaDAO.lista();
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        mensalistaDAO.excluir(id);
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensalista update(@PathParam("id") Long id,
            Mensalista mensalista) {
        if (!Objects.equals(id, mensalista.getId())) {
            throw new WebApplicationException
                            (Response.Status.BAD_REQUEST);
        }
        return mensalistaDAO.atualizar(mensalista);
    }
    
    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Long id) {
        final Mensalista mensalista = mensalistaDAO.buscar(id);
        if (mensalista == null) {
            throw new EntityNotFoundException();
        }
        return Response.ok(mensalista).build();
    }  
}
