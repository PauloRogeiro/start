package start.com.br.startapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import start.com.br.startapp.model.bd.ModeloBanco;
import start.com.br.startapp.model.bd.TABELA_USUARIO;

/**
 * Insere, Atualiza, Exclui, Recupera um usuário do banco
 * Created by Paulo Rogério Oliveira da Silva on 16/09/2017.
 */

public class ServicoPersistencia {

    private ModeloBanco banco ;
    private static ServicoPersistencia instance;


    public static ServicoPersistencia getInstance(Context contexto){

        if( instance == null) {

            instance =  new ServicoPersistencia(contexto);
        }
        return instance;
    }


    /**
     * Instancia o Serviço de persistência
     * @param contexto da aplicação
     */
    private ServicoPersistencia(Context contexto){
        banco = new ModeloBanco(contexto);
    }

    /**
     * Inseri um usuário no banco
     * @param usuario a ser inserido
     * @return usuário com ID incrementado
     */
    public Usuario inserirUsuario(Usuario usuario){
        ContentValues dados = new ContentValues();

        dados.put(TABELA_USUARIO.NOME.toString(), usuario.getNome());
        dados.put(TABELA_USUARIO.SOBRE_NOME.toString(), usuario.getSobreNome());
        dados.put(TABELA_USUARIO.EMAIL.toString(), usuario.getMail());
        dados.put(TABELA_USUARIO.FONE.toString(), usuario.getFone());
        dados.put(TABELA_USUARIO.AVATAR.toString(), usuario.getAvatar());
        usuario.setId((int)banco.getWritableDatabase().insert(TABELA_USUARIO.ID.getTabela(),null,dados));

        Cursor c = banco.getReadableDatabase().query(TABELA_USUARIO.ID.getTabela(), null, String.format(TABELA_USUARIO.ID + " = ?"), new String[]{usuario.getId().toString()}, null,null,null);
        if (c !=  null) c.moveToFirst();
        for(int i = 0; (c != null && i <= c.getCount()-1 ); i++){
            Usuario u = new Usuario(    c.getInt(TABELA_USUARIO.ID.ordinal()),
                                     c.getString(TABELA_USUARIO.NOME.ordinal()),
                                     c.getString(TABELA_USUARIO.SOBRE_NOME.ordinal()),
                                     c.getString(TABELA_USUARIO.EMAIL.ordinal()),
                                     c.getString(TABELA_USUARIO.FONE.ordinal()),
                                     c.getString(TABELA_USUARIO.AVATAR.ordinal() ));
            return u;

        }



        return null;

    }

    /**
     * Exclui um usuário do banco de dados
     * @param usuario a ser excluído
     * @return  quantidade de usuário excluído
     */
    public int excluirUsuario(Usuario usuario){
        return banco.getWritableDatabase().delete(TABELA_USUARIO.ID.getTabela(), String.format(TABELA_USUARIO.ID + " = ?"), new String[]{usuario.getId().toString()});
    }

    /**
     * Atualiza os dados de um usuário no banco de dados
     * @param usuario a ser atualizado
     * @return quantidade de usuários atualizados
     */
    public void atualizarUsuario(Usuario usuario){
        ContentValues dados = new ContentValues();
        dados.put(TABELA_USUARIO.NOME.toString(), usuario.getNome());
        dados.put(TABELA_USUARIO.SOBRE_NOME.toString(), usuario.getSobreNome());
        dados.put(TABELA_USUARIO.EMAIL.toString(), usuario.getMail());
        dados.put(TABELA_USUARIO.FONE.toString(), usuario.getFone());
        dados.put(TABELA_USUARIO.AVATAR.toString(), usuario.getAvatar());
        banco.getWritableDatabase().update(TABELA_USUARIO.ID.getTabela(), dados, String.format(TABELA_USUARIO.ID + " = ?"), new String[]{usuario.getId().toString()});
    }

    public Usuario recuperaUsuario(Integer id){
        Cursor c = banco.getReadableDatabase().query(TABELA_USUARIO.ID.getTabela(), null, String.format(TABELA_USUARIO.ID + " = ?"), new String[]{id.toString()}, null,null,null);
        if (c !=  null) c.moveToFirst();
        for(int i = 0; (c != null && c.getCount() < i); i++){
            Usuario u = new Usuario(    c.getInt(TABELA_USUARIO.ID.ordinal()),
                    c.getString(TABELA_USUARIO.NOME.ordinal()),
                    c.getString(TABELA_USUARIO.SOBRE_NOME.ordinal()),
                    c.getString(TABELA_USUARIO.EMAIL.ordinal()),
                    c.getString(TABELA_USUARIO.FONE.ordinal()),
                    c.getString(TABELA_USUARIO.AVATAR.ordinal() ));
            return u;

        }
        return null;
    }



}
