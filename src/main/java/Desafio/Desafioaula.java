package Desafio;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.JsonHelper;
import model.Produto;

@WebServlet(urlPatterns = "/desafioaula")
public class Desafioaula extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private List<Produto> lista = new ArrayList<>();
	private JsonHelper jsonHelper = new JsonHelper();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String descricao = req.getParameter("descricao");

		double valor = Double.parseDouble(req.getParameter("valor"));

		int id = lista.size() + 1;

		Produto pro = new Produto(id, descricao, valor);

		lista.add(pro);

		try {
			resp.getWriter().println(jsonHelper.gerarJson(pro));

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String json;
		if (req.getParameter("op").equals("soma")) {
			double soma = 0;
			for (int i = 0; i < lista.size(); i++) {
				soma = soma + lista.get(i).getValor();
			}
			resp.getWriter().println("{Soma: " + soma + " }");
		} else {
			try {
				json = jsonHelper.gerarJsonLista(lista);
				resp.getWriter().print(json);
			} catch (IllegalArgumentException e) {

				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));

		String descricao = req.getParameter("descricao");

		Double valor = Double.parseDouble(req.getParameter("valor"));

		for (int j = 0; j < lista.size(); j++) {
			if (lista.get(j).getId() == id) {

				if (descricao != null) {
					lista.get(j).setDescricao(descricao);
				}
				if (valor != null) {
					lista.get(j).setValor(valor);
				}
			}
		}
		String json;

		try {
			json = jsonHelper.gerarJsonLista(lista);
			resp.getWriter().print(json);
		} catch (IllegalArgumentException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));

		for (int j = 0; j < lista.size(); j++) {
			if (lista.get(j).getId() == id) {
				lista.remove(j);
			}
		}
	}

}
