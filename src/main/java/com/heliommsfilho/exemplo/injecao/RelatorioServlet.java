package com.heliommsfilho.exemplo.injecao;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/relatorio")
public class RelatorioServlet extends HttpServlet {

	private static final long serialVersionUID = 8151765683270062296L;
	
	@Inject
	private RelatorioService relatorioService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println(relatorioService.totalPedidosMesAtual());
	}

}
