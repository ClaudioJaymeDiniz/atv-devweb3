package com.autobots.automanager.modelos.adicionador;

import com.autobots.automanager.controles.DocumentoControle;
import com.autobots.automanager.entidades.Documento;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkDocumento implements AdicionadorLink<Documento> {

    @Override
    public void adicionarLink(List<Documento> lista){
        for (Documento documento : lista) {
            long id = documento.getId();
            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(
                            WebMvcLinkBuilder.methodOn(DocumentoControle.class)
                                .obterDocumento(id)).withSelfRel();

            documento.add(linkProprio);
        }
    }

    public void adicionarLink(Documento objeto) {

        Link linkProprio = WebMvcLinkBuilder
                .linkTo(
                        WebMvcLinkBuilder.methodOn(DocumentoControle.class)
                            .obterDocumento(0)).withRel("documentos");

        objeto.add(linkProprio);
    }
}
