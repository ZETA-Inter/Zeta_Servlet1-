package com.zeta_servlet;
import com.zeta_servlet.daos.FornecedorDAO;
import ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.model.Fornecedor;

public class Main {
    public static void main(String[] args) {
        // TESTE DE CONEXÃO
//        try {
//            Conexao dbConn = new Conexao();
//            if (dbConn.conectar() == null) {
//                System.out.println("Erro de conexão");
//            }
//            else {
//                System.out.println("Conexão bem sucedida");
//            }
//            System.out.println("Saindo do db! \uD83D\uDC49\uD83D\uDC48\uD83D\uDE16");
//            dbConn.desconectar(dbConn.conectar());
//            }
//            catch (Exception e) {
//                ExceptionHandler eh = new ExceptionHandler(e);
//                eh.printExeption();
//            }
        //// TESTE ADMIN
//        AdmDAO admDao = new AdmDAO();
//        Adm admTOP = new Adm("joaoSouzera00@germinare.org.br",1,"admtop");
//        Adm adm1 = new Adm("raphaely.sales@germinare.org.br", 2, "e2510");

//        if (admDao.updateSenha(adm1, "e2510") == 1){
//            System.out.println("Funcionou");
//        }
//        else{
//            System.out.println("Não funcionou");
//        }







//        if (adm.inserir(adm1) == 1){
//            System.out.println("Administrador cadrastado com sucesso!");
//        }
//        else{
//            System.out.println("Não foi possível inserir o administrador...");
//        }
//        if(adm.remover(2)){
//            System.out.println("Funcionou");
//        }
//        else{
//            System.out.println("erro");
//        }
//        System.out.println(admDao.buscar()); // buscar por email

//        System.out.println(admDao.buscarId(1));


        // TESTES FORNECEDOR



    FornecedorDAO fornecedorDAO = new FornecedorDAO();
    System.out.println(fornecedorDAO.buscar());


    }//main
}//class