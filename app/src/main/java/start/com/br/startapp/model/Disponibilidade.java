package start.com.br.startapp.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;


/**
 * Gerencia a disponibilidade de cada hora do usuário considerando a mínima unidade de tempo em minutos
 * que podem aumentar ou decrescer de 5 em 5 minutos
 * Created by Paulo Rogério Oliveira da Silva on 23/09/2017.
 */
//@Todo adicionar validações, exceções
public class Disponibilidade implements Serializable {


    public static final int TOTAL_HORAS_DIA = 24;
    public static final Usuario USUARIO_NAO_DEFINIDO = null;
    public static final Long DIA_NAO_DEFINIDO = null;




    private Usuario usuario;
    private Long dia;
    private List<Horario> disponibilidadeList = new ArrayList<>(TOTAL_HORAS_DIA);


    /**
     * @param usuario
     * @dia
     * Construtor da classe, inicia a classe com todas as horas do dia disponíveis
     */
    public Disponibilidade(Usuario usuario, Long dia){
        this.usuario = usuario;
        this.dia = dia;
        /* Disponibiliando todos os horários do dia*/
        for( Horario.HORA_DIA hora : Horario.HORA_DIA.VIGESIMA_TERCEIRA.values()){
            disponibilidadeList.add(new Horario(hora, Horario.MINUTO_INICIAL_HORA, Horario.MINUTO_FINAL_HORA,  this));
        }



    }


    /**
     * Verifica se os horários do intervalo passado está disponível
     * @param dataInicial
     * @param dataFinal
     * @return true se todas as horas e minutos entre as datas estão disponíveis
     */
    public boolean intervaloDisponivel(long dataInicial, long dataFinal){

        /*criando as datas a partir dos mili segundos*/
        Date dataI = new Date(dataInicial);
        Date dataF = new Date(dataFinal);

        /*Inicializando as horas e minutos*/
        int horaInicial = dataI.getHours();
        int horaFinal = dataF.getHours();

        boolean horarioIntermediarioUsado = false;

        for( int i = horaInicial; i<=horaFinal; i++){
            /* Se for uma horário intermediário ou seja está entre a hora inicial e final, então deve estar totalmente disponível */
            if( i>horaInicial && i<horaFinal  )  {
                horarioIntermediarioUsado = disponibilidadeList.get( i ).getStatus().equals(Horario.STATUS_HORA.PARCIALMENTE_DISPONIVEL)
                                        ||  disponibilidadeList.get( i ).getStatus().equals(Horario.STATUS_HORA.INDISPONIVEL);
                if( horarioIntermediarioUsado ){
                    break;
                }
            }
        }
        /* se o horário inicial a partir do minuto inicial estiver completamente livre */
        /* se o horário final a partir do minuto inicial estiver completamente livre*/
        /* se não há nenhum horário no meio*/
        return !horarioIntermediarioUsado && disponibilidadeList.get(horaInicial).getStatus().equals(Horario.STATUS_HORA.DISPONIVEL) &&
                disponibilidadeList.get(horaFinal).getStatus().equals(Horario.STATUS_HORA.DISPONIVEL)  ;
    }






    public Usuario getUsuario() {
        return usuario;
    }

    public Long getDia() {
        return dia;
    }

    /**
     *
     * @param periodo do dia que será disponibilizado os horários
     * @return as disponibilidades com base em um  período do dia
     */
    public List<Horario> getDisponibilidadeList(Horario.PERIODO_DIA periodo) {

       return disponibilidadeList.subList(periodo.getInicio().ordinal(), periodo.getFim().ordinal()+1 );
    }



}
