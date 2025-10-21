package com.zeta_servlet.controller;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.AdmDAO;
import com.zeta_servlet.model.Adm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/loginADM")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            AdmDAO admDAO = new AdmDAO();
            String senha = request.getParameter("userpassword");
            String email = request.getParameter("email");
            List<Adm> adms = admDAO.buscarPorEmail(email);
            if (!adms.isEmpty()){
                if (adms.get(0).getEmail().equals(email) && adms.get(0).getSenha().equals(senha)) {
                    request.setAttribute("option", 1);
                    System.out.println(1);
                    request.getRequestDispatcher("CRUD.html").forward(request, response);
                } else {
                    request.setAttribute("option", 0);
                    System.out.println(0);
                    request.getRequestDispatcher("WEB-INF/resultadoLogin.jsp").forward(request, response);
                }
            }else{
                request.setAttribute("option", 0);
                System.out.println(0);
                request.getRequestDispatcher("WEB-INF/resultadoLogin.jsp").forward(request, response);
            }

        }catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
            System.out.println(-1);
            request.getRequestDispatcher("WEB-INF/resultadoLogin.jsp").forward(request, response);

        }
    }
}
