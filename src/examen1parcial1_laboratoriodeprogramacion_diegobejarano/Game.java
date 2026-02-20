/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1parcial1_laboratoriodeprogramacion_diegobejarano;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author diego
 */
public class Game extends Rentitem implements MenuActions{
    private Calendar fechadepublicacion;
    private ArrayList<String> especificaciones;

    public Game(String code, String nombre, double preciobase) {
        super(code, nombre, preciobase);
        this.fechadepublicacion = fechadepublicacion;
        this.especificaciones = especificaciones;
    }

    public Calendar getFechadepublicacion() {
        return fechadepublicacion;
    }

    public void setFechadepublicacion(Calendar fechadepublicacion) {
        this.fechadepublicacion = fechadepublicacion;
    }

    public ArrayList<String> getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(ArrayList<String> especificaciones) {
        this.especificaciones = especificaciones;
    }
    
    public void setFechaPublicacion(int anio, int mes, int dia){
        fechadepublicacion.set(Calendar.YEAR, anio);
        fechadepublicacion.set(Calendar.MONTH, mes - 1);
        fechadepublicacion.set(Calendar.DAY_OF_MONTH, dia);
    }
    public void agregarEspecificacion(String especificacion){
        especificaciones.add(especificacion);
    }
    public void listaEspecificaciones(){
        listaEspecificacionesRecursivo(0);
    }
    private void listaEspecificacionesRecursivo(int in){
        if(in >= especificaciones.size()){
            return;
        }else{
            System.out.println("  - "+especificaciones.get(in));
            listaEspecificacionesRecursivo(in + 1);
        }
        
    }
    public String getEspecificacionesTexto(){
            if(especificaciones.size() == 0){
                return "Sin especificaciones agregadas";
            }else{
                String mensaje = "";
                for (int i = 0; i < especificaciones.size(); i++) {
                    mensaje = mensaje + "- " + especificaciones.get(i) + "\n";
                }
                   return mensaje;
            }
         
        }
}
