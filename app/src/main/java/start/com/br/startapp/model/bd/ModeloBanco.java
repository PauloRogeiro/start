package start.com.br.startapp.model.bd;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

/**
 * Created by Paulo Rogério Oliveira da Silva on 16/09/2017.
 * Classe que define o modelo do banco de dados;
 * Cria o banco e a tabela Usuario
 */

public class ModeloBanco extends SQLiteOpenHelper {

    private static String NOME_BANCO = "start.db";
    private static int VERSAO_BANCO = 1;

    /**
     * Instancia o Modelo de banco
     * @param contexto da aplicação
     */
    public ModeloBanco(Context contexto){
        super(contexto, NOME_BANCO, null, VERSAO_BANCO);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tabelaUsuario = "";
        tabelaUsuario = tabelaUsuario + String.format(" CREATE TABLE %s ("                                , TABELA_USUARIO.ID.getTabela()  );
        tabelaUsuario = tabelaUsuario + String.format( " %s %s PRIMARY KEY,  "                            , TABELA_USUARIO.ID.getCampo()          ,  TABELA_USUARIO.ID.getRestricao()  );
        tabelaUsuario = tabelaUsuario + String.format("  %s %s %s, ", TABELA_USUARIO.FONE.getCampo()      , TABELA_USUARIO.FONE.getTipo()         ,  TABELA_USUARIO.FONE.getRestricao()   );
        tabelaUsuario = tabelaUsuario + String.format("  %s %s %s, ", TABELA_USUARIO.NOME.getCampo()      , TABELA_USUARIO.NOME.getTipo()         ,  TABELA_USUARIO.NOME.getRestricao()   );
        tabelaUsuario = tabelaUsuario + String.format("  %s %s %s, ", TABELA_USUARIO.EMAIL.getCampo()     , TABELA_USUARIO.EMAIL.getTipo()        ,  TABELA_USUARIO.EMAIL.getRestricao()  );
        tabelaUsuario = tabelaUsuario + String.format("  %s %s %s, ", TABELA_USUARIO.SOBRE_NOME.getCampo(), TABELA_USUARIO.SOBRE_NOME.getTipo()   ,  TABELA_USUARIO.SOBRE_NOME.getRestricao()  );
        tabelaUsuario = tabelaUsuario + String.format("  %s %s %s )", TABELA_USUARIO.AVATAR.getCampo()    , TABELA_USUARIO.AVATAR.getTipo()       ,  TABELA_USUARIO.AVATAR.getRestricao()  );
        sqLiteDatabase.execSQL(tabelaUsuario);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(String.format("DROP TABLE %s", TABELA_USUARIO.ID.getTabela()));
        onCreate(sqLiteDatabase);
    }
}
