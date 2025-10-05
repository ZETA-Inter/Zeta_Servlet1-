package com.zeta_servlet.CRUD;

import com.zeta_servlet.daos.JDBC.Conexao;
import ExceptionHandler.ExceptionHandler;

import java.sql.*;

public abstract class CRUD {


    // outros metodos
    public boolean remover(int id, String table) {
//        estabelecendo conexão com BD
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String remover = "DELETE FROM "+table+" WHERE id = ?";
            PreparedStatement pstm = conn.prepareStatement(remover);
            pstm.setInt(1, id);
            if (pstm.executeUpdate()>0){
                return true;
            }
            return false;
        } catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return false;
        }finally {
            conexao.desconectar(conn);
        }
    }


    public ResultSet buscarPorIdR(int id, String table) {
        ResultSet rset=null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {

            Statement stmt = conn.createStatement();
            String busca = "SELECT * FROM "+table+" WHERE id = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setInt(1,id);
            rset=pstm.executeQuery();

        }

        catch (SQLException se) {


            se.printStackTrace();
            System.out.println("Erro ao burcar dados");
            return null;
        }
        finally {
            conexao.desconectar(conn);
        }
        return rset;
    }


    public ResultSet buscarR(String table) {
        ResultSet rset=null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            Statement stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT * FROM "+table+" ORDER BY id");
        } catch (SQLException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException | IllegalStateException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        finally {
            conexao.desconectar(conn);
        }
        return rset;
    }

}
