package com.autobots.automanager.repositorios.usuario.select;

import com.autobots.automanager.entitades.usuario.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioSelecionador {
    public Usuario selecionar(List<Usuario> usuarios, long id) {

        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }
}
