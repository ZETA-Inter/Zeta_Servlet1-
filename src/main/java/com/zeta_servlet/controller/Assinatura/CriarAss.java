package com.zeta_servlet.controller.Assinatura;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.AssinaturaDAO;
import com.zeta_servlet.model.Assinatura;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(value = "/criarAss")
public class CriarAss extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            String plano = request.getParameter("plano");
            int benf = Integer.parseInt(request.getParameter("beneficiarios"));
            String descBef = request.getParameter("beneficiariosDesc");
            double preFixo = Double.parseDouble(request.getParameter("precoFixo"));
            BigDecimal moneyPrecoFixo = BigDecimal.valueOf(preFixo);
            double precoProd = Double.parseDouble(request.getParameter("precoProdutor"));
            BigDecimal moneyPrecoProd = BigDecimal.valueOf(precoProd);

            Assinatura assinatura = new Assinatura(0, plano, benf, descBef, moneyPrecoFixo, moneyPrecoProd);
            AssinaturaDAO assinaturaDao = new AssinaturaDAO();
            int i = assinaturaDao.inserir(assinatura);
           if (i==1){
               System.out.println(assinatura);
               request.setAttribute("option", 1);
               System.out.println(1);
               request.getRequestDispatcher("WEB-INF/resultadoCriarAss.jsp").forward(request, response);
           }
           else if (i==0){
               request.setAttribute("option", 0);
               System.out.println(0);
               request.getRequestDispatcher("WEB-INF/resultadoCriarAss.jsp").forward(request, response);
           }
           else{
               request.setAttribute("option", -1);
               System.out.println(-1);
               request.getRequestDispatcher("WEB-INF/resultadoCriarAss.jsp").forward(request, response);
           }


        }catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
            request.getRequestDispatcher("WEB-INF/resultadoCriarAss.jsp").forward(request, response);
        }
    }
}

