/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.estacionamento.rest;

import br.com.senai.estacionamento.dao.VeiculoDAO;
import br.com.senai.estacionamento.model.Veiculo;
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
@Path("veiculos")
@Produces(MediaType.APPLICATION_JSON)
public class VeiculoResource {
    @Inject
    private VeiculoDAO veiculoDAO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Veiculo insert(Veiculo veiculo) {
        veiculoDAO.insere(veiculo);
        return veiculo;
    }

    @GET
    public List<Veiculo> list() {
        return veiculoDAO.lista();
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        veiculoDAO.excluir(id);
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Veiculo update(@PathParam("id") Long id,
            Veiculo veiculo) {
        if (!Objects.equals(id, veiculo.getId())) {
            throw new WebApplicationException
                            (Response.Status.BAD_REQUEST);
        }
        return veiculoDAO.atualizar(veiculo);
    }
    
    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Long id) {
        final Veiculo veiculo = veiculoDAO.buscar(id);
        if (veiculo == null) {
            throw new EntityNotFoundException();
        }
        return Response.ok(veiculo).build();
    }  
}
