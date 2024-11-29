package com.autobots.automanager.repositorios.usuario.delete;

import java.util.List;
import java.util.stream.Collectors;

import com.autobots.automanager.entitades.empresa.Empresa;
import com.autobots.automanager.entitades.empresa.Veiculo;
import com.autobots.automanager.entitades.empresa.Venda;
import com.autobots.automanager.entitades.usuario.Usuario;
import com.autobots.automanager.repositorios.empresa.EmpresaRepositorio;
import com.autobots.automanager.repositorios.empresa.VendaRepositorio;
import com.autobots.automanager.repositorios.empresa.delete.CredencialExcluidor;
import com.autobots.automanager.repositorios.usuario.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioExcluidor {

    private final DocumentoExcluidor documentoExcluidor = new DocumentoExcluidor();

    private final EmailExcluidor emailExcluidor = new EmailExcluidor();

    private final CredencialExcluidor credencialExcluidor = new CredencialExcluidor();

    private final TelefoneExcluidor telefoneExcluidor = new TelefoneExcluidor();

    @Autowired
    private EmpresaRepositorio empresaRepositorio;

    @Autowired
    private UsuarioRepositorio repositorio;

    @Autowired
    private VendaRepositorio vendaRepositorio;

    public void excluir(Usuario usuario) {

        if (usuario == null) {
            return;
        }

        List<Venda> vendas = vendaRepositorio.findAll();

        for (Venda venda : vendas) {
            if (venda.getCliente() != null && venda.getCliente().getId().equals(usuario.getId())) {
                venda.setCliente(null);

                vendaRepositorio.save(venda);
            }

            if (venda.getFuncionario() != null && venda.getFuncionario().getId().equals(usuario.getId())) {
                venda.setFuncionario(null);

                vendaRepositorio.save(venda);
            }
        }

        documentoExcluidor.excluir(usuario, usuario.getDocumentos().stream().collect(Collectors.toList()));

        emailExcluidor.excluir(usuario, usuario.getEmails().stream().collect(Collectors.toList()));

        credencialExcluidor.excluir(usuario, usuario.getCredenciais().stream().collect(Collectors.toList()));

        usuario.getCredenciais().clear();

        telefoneExcluidor.excluir(usuario, usuario.getTelefones().stream().collect(Collectors.toList()));


        for (Veiculo veiculo : usuario.getVeiculos()) {
            veiculo.setProprietario(null);
        }

        List<Empresa> empresas = empresaRepositorio.findAll();

        empresas.forEach(empresa ->
                empresa.getUsuarios().removeIf(user -> user.getId().equals(usuario.getId())));

        repositorio.delete(usuario);
    }
}