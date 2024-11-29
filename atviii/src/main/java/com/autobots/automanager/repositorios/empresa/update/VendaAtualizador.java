package com.autobots.automanager.repositorios.empresa.update;

import com.autobots.automanager.entitades.empresa.Mercadoria;
import com.autobots.automanager.entitades.empresa.Servico;
import com.autobots.automanager.entitades.empresa.Veiculo;
import com.autobots.automanager.entitades.empresa.Venda;
import com.autobots.automanager.entitades.usuario.Usuario;
import com.autobots.automanager.modelos.StringVerificadorNulo;
import com.autobots.automanager.modelos.empresa.VendaDto;
import com.autobots.automanager.repositorios.empresa.MercadoriaRepositorio;
import com.autobots.automanager.repositorios.empresa.ServicoRepositorio;
import com.autobots.automanager.repositorios.empresa.VeiculoRepositorio;
import com.autobots.automanager.repositorios.empresa.VendaRepositorio;
import com.autobots.automanager.repositorios.usuario.UsuarioRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class VendaAtualizador {

    @Autowired
    private VendaRepositorio vendaRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private VeiculoRepositorio veiculoRepositorio;

    @Autowired
    private ServicoRepositorio servicoRepositorio;

    @Autowired
    private MercadoriaRepositorio mercadoriaRepositorio;

    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Venda venda, VendaDto atualizacao) {

        if (atualizacao == null || venda == null) {
            return;
        }

        if (!verificador.verificar(atualizacao.getIdentificacao())) {
            venda.setIdentificacao(atualizacao.getIdentificacao());
        }

        Optional<Usuario> clienteOpt = usuarioRepositorio.findById(atualizacao.getCliente());
        clienteOpt.ifPresent(venda::setCliente);


        Optional<Usuario> funcionarioOpt = usuarioRepositorio.findById(atualizacao.getFuncionario());
        funcionarioOpt.ifPresent(venda::setFuncionario);


        Optional<Veiculo> veiculoOpt = veiculoRepositorio.findById(atualizacao.getVeiculo());
        veiculoOpt.ifPresent(veiculo -> {veiculo.getVendas().add(venda);
            venda.setVeiculo(veiculo);});

        List<Servico> servicos = new ArrayList<>();

        for (long servicoId : atualizacao.getServicos()) {
            Servico servico = servicoRepositorio.findById(servicoId).orElse(null);

            if (servico != null) {
                servicos.add(servico);
            }
        }

        venda.setServicos((Set<Servico>) servicos);

        List<Mercadoria> mercadorias = new ArrayList<>();

        for (long mercadoriaId : atualizacao.getMercadorias()) {
            Mercadoria mercadoria = mercadoriaRepositorio.findById(mercadoriaId).orElse(null);

            if (mercadoria != null) {
                mercadorias.add(mercadoria);
            }
        }
        venda.setMercadorias((Set<Mercadoria>) mercadorias);

        vendaRepositorio.save(venda);
    }

    public void atualizar(Set<Venda> vendas, List<VendaDto> atualizacoes) {

        if (vendas == null || atualizacoes == null || vendas.isEmpty() || atualizacoes.isEmpty()) {
            return;
        }

        for (VendaDto atualizacao : atualizacoes) {
            if (atualizacao.getId() != null) {
                for (Venda venda : vendas) {
                    if (atualizacao.getId().equals(venda.getId())) {
                        atualizar(venda, atualizacao);
                        break;
                    }
                }
            }
        }
    }
}