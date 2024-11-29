package com.autobots.automanager.repositorios.empresa.select;

import com.autobots.automanager.entitades.empresa.Venda;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VendaSelecionador {
    public Venda selecionar(List<Venda> vendas, long id) {

        for (Venda venda : vendas) {
            if (venda.getId() == id) {
                return venda; // Return the sale as soon as it's found
            }
        }
        return null; // Return null if no sale is found
    }
}