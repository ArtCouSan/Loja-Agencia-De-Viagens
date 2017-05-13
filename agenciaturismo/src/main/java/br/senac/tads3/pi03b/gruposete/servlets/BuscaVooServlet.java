package br.senac.tads3.pi03b.gruposete.servlets;

import br.senac.tads3.pi03b.gruposete.dao.VooDAO;
import br.senac.tads3.pi03b.gruposete.models.Voo;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "BuscaVooServlet", urlPatterns = {"/BuscaVoo"})
public class BuscaVooServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Buscar/BuscaVoo.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        boolean erro = false;

        String busca = request.getParameter("pesquisa");

        if (!erro) {

            try {

                List<Voo> encontrados;

                VooDAO dao = new VooDAO();

                encontrados = dao.procurarVoo(busca);

                HttpSession sessao = request.getSession();

                sessao.setAttribute("encontrados", encontrados);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Listar/ListaVoo.jsp");
                dispatcher.forward(request, response);

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(BuscaVooServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Listar/ListaVoo.jsp");
            dispatcher.forward(request, response);

        }

    }
}
