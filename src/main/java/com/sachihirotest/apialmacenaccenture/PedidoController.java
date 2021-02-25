package com.sachihirotest.apialmacenaccenture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class PedidoController {

    @Autowired
    private PedidoDAO pedidoDao;

    @GetMapping("/verPedido")
    public Pedido verPedido(@RequestParam(value = "idPedido", defaultValue = "12345") int idPedido) {
        return pedidoDao.getPedido(idPedido);
    }

    @PostMapping(path = "/crearPedido",
            consumes = "application/json")
    public HttpStatus addPedido(
            @RequestBody Pedido pedido)
    {
        int idNuevoPedido = pedidoDao.getAllPedidos().getListaPedidos().size() + 1;
        boolean pedible = false;

        pedido.setIdPedido(idNuevoPedido);

        // Verificación de costos de IVA y Domicilio
        if(pedido.getValorProductos() > 70000.0){
            pedible = true;
            if(pedido.getValorProductos() > 100000.0){
                pedido.setValorIVA(pedido.getValorProductos()*0.19);
                pedido.setValorDomicilio(0.0);
            }else{
                pedido.setValorIVA(pedido.getValorProductos()*0.19);
                pedido.setValorDomicilio(5000.0);
            }
        }
        // Verificación de creación de pedido
        if(pedible){
            pedidoDao.addPedido(pedido);
            return HttpStatus.ACCEPTED;
        }else{
            return HttpStatus.BAD_REQUEST;
        }

    }


    @PostMapping(path = "/editarPedido",
            consumes = "application/json")
    public HttpStatus editPedido(@RequestBody Pedido pedidoEditado)
    {
        boolean editable = false;
        Pedido pedidoAnterior = pedidoDao.getPedido(pedidoEditado.getIdPedido());

        if(pedidoAnterior != null){

            Duration diffTime = Duration.between(pedidoAnterior.getFechaCreacion(),
                    pedidoEditado.getFechaCreacion());

            if((!diffTime.isNegative()) && (diffTime.toHours() < 5)){
                // Verificación de costos de pedidos
                if(pedidoEditado.getValorProductos() >= pedidoAnterior.getValorProductos()){
                    if(pedidoEditado.getValorProductos() > 70000){
                        if(pedidoEditado.getValorProductos() > 100000.0){
                            pedidoEditado.setValorIVA(pedidoEditado.getValorProductos()*0.19);
                            pedidoEditado.setValorDomicilio(0.0);
                        }else{
                            pedidoEditado.setValorIVA(pedidoEditado.getValorProductos()*0.19);
                            pedidoEditado.setValorDomicilio(5000.0);
                        }

                        pedidoDao.editPedido(pedidoEditado);
                        return HttpStatus.ACCEPTED;

                    }else{
                        return HttpStatus.BAD_REQUEST;
                    }
                }else{
                    return HttpStatus.BAD_REQUEST;
                }
            }else{
                return HttpStatus.BAD_REQUEST;
            }
        }else{
            return HttpStatus.BAD_REQUEST;
        }

    }

    @PostMapping(path = "/eliminarPedido")
    public HttpStatus deletePedido(@RequestParam(value = "idPedido", defaultValue = "12345") int idPedido)
    {

        Pedido pedido = pedidoDao.getPedido(idPedido);

        if (pedido != null){

            /*

            // Para pruebas de eliminación pedido 1

            Duration diffTime = Duration.between(pedido.getFechaCreacion(),
            LocalDateTime.of(2021, 2, 24, 18, 12, 1));

             */

            Duration diffTime = Duration.between(pedido.getFechaCreacion(),
            LocalDateTime.now());

            if(!diffTime.isNegative()){

                if(diffTime.toHours() < 12){
                    // Antes de 12 horas: eliminación del pedido sin problemas
                    pedidoDao.eliminarPedido(idPedido);
                }else{
                    // Despues de 12 horas: el pedido es "eliminado", pero dado que se le cobrará
                    // al cliente el 10% de su valor como multa, este es en realidad editado
                    // para ejecutar dicho cobro

                    pedido.setListaProductos(new ArrayList<String>(Arrays.asList("Multa por cancelación " +
                            "de pedido pasadas las 12 horas.")));
                    pedido.setValorProductos(pedido.getValorProductos()*0.1);
                    pedido.setValorIVA(0.0);
                    pedido.setValorDomicilio(0.0);
                    pedidoDao.editPedido(pedido);
                }
                return HttpStatus.ACCEPTED;
            }else{
                return HttpStatus.BAD_REQUEST;
            }
        }else{
            return HttpStatus.BAD_REQUEST;
        }

    }

}
