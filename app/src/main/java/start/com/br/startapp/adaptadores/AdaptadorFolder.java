package start.com.br.startapp.adaptadores;




import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import start.com.br.startapp.R;
import start.com.br.startapp.fragmentos.FragmentoFolder;
import start.com.br.startapp.fragmentos.ItemFragment;
import start.com.br.startapp.fragmentos.ItemFragmentDisponibilidade;


/**
 * Created by Paulo Rogério Oliveira da Silva on 18/09/2017.
 * A {@link FragmentPagerAdapter}
 * Retorna uma instancia de fragmento correspondente a cada folder clicado pelo usuário
 */
public  class AdaptadorFolder extends FragmentPagerAdapter {

    private AdaptadorDisponibilidade adaptadorDisponibilidade;

    public AdaptadorFolder(FragmentManager fm) {
        super(fm);
    }
        private String[] tituloAbas;

    /**
     * Constroi o adptador passando um array com os nomes das abas
     * @param tituloAbas
     * @param fm gerenciador de framentos do android
     */
    public AdaptadorFolder(FragmentManager fm, String[] tituloAbas, AdaptadorDisponibilidade adaptador){
        super(fm);
        this.tituloAbas = tituloAbas;
        this.adaptadorDisponibilidade = adaptador;
    }
    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment f = null;

        //View agendamento feito
        if( position == 0 ) {
            f = ItemFragment.newInstance(0);
        } else if (position ==1) {
            //view disponibilidade
            f = ItemFragmentDisponibilidade.newInstance(0, adaptadorDisponibilidade);
        }else{
            f = ItemFragmentDisponibilidade.newInstance(0, adaptadorDisponibilidade);
        }
        return f;

    }

    public AdaptadorDisponibilidade getAdaptadorDisponibilidade() {
        return adaptadorDisponibilidade;
    }

    public void setAdaptadorDisponibilidade(AdaptadorDisponibilidade adaptadorDisponibilidade) {
        this.adaptadorDisponibilidade = adaptadorDisponibilidade;
    }

    public String[] getTituloAbas() {
        return tituloAbas;
    }

    public void setTituloAbas(String[] tituloAbas) {
        this.tituloAbas = tituloAbas;
    }

    @Override
    public int getCount() {
        // Show  total pages.
        return this.tituloAbas.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.tituloAbas[position];
    }
}