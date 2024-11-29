package com.autobots.automanager.modelos.endereco;

import com.autobots.automanager.entidades.Endereco;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EnderecoSelecionador {
    public Endereco selecionar(List<Endereco> enderecos, long id){
        for (Endereco endereco : enderecos){
            if (endereco.getId() == id){
                return endereco;
            }
        }
        return null;
    }
}
