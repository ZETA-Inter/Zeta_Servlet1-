package com.zeta_servlet.daos;

import com.zeta_servlet.daos.JDBC.Conexao;
import ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.model.Aula;
import com.zeta_servlet.CRUD.CRUD;
import com.zeta_servlet.model.Flash_card;
import com.zeta_servlet.model.Lei;
import com.zeta_servlet.model.Texto_corrido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AulaDAO extends CRUD{

    public int inserir(Aula aula) {
        Connection conn = null;
        Conexao conexao = new Conexao();
        try {

            conn = conexao.conectar(); // abre a conexão com o banco
            String consulta = "insert into aula(descricao, nome, id_aula) values(?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(consulta);
            //Setando valores dos parametros
            pstmt.setString(1, aula.getDescricao());
            pstmt.setString(2, aula.getNome());
            pstmt.setInt(3, aula.getId_curso());

            // querys dos outros valores

            //query flashCards
            for (int i = 0; i < aula.getFlashCards().size(); i++) {
                String consultaFlash = "insert into flash(flash_card, id_aula) values(?, ?)";
                PreparedStatement pstmtFlash = conn.prepareStatement(consultaFlash);
                pstmt.setString(1, aula.getFlashCards().get(i).getFlash_card());
                pstmt.setInt(2, aula.getFlashCards().get(i).getId_aula());
            }
            //query texto_corrido
            for (int i = 0; i < aula.getTexto_corridos().size(); i++) {
                String consultaTexto = "insert into texto_corrido(texto_corrido, id_aula) values(?, ?)";
                PreparedStatement pstmtFlash = conn.prepareStatement(consultaTexto);
                pstmt.setString(1, aula.getTexto_corridos().get(i).getTexto_corrido());
                pstmt.setInt(2, aula.getTexto_corridos().get(i).getId_aula());
            }

            //query lei
            for (int i = 0; i < aula.getLeis().size(); i++) {
                String consultaTexto = "insert into lei(lei, id_aula) values(?, ?)";
                PreparedStatement pstmtFlash = conn.prepareStatement(consultaTexto);
                pstmt.setString(1, aula.getLeis().get(i).getLei());
                pstmt.setInt(2, aula.getLeis().get(i).getId_aula());
            }




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



    public int updateNome(Aula aula, String nome) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            PreparedStatement pstm = conn.prepareStatement("UPDATE aula SET nome = ? WHERE id = ?;");
            pstm.setString(1, nome);
            pstm.setInt(2, aula.getId());
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
    public int updateDescricao(Aula aula, String desc) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            PreparedStatement pstm = conn.prepareStatement("UPDATE aula SET descricao = ? WHERE id = ?;");
            pstm.setString(1, desc);
            pstm.setInt(2, aula.getId());
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

    public boolean remover(int id) {return super.remover(id, "aula");}

    public List<Aula> buscar() {
        //query
        List<Aula> liAU = new ArrayList<>();
        List<Flash_card> liF = new ArrayList<>();
        List<Texto_corrido> liT = new ArrayList<>();
        List<Lei> liL = new ArrayList<>();

        ResultSet rsetA = null;
        ResultSet rsetF = null;
        ResultSet rsetT = null;
        ResultSet rsetL = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {

            rsetA = buscarR("aula");

            while (rsetA.next()) {
                int id = rsetA.getInt("id");

                rsetF = buscarPorIdR(id, "flash_card");
                while (rsetF.next()) {
                    Flash_card flash = new Flash_card(rsetF.getInt("id"), rsetF.getString("flash_card"), rsetF.getInt("id_aula"));
                    liF.add(flash);
                }

                rsetL = buscarPorIdR(id, "lei");
                while (rsetL.next()){
                    Lei lei = new Lei(rsetL.getInt("id"), rsetL.getString("lei"), rsetL.getInt("id_aula"));
                    liL.add(lei);
                }

                rsetT = buscarPorIdR(id, "texto_corrido");
                while (rsetT.next()){
                    Texto_corrido texto = new Texto_corrido(rsetT.getInt("id"), rsetT.getString("lei"), rsetT.getInt("id_auala"));
                    liT.add(texto);
                }

                Aula aula = new Aula(rsetA.getInt("id"), rsetA.getString("descricao"), rsetA.getString("nome"), rsetA.getInt("id_aula"), liT, liF, liL);
                liAU.add(aula);
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
            return liAU;
        }
    }

    public List<Aula> buscarPorId(int id) {
        //query
        List<Aula> liAU = new ArrayList<>();
        List<Flash_card> liF = new ArrayList<>();
        List<Texto_corrido> liT = new ArrayList<>();
        List<Lei> liL = new ArrayList<>();
        ResultSet rsetA = null;
        ResultSet rsetF = null;
        ResultSet rsetT = null;
        ResultSet rsetL = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM aula WHERE id = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setInt(1, id);
            rsetA = pstm.executeQuery();


            while (rsetA.next()) {
                rsetF = buscarPorIdR(id, "flash_card");
                while (rsetF.next()) {
                    Flash_card flash = new Flash_card(rsetF.getInt("id"), rsetF.getString("flash_card"), rsetF.getInt("id_aula"));
                    liF.add(flash);
                }

                rsetL = buscarPorIdR(id, "lei");
                while (rsetL.next()){
                    Lei lei = new Lei(rsetL.getInt("id"), rsetL.getString("lei"), rsetL.getInt("id_aula"));
                    liL.add(lei);
                }

                rsetT = buscarPorIdR(id, "texto_corrido");
                while (rsetT.next()){
                    Texto_corrido texto = new Texto_corrido(rsetT.getInt("id"), rsetT.getString("lei"), rsetT.getInt("id_auala"));
                    liT.add(texto);
                }

                Aula aula = new Aula(rsetA.getInt("id"), rsetA.getString("descricao"), rsetA.getString("nome"), rsetA.getInt("id_aula"), liT, liF, liL);
                liAU.add(aula);
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
            return liAU;
        }
    }

    public List<Aula> buscarPorNome(String nome) {
        //query
        List<Aula> liAU = new ArrayList<>();
        List<Flash_card> liF = new ArrayList<>();
        List<Texto_corrido> liT = new ArrayList<>();
        List<Lei> liL = new ArrayList<>();
        ResultSet rsetA = null;
        ResultSet rsetF = null;
        ResultSet rsetT = null;
        ResultSet rsetL = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM aula WHERE nome = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setString(1, nome);
            rsetA = pstm.executeQuery();


            while (rsetA.next()) {
                int id = rsetA.getInt("id");

                rsetF = buscarPorIdR(id, "flash_card");
                while (rsetF.next()) {
                    Flash_card flash = new Flash_card(rsetF.getInt("id"), rsetF.getString("flash_card"), rsetF.getInt("id_aula"));
                    liF.add(flash);
                }

                rsetL = buscarPorIdR(id, "lei");
                while (rsetL.next()){
                    Lei lei = new Lei(rsetL.getInt("id"), rsetL.getString("lei"), rsetL.getInt("id_aula"));
                    liL.add(lei);
                }

                rsetT = buscarPorIdR(id, "texto_corrido");
                while (rsetT.next()){
                    Texto_corrido texto = new Texto_corrido(rsetT.getInt("id"), rsetT.getString("lei"), rsetT.getInt("id_auala"));
                    liT.add(texto);
                }

                Aula aula = new Aula(rsetA.getInt("id"), rsetA.getString("descricao"), rsetA.getString("nome"), rsetA.getInt("id_aula"), liT, liF, liL);
                liAU.add(aula);
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
            return liAU;
        }
    }

    public List<Aula> buscarPorIdModulo(int id_modulo) {
        //query
        List<Aula> liAU = new ArrayList<>();
        List<Flash_card> liF = new ArrayList<>();
        List<Texto_corrido> liT = new ArrayList<>();
        List<Lei> liL = new ArrayList<>();
        ResultSet rsetA = null;
        ResultSet rsetF = null;
        ResultSet rsetT = null;
        ResultSet rsetL = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM aula WHERE id_modulo = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setInt(1, id_modulo);
            rsetA = pstm.executeQuery();


            while (rsetA.next()) {
                int id = rsetA.getInt("id");

                rsetF = buscarPorIdR(id, "flash_card");
                while (rsetF.next()) {
                    Flash_card flash = new Flash_card(rsetF.getInt("id"), rsetF.getString("flash_card"), rsetF.getInt("id_aula"));
                    liF.add(flash);
                }

                rsetL = buscarPorIdR(id, "lei");
                while (rsetL.next()){
                    Lei lei = new Lei(rsetL.getInt("id"), rsetL.getString("lei"), rsetL.getInt("id_aula"));
                    liL.add(lei);
                }

                rsetT = buscarPorIdR(id, "texto_corrido");
                while (rsetT.next()){
                    Texto_corrido texto = new Texto_corrido(rsetT.getInt("id"), rsetT.getString("lei"), rsetT.getInt("id_auala"));
                    liT.add(texto);
                }

                Aula aula = new Aula(rsetA.getInt("id"), rsetA.getString("descricao"), rsetA.getString("nome"), rsetA.getInt("id_aula"), liT, liF, liL);
                liAU.add(aula);
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
            return liAU;
        }
    }



}
