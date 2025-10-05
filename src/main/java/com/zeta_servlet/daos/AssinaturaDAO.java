package com.zeta_servlet.daos;

import com.zeta_servlet.daos.JDBC.Conexao;
import ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.model.Assinatura;
import com.zeta_servlet.CRUD.CRUD;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssinaturaDAO extends CRUD{

    public int inserir(Assinatura assinatura) {
        Connection conn = null;
        Conexao conexao = new Conexao();
        try {

            conn = conexao.conectar(); // abre a conexão com o banco
            String consulta = "insert into assinatura(tp_plano, benef_qtd_cursos, benef_desc_plno, preco_fixo, preco_qtd_produtores) values(?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(consulta);
            //Setando valores dos parametros
            pstmt.setString(1, assinatura.getTpPlano());
            pstmt.setInt(2, assinatura.getBenefQtdCursos());
            pstmt.setString(3, assinatura.getBenefDescPlno());
            pstmt.setBigDecimal(4, assinatura.getPrecoFixo());
            pstmt.setBigDecimal(5, assinatura.getPrecoQtdProdutores());



            if (pstmt.executeUpdate() >0){
                return 1;
            }
            return 0;
        }

        catch (SQLException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException | IllegalStateException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
        }
        catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
        }
        finally {
            conexao.desconectar(conn);
        }
    }

    public int updatePrecoQtdProdutores(Assinatura assinatura, BigDecimal money) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            PreparedStatement pstm = conn.prepareStatement("UPDATE assinatura SET preco_qtd_produtores = ? WHERE id = ?;");
            pstm.setBigDecimal(1, money);
            pstm.setInt(2, assinatura.getId());
            if (pstm.executeUpdate()>0){
                return 1;

            }  return 0;
        }
        catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
        }
        finally {
            conexao.desconectar(conn);
        }
    }

    public int updateTp_Plano(Assinatura assinatura, String tp_plano) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            PreparedStatement pstm = conn.prepareStatement("UPDATE assinatura SET tp_plano = ? WHERE id = ?;");
            pstm.setString(1, tp_plano);
            pstm.setInt(2, assinatura.getId());
            if (pstm.executeUpdate()>0){
                return 1;

            }  return 0;
        }
        catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
        }
        finally {
            conexao.desconectar(conn);
        }
    }

    public int updateBnfQtdCurso(Assinatura assinatura, int cursos) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            PreparedStatement pstm = conn.prepareStatement("UPDATE assinatura SET benef_qtd_cursos = ? WHERE id = ?;");
            pstm.setInt(1, cursos);
            pstm.setInt(2, assinatura.getId());
            if (pstm.executeUpdate()>0){
                return 1;

            }  return 0;
        }
        catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
        }
        finally {
            conexao.desconectar(conn);
        }
    }

    public int updatebenefDescPlno(Assinatura assinatura, String desc) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            PreparedStatement pstm = conn.prepareStatement("UPDATE assinatura SET benef_desc_plno = ? WHERE id = ?;");
            pstm.setString(1, desc);
            pstm.setInt(2, assinatura.getId());
            if (pstm.executeUpdate()>0){
                return 1;

            }  return 0;
        }
        catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
        }
        finally {
            conexao.desconectar(conn);
        }
    }

    public int updatePreco_fixo(Assinatura assinatura, BigDecimal money) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            PreparedStatement pstm = conn.prepareStatement("UPDATE assinatura SET preco_fixo = ? WHERE id = ?;");
            pstm.setBigDecimal(1, money);
            pstm.setInt(2, assinatura.getId());
            if (pstm.executeUpdate()>0){
                return 1;

            }  return 0;
        }
        catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
        }
        finally {
            conexao.desconectar(conn);
        }
    }




    public boolean remover(int id) {return super.remover(id, "Assinatura");}

    public List<Assinatura> buscar() {
        //query
        List<Assinatura> liASS = new ArrayList<>();
        ResultSet rset = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {

            rset = buscarR("assinatura");



                while (rset.next()) {
            Assinatura assinatura = new Assinatura(rset.getInt("id"),rset.getString("tp_plano"), rset.getInt("benef_qtd_cursos"), rset.getString("benef_desc_plno"), rset.getBigDecimal("preco_fixo"), rset.getBigDecimal("preco_qtd_produtores"));
                    liASS.add(assinatura);
                }


        }

        catch (SQLException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException | IllegalStateException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }

        catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        finally {
            conexao.desconectar(conn);
            return liASS;
        }
    }

    public List<Assinatura> buscarPorId(int id) {
        //query
        List<Assinatura> liASS = new ArrayList<>();
        ResultSet rset = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM assinatura WHERE id = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();




            while (rset.next()) {
                Assinatura assinatura = new Assinatura(rset.getInt("id"),rset.getString("tp_plano"), rset.getInt("benef_qtd_cursos"), rset.getString("benef_desc_plno"), rset.getBigDecimal("preco_fixo"), rset.getBigDecimal("preco_qtd_produtores"));
                liASS.add(assinatura);
            }

        }

        catch (SQLException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException | IllegalStateException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }

        catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        finally {
            conexao.desconectar(conn);
            return liASS;
        }
    }

    public List<Assinatura> buscarPorTpPlano(String plano) {
        //query
        List<Assinatura> liASS = new ArrayList<>();
        ResultSet rset = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM assinatura WHERE tp_plano = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setString(1, plano);
            rset = pstm.executeQuery();




            while (rset.next()) {
                Assinatura assinatura = new Assinatura(rset.getInt("id"),rset.getString("tp_plano"), rset.getInt("benef_qtd_cursos"), rset.getString("benef_desc_plno"), rset.getBigDecimal("preco_fixo"), rset.getBigDecimal("preco_qtd_produtores"));
                liASS.add(assinatura);
            }

        }

        catch (SQLException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException | IllegalStateException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }

        catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        finally {
            conexao.desconectar(conn);
            return liASS;
        }
    }

    public List<Assinatura> buscarPorQtdCursos(int qtd) {
        //query
        List<Assinatura> liASS = new ArrayList<>();
        ResultSet rset = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM assinatura WHERE benef_qtd_cursos = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setInt(1, qtd);
            rset = pstm.executeQuery();




            while (rset.next()) {
                Assinatura assinatura = new Assinatura(rset.getInt("id"),rset.getString("tp_plano"), rset.getInt("benef_qtd_cursos"), rset.getString("benef_desc_plno"), rset.getBigDecimal("preco_fixo"), rset.getBigDecimal("preco_qtd_produtores"));
                liASS.add(assinatura);
            }

        }

        catch (SQLException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException | IllegalStateException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }

        catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        finally {
            conexao.desconectar(conn);
            return liASS;
        }
    }


    public List<Assinatura> buscarPorFixo(BigDecimal money) {
        //query
        List<Assinatura> liASS = new ArrayList<>();
        ResultSet rset = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM assinatura WHERE preco_fixo = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setBigDecimal(1, money);
            rset = pstm.executeQuery();




            while (rset.next()) {
                Assinatura assinatura = new Assinatura(rset.getInt("id"),rset.getString("tp_plano"), rset.getInt("benef_qtd_cursos"), rset.getString("benef_desc_plno"), rset.getBigDecimal("preco_fixo"), rset.getBigDecimal("preco_qtd_produtores"));
                liASS.add(assinatura);
            }

        }

        catch (SQLException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException | IllegalStateException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }

        catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        finally {
            conexao.desconectar(conn);
            return liASS;
        }
    }


    public List<Assinatura> buscarPrecoQtdProdutores(BigDecimal money) {
        //query
        List<Assinatura> liASS = new ArrayList<>();
        ResultSet rset = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM assinatura WHERE preco_qtd_produtores = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setBigDecimal(1, money);
            rset = pstm.executeQuery();




            while (rset.next()) {
                Assinatura assinatura = new Assinatura(rset.getInt("id"),rset.getString("tp_plano"), rset.getInt("benef_qtd_cursos"), rset.getString("benef_desc_plno"), rset.getBigDecimal("preco_fixo"), rset.getBigDecimal("preco_qtd_produtores"));
                liASS.add(assinatura);
            }

        }
        catch (SQLException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException | IllegalStateException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }

        catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        finally {
            conexao.desconectar(conn);
            return liASS;
        }
    }
}
