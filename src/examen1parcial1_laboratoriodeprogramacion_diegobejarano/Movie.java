/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1parcial1_laboratoriodeprogramacion_diegobejarano;

import java.util.Calendar;

/**
 *
 * @author diego
 */
public class Movie extends Rentitem {

    private Calendar fechaEstreno;

    public Movie(String code, String nombre, double preciobase) {
        super(code, nombre, preciobase);
        this.fechaEstreno = Calendar.getInstance(); // esta linea pone la fecha actual por defecto
    }

    public Calendar getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(Calendar fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public String getEstado() {
        Calendar ahora = Calendar.getInstance();
        Calendar limite = (Calendar) fechaEstreno.clone();
        limite.add(Calendar.MONTH, 3);

        if (ahora.before(limite)) {
            return "Estreno";
        } else {
            return "Normal";
        }

    }
    @Override
    public double pagorenta(int dias){
        double total = getPreciobase() * dias;
        String estado = getEstado();
        
        if(estado.endsWith("Estreno")&&dias>2){
            int diasextra = dias - 2;
            total = total + (diasextra * 50);
        }else if(estado.endsWith("Normal")&&dias>5){
            int diasextra = dias - 5;
            total = total + (diasextra * 30);
        }
        return total;
    }

    @Override
    public String toString() {
        return super.toString() + " Estado: " + getEstado() + " - Movie";
    }
    
}
