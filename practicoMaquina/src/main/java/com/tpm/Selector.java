package com.tpm;

import java.util.List;

public interface Selector {
    public Server serverActual(List<Server> listaServers);
}
