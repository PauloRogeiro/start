package start.com.br.startapp.model;

import java.io.Serializable;

/**
 * Created by Gamer Primetek on 24/09/2017.
 */
public class Horario implements Serializable {


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Horario)) return false;

        Horario horario = (Horario) o;

        if (disponivel != horario.disponivel) return false;
        if (minutoInicial != horario.minutoInicial) return false;
        if (minutoFinal != horario.minutoFinal) return false;
        if (periodo != horario.periodo) return false;
        if (hora != horario.hora) return false;
        if (!meridiam.equals(horario.meridiam)) return false;
        return disponibilidade.equals(horario.disponibilidade);

    }

    @Override
    public int hashCode() {
        int result = hora.hashCode();
        result = 31 * result + minutoInicial;
        result = 31 * result + minutoFinal;
        return result;
    }
    public enum PERIODO_DIA {
        PERIODO_MANHA( HORA_DIA.SEXTA, HORA_DIA.DECIMA_PRIMEIRA),
        PERIODO_TARDE( HORA_DIA.DECIMA_SEGUNDA, HORA_DIA.DECIMA_SETIMA),
        PERIODO_NOITE( HORA_DIA.DECIMA_OITAVA, HORA_DIA.VIGESIMA_TERCEIRA),
        PERIODO_MADRUGADA( HORA_DIA.ZERO, HORA_DIA.QUINTA);

        PERIODO_DIA(HORA_DIA inicio, HORA_DIA fim){
            this.inicio = inicio;
            this.fim = fim;
        }

        private HORA_DIA inicio;
        private HORA_DIA fim;

        public HORA_DIA getInicio() {
            return inicio;
        }

        public HORA_DIA getFim() {
            return fim;
        }
    };

    public static final  int PERIODO_DIA_MANHA= 1;
    public static final  int PERIODO_DIA_TARDE= 2;
    public static final  int PERIODO_DIA_NOITE= 3;
    public static final  int PERIODO_DIA_MADRUGADA= 4;

   public enum HORA_DIA{

        ZERO,                           PRIMEIRA,                           SEGUNDA,
        TERCEIRA,                       QUARTA,                             QUINTA,
        SEXTA,                          SETIMA,                             OITAVA,
        NONA,                           DECIMA,                             DECIMA_PRIMEIRA,
        DECIMA_SEGUNDA,                 DECIMA_TERCEIRA,                    DECIMA_QUARTA,
        DECIMA_QUINTA,                  DECIMA_SEXTA,                       DECIMA_SETIMA,
        DECIMA_OITAVA,                  DECIMA_NONA,                        VIGESIMA,
        VIGESIMA_PRIMEIRA,              VIGESIMA_SEGUNDA,                   VIGESIMA_TERCEIRA


    };

    public enum STATUS_HORA{

        DISPONIVEL("Total"), PARCIALMENTE_DISPONIVEL("Parcial"), INDISPONIVEL("Nenhuma");


        STATUS_HORA(String descricao){
            this.descricao  = descricao;
        }

        private String descricao;

        public String getDescricao() {
            return descricao;
        }
    }


    public static final  int MINUTO_INICIAL_HORA = 0;
    public static final  int MINUTO_FINAL_HORA= 60;
    public static final  String ANTE_MERIDIEM= "AM";
    public static final  String POST_MERIDIEM= "PM";


    private boolean disponivel = true;
    private HORA_DIA hora;
    private int minutoInicial;
    private int minutoFinal;
    private int periodo;
    private String meridiam;
    private Disponibilidade disponibilidade;


    /**
     * Full constructor
     * @param hora
     * @param minutoInicial
     * @param minutoFinal
     */
    public Horario(HORA_DIA hora, int minutoInicial, int minutoFinal,  Disponibilidade disponibilidade) {
        this.hora = hora;
        this.minutoInicial = minutoInicial;
        this.minutoFinal = minutoFinal;
        this.disponibilidade = disponibilidade;
        this.disponivel = true;

        /* setando o meridiam */
        if( hora.ordinal() >= HORA_DIA.DECIMA_SEGUNDA.ordinal() ) {
            meridiam = POST_MERIDIEM;
        }else {
            meridiam = ANTE_MERIDIEM;
        }

    }

    /**
     * Indisponibiliza todos os minutos da hora
     */
    public void indisponibilizarHorario(){
        this.setMinutoInicial( Horario.MINUTO_INICIAL_HORA );
        this.setMinutoFinal( Horario.MINUTO_FINAL_HORA );
        this.setDisponivel( false );
    }


    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }


    public void setMinutoInicial(int minutoInicial) {
        this.minutoInicial = minutoInicial;
    }

    public void setMinutoFinal(int minutoFinal) {
        this.minutoFinal = minutoFinal;
    }


    public HORA_DIA getHora() {
        return hora;
    }

    public int getMinutoInicial() {
        return minutoInicial;
    }

    public int getMinutoFinal() {
        return minutoFinal;
    }



    public String representacaoHoraInicial(){
        return minutoInicial == MINUTO_FINAL_HORA? (this.hora.ordinal()+1) + ":"+ String.format(Integer.valueOf(MINUTO_INICIAL_HORA).toString(),"00"):this.hora.ordinal() + ":"+
                String.format(Integer.valueOf(minutoInicial).toString(),"00");
    }
    public String representacaoHoraFinal(){
        /* Se for o último minuto da hora então será exibido a próxima hora, ninguém diz 1:60 dizem 2:00*/
        return  minutoFinal == MINUTO_FINAL_HORA? (this.hora.ordinal()+1) + ":"+ String.format(Integer.valueOf(MINUTO_INICIAL_HORA).toString(),"00"):this.hora.ordinal() + ":"+
                String.format(Integer.valueOf(minutoFinal).toString(),"00");
    }

    /**
     *
     * @return o status do horário, Parcialmente disponível, Disponível ou Indisponível
     */
    public STATUS_HORA getStatus(){
        STATUS_HORA status = STATUS_HORA.DISPONIVEL;
        /* Se o horário foi alterado os minutos então está parcialmente disponível */
        if(  ((this.minutoInicial != MINUTO_INICIAL_HORA )|| (this.minutoFinal != MINUTO_FINAL_HORA ) ) && (this.minutoInicial!=this.minutoFinal) )
        {
            status = STATUS_HORA.PARCIALMENTE_DISPONIVEL;
        } else if( (this.minutoInicial == MINUTO_INICIAL_HORA )&& (this.minutoFinal == MINUTO_FINAL_HORA   )   ) {

            status = STATUS_HORA.DISPONIVEL;
        }  else {
            /* minuto inicial igual ao final*/
            status  =STATUS_HORA.INDISPONIVEL;

        }

        return status;

    }

    public String getMeridiam() {
        return meridiam;
    }

    public Disponibilidade getDisponibilidade() {
        return disponibilidade;
    }
}
