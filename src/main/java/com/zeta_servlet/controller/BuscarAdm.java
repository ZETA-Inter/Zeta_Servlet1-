package com.zeta_servlet.controller;

import com.zeta_servlet.daos.AdmDAO;
import com.zeta_servlet.model.Adm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/buscaAdm")
public class BuscarAdm extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
                    try {
            AdmDAO admDAO = new AdmDAO();

            String acao = request.getParameter("acao");
            List<Adm> adms;
            if ("filtro".equals(acao)) {
                String idS = request.getParameter("id");
                String email = request.getParameter("email");
                if (email != null && !email.trim().isEmpty()) {
                    adms = admDAO.buscarPorEmail(email);
                    System.out.println(adms);
                    request.setAttribute("list", adms);
                    if (adms.isEmpty()) {
                        request.getRequestDispatcher("WEB-INF/erroBuscaAdm.jsp").forward(request, response);

                    }
                } else if (idS != null) {
                    int id = Integer.parseInt(idS);
                    adms = admDAO.buscarId(id);
                    request.setAttribute("list", adms);
                    System.out.println(adms);
                    if (adms.isEmpty()) {
                        request.getRequestDispatcher("WEB-INF/erroBuscaAdm.jsp").forward(request, response);

                    }

                } else {
                    request.getRequestDispatcher("WEB-INF/erroBuscaAdm.jsp").forward(request, response);

                }
                request.getRequestDispatcher("WEB-INF/resultadoBuscaAdm.jsp").forward(request, response);
            }

            else if ("geral".equals(acao)) {
                adms=admDAO.buscar();
                request.setAttribute("list", adms);
                System.out.println(adms);
                request.getRequestDispatcher("WEB-INF/resultadoBuscaAdm.jsp").forward(request, response);
            }


        }catch (Exception e){
            request.setAttribute("MensagemErro", "Ocorreu um erro, tente novamente");
            request.getRequestDispatcher("WEB-INF/erroBuscaAdm.jsp").forward(request, response);

        }
    }
}