package start.com.br.startapp;


import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;


import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import start.com.br.startapp.model.ServicoPersistencia;
import start.com.br.startapp.model.Usuario;


/**
 * Testa o crudo do usuário
 * Created by Paulo Rogério Oliveira da Silva on 16/09/2017.
 */
@RunWith(AndroidJUnit4.class)
public class TesteServicoPersistencia  {

    @Test
    public void usuarioInserido(){
        Usuario joao =new Usuario(1, "Joao", "Silva","eunoob.@gmail.com","649999999","");
        ServicoPersistencia sp = ServicoPersistencia.getInstance(InstrumentationRegistry.getTargetContext());
        Usuario novo  = sp.inserirUsuario(joao);
        Assert.assertEquals(novo.getNome(), "joao");

    }

    @Test(expected = RuntimeException.class)
    public void usuarioNaoInserido(){
        Usuario joao =new Usuario(1, null, "Silva","eunoob.@gmail.com","649999999","");
        ServicoPersistencia sp =  ServicoPersistencia.getInstance(InstrumentationRegistry.getTargetContext());
        Usuario novo  = sp.inserirUsuario(joao);
        Assert.assertEquals(novo.getNome(), "joao");

    }


}
