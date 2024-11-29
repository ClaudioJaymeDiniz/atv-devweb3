package com.autobots.automanager.modelos.usuario;

import com.autobots.automanager.entitades.usuario.*;
import com.autobots.automanager.enumeracoes.PerfilUsuario;
import lombok.Data;

import java.util.Set;

@Data
public class UsuarioDto {
    private Long id;

    private String nome;

    private String nomeSocial;

    private Set<PerfilUsuario> perfis;

    private Endereco endereco;
}
