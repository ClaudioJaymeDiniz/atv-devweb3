package com.autobots.automanager.repositorios.usuario.update;

import com.autobots.automanager.entitades.usuario.Usuario;
import com.autobots.automanager.modelos.StringVerificadorNulo;
import com.autobots.automanager.modelos.usuario.UsuarioDto;
import org.springframework.stereotype.Service;

@Service
public class UsuarioAtualizador {

    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    private void atualizarDados(Usuario usuario, UsuarioDto usuarioDto) {

        if (usuarioDto == null) {
            return;
        }
        if (!verificador.verificar(usuarioDto.getNome())) {
            usuario.setNome(usuarioDto.getNome());
        }

        if (!verificador.verificar(usuarioDto.getNomeSocial())) {
            usuario.setNomeSocial(usuarioDto.getNomeSocial());
        }

        if (usuarioDto.getPerfis() != null) {
            usuario.setPerfis(usuarioDto.getPerfis());
        }
    }

    public void atualizar(Usuario usuario, UsuarioDto usuarioDto) {
        if (usuarioDto != null) {
            atualizarDados(usuario, usuarioDto);
        }
    }
}
