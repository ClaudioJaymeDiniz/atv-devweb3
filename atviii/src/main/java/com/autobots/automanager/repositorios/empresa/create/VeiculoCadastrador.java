package com.autobots.automanager.repositorios.empresa.create;

import com.autobots.automanager.entitades.empresa.Veiculo;
import com.autobots.automanager.entitades.usuario.Usuario;
import com.autobots.automanager.modelos.StringVerificadorNulo;
import com.autobots.automanager.modelos.empresa.VeiculoDto;
import com.autobots.automanager.repositorios.usuario.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoCadastrador {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public void cadastrar(Usuario usuario, VeiculoDto veiculo) {

        if (veiculo != null) {
            Veiculo novoVeiculo = new Veiculo();

            if (!verificador.verificar(veiculo.getModelo())) {
                novoVeiculo.setModelo(veiculo.getModelo());
            }

            if (!verificador.verificar(veiculo.getPlaca())) {
                novoVeiculo.setPlaca(veiculo.getPlaca());
            }

            novoVeiculo.setTipo(veiculo.getTipo());
            novoVeiculo.setProprietario(usuario);
            usuario.getVeiculos().add(novoVeiculo);

            usuarioRepositorio.save(usuario);
        }
    }

    public void cadastrar(Usuario usuario, List<VeiculoDto> veiculosNovos) {

        for (VeiculoDto veiculo : veiculosNovos) {
            cadastrar(usuario, veiculo);
        }
    }
}