/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1parcial1_laboratoriodeprogramacion_diegobejarano;

import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class Game extends Rentitem implements Menudeacciones{
    private Calendar fechadepublicacion;
    private ArrayList<String> especificaciones;

    public Game(String code, String nombre) {
        super(code, nombre, 20);
        this.fechadepublicacion = Calendar.getInstance();
        this.especificaciones = new ArrayList<String>();
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
    @Override
    public double pagorenta(int dias){
        return getPreciobase()*dias;
    }
    
    @Override
    public String toString(){
        int anio = fechadepublicacion.get(Calendar.YEAR);
        int mes = fechadepublicacion.get(Calendar.MONTH)+1;
        int dia = fechadepublicacion.get(Calendar.DAY_OF_MONTH);
        return super.toString() + " Publicacion: " + dia + "/"+mes+"/"+anio+" PS3 Game";
    }
    @Override
    public void ejecutaropcion(int op){
        switch(op){
            case 1:
                String year = JOptionPane.showInputDialog("Ingrese el anio: ");
                String messtr = JOptionPane.showInputDialog("Ingrese el mes (1-12): ");
                String diastr = JOptionPane.showInputDialog("Ingrese el dia: ");
                if(year != null && messtr != null && diastr != null){
                    setFechaPublicacion(Integer.parseInt(year),Integer.parseInt(messtr),Integer.parseInt(diastr));
                    JOptionPane.showMessageDialog(null, "Fecha actualizada correctamente.");
                }
                break;
            case 2:
                String specicifacion = JOptionPane.showInputDialog("Ingrese la especificacion: ");
                if(specicifacion != null && !specicifacion.trim().equals("")){
                    agregarEspecificacion(specicifacion);
                    JOptionPane.showMessageDialog(null, "Especificacion agregada.");
                }
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "Especificaciones de "+getNombre()+":\n"+getEspecificacionesTexto());
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opcion no valida");
                        break;
        }
    }
    @Override
    public void submenu(){
        String op = "SUBMENU DE" + getNombre() + "\n"
                + "1. Actualizar fecha de publicacion\n"
                + "2. Agregar especificacion\n"
                + "3. Ver especificaciones\n"
                + "0. Salir";
        JOptionPane.showMessageDialog(null, op);
    }
}
