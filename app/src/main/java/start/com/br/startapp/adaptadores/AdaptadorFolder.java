package start.com.br.startapp.adaptadores;




import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import start.com.br.startapp.R;
import start.com.br.startapp.fragmentos.FragmentoFolder;
import start.com.br.startapp.fragmentos.ItemFragment;


/**
 * Created by Paulo Rogério Oliveira da Silva on 18/09/2017.
 * A {@link FragmentPagerAdapter}
 * Retorna uma instancia de fragmento correspondente a cada folder clicado pelo usuário
 */
public  class AdaptadorFolder extends FragmentPagerAdapter {


    public AdaptadorFolder(FragmentManager fm) {
        super(fm);
    }
        private String[] tituloAbas;

    /**
     * Constroi o adptador passando um array com os nomes das abas
     * @param tituloAbas
     * @param fm gerenciador de framentos do android
     */
    public AdaptadorFolder(FragmentManager fm, String[] tituloAbas){
        super(fm);
        this.tituloAbas = tituloAbas;
    }
    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment f = null;

        //View agendamento feito
        if( position == 0 ) {
            f = ItemFragment.newInstance(0);
        } else {
            f = FragmentoFolder.newInstance(R.layout.fragment_item_list);

        }
        return f;

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