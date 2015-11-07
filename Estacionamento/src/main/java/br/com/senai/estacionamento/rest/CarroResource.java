/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.estacionamento.rest;

import br.com.senai.estacionamento.dao.CarroDAO;
import br.com.senai.estacionamento.model.Carro;
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
@Path("carros")
@Produces(MediaType.APPLICATION_JSON)
public class CarroResource {
    @Inject
    private CarroDAO carroDAO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Carro insert(Carro carro) {
        carroDAO.insere(carro);
        return carro;
    }

    @GET
    public List<Carro> list() {
        return carroDAO.lista();
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        carroDAO.excluir(id);
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Carro update(@PathParam("id") Long id,
            Carro carro) {
        if (!Objects.equals(id, carro.getId())) {
            throw new WebApplicationException
                            (Response.Status.BAD_REQUEST);
        }
        return carroDAO.atualizar(carro);
    }
    
    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Long id) {
        final Carro carro = carroDAO.buscar(id);
        if (carro == null) {
            throw new EntityNotFoundException();
        }
        return Response.ok(carro).build();
    }  
}
