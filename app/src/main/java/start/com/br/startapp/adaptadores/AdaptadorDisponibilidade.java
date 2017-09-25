package start.com.br.startapp.adaptadores;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.edmodo.rangebar.RangeBar.OnRangeBarChangeListener;
import com.edmodo.rangebar.RangeBar;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import start.com.br.startapp.R;
import start.com.br.startapp.fragmentos.ItemFragmentDisponibilidade.OnListFragmentInteractionListener;
import start.com.br.startapp.model.Agendamento;
import start.com.br.startapp.model.Disponibilidade;
import start.com.br.startapp.model.Horario;
import start.com.br.startapp.model.Horario.HORA_DIA;

/**
 *  Faz o vinculo entre o Recycle View e seus dados.
 * {@link RecyclerView.Adapter} that can display a {@link} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 *
 */
public class AdaptadorDisponibilidade extends RecyclerView.Adapter<AdaptadorDisponibilidade.UIHolder> implements Serializable {

    private List<Horario> disponibilidades = Collections.emptyList();
    private final OnListFragmentInteractionListener mListener;



    public void setDisponibilidade(List<Horario> disponibilidade){
        this.disponibilidades = disponibilidade;
        this.notifyDataSetChanged();
    }


    public AdaptadorDisponibilidade(OnListFragmentInteractionListener listener){
        super();
        this.mListener = listener;
    }

    /**
     *
     * @param disponibilidades lista com os horários disponíveis de um usuário da aplicação
     * @param listener ouvinte do evento de interação com o RecicleView
     */
    public AdaptadorDisponibilidade(List<Horario> disponibilidades, OnListFragmentInteractionListener listener) {
        this.disponibilidades = disponibilidades;
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
    public UIHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        /**
         * Preenche um objeto view que representa o layout da disponibilidade XML
         * necessário para poder buscar os objetos internos com o findByID
         */
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.disponibilidade_item, parent, false);
        return new UIHolder(view);
    }

    /**
     * Os objetos internos do holder, texts, imageView devem ser atualizados conforme a posição sendo printada na UI
     * @param linha Ui que representa uma linha no RecycleView
     * @param position sendo inserida na UI linha
     */
    @Override
    public void onBindViewHolder(final UIHolder linha, int position) {
        linha.horario = disponibilidades.get(position);
        linha.text_horario.setText( Integer.valueOf( disponibilidades.get(position).getHora().ordinal() ).toString());
        linha.text_meridiam.setText(disponibilidades.get(position).getMeridiam());
        linha.text_horaInicial.setText(disponibilidades.get(position).representacaoHoraInicial());
        linha.text_horaFinal.setText(disponibilidades.get(position).representacaoHoraFinal());
        linha.switch_disponibilidade.setText(disponibilidades.get(position).getStatus().getDescricao());
        linha.rageBar.setLeft(disponibilidades.get(position).getMinutoInicial());
        linha.rageBar.setRight(disponibilidades.get(position).getMinutoFinal());




        linha.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(linha.horario);
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
        return disponibilidades.size();
    }

    public class UIHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView text_horario;
        public final TextView text_meridiam;
        public final TextView text_horaInicial;
        public final TextView text_horaFinal;
        public final TextView switch_disponibilidade;
        public final com.edmodo.rangebar.RangeBar rageBar;
        public Horario horario;

        public UIHolder(View view) {
            super(view);
            mView = view;
            view.setAnimation(new Animation() {
            });
            this.text_horario = (TextView) view.findViewById(R.id.disponibilidade_hora);
            this.text_meridiam = (TextView) view.findViewById(R.id.disponibilidade_meridium);
            this.text_horaInicial = (TextView) view.findViewById(R.id.disponibilidade_inicio);
            this.text_horaFinal = (TextView) view.findViewById(R.id.disponibilidade_fim);
            this.switch_disponibilidade = (TextView) view.findViewById(R.id.disponibilidade_indisponivel);
            this.rageBar = (com.edmodo.rangebar.RangeBar) view.findViewById(R.id.seekBar);

            rageBar.setOnRangeBarChangeListener(new  RangeBar.OnRangeBarChangeListener() {

                @Override
                public void onIndexChangeListener(RangeBar rangeBar, int leftThumbIndex, int rightThumbIndex) {
                    horario.setMinutoInicial(rangeBar.getLeftIndex()*5);
                    horario.setMinutoFinal((rangeBar.getRightIndex()*5));
                    text_horaInicial.setText(horario.representacaoHoraInicial());
                    text_horaFinal.setText(horario.representacaoHoraFinal());
                    switch_disponibilidade.setText(horario.getStatus().getDescricao());


                }
            });



        }

    }
}
