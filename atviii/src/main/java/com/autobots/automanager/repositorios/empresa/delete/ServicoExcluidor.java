package com.autobots.automanager.repositorios.empresa.delete;

import com.autobots.automanager.entitades.empresa.Empresa;
import com.autobots.automanager.entitades.empresa.Servico;
import com.autobots.automanager.entitades.empresa.Venda;
import com.autobots.automanager.repositorios.empresa.ServicoRepositorio;
import com.autobots.automanager.repositorios.empresa.VendaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoExcluidor {

    @Autowired
    private VendaRepositorio vendaRepositorio;

    @Autowired
    private ServicoRepositorio servicoRepositorio;

    public void excluir(Empresa empresa, Servico servico) {

        if (empresa != null && servico != null) {

            empresa.getServicos().remove(servico);
            List<Venda> vendas = vendaRepositorio.findAll();

            for (Venda venda : vendas) {
                venda.getServicos().removeIf(svc -> svc != null && svc.getId().equals(servico.getId()));
            }
            servicoRepositorio.delete(servico);
        }
    }

    public void excluir(Empresa empresa, List<Servico> servicos) {

        if (empresa != null && servicos != null) {
            for (Servico servicoExcluido : servicos) {
                if (servicoExcluido != null && servicoExcluido.getId() != null) {
                    excluir(empresa, servicoExcluido);
                }
            }
        }
}
}