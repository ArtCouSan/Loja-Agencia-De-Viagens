package br.senac.tads3.pi03b.gruposete.servlets;

import br.senac.tads3.pi03b.gruposete.dao.FuncionarioDAO;
import br.senac.tads3.pi03b.gruposete.models.Funcionario;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

@WebServlet(name = "BuscaFuncionarioServlet", urlPatterns = {"/BuscaFuncionario"})
public class BuscaFuncionarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/BuscaFuncionario.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FuncionarioDAO dao = new FuncionarioDAO();
        String pesquisa = request.getParameter("pesquisa");

        HttpSession sessao = request.getSession();
        String identificacaoF = (String) sessao.getAttribute("tipo");
        String filial = (String) sessao.getAttribute("filial");

        try {

            if ("".equals(pesquisa.trim())) {

                if (identificacaoF.equalsIgnoreCase("Master") || identificacaoF.equalsIgnoreCase("Gerente_Informatica")) {

                    List<Funcionario> encontrados = dao.ListarFuncionario();
                    request.setAttribute("encontrados", encontrados);
                    request.setAttribute("pesquisa", pesquisa);

                } else {

                    List<Funcionario> encontrados = dao.ListarFuncionario(filial);
                    request.setAttribute("encontrados", encontrados);
                    request.setAttribute("pesquisa", pesquisa);

                }

            } else {

                if (identificacaoF.equalsIgnoreCase("Master") || identificacaoF.equalsIgnoreCase("Gerente_Informatica")) {

                    List<Funcionario> encontrados = dao.procurarFuncionario(pesquisa);
                    request.setAttribute("encontrados", encontrados);
                    request.setAttribute("pesquisa", pesquisa);

                } else {

                    List<Funcionario> encontrados = dao.procurarFuncionario(pesquisa, filial);
                    request.setAttribute("encontrados", encontrados);
                    request.setAttribute("pesquisa", pesquisa);

                }

            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ListaFuncionario.jsp");
            dispatcher.forward(request, response);

        } catch (IOException | ClassNotFoundException | SQLException ex) {

            Logger.getLogger(BuscaFuncionarioServlet.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
