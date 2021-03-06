/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.estacionamento.rest;

import br.com.senai.estacionamento.dao.ConfiguracaoDAO;
import br.com.senai.estacionamento.model.Configuracao;
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
@Path("configuracoes")
@Produces(MediaType.APPLICATION_JSON)
public class ConfiguracaoResource {
    @Inject
    private ConfiguracaoDAO configuracaoDAO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Configuracao insert(Configuracao configuracao) {
        configuracaoDAO.insere(configuracao);
        return configuracao;
    }

    @GET
    public List<Configuracao> list() {
        return configuracaoDAO.lista();
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        configuracaoDAO.excluir(id);
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Configuracao update(@PathParam("id") Long id,
            Configuracao configuracao) {
        if (!Objects.equals(id, configuracao.getId())) {
            throw new WebApplicationException
                            (Response.Status.BAD_REQUEST);
        }
        return configuracaoDAO.atualizar(configuracao);
    }
    
    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Long id) {
        final Configuracao configuracao = configuracaoDAO.buscar(id);
        if (configuracao == null) {
            throw new EntityNotFoundException();
        }
        return Response.ok(configuracao).build();
    }  
}
