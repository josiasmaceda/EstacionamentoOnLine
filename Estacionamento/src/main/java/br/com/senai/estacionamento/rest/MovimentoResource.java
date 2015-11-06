/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.estacionamento.rest;

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
import br.com.senai.estacionamento.dao.MovimentoDAO;
import br.com.senai.estacionamento.model.Movimento;

/**
 *
 * @author josias
 */

@Stateless
@Path("movimentos")
@Produces(MediaType.APPLICATION_JSON)
public class MovimentoResource {

    @Inject
    private MovimentoDAO movimentoDAO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Movimento insert(Movimento movimento) {
        movimentoDAO.insere(movimento);
        return movimento;
    }

    @GET
    public List<Movimento> list() {
        return movimentoDAO.lista();
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        movimentoDAO.excluir(id);
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Movimento update(@PathParam("id") Long id,
            Movimento movimento) {
        if (!Objects.equals(id, movimento.getId())) {
            throw new WebApplicationException
                            (Response.Status.BAD_REQUEST);
        }
        return movimentoDAO.atualizar(movimento);
    }
    
    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Long id) {
        final Movimento movimento = movimentoDAO.buscar(id);
        if (movimento == null) {
            throw new EntityNotFoundException();
        }
        return Response.ok(movimento).build();
    }    
}