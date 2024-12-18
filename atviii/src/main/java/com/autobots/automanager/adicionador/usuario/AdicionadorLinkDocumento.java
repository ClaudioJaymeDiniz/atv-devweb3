package com.autobots.automanager.adicionador.usuario;

import com.autobots.automanager.adicionador.AdicionadorLink;
import com.autobots.automanager.controles.usuario.DocumentoControle;
import com.autobots.automanager.entitades.usuario.Documento;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkDocumento implements AdicionadorLink<Documento> {

    @Override
    public void adicionarLink(List<Documento> lista) {

        for (Documento documento : lista) {
            long id = documento.getId();

            Link linkProprio = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                            .methodOn(DocumentoControle.class).obterDocumento(id))
                                .withSelfRel();

            documento.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(Documento objeto) {

        Link linkProprio = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                        .methodOn(DocumentoControle.class).obterDocumentos())
                            .withRel("documentos");

        objeto.add(linkProprio);
    }
}