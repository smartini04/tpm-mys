package escenario;

import com.etapa2.Selector;
import com.etapa2.Server;
import java.util.List;

public class Seleccionador implements Selector {

    public Seleccionador() {

    }

    @Override
    public Server serverActual(List<Server> listaServers) {
        Server serverActual;
        serverActual = listaServers.getFirst();
        int tamColaMin = serverActual.getCola().largo();


        for (Server s : listaServers) {
            if (!s.estaOcupado()) {
                return s;

            } else if (s.getCola().largo() < tamColaMin) {
                tamColaMin = s.getCola().largo();
                serverActual = s;
            }
        }

        return serverActual;
    }
}
