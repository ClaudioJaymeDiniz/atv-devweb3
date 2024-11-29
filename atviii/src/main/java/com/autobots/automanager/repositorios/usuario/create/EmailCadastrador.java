package com.autobots.automanager.repositorios.usuario.create;

import com.autobots.automanager.entitades.usuario.Email;
import com.autobots.automanager.entitades.usuario.Usuario;
import com.autobots.automanager.modelos.StringVerificadorNulo;

import java.util.List;

public class EmailCadastrador {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void cadastrar(Usuario usuario, Email email) {

        if (email != null) {
            Email novoEmail = new Email();

            if (!verificador.verificar(email.getEndereco())) {
                novoEmail.setEndereco(null);
            }
            usuario.getEmails().add(novoEmail);
        }
    }

    public void cadastrar(Usuario usuario, List<Email> emails) {

        for (Email Email : emails) {
            cadastrar(usuario, Email);
        }
    }
}