package start.com.br.startapp.atividade;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.login.*;
import com.facebook.*;


import junit.framework.Assert;

import java.util.Arrays;

import start.com.br.startapp.R;
import start.com.br.startapp.model.ServicoPersistencia;
import start.com.br.startapp.model.Usuario;


public class Acesso extends Activity {
    public static final String INTENCAO_DE_ACESSO_PRINCIPAL = "start.com.br.startapp.atividade.Principal";
    public static final String USUARIO_CONECTADO = "usuario";

    CallbackManager callbackManager;


    /**
     * Configura o comportamento da aplicação quando o usuário clicar no botão do facebook
     */
    private void configuraComportamentoClickBotaoFacebook() {
        callbackManager = CallbackManager.Factory.create();
        // Callback registration
        LoginManager l = LoginManager.getInstance();
        l.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent principal = new Intent(Acesso.this, Principal.class);
                /* Adicionando dados a intenção de abrir a atividade principal da aplicação*/
                Profile profile = Profile.getCurrentProfile();

                Usuario u = new Usuario(null,profile.getFirstName(), profile.getLastName(), "","",profile.getProfilePictureUri(300,300).toString());
                principal = principal.putExtra(USUARIO_CONECTADO, u);
                startActivity(principal );
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

    }


    /***
     * Adiciona implementação de um evento que detecta se o usuário clicou no menu de login facebook
     * @param botao que será extraído o click no botão facebook
     * @param idMenu identficador do menu para a localização do menu facebbok
     *
     */
    private void setClickMenuFacebook(Button botao, final int idMenu) {


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View item) {
                    /*Realiza o login solicitando as permissões do perfil do usuário*/
                    LoginManager.getInstance().logInWithReadPermissions(Acesso.this, Arrays.asList("email"));


            }
        };
        botao.setOnClickListener(listener);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atividade_acesso);
        Button button = (Button) findViewById(R.id.acesso_facebook);

        /* Recuperando componentes de layout XML */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);



        /* Integração facebook*/
        this.configuraComportamentoClickBotaoFacebook();
        this.setClickMenuFacebook(button, R.id.entrada_facebook);





    }
    }


