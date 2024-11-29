package com.autobots.automanager.repositorios.empresa.delete;

import com.autobots.automanager.entitades.empresa.Veiculo;
import com.autobots.automanager.entitades.empresa.Venda;
import com.autobots.automanager.entitades.usuario.Usuario;
import com.autobots.automanager.repositorios.usuario.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoExcluidor {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public void excluir(Usuario usuario, Veiculo veiculo) {

        if (usuario != null && veiculo != null) {
            usuario.getVeiculos().remove(veiculo);
            usuarioRepositorio.save(usuario);

            if (veiculo.getVendas() != null) {
                for (Venda venda : veiculo.getVendas()) {
                    venda.setVeiculo(null);
                }
            }
        }
    }

    public void excluir(Usuario usuario, List<Veiculo> veiculos) {

        if (usuario != null && veiculos != null) {
            for (Veiculo veiculoExcluido : veiculos) {
                if (veiculoExcluido != null && veiculoExcluido.getId() != null) {
                    excluir(usuario, veiculoExcluido);
                }
            }
        }
    }
}
