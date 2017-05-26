package br.senac.tads3.pi03b.gruposete.servlets;

import br.senac.tads3.pi03b.gruposete.dao.ClienteDAO;
import br.senac.tads3.pi03b.gruposete.models.Cliente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "AlteraClienteServlet", urlPatterns = {"/EditarCliente"})
public class AlteraClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        ClienteDAO dao = new ClienteDAO();
        String action = request.getParameter("action");
        if ("edit".equalsIgnoreCase(action)) {
            try {
                Cliente clientes = dao.getClienteById(id);
                request.setAttribute("clientes", clientes);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(AlteraClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Editar/EditarCliente.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            ClienteDAO dao = new ClienteDAO();
            boolean erro = true, verificarCPF;
            String nome = request.getParameter("nome");
            if (nome == null || nome.length() < 1) {
                erro = true;
                request.setAttribute("erroNome", true);
            }
            String cpf = request.getParameter("cpf");
            verificarCPF = dao.verificarCPF(cpf);
            if (cpf == null || !"   .   .   -  ".equals(cpf)) {
                erro = true;
                request.setAttribute("erroCpf", true);
            }
            String sexo = request.getParameter("sexo");
            if ("".equals(sexo)) {
                erro = true;
                request.setAttribute("erroSexo", true);
            }
            String data_nasc = request.getParameter("nascimento");
            if (data_nasc == null || !"  /  /    ".equals(data_nasc)) {
                erro = true;
                request.setAttribute("erroNascimento", true);
            }
            String telefone = request.getParameter("telefone");
            if (telefone == null || !"(  )    -    ".equals(telefone)) {
                erro = true;
                request.setAttribute("erroTelefone", true);
            }
            String celular = request.getParameter("celular");
            if (celular == null || !"(  )     -    ".equals(celular)) {
                erro = true;
                request.setAttribute("erroCelular", true);
            }
            String email = request.getParameter("email");
            if (email == null || !email.contains("@") && !email.contains(".com") || !email.contains(".com.br")) {
                erro = true;
                request.setAttribute("erroEmail", true);
            }
            int numero = Integer.parseInt(request.getParameter("numero"));
            if (numero <= 0) {
                erro = true;
                request.setAttribute("erroNumero", true);
            }
            String cep = request.getParameter("cep");
            if (cep == null || !"     -   ".equals(cep)) {
                erro = true;
                request.setAttribute("erroCep", true);
            }
            String rua = request.getParameter("rua");
            if (rua == null || rua.length() < 1) {
                erro = true;
                request.setAttribute("erroRua", true);
            }
            String bairro = request.getParameter("bairro");
            if (bairro == null || bairro.length() < 1) {
                erro = true;
                request.setAttribute("erroBairro", true);
            }
            String cidade = request.getParameter("cidade");
            if (cidade == null || cidade.length() < 1) {
                erro = true;
                request.setAttribute("erroCidade", true);
            }
            String complemento = request.getParameter("complemento");
            if (complemento == null || complemento.length() < 1) {
                erro = true;
                request.setAttribute("erroComplemento", true);
            }
            int id = Integer.parseInt(request.getParameter("identificacao"));
            if (!erro) {

                Cliente cliHumilde = new Cliente(nome, cpf, sexo, data_nasc, numero,
                        cep, rua, bairro, cidade, complemento, celular,
                        telefone, email, true);
                cliHumilde.setId_cliente(id);
                try {

                    dao.alterar(cliHumilde);
                    HttpSession sessao = request.getSession();
                    sessao.setAttribute("editarCliente", cliHumilde);
                    response.sendRedirect("index.jsp");

                } catch (IOException ex) {

                    Logger.getLogger(AlteraClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(AlteraClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Editar/EditarCliente.jsp");
                dispatcher.forward(request, response);
            }

        } catch (SQLException | ClassNotFoundException ex) {

            Logger.getLogger(AlteraClienteServlet.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
