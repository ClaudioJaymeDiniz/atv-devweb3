package com.autobots.automanager.adicionador.empresa;

import com.autobots.automanager.adicionador.AdicionadorLink;
import com.autobots.automanager.controles.empresa.MercadoriaControle;
import com.autobots.automanager.entitades.empresa.Mercadoria;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkMercadoria implements AdicionadorLink<Mercadoria> {

    @Override
    public void adicionarLink(List<Mercadoria> lista) {

        for (Mercadoria mercadoria : lista) {

            long id = mercadoria.getId();

            if (!mercadoria.hasLink("self")) {

                Link linkProprio = WebMvcLinkBuilder

                        .linkTo(WebMvcLinkBuilder

                                .methodOn(MercadoriaControle.class)

                                .obterMercadoria(id))

                        .withSelfRel();

                mercadoria.add(linkProprio);

            }
        }
    }

    @Override
    public void adicionarLink(Mercadoria mercadoria) {

        if (!mercadoria.hasLink("mercadorias")) {

            Link linkProprio = WebMvcLinkBuilder

                    .linkTo(WebMvcLinkBuilder

                            .methodOn(MercadoriaControle.class)

                            .obterMercadorias())

                    .withRel("mercadorias");

            mercadoria.add(linkProprio);

        }
    }
}