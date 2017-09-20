package start.com.br.startapp.model;

import java.io.Serializable;

/**
 * Representa um usuário no sistema
 * Created by Paulo Rogério Oliveira da Silva on 16/09/2017.
 */

public class Usuario implements Serializable {

    private Integer id;
    private String nome;
    private String sobreNome;
    private String mail;
    private String fone;
    private String avatar;

    public Usuario(Integer id, String nome, String sobreNome, String mail, String fone, String avatar) {
        this.id = id;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.mail = mail;
        this.fone = fone;
        this.avatar = avatar;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
