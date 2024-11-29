package com.autobots.automanager.adicionador.empresa;

import com.autobots.automanager.adicionador.AdicionadorLink;
import com.autobots.automanager.adicionador.usuario.AdicionadorLinkTelefone;
import com.autobots.automanager.adicionador.usuario.AdicionadorLinkUsuario;
import com.autobots.automanager.controles.empresa.EmpresaControle;
import com.autobots.automanager.entitades.empresa.Empresa;
import com.autobots.automanager.entitades.empresa.Servico;
import com.autobots.automanager.entitades.empresa.Venda;
import com.autobots.automanager.entitades.usuario.Telefone;
import com.autobots.automanager.entitades.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdicionadorLinkEmpresa implements AdicionadorLink<Empresa> {

    @Autowired
    private AdicionadorLinkTelefone adicionadorLinkTelefone;

    @Autowired
    private AdicionadorLinkUsuario adicionadorLinkUsuario;

    @Autowired
    private AdicionadorLinkServico adicionadorLinkServico;

    @Autowired
    private AdicionadorLinkVenda adicionadorLinkVenda;

    @Override
    public void adicionarLink(List<Empresa> lista) {

        for (Empresa empresa : lista) {

            long id = empresa.getId();

            Link linkProprio = WebMvcLinkBuilder

                    .linkTo(WebMvcLinkBuilder

                            .methodOn(EmpresaControle.class)

                            .obterEmpresa(id))

                    .withSelfRel();

            empresa.add(linkProprio);

            List<Telefone> telefones = empresa.getTelefones().stream().collect(Collectors.toList());

            adicionadorLinkTelefone.adicionarLink(telefones);

            List<Usuario> usuarios = empresa.getUsuarios().stream().collect(Collectors.toList());

            adicionadorLinkUsuario.adicionarLink(usuarios);

            List<Servico> servicos = empresa.getServicos().stream().collect(Collectors.toList());

            adicionadorLinkServico.adicionarLink(servicos);

            List<Venda> empresas = empresa.getVendas().stream().collect(Collectors.toList());

            adicionadorLinkVenda.adicionarLink(empresas);
        }
    }

    @Override
    public void adicionarLink(Empresa empresa) {

        Link linkProprio = WebMvcLinkBuilder

                .linkTo(WebMvcLinkBuilder

                        .methodOn(EmpresaControle.class)

                        .obterEmpresas())

                .withRel("empresas");

        empresa.add(linkProprio);

        List<Telefone> telefones = empresa.getTelefones().stream().collect(Collectors.toList());

        adicionadorLinkTelefone.adicionarLink(telefones);

        List<Usuario> usuarios = empresa.getUsuarios().stream().collect(Collectors.toList());

        adicionadorLinkUsuario.adicionarLink(usuarios);

    }
}