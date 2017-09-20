package start.com.br.startapp;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import start.com.br.startapp.model.bd.ModeloBanco;
import static org.junit.Assert.*;

/**
 * Created by Paulo Rogério Oliveira da Silva on 16/09/2017.
 * Testa se o banco foi criado com sucesso
 */
@RunWith(AndroidJUnit4.class)
public class TesteModeloBanco {


    /**
     * Testa se o banco foi criado com sucesso
     */
    @Test
    public void bancoCriado(){
        ModeloBanco  banco = new ModeloBanco(InstrumentationRegistry.getTargetContext());
        assertNotNull(banco);

    }


    /**
     * Testa se o banco não foi criado com sucesso
     */
    @Test
    public void bancoNaoCriado(){
        ModeloBanco  banco = new ModeloBanco(InstrumentationRegistry.getTargetContext());
        banco = new ModeloBanco(InstrumentationRegistry.getTargetContext());

    }


}
