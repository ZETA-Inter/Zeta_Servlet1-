package com.zeta_servlet.controller;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.AssinaturaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(value = "/removerAss")
public class RemoverAss extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            AssinaturaDAO assinaturaDao = new AssinaturaDAO();
            boolean i = assinaturaDao.remover(id);
            if (i){
                System.out.println(i);
                request.setAttribute("option", 1);
                request.getRequestDispatcher("WEB-INF/resultadoRemoverAss.jsp").forward(request, response);
            }
            else if (!i){
                System.out.println(i);
                request.setAttribute("option", 0);
                request.getRequestDispatcher("WEB-INF/resultadoRemoverAss.jsp").forward(request, response);
            }

        }catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
            request.getRequestDispatcher("WEB-INF/resultadoRemoverAss.jsp").forward(request, response);
        }
    }
}

