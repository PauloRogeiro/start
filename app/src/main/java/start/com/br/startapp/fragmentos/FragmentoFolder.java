package start.com.br.startapp.fragmentos;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import start.com.br.startapp.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentoFolder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoFolder extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String VIEW_INFLADA = "folder";

    // TODO: Rename and change types of parameters
    private int idView;


    public FragmentoFolder() {
        // Required empty public constructor
    }

    /**
     * Cria uma instancia do fragmento informando qual o folder deve ser exibido
     *
     * @param idView identificador da view
     * @return A new instance of fragment FragmentoFolder.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentoFolder newInstance(int idView) {
        FragmentoFolder fragment = new FragmentoFolder();
        Bundle args = new Bundle();
        args.putInt(VIEW_INFLADA, idView);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idView = getArguments().getInt(VIEW_INFLADA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vw = inflater.inflate(getArguments().getInt(VIEW_INFLADA),container, false);
        return vw;
    }

}
