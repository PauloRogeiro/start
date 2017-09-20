package start.com.br.startapp.fragmentos;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import start.com.br.startapp.R;
import start.com.br.startapp.fragmentos.ItemFragment.OnListFragmentInteractionListener;
import start.com.br.startapp.model.Agendamento;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class AdaptadorAgenda extends RecyclerView.Adapter<AdaptadorAgenda.UiLinha> {

    private List<Agendamento> agendamentos = Collections.emptyList();
    private final OnListFragmentInteractionListener mListener;




    /**
     *
     * @param agendamentos lista com os agendamentos da aplicação
     * @param listener ouvint do evento de interação com o RecicleView
     */
    public AdaptadorAgenda(List<Agendamento> agendamentos, OnListFragmentInteractionListener listener) {
        this.agendamentos = agendamentos;
        mListener = listener;
    }

    /**
     * Retorna um objeto que represeta uma linha no RecycleView
     * método chamado pela UI quando a linha é criada
     * @param parent visão que está chamando o método
     * @param viewType
     * @return
     */
    @Override
    public UiLinha onCreateViewHolder(ViewGroup parent, int viewType) {

        /**
         * Preenche um objeto view que representa o layout de agendamentos XML
         * necessário para poder buscar os objetos internos com o findByID
         */
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_agendamento, parent, false);
        return new UiLinha(view);
    }

    /**
     * Os objetos internos do holder, texts, imageView devem ser atualizados conforme a posição sendo printada na UI
     * @param linha Ui que representa uma linha no RecycleView
     * @param position sendo inserida na UI linha
     */
    @Override
    public void onBindViewHolder(final UiLinha linha, int position) {
        linha.agendamento = agendamentos.get(position);
        linha.pessoa_foto.setImageResource(agendamentos.get(position).getFoto());
        linha.pessoa_nome.setText(agendamentos.get(position).getPessoa());
        linha.pessoa_data.setText(new java.util.Date(agendamentos.get(position).getData()).toString());
        linha.pessoa_funcao.setText(agendamentos.get(position).getFuncao());

        linha.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(linha.agendamento);
                }
            }
        });
    }

    /**
     *
     * @return tamanho da lista
     */
    @Override
    public int getItemCount() {
        return agendamentos.size();
    }

    public class UiLinha extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView pessoa_foto;
        public final TextView pessoa_nome;
        public final TextView pessoa_data;
        public final TextView pessoa_funcao;
        public Agendamento agendamento;

        public UiLinha(View view) {
            super(view);
            mView = view;
            this.pessoa_foto = (ImageView) view.findViewById(R.id.agendamento_pessoa_foto);
            this.pessoa_nome = (TextView) view.findViewById(R.id.agendamento_pessoa_nome);
            this.pessoa_data = (TextView) view.findViewById(R.id.agendamento_pessoa_data);
            this.pessoa_funcao = (TextView) view.findViewById(R.id.agendamento_pessoa_funcao);

        }

    }
}
