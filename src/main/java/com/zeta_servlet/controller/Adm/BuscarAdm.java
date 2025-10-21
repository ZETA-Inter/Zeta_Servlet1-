package com.zeta_servlet.controller.Adm;

import com.zeta_servlet.daos.AdmDAO;
import com.zeta_servlet.model.Adm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/buscaAdm")
public class BuscarAdm extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            AdmDAO admDAO = new AdmDAO();
            List<Adm> adms = admDAO.buscarId(id); // Sua DAO funciona e retorna a lista

            if (adms != null && !adms.isEmpty()) {
                Adm admEncontrado = adms.get(0);
                request.setAttribute("administrador", admEncontrado); // Passa o objeto
                request.setAttribute("idBusca", admEncontrado.getId());
            } else {
                request.setAttribute("erro", "Nenhum administrador encontrado para o ID: " + id);
            }

            request.getRequestDispatcher("WEB-INF/resultadoBuscaAdm.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("erro", "ID inválido. Forneça um número.");
            request.getRequestDispatcher("WEB-INF/resultadoBuscaAdm.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Isso ajuda a ver a exceção exata nos logs
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
