package com.zeta_servlet.controller.Assinatura;

import com.zeta_servlet.daos.*;
import com.zeta_servlet.daos.JDBC.Conexao;
import com.zeta_servlet.model.Adm;
import com.zeta_servlet.model.Assinatura;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

@WebServlet("/AssinaturaServlet")
public class AssinaturaServlet extends HttpServlet {
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
                    AssinaturaDAO AssinaturaDaoBID = new AssinaturaDAO();

                    List<Assinatura> lista = AssinaturaDaoBID.buscarPorId(id);

                    request.setAttribute("Assinaturas", lista);
                    request.getRequestDispatcher("/WEB-INF/Assinatura/resultadoBuscarIDAssinatura.jsp").forward(request, response);
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
                case "inserirAssinatura":
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
                        request.getRequestDispatcher("/WEB-INF/Assinatura/resultadoInsercaoAssinatura.jsp").forward(request, response);
                    }
                    else if (i==0){
                        request.setAttribute("option", 0);
                        System.out.println(0);
                        request.getRequestDispatcher("/WEB-INF/Assinatura/resultadoInsercaoAssinatura.jsp").forward(request, response);
                    }
                    else{
                        request.setAttribute("option", -1);
                        System.out.println(-1);
                        request.getRequestDispatcher("/WEB-INF/Assinatura/resultadoInsercaoAssinatura.jsp").forward(request, response);
                    }
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
                    AssinaturaDAO AssinaturaDao = new AssinaturaDAO();
                    boolean index = AssinaturaDao.remover(id);
                    if (index){
                        System.out.println(index);
                        request.setAttribute("option", 1);
                        request.getRequestDispatcher("/WEB-INF/Assinatura/resultadoRemoverAssinatura.jsp").forward(request, response);
                    }
                    else if (!index){
                        System.out.println(index);
                        request.setAttribute("option", 0);
                        request.getRequestDispatcher("/WEB-INF/Assinatura/resultadoRemoverAssinatura.jsp").forward(request, response);
                    }
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
