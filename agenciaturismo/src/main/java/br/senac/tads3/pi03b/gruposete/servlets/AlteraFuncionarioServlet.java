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

@WebServlet(name = "AlteraFuncionarioServlet", urlPatterns = {"/EditarFuncionario"})
public class AlteraFuncionarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        FuncionarioDAO dao = new FuncionarioDAO();
        String action = request.getParameter("action");
        if ("edit".equalsIgnoreCase(action)) {
            try {
                Funcionario funcionarios = dao.getFuncionarioById(id);
                request.setAttribute("funcionarios", funcionarios);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(AlteraFuncionarioServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/EditarFuncionario.jsp");
            dispatcher.forward(request, response);
        }
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
        String acesso = request.getParameter("acesso");
        int id = Integer.parseInt(request.getParameter("identificacao"));

        request.setAttribute("erroNome", service.validaNome(nome));
        request.setAttribute("erroNascimento", service.validaNascimento(data_nasc));
        request.setAttribute("erroRua", service.validaRua(rua));
        request.setAttribute("erroNumero", service.validaNumero(numero));
        request.setAttribute("erroCep", service.validaCep(cep));
        request.setAttribute("erroCidade", service.validaCidade(cidade));
        request.setAttribute("erroEmail", service.validaEmail(email));
        request.setAttribute("erroDepartamento", service.validaDepartamento(departamento));
        request.setAttribute("erroCargo", service.validaCargo(cargo));
        request.setAttribute("erroFilial", service.validaFilial(filial));
        request.setAttribute("erroAcesso", service.validaAcesso(acesso));

        Funcionario func = new Funcionario(nome.trim(), sexo.trim(), data_nasc.trim(),
                numero, cep.trim(), rua.trim(), estado.trim(), cidade.trim(), complemento.trim(),
                celular.trim(), telefone.trim(), email.trim(), true, cargo.trim(), filial.trim(), departamento.trim(), acesso.trim());
        func.setId(id);

        try {
            
            if (service.validaFuncionario(nome, data_nasc, rua, numero, cep, cidade, email, departamento, cargo, filial, acesso)) {
                
                try {
                    
                    Funcionario funcionarios = dao.getFuncionarioById(id);
                    request.setAttribute("funcionarios", funcionarios);
                    
                } catch (ClassNotFoundException | SQLException e) {
                    
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/EditarFuncionario.jsp");
                dispatcher.forward(request, response);
                
            } else {
                
                try {
                    dao.alterar(func);
                    HttpSession sessao = request.getSession();
                    int identificacaoF = (int) sessao.getAttribute("id_func");
                    relatorio.setId_func(identificacaoF);
                    relatorio.setMudanca("Alteração de funcionario efetuado!");
                    relatorioDAO.inserir(relatorio);
                    response.sendRedirect(request.getContextPath() + "/inicio");
                } catch (Exception ex) {
                    Logger.getLogger(AlteraFuncionarioServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlteraFuncionarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlteraFuncionarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
