package com.autobots.automanager.entitades.empresa;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.autobots.automanager.entitades.Credencial;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class CredencialCodigoBarra extends Credencial {
	@Column(nullable = false, unique = true)
	private long codigo;
}