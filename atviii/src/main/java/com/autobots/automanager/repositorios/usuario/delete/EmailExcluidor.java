package com.autobots.automanager.repositorios.usuario.delete;

import com.autobots.automanager.entitades.usuario.Email;
import com.autobots.automanager.entitades.usuario.Usuario;
import com.autobots.automanager.modelos.StringVerificadorNulo;
import org.slf4j.LoggerFactory;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class EmailExcluidor {
    private static final Logger logger = LoggerFactory.getLogger(EmailExcluidor.class);

    static {
        logger.info("Classe EmailExcluidor inicializada.");
    }

    private final StringVerificadorNulo verificador = new StringVerificadorNulo();


    public boolean excluir(Usuario usuario, Email email) {

        if (usuario == null || email == null) {
            logger.warn("Usuário ou email é nulo. Exclusão não realizada.");
            return false;
        }

        if (!verificador.verificar(email.getEndereco())) {
            if (usuario.getEmails().remove(email)) {
                logger.info("Email {} excluído com sucesso do usuário {}");
                return true;
            } else {

                logger.warn("Email {} não encontrado na lista de emails do usuário {}");
            }
        }
        return false;
    }

    public void excluir(Usuario usuario, List<Email> emails) {

        if (usuario == null || emails == null) {
            logger.warn("Usuário ou lista de emails é nula. Exclusão não realizada.");
            return;
        }

        for (Email emailExcluido : emails) {
            if (emailExcluido.getId() != null) {
                excluir(usuario, emailExcluido);
            }
        }
    }
}