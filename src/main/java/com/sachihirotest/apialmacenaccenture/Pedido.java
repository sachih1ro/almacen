package com.sachihirotest.apialmacenaccenture;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Pedido {

    private int idPedido;
    private int idCliente;
    private String direccion;
    private ArrayList<String> listaProductos;
    private double valorProductos;
    private double valorIVA;
    private double valorDomicilio;
    private LocalDateTime fechaCreacion;
    private String email;


    public Pedido(int idPedido,int idCliente, String direccion, ArrayList<String> listaProductos,
                  double valorProductos, double valorIVA, double valorDomicilio,
                  LocalDateTime fechaCreacion, String email) {

        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.direccion = direccion;
        this.listaProductos = listaProductos;
        this.valorProductos = valorProductos;
        this.valorIVA = valorIVA;
        this.valorDomicilio = valorDomicilio;
        this.fechaCreacion = fechaCreacion;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idCliente=" + idCliente +
                ", direccion='" + direccion + '\'' +
                ", listaProductos=" + listaProductos +
                ", valorProductos=" + valorProductos +
                ", valorIVA=" + valorIVA +
                ", valorDomicilio=" + valorDomicilio +
                ", fechaCreacion=" + fechaCreacion +
                ", email='" + email + '\'' +
                '}';
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<String> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<String> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public double getValorProductos() {
        return valorProductos;
    }

    public void setValorProductos(double valorProductos) {
        this.valorProductos = valorProductos;
    }

    public double getValorIVA() {
        return valorIVA;
    }

    public void setValorIVA(double valorIVA) {
        this.valorIVA = valorIVA;
    }

    public double getValorDomicilio() {
        return valorDomicilio;
    }

    public void setValorDomicilio(double valorDomicilio) {
        this.valorDomicilio = valorDomicilio;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
