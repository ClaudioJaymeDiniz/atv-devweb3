package com.autobots.automanager.repositorios.empresa.delete;

import com.autobots.automanager.entitades.empresa.Empresa;
import com.autobots.automanager.entitades.empresa.Mercadoria;
import com.autobots.automanager.entitades.empresa.Venda;
import com.autobots.automanager.repositorios.empresa.MercadoriaRepositorio;
import com.autobots.automanager.repositorios.empresa.VendaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MercadoriaExcluidor {

    @Autowired
    private VendaRepositorio vendaRepositorio;

    @Autowired
    private MercadoriaRepositorio mercadoriaRepositorio;

    public void excluir(Empresa empresa, Mercadoria mercadoria) {

        if (empresa != null && mercadoria != null) {
            empresa.getMercadorias().remove(mercadoria);
            List<Venda> vendas = vendaRepositorio.findAll();

            for (Venda venda : vendas) {
                venda.getMercadorias().removeIf
                        (merc -> merc != null && merc.getId().equals(mercadoria.getId()));
            }
            mercadoriaRepositorio.delete(mercadoria);
        }
    }

    public void excluir(Empresa empresa, List<Mercadoria> mercadorias) {

        if (empresa != null && mercadorias != null) {
            for (Mercadoria mercadoriaExcluida : mercadorias) {
                if (mercadoriaExcluida != null && mercadoriaExcluida.getId() != null) {
                    excluir(empresa, mercadoriaExcluida);
                }
            }
        }
    }
}