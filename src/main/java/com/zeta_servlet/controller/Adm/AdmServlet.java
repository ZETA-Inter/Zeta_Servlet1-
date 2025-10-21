package com.zeta_servlet.controller.Adm;

import com.zeta_servlet.daos.AdmDAO;
import com.zeta_servlet.daos.JDBC.Conexao;
import com.zeta_servlet.model.Adm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/AdmServlet")
public class AdmServlet extends HttpServlet {
    @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String action = request.getParameter("action");
            String valor = request.getParameter("valor");

            Conexao conexao = new Conexao();

            Connection conn = null;

            try {
                conn = conexao.conectar();

                switch (action){
                    case "buscarPorId":

                        int id = Integer.parseInt(valor);
                        AdmDAO admDaoBID = new AdmDAO();

                        List<Adm> lista = admDaoBID.buscarId(id);

                        request.setAttribute("adms", lista);
                        request.getRequestDispatcher("/WEB-INF/Adm/resultadoBuscarIDAdm.jsp").forward(request, response);
                        break;

                    case "buscarPorEmail":

                        AdmDAO admDaoE = new AdmDAO();

                        List<Adm> listaE = admDaoE.buscarPorEmail(valor);

                        request.setAttribute("adms", listaE);
                        request.getRequestDispatcher("/WEB-INF/Adm/resultadoBuscarEAdm.jsp").forward(request, response);
                        break;
                }
            }

            catch (Exception e){
                request.setAttribute("mensagem", "Erro " + e.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }

            finally {
                conexao.desconectar(conn);
            }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        Conexao conexao = new Conexao();


        Connection conn = null;

        try {
            conn = conexao.conectar();

            switch (action){
                case "inserirAdm":
                    String email = request.getParameter("email");
                    String senha = request.getParameter("senha");

                    Adm admNovo = new Adm(email, senha);

                    AdmDAO admDao = new AdmDAO();
                    admDao.inserir(admNovo);

                    request.setAttribute("mensagem", "Adm inserido com sucesso");
                    request.setAttribute("AdmInserido", admNovo);
                    request.getRequestDispatcher("/WEB-INF/Adm/resultadoInsercaoAdm.jsp").forward(request, response);
                    break;

                case "updateEmail":
                     int idE = Integer.parseInt(request.getParameter("id"));
                     String Email = request.getParameter("email");

                     AdmDAO admDaoUE = new AdmDAO();

                     admDaoUE.updateEmail(admDaoUE.buscarId(idE).get(0), Email);

                     request.setAttribute("mensagem", "Email alterado com sucesso");
                     request.getRequestDispatcher("/WEB-INF/Adm/resultadoUpdateEmailAdm.jsp").forward(request, response);
                     break;

                case "updateSenha":
                    int idS = Integer.parseInt(request.getParameter("id"));
                    String Senha = request.getParameter("senha");

                    AdmDAO admDaoUS = new AdmDAO();

                    admDaoUS.updateSenha(admDaoUS.buscarId(idS).get(0), Senha);


                    request.setAttribute("mensagem", "Senha alterada com sucesso");
                    request.getRequestDispatcher("/WEB-INF/Adm/resultadoUpdateSenhaAdm.jsp").forward(request, response);
                    break;

                case "removerAdm":
                     int id = Integer.parseInt(request.getParameter("id"));

                     AdmDAO admDaoR = new AdmDAO();

                     admDaoR.remover(id);

                     request.setAttribute("mensagem", "Adm removido com sucesso");
                     request.getRequestDispatcher("/WEB-INF/Adm/resultadoRemoverAdm.jsp").forward(request, response);
                     break;

                default:
                    request.setAttribute("mensagem", "Ação inválida");
                    request.getRequestDispatcher("erro.jsp").forward(request,response);
                    break;
            }

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

