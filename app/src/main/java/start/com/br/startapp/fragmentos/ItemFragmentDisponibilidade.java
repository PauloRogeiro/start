package start.com.br.startapp.fragmentos;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edmodo.rangebar.RangeBar;
import com.google.zxing.client.result.AddressBookParsedResult;

import start.com.br.startapp.R;
import start.com.br.startapp.adaptadores.AdaptadorDisponibilidade;
import start.com.br.startapp.model.Disponibilidade;
import start.com.br.startapp.model.Horario;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ItemFragmentDisponibilidade extends Fragment {

    /**
     * Objetivo dessa classe é picotar os lados dos itens do listview para mostrar o backGround pai
     */
    private class Space extends RecyclerView.ItemDecoration{
        private int espaco;


        public Space(int espaco) {
            this.espaco = espaco;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = espaco;
            outRect.bottom = espaco;
            outRect.right = espaco;

            //É o primeiro item view então adiciona um espaço em cima também
            if(  parent.getChildLayoutPosition(view) == 0  ){
                outRect.top = espaco;
            }

        }
    }


    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ADAPTADOR_INSTALADO = "adaptador";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private AdaptadorDisponibilidade adaptador;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragmentDisponibilidade() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragmentDisponibilidade newInstance(int columnCount, AdaptadorDisponibilidade adptador) {
        ItemFragmentDisponibilidade fragment = new ItemFragmentDisponibilidade();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putSerializable(ADAPTADOR_INSTALADO, adptador);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            adaptador= (AdaptadorDisponibilidade) getArguments().get(ADAPTADOR_INSTALADO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list_disponibilidade, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
             Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            recyclerView.addItemDecoration(new Space(50));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adaptador);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Horario item);
    }
}
