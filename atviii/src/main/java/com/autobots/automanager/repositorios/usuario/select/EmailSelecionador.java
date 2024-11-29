package com.autobots.automanager.repositorios.usuario.select;

import com.autobots.automanager.entitades.usuario.Email;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailSelecionador {
    public Email selecionar(List<Email> emails, long id) {

        for (Email email : emails) {
            if (email.getId() == id) {
                return email;
            }
        }
        return null;
    }
}