package com.autobots.automanager.repositorios.empresa.delete;

import com.autobots.automanager.entitades.empresa.Venda;
import com.autobots.automanager.entitades.usuario.Usuario;
import com.autobots.automanager.modelos.empresa.VendaDto;
import com.autobots.automanager.repositorios.empresa.VendaRepositorio;
import com.autobots.automanager.repositorios.usuario.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class VendaExcluidor {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private VendaRepositorio vendaRepositorio;

    public void excluir(Set<Venda> vendas, VendaDto vendaDto) {

        if (vendaDto != null && vendaDto.getId() != null) {
            Optional<Venda> optionalVenda = vendaRepositorio.findById(vendaDto.getId());

            if (optionalVenda.isPresent()) {
                Venda venda = optionalVenda.get();

                if (venda.getCliente() != null) {
                    Usuario cliente = venda.getCliente();
                    cliente.getVendas().remove(venda);

                    usuarioRepositorio.save(cliente);
                }

                if (venda.getFuncionario() != null) {
                    Usuario funcionario = venda.getFuncionario();
                    funcionario.getVendas().remove(venda);

                    usuarioRepositorio.save(funcionario);
                }

                if (venda.getVeiculo() != null) {
                    venda.getVeiculo().getVendas().remove(venda);
                }
                vendas.remove(venda);

                vendaRepositorio.delete(venda);
            }
        }
    }

    public void excluir(Set<Venda> vendas, List<VendaDto> vendasExcluidas) {

        for (VendaDto vendaExcluida : vendasExcluidas) {
            if (vendaExcluida.getId() != null) {
                excluir(vendas, vendaExcluida);
            }
        }
    }
}