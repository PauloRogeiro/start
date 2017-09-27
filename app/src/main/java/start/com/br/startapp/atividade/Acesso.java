package start.com.br.startapp.atividade;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.transition.Transition;
import android.support.transition.TransitionValues;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.login.*;
import com.facebook.*;


import junit.framework.Assert;

import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;
import start.com.br.startapp.R;
import start.com.br.startapp.model.ServicoPersistencia;
import start.com.br.startapp.model.Usuario;


public class Acesso extends Activity {
    public static final String INTENCAO_DE_ACESSO_PRINCIPAL = "start.com.br.startapp.atividade.Principal";
    public static final String USUARIO_CONECTADO = "usuario";
    private CircleImageView image;

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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setExitTransition((android.transition.Transition) new Explode());
                }
                /*Adicionando imagem na tela de exibição*/
                Glide.with(Acesso.this).load(Uri.parse(profile.getProfilePictureUri(300,300).toString())).into(image);
                Usuario u = new Usuario(null,profile.getFirstName(), profile.getLastName(), "","",profile.getProfilePictureUri(300,300).toString());
                principal = principal.putExtra(USUARIO_CONECTADO, u);
                startActivity(principal);

            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(Acesso.this, R.string.acesso_facebook_login_cancel, Toast.LENGTH_LONG);
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(Acesso.this, R.string.acesso_facebook_login_fail, Toast.LENGTH_LONG);
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
        image = (CircleImageView) findViewById(R.id.appCompatImageView);

        /* Recuperando componentes de layout XML */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.fab_jump_to_down);
        hyperspaceJumpAnimation.setDuration(3000);
        fab.startAnimation(hyperspaceJumpAnimation);

        /* Integração facebook*/
        this.configuraComportamentoClickBotaoFacebook();
        this.setClickMenuFacebook(button, R.id.entrada_facebook);





    }
    }


