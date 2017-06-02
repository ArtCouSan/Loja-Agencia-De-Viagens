package br.senac.tads3.pi03b.gruposete.servlets;

import br.senac.tads3.pi03b.gruposete.dao.VooDAO;
import br.senac.tads3.pi03b.gruposete.models.Voo;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "CadastroVooServlet", urlPatterns = {"/CadastroVoo"})
public class CadastroVooServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/Cadastrar/CadastroVoo.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        VooDAO dao = new VooDAO();
        boolean erro = false;

        String origem = request.getParameter("origem");
        String destino = request.getParameter("destino");
        String data_ida = request.getParameter("data_ida");
        String data_volta = request.getParameter("data_volta");
        int quantidade_passagens;
        float preco = Float.parseFloat(request.getParameter("preco"));

        if (origem == null || origem.length() < 1) {
            erro = true;
            request.setAttribute("erroOrigem", true);
        }

        if (destino == null || destino.length() < 1) {
            erro = true;
            request.setAttribute("erroDestino", true);
        }

        if (data_ida == null || !"  /  /    ".equals(data_ida)) {
            erro = true;
            request.setAttribute("erroData_ida", true);
        }

        if (data_volta == null || !"  /  /    ".equals(data_volta)) {
            erro = true;
            request.setAttribute("erroData_volta", true);
        }

        try {
            quantidade_passagens = Integer.parseInt(request.getParameter("quantidade_passagens"));
//            if (quantidade_passagens < 1) {
//                erro = true;
//                request.setAttribute("erroQuantidade_passagens", true);
//            }
        } catch (NumberFormatException e) {
            quantidade_passagens = 0;
        }

        if (preco < 0) {
            erro = true;
            request.setAttribute("erroPreco", true);
        }

        if (erro == false) {
            Voo voo = new Voo(data_ida, data_volta, destino, origem, quantidade_passagens, preco, true);

            try {
                dao.inserir(voo);
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/Layout/index.jsp");
                dispatcher.forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(CadastroVooServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/Cadastrar/CadastroVoo.jsp");
            dispatcher.forward(request, response);
        }
    }
}
