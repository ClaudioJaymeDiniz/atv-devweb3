package com.autobots.automanager.repositorios.empresa.select;

import com.autobots.automanager.entitades.empresa.Empresa;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmpresaSelecionador {
    public Empresa selecionar(List<Empresa> empresas, long id) {

        for (Empresa empresa : empresas) {
            if (empresa.getId() == id) {
                return empresa;
            }
        }

        return null;
    }
}