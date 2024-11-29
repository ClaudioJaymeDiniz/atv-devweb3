package com.autobots.automanager.adicionador.empresa;

import com.autobots.automanager.adicionador.AdicionadorLink;
import com.autobots.automanager.controles.empresa.ServicoControle;
import com.autobots.automanager.entitades.empresa.Servico;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkServico implements AdicionadorLink<Servico> {

    @Override
    public void adicionarLink(List<Servico> lista) {

        for (Servico servico : lista) {

            long id = servico.getId();

            if (!servico.hasLink("self")) {

                Link linkProprio = WebMvcLinkBuilder

                        .linkTo(WebMvcLinkBuilder

                                .methodOn(ServicoControle.class)

                                .obterServico(id))

                        .withSelfRel();

                servico.add(linkProprio);

            }
        }
    }

    @Override
    public void adicionarLink(Servico servico) {

        if (!servico.hasLink("servicos")) {

            Link linkProprio = WebMvcLinkBuilder

                    .linkTo(WebMvcLinkBuilder

                            .methodOn(ServicoControle.class)

                            .obterServicos())

                    .withRel("servicos");

            servico.add(linkProprio);

        }
    }
}