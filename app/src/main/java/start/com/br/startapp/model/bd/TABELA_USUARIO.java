package start.com.br.startapp.model.bd;

/**
 * Created by Gamer Primetek on 16/09/2017.
 */

public enum TABELA_USUARIO {

    ID("_ID", "INTEGER", ""),
    NOME("NOME", "TEXT", "NOT NULL"),
    SOBRE_NOME("sobreNome","Text","NOT NULL"),
    EMAIL("Email","Text",""),
    FONE("Fone","text",""),
    AVATAR("avatar", "Text","");


    String tipo;
    String campo;
    String tabela;
    String restricao;


    TABELA_USUARIO(String campo, String tipo, String restricao){
        this.tipo = tipo;
        this.campo = campo;
        this.restricao = restricao;
        this.tabela = "Usuario";
    }

    public String getTipo() {
        return tipo;
    }

    public String getCampo() {
        return campo;
    }

    public String getTabela() {
        return tabela;
    }

    public String getRestricao() {
        return restricao;
    }


    @Override
    public String toString(){
        return this.campo;
    }
}
