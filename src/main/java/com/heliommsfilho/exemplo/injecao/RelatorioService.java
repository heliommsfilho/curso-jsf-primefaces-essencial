package com.heliommsfilho.exemplo.injecao;

import java.math.BigDecimal;

import javax.inject.Inject;

public class RelatorioService {

	@Inject
	private Pedidos pedidos;

	public BigDecimal totalPedidosMesAtual() {
		return pedidos.totalPedidosMesAtual();
	}
}
