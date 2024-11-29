package com.autobots.automanager.adicionador.empresa;

import com.autobots.automanager.adicionador.AdicionadorLink;
import com.autobots.automanager.controles.empresa.CredencialControle;
import com.autobots.automanager.entitades.empresa.CredencialCodigoBarra;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkCredencialCodigoBarra implements AdicionadorLink<CredencialCodigoBarra> {

    @Override
    public void adicionarLink(List<CredencialCodigoBarra> lista) {

        for (CredencialCodigoBarra credencial : lista) {

            long id = credencial.getId();

            Link linkProprio = WebMvcLinkBuilder

                    .linkTo(WebMvcLinkBuilder

                            .methodOn(CredencialControle.class)

                            .obterCredencialCodigoBarra(id))

                    .withSelfRel();


            credencial.add(linkProprio);

        }
    }

    @Override
    public void adicionarLink(CredencialCodigoBarra objeto) {

        Link linkProprio = WebMvcLinkBuilder

                .linkTo(WebMvcLinkBuilder

                        .methodOn(CredencialControle.class)

                        .obterCredenciaisCodigoBarra())

                .withRel("credenciais");

        objeto.add(linkProprio);

    }
}
