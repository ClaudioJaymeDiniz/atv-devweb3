package com.autobots.automanager.modelos.empresa;

import com.autobots.automanager.entitades.usuario.Endereco;
import lombok.Data;

@Data
public class EmpresaDto {
    private String razaoSocial;

    private String nomeFantasia;

    private Endereco endereco;
}