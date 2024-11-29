package com.autobots.automanager.adicionador.usuario;

import com.autobots.automanager.adicionador.AdicionadorLink;
import com.autobots.automanager.controles.usuario.TelefoneControle;
import com.autobots.automanager.entitades.usuario.Telefone;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkTelefone implements AdicionadorLink<Telefone> {
    @Override
    public void adicionarLink(List<Telefone> lista) {

        for (Telefone telefone : lista) {
            long id = telefone.getId();

            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder.methodOn(TelefoneControle.class)
                            .obterTelefone(id)).withSelfRel();

            telefone.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(Telefone objeto) {

        Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(TelefoneControle.class).obterTelefones())
                            .withRel("telefones");

        objeto.add(linkProprio);
    }
}