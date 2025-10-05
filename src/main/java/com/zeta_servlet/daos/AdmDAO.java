package com.zeta_servlet.daos;
import com.zeta_servlet.CRUD.CRUD;
import com.zeta_servlet.daos.JDBC.Conexao;
import ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.model.Adm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdmDAO extends CRUD {


    public int inserir(Adm adm) {
        Connection conn = null;
        Conexao conexao = new Conexao();
        try {

            conn = conexao.conectar(); // abre a conexão com o banco
            String consulta = "insert into adm(email, senha) values(?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(consulta);
            //Setando valores dos parametros
            pstmt.setString(1, adm.getEmail());
            pstmt.setString(2, adm.getSenha());
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





    public int updateSenha(Adm adm, String senha) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            PreparedStatement pstm = conn.prepareStatement("UPDATE adm SET senha = ? WHERE id = ?;");
            pstm.setString(1, senha);
            pstm.setInt(2, adm.getId());
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

    public int updateEmail(Adm adm, String email) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            PreparedStatement pstm = conn.prepareStatement("UPDATE adm SET email = ? WHERE id = ?;");
            pstm.setString(1, email);
            pstm.setInt(2, adm.getId());
            if (pstm.executeUpdate()>0){
                return 1;

            }  return 0;
        }
        catch (SQLException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
        }
        finally {
            conexao.desconectar(conn);
        }
    }


    public int verificarDados(Adm adm){
        ResultSet rset=null;
        int id;
        String email;
        String senha;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {

            Statement stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT * FROM adm ORDER BY id");
            senha=rset.getString("senha");
            email=rset.getString("email");
            id=rset.getInt("id");

            if (adm.getEmail()==email && adm.getSenha()==senha && adm.getId()==id){
                return 1;
            }
            return 0;
        }catch (SQLException e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
        }finally {
            conexao.desconectar(conn);
        }
    }


    public boolean remover(int id) {return super.remover(id, "adm");}


    public List<Adm> buscar() {
        //query
        List<Adm> liADM = new ArrayList<>();
        ResultSet rset = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {

            rset = buscarR("Adm");


            while (rset.next()) {

                Adm adm = new Adm(rset.getString("email"), rset.getInt("id"), rset.getString("senha"));
                liADM.add(adm);
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
            return liADM;
        }
    }

    public List<Adm> buscarId(int id) {
        //query
        List<Adm> liADM = new ArrayList<>();
        ResultSet rset = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM adm WHERE id = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();




                while (rset.next()) {
                    Adm adm = new Adm(rset.getString("email"), rset.getInt("id"), rset.getString("senha"));
                    liADM.add(adm);
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
            return liADM;
        }
    }



    public List<Adm> buscarPorEmail(String email) {
        //query
        List<Adm> liADM = new ArrayList<>();
        ResultSet rset = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM adm WHERE email = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setString(1, email);
            rset = pstm.executeQuery();




            while (rset.next()) {
                Adm adm = new Adm(rset.getString("email"), rset.getInt("id"), rset.getString("senha"));
                liADM.add(adm);
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
            return liADM;
        }
    }

}
