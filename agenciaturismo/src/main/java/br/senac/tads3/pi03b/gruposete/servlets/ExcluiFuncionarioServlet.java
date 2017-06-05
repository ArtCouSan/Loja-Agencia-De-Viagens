package br.senac.tads3.pi03b.gruposete.servlets;

import br.senac.tads3.pi03b.gruposete.dao.FuncionarioDAO;
import br.senac.tads3.pi03b.gruposete.dao.RelatorioDAO;
import br.senac.tads3.pi03b.gruposete.models.RelatorioMudancas;
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

@WebServlet("/ExcluiFuncionarioServlet")
public class ExcluiFuncionarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        FuncionarioDAO query = new FuncionarioDAO();
        
        if ("delete".equalsIgnoreCase(action)) {
            String cpf = request.getParameter("cpf");
            try {
                
                query.excluir(cpf);
                RelatorioDAO relatorioDAO = new RelatorioDAO();
                RelatorioMudancas relatorio = new RelatorioMudancas();
                relatorio.setId_funcionario(1);
                relatorio.setMudanca("Exclusão de funcionario efetuada!");
                relatorioDAO.inserir(relatorio);
                
            } catch (ClassNotFoundException | SQLException ex) {
                
                Logger.getLogger(ExcluiFuncionarioServlet.class.getName()).log(Level.SEVERE, null, ex);
                
            }
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ListaFuncionario.jsp");
            dispatcher.forward(request, response);
            
        }
    }
}
