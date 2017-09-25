package start.com.br.startapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import start.com.br.startapp.R;
/**
 * Representa um agendamento realizado pelo usuário
 * Created by Paulo Rogério Oliveira da Silva on 19/09/2017.
 */



public class Agendamento implements Serializable{

    private String funcao;
    private String pessoa;
    private int foto;
    private long data;


    /**
     * Gerando dados para demonstração
     * @return Lista de agendamentos para teste
     */
    public static List<Agendamento> agendamentosParaDemonstracao(){

        Agendamento[] demonstracao = new Agendamento[]{ new Agendamento("Programador", "Paulo", R.drawable.paulo, new java.util.Date().getTime()),
                new Agendamento("Urologista", "Joao",R.drawable.joao , new java.util.Date().getTime()),
                new Agendamento("Dentista", "Julio", R.drawable.julio, new java.util.Date().getTime()),
                new Agendamento("Arquiteta", "Fernanda", R.drawable.fernanda, new java.util.Date().getTime())


        };

        return java.util.Arrays.asList(demonstracao);

    }

    public Agendamento(String funcao, String pessoa, int foto, long data) {
        this.funcao = funcao;
        this.pessoa = pessoa;
        this.foto = foto;
        this.data = data;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getPessoa() {
        return pessoa;
    }

    public void setPessoa(String pessoa) {
        this.pessoa = pessoa;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }
}
