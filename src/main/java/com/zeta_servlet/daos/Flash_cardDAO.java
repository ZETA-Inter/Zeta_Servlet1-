package com.zeta_servlet.daos;


import com.zeta_servlet.daos.JDBC.Conexao;
import ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.CRUD.CRUD;
import com.zeta_servlet.model.Flash_card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Flash_cardDAO extends CRUD{

    public int inserir(Flash_card flash) {
        Connection conn = null;
        Conexao conexao = new Conexao();
        try {

            conn = conexao.conectar(); // abre a conexão com o banco
            String consulta = "insert into flash_card(flash_card, id_aula) values(?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(consulta);
            //Setando valores dos parametros
            pstmt.setString(1, flash.getFlash_card());
            pstmt.setInt(2, flash.getId_aula());



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

    public int updateFlashCard(Flash_card flash) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            PreparedStatement pstm = conn.prepareStatement("UPDATE flash_card SET flash_card = ? WHERE id = ?;");
            pstm.setString(1, flash.getFlash_card());
            pstm.setInt(2, flash.getId());
            if (pstm.executeUpdate()>0){
                return 1;

            }  return 0;
        }
        catch (SQLException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException | IllegalStateException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
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

    public int updateIdAula(Flash_card flash) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            PreparedStatement pstm = conn.prepareStatement("UPDATE flash_card SET id_aula = ? WHERE id = ?;");
            pstm.setInt(1, flash.getId_aula());
            pstm.setInt(2, flash.getId());
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

    public boolean remover(int id) {return super.remover(id, "flash_card");}

    public List<Flash_card> buscar() {
        //query
        List<Flash_card> liF = new ArrayList<>();
        ResultSet rset = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {

            rset = buscarR("flash_card");



            while (rset.next()) {
                Flash_card flash = new Flash_card(rset.getInt("id"), rset.getString("flash_card"), rset.getInt("id_aula"));
                liF.add(flash);
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
            return liF;
        }
    }

    public List<Flash_card> buscarPorFlashCard(String flash_card) {
        //query
        List<Flash_card> liF = new ArrayList<>();
        ResultSet rset = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM flash_card WHERE flash_card = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setString(1, flash_card);
            rset = pstm.executeQuery();




            while (rset.next()) {
                Flash_card flash = new Flash_card(rset.getInt("id"), rset.getString("flash_card"), rset.getInt("id_aula"));
                liF.add(flash);
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
            return liF;
        }
    }

    public List<Flash_card> buscarPorIdAula(int id_aula) {
        //query
        List<Flash_card> liF = new ArrayList<>();
        ResultSet rset = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM flash_cards WHERE id_aula = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setInt(1, id_aula);
            rset = pstm.executeQuery();




            while (rset.next()) {
                Flash_card flash = new Flash_card(rset.getInt("id"), rset.getString("flash_card"), rset.getInt("id_aula"));
                liF.add(flash);
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
            return liF;
        }
    }


}
