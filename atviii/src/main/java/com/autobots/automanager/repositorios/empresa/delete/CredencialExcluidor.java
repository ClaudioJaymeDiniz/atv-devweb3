package com.autobots.automanager.repositorios.empresa.delete;

import com.autobots.automanager.entitades.Credencial;
import com.autobots.automanager.entitades.usuario.Usuario;

import java.util.List;

public class CredencialExcluidor {
    public void excluir(Credencial credencial, Credencial credencialExcluida) {

        if (credencialExcluida != null && credencial.getId() != null && credencial.getId().equals(credencialExcluida.getId())) {
            credencial.setInativo(true);
        }
    }

    public void excluir(Usuario usuario, List<Credencial> credenciaisExcluidas) {

        if (usuario != null && credenciaisExcluidas != null) {
            for (Credencial credencial : usuario.getCredenciais()) {
                credenciaisExcluidas.stream()
                        .filter(credencialExcluida -> credencialExcluida.getId() != null)

                        .forEach(credencialExcluida -> excluir(credencial, credencialExcluida));
            }
        }
    }
}