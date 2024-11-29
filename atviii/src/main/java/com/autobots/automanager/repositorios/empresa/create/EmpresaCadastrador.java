package com.autobots.automanager.repositorios.empresa.create;

import com.autobots.automanager.entitades.empresa.Empresa;
import com.autobots.automanager.modelos.StringVerificadorNulo;
import com.autobots.automanager.modelos.empresa.EmpresaDto;
import com.autobots.automanager.repositorios.usuario.create.EnderecoCadastrador;

import java.util.List;

public class EmpresaCadastrador {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public Empresa cadastrar(EmpresaDto empresa) {

        Empresa novaEmpresa = new Empresa();
        if (empresa != null) {
            if (!verificador.verificar(empresa.getRazaoSocial())) {
                novaEmpresa.setRazaoSocial(empresa.getRazaoSocial());
            }

            if (!verificador.verificar(empresa.getNomeFantasia())) {
                novaEmpresa.setNomeFantasia(empresa.getNomeFantasia());
            }

            EnderecoCadastrador enderecoCadastrador = new EnderecoCadastrador();
            enderecoCadastrador.cadastrarEmp(novaEmpresa, empresa.getEndereco());
        }
        return novaEmpresa;
    }

    public void cadastrar(List<EmpresaDto> empresasNovas) {

        for (EmpresaDto empresa : empresasNovas) {
            cadastrar(empresa);
        }
    }
}