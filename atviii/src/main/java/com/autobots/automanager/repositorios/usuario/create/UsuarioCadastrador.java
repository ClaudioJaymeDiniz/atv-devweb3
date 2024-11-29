package com.autobots.automanager.repositorios.usuario.create;

import com.autobots.automanager.entitades.usuario.Usuario;
import com.autobots.automanager.modelos.StringVerificadorNulo;
import com.autobots.automanager.modelos.usuario.UsuarioDto;

import java.util.List;

public class UsuarioCadastrador {

    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public Usuario cadastrar(UsuarioDto usuario) {

        Usuario novaUsuario = new Usuario();

        if (usuario != null) {
            if (!verificador.verificar(usuario.getNome())) {
                novaUsuario.setNome(usuario.getNome());
            }
            if (!verificador.verificar(usuario.getNomeSocial())) {
                novaUsuario.setNomeSocial(usuario.getNomeSocial());

            }
            EnderecoCadastrador enderecoCadastrador = new EnderecoCadastrador();
            enderecoCadastrador.cadastrarUser(novaUsuario, usuario.getEndereco());

            if(usuario.getPerfis() != null) {
                novaUsuario.setPerfis(usuario.getPerfis());
            }
        }
        return novaUsuario;
    }

    public void cadastrar(List<UsuarioDto> usuariosNovas) {

        for (UsuarioDto usuario : usuariosNovas) {
            cadastrar(usuario);
        }
    }
}