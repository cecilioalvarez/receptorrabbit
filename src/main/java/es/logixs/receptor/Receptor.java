package es.logixs.receptor;

import es.logixs.Persona;
import org.springframework.stereotype.Component;

@Component
public class Receptor {

    public void recibir(Persona persona) {
        System.out.println("Recepcion: <" + persona.getNombre()+persona.getEdad()+ ">");

    }



}
