package org.edu.service;

import org.edu.model.Pedido;

public interface PedidoService {
    public abstract void efetiva(Pedido pedido);
    public abstract void cancela(Pedido pedido);
}
