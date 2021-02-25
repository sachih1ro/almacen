package com.sachihirotest.apialmacenaccenture;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@Repository
public class PedidoDAO {
    private static Pedidos list = new Pedidos();
    static
    {

        list.getListaPedidos().add(
                new Pedido(1,12345, "Carrera 11#14-08",
                        new ArrayList<String>(Arrays.asList("Pan","Agua","Cereal")),
                        110000.0, 20900.0, 0.0,
                        LocalDateTime.of(2021, 2, 24, 17, 12, 1),
                        "juanap@gmail.com"));

        list.getListaPedidos().add(
                new Pedido(2,54321, "Carrera 11#14-18",
                        new ArrayList<String>(Arrays.asList("Lechuga","Tomate")),
                        80000.0, 15200.0, 5000.0,
                        LocalDateTime.of(2021, 1, 28, 17, 12, 1),
                        "adriano@gmail.com"));

        list.getListaPedidos().add(
                new Pedido(3,12321, "Carrera 11#15-19",
                        new ArrayList<String>(Arrays.asList("Manzanas","Mermelada","Galletas")),
                        200000.0, 38000.0, 0.0,
                        LocalDateTime.of(2020, 12, 30, 6, 10, 19),
                        "carlala@gmail.com"));

    }

    public Pedidos getAllPedidos()
    {
        return list;
    }

    public Pedido getPedido(int idPedido) {
        for (Pedido pedido:list.getListaPedidos()){
            if(pedido.getIdPedido() == idPedido){
                return pedido;
            }
        }
        return null;
    }

    public void editPedido(Pedido pedidoEditado){

        Pedido pedidoAEditar = getPedido(pedidoEditado.getIdPedido());

        if (pedidoAEditar != null){
            list.getListaPedidos().set(list.getListaPedidos().indexOf(pedidoAEditar), pedidoEditado);
        }

    }

    public void eliminarPedido(int idPedido){

        Pedido pedidoAEliminar = getPedido(idPedido);

        if (pedidoAEliminar != null){
            list.getListaPedidos().remove(pedidoAEliminar);
        }

    }

    public void addPedido(Pedido pedido) {
        list.getListaPedidos()
                .add(pedido);
    }


}
