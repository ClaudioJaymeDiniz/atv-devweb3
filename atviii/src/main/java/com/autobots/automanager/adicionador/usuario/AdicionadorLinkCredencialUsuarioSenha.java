package com.autobots.automanager.adicionador.usuario;

import com.autobots.automanager.adicionador.AdicionadorLink;
import com.autobots.automanager.controles.empresa.CredencialControle;
import com.autobots.automanager.entitades.usuario.CredencialUsuarioSenha;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkCredencialUsuarioSenha implements AdicionadorLink<CredencialUsuarioSenha> {

    @Override
    public void adicionarLink(List<CredencialUsuarioSenha> lista) {

        for (CredencialUsuarioSenha credencial : lista) {
            long id = credencial.getId();

            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder.methodOn(CredencialControle.class)
                            .obterCredencialUsuarioSenha(id)).withSelfRel();

            credencial.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(CredencialUsuarioSenha objeto) {

        Link linkProprio = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                        .methodOn(CredencialControle.class).obterCredenciaisUsuarioSenha())
                            .withRel("credenciais");

        objeto.add(linkProprio);
    }
}