package br.senac.tads3.pi03b.gruposete.servlets;

import br.senac.tads3.pi03b.gruposete.dao.FuncionarioDAO;
import br.senac.tads3.pi03b.gruposete.dao.RelatorioDAO;
import br.senac.tads3.pi03b.gruposete.models.Funcionario;
import br.senac.tads3.pi03b.gruposete.models.RelatorioMudancas;
import br.senac.tads3.pi03b.gruposete.services.FuncionarioService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CadastroFuncionarioServlet", urlPatterns = {"/CadastroFuncionario"})
public class CadastroFuncionarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/CadastroFuncionario.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FuncionarioService service = new FuncionarioService();
        FuncionarioDAO dao = new FuncionarioDAO();

        RelatorioDAO relatorioDAO = new RelatorioDAO();
        RelatorioMudancas relatorio = new RelatorioMudancas();

        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String sexo = request.getParameter("sexo");
        String data_nasc = request.getParameter("nascimento");
        String telefone = request.getParameter("telefone");
        String celular = request.getParameter("celular");
        String email = request.getParameter("email");
        int numero = Integer.parseInt(request.getParameter("numero"));
        String cep = request.getParameter("cep");
        String rua = request.getParameter("rua");
        String estado = request.getParameter("estado");
        String cidade = request.getParameter("cidade");
        String complemento = request.getParameter("complemento");
        String cargo = request.getParameter("cargo");
        String filial = request.getParameter("filial");
        String departamento = request.getParameter("departamento");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String acesso = request.getParameter("acesso");

        request.setAttribute("erroNome", service.validaNome(nome));
        try {
            request.setAttribute("erroCpf", service.validaCpf(cpf));
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CadastroFuncionarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("erroNascimento", service.validaNascimento(data_nasc));
        request.setAttribute("erroRua", service.validaRua(rua));
        request.setAttribute("erroNumero", service.validaNumero(numero));
        request.setAttribute("erroCep", service.validaCep(cep));
        request.setAttribute("erroCidade", service.validaCidade(cidade));
        request.setAttribute("erroEmail", service.validaEmail(email));
        request.setAttribute("erroDepartamento", service.validaDepartamento(departamento));
        request.setAttribute("erroCargo", service.validaCargo(cargo));
        request.setAttribute("erroFilial", service.validaFilial(filial));
        request.setAttribute("erroLogin", service.validaLogin(login));
        request.setAttribute("erroSenha", service.validaSenha(senha));
        request.setAttribute("erroAcesso", service.validaAcesso(acesso));

        Funcionario func = new Funcionario(nome.trim(), cpf.trim(), sexo.trim(), data_nasc.trim(),
                numero, cep.trim(), rua.trim(), estado.trim(), cidade.trim(), complemento.trim(),
                celular.trim(), telefone.trim(), email.trim(), true, cargo.trim(), filial.trim(), departamento.trim(), login.trim(), senha.trim(), acesso.trim());

        try {
            if (service.validaFuncionario(nome, cpf, data_nasc, rua, numero, cep, cidade, email, departamento, cargo, filial, login, senha, acesso)) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/CadastroFuncionario.jsp");
                dispatcher.forward(request, response);
            } else {
                try {
                    dao.inserir(func);
                    HttpSession sessao = request.getSession();
                    int identificacaoF = (int) sessao.getAttribute("id_func");
                    relatorio.setId_func(identificacaoF);
                    relatorio.setMudanca("Cadastro de funcionario efetuado!");
                    relatorioDAO.inserir(relatorio);
                    response.sendRedirect(request.getContextPath() + "/inicio");
                } catch (Exception ex) {
                    
                    Logger.getLogger(CadastroFuncionarioServlet.class.getName()).log(Level.SEVERE, null, ex);
                    
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CadastroFuncionarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
