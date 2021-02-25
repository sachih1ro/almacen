package com.sachihirotest.apialmacenaccenture;

import java.util.ArrayList;
import java.util.List;

public class Pedidos {

    private List<Pedido> listaPedidos;

    public List<Pedido> getListaPedidos() {
        if (listaPedidos == null) {
            listaPedidos= new ArrayList<>();
        }
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }


}

