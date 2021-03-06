package start.com.br.startapp.atividade;

import android.app.Activity;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import start.com.br.startapp.R;
import start.com.br.startapp.adaptadores.AdaptadorDisponibilidade;
import start.com.br.startapp.adaptadores.AdaptadorFolder;
import start.com.br.startapp.fragmentos.ItemFragment;
import start.com.br.startapp.fragmentos.ItemFragmentDisponibilidade;
import start.com.br.startapp.model.Agendamento;
import start.com.br.startapp.model.Disponibilidade;
import start.com.br.startapp.model.Horario;
import start.com.br.startapp.model.Usuario;


/**
 * Representa o estado principal da aplicação;
 * Created by Paulo Rogério Oliveira da Silva on 17/09/2017.
 */

public class Principal extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener, ItemFragmentDisponibilidade.OnListFragmentInteractionListener {


    private AdaptadorFolder adaptador;
    private ViewPager pageViewer;
    private Disponibilidade disponibilidade = new Disponibilidade(Disponibilidade.USUARIO_NAO_DEFINIDO, Disponibilidade.DIA_NAO_DEFINIDO);




    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.atividade_principal);

        /* Recuperando e configurando o botão flutuante  */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        /* Recuperando e configurando a toobar */
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_app);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Fade());
        }


        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        adaptador = new AdaptadorFolder(getSupportFragmentManager(), new String[]{"Agendamentos", "Disponibilidade", "Encontrar"}, new AdaptadorDisponibilidade(this));

        // Set up the ViewPager with the sections adapter.
        pageViewer = (ViewPager) findViewById(R.id.tab_aplicacao);
        pageViewer.setAdapter(adaptador);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.layoutTab);
        tabLayout.setupWithViewPager(pageViewer);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //Tab de disponibilidade
                if( tab.getPosition() == 1) {
                    toolbar.inflateMenu(R.menu.menu_aplicacao);
                    adaptador.getAdaptadorDisponibilidade().setDisponibilidade(disponibilidade.getDisponibilidadeList(Horario.PERIODO_DIA.PERIODO_MANHA));
                    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            switch (item.getItemId()){
                                case R.id.periodo_manha:
                                    adaptador.getAdaptadorDisponibilidade().setDisponibilidade(disponibilidade.getDisponibilidadeList(Horario.PERIODO_DIA.PERIODO_MANHA));
                                    return true;
                                case R.id.periodo_tarde:
                                    adaptador.getAdaptadorDisponibilidade().setDisponibilidade(disponibilidade.getDisponibilidadeList(Horario.PERIODO_DIA.PERIODO_TARDE));
                                    return true;
                                case R.id.periodo_noite:
                                    adaptador.getAdaptadorDisponibilidade().setDisponibilidade(disponibilidade.getDisponibilidadeList(Horario.PERIODO_DIA.PERIODO_NOITE));
                                    return true;
                                case R.id.periodo_madrugada:
                                    adaptador.getAdaptadorDisponibilidade().setDisponibilidade(disponibilidade.getDisponibilidadeList(Horario.PERIODO_DIA.PERIODO_MADRUGADA));
                                    return true;
                            }

                            return false;
                        }
                    });
                } else{
                    toolbar.getMenu().clear();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        /* Recuperando e configurando o NavigationView */
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.navegacao_configuracoes);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawerLayout, toolbar, R.string.configuracao_ativa, R.string.configuracao_intativa);
        drawerLayout.addDrawerListener(toggle);

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (getIntent().getExtras() != null) {
                    Usuario u = (Usuario) getIntent().getExtras().get(Acesso.USUARIO_CONECTADO);

                    if (u != null) {
                        CircleImageView iv = (CircleImageView) findViewById(R.id.perfil_avatar);

                        TextView text_nome = (TextView) findViewById(R.id.perfil_nome);
                        TextView text_sobrenome = (TextView) findViewById(R.id.perfil_sobrenome);

                        Glide.with(Principal.this).load(u.getAvatar()).into(iv);

                        text_nome.setText(u.getNome());
                        text_sobrenome.setText(u.getSobreNome());

                    }
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {


            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        toggle.syncState();


    }


    @Override
    public void onListFragmentInteraction(Agendamento item) {

    }

    @Override
    public void onListFragmentInteraction(Horario item) {


    }


}
