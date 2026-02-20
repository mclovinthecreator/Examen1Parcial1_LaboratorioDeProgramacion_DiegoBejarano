/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1parcial1_laboratoriodeprogramacion_diegobejarano;

import javax.swing.ImageIcon;

/**
 *
 * @author diego
 */
public abstract class Rentitem {
    private String code;
    private String nombre;
    private double preciobase;
    private int copiasDisponibles;
    private ImageIcon imagen;

    public Rentitem(String code, String nombre, double preciobase) {
        this.code = code;
        this.nombre = nombre;
        this.preciobase = preciobase;
        this.copiasDisponibles = 0;
        this.imagen = null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPreciobase() {
        return preciobase;
    }

    public void setPreciobase(double preciobase) {
        this.preciobase = preciobase;
    }

    public int getCopiasDisponibles() {
        return copiasDisponibles;
    }

    public void setCopiasDisponibles(int copiasDisponibles) {
        this.copiasDisponibles = copiasDisponibles;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }
    
    public abstract double pagorenta(int dias);

    @Override
    public String toString() {
        return "Rentitem{" + "code=" + code + ", nombre=" + nombre + ", preciobase=" + preciobase + "Lps , copiasDisponibles=" + copiasDisponibles + '}';
    }
        
    
}
