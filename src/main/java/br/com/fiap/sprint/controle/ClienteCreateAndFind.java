package br.com.fiap.sprint.controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.sprint.dao.ClienteDao;
import br.com.fiap.sprint.modelo.Cliente;


@WebServlet("/CreateAndFind")
public class ClienteCreateAndFind extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ClienteCreateAndFind() {
        super();

    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pesquisa = request.getParameter("pesquisa");
		
		if(pesquisa == null) {
			pesquisa="";
		}
		
		List<Cliente> clientes;
		try {
			clientes = ClienteDao.find(pesquisa);
			request.setAttribute("clientes", clientes);
			RequestDispatcher resquesDispatcher = request.getRequestDispatcher("lista.jsp");
			resquesDispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = new Cliente();
		
		cliente.setNome(request.getParameter("nome"));
		cliente.setSobrenome(request.getParameter("sobrenome"));
		cliente.setEmail(request.getParameter("email"));
		cliente.setTelefone(request.getParameter("telefone"));
		cliente.setCpf(request.getParameter("cpf"));

		
		ClienteDao.create(cliente);
		
		doGet(request, response);
	}

}
