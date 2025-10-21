package com.zeta_servlet.controller.Adm;

import com.zeta_servlet.daos.AdmDAO;
import com.zeta_servlet.daos.JDBC.Conexao;
import com.zeta_servlet.model.Adm;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/BuscarGeralAdmServlet")
public class BuscarGeralAdmServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Conexao conexao = new Conexao();

        Connection conn = null;

        try {
            conn = conexao.conectar();

            AdmDAO admDao = new AdmDAO();

            List<Adm> lista = admDao.buscar();

            request.setAttribute("adms", lista);
            request.getRequestDispatcher("/Adm/consultarAdm.jsp").forward(request, response);

        }

        catch (Exception e){
            request.setAttribute("mensagem", "Erro " + e.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }

        finally {
            conexao.desconectar(conn);
        }


    }
}
