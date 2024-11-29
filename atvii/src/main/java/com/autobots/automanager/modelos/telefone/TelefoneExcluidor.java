package com.autobots.automanager.modelos.telefone;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelos.StringVerificadorNulo;

import java.util.List;

public class TelefoneExcluidor {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void excluir(Cliente cliente, Telefone telefone) {
        if (telefone != null &&
                !verificador.verificar(telefone.getDdd()) &&
                !verificador.verificar(telefone.getNumero())) {
            cliente.getTelefones().remove(telefone);
        }
    }
    public void excluir(Cliente cliente, List<Telefone> telefones) {
        if(telefones == null){
            telefones.stream().filter(telefone -> telefone.getId() != null)
                    .forEach(telefone -> excluir(cliente, telefone));
        }
    }
}
