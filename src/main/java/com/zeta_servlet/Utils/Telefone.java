package Utils;

public class Telefone {
   private int id;
   private int idFor;
   private String telefone;

    public Telefone(int id, int idFor, String telefone) {
        this.id = id;
        this.idFor = idFor;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public int getIdFor() {
        return idFor;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override
    public String toString() {
        return "    \nID: "+id+"\n"+
                "   ID fornecedor: "+idFor+"\n"+
                "   Telefone: "+telefone+"\n"+
                "=".repeat(30);
    }
}
