package com.autobots.automanager.repositorios.empresa.create;

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

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class VendaCadastrador {
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

    StringVerificadorNulo verificador = new StringVerificadorNulo();

    @SuppressWarnings("null")
    public void cadastrar(Set<Venda> vendas, VendaDto venda) {

        if (venda != null) {
            Venda novaVenda = new Venda();
            novaVenda.setCadastro(new Date());

            if (!verificador.verificar(venda.getIdentificacao())) {
                novaVenda.setIdentificacao(venda.getIdentificacao());
            }

            Usuario cliente = usuarioRepositorio.findById(venda.getCliente()).get();

            if (cliente != null) {
                novaVenda.setCliente(cliente);
                cliente.getVendas().add(novaVenda);
            }

            Usuario funcionario = usuarioRepositorio.findById(venda.getFuncionario()).get();

            if (funcionario != null) {
                novaVenda.setFuncionario(funcionario);
                funcionario.getVendas().add(novaVenda);
            }

            Veiculo veiculo = veiculoRepositorio.findById(venda.getVeiculo()).get();

            if (veiculo != null) {
                veiculo.getVendas().add(novaVenda);
                novaVenda.setVeiculo(veiculo);
            }

            for (long servicoId : venda.getServicos()) {
                Servico servico = servicoRepositorio.findById(servicoId).get();
                novaVenda.getServicos().add(servico);
            }

            for (long mercadoriaId : venda.getMercadorias()) {
                Mercadoria mercadoria = mercadoriaRepositorio.findById(mercadoriaId).get();
                novaVenda.getMercadorias().add(mercadoria);
            }
            vendaRepositorio.save(novaVenda);
            veiculoRepositorio.save(veiculo);
            usuarioRepositorio.save(cliente);
            usuarioRepositorio.save(funcionario);

            vendas.add(novaVenda);
        }
    }

    public void cadastrar(Set<Venda> vendas, List<VendaDto> vendasNovas) {

        for (VendaDto venda : vendasNovas) {
            cadastrar(vendas, venda);
        }
    }
}