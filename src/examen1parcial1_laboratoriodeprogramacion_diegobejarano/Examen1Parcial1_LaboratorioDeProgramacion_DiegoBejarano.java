/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package examen1parcial1_laboratoriodeprogramacion_diegobejarano;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author diego
 */
public class Examen1Parcial1_LaboratorioDeProgramacion_DiegoBejarano {

    static ArrayList<Rentitem> items = new ArrayList<Rentitem>();

    public static void main(String[] args) {
        int op = menu();
        while (op != 5) {
            switch (op) {
                case 1:
                    agregaritem();
                    break;
                case 2:
                    rentaritem();
                    break;
                case 3:
                    ejecutarsubmenu();
                    break;
                case 4:
                    imprimirtodo();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion no valida");
                    break;
            }
            op = menu();
        }

    }

    public static int menu() {
        String opstr = JOptionPane.showInputDialog(null, "MENU\n"
                + "1. Agregar Item\n"
                + "2. Rentar\n"
                + "3. Ejecutar Submenu\n"
                + "4. Imprimir todo\n"
                + "5. Salir");
        if(opstr == null) System.exit(0);

        int op = Integer.parseInt(opstr);
        return op;

    }

    public static void agregaritem() {
        String[] tipos = {"Movie", "Game"};
        int tipo = JOptionPane.showOptionDialog(null, "Que desea agregar", "Tipo de Item", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);
        if (tipo == JOptionPane.CLOSED_OPTION) {
            return;
        }

        String code = JOptionPane.showInputDialog("Ingrese el codigo del item:");
        if (code == null || code.trim().equals("")) {
            return;
        }

        if (buscarItem(code) != null) {
            JOptionPane.showMessageDialog(null, "ERROR: Ese codigo ya extiste, eliga otro");
            return;
        }

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre: ");
        if (nombre == null || nombre.trim().equals("")) {
            return;
        }
        ImageIcon imagen = cargarImagen();

        if (tipo == 0) {
            String preciostr = JOptionPane.showInputDialog("Ingrese el precio base de renta");
            if (preciostr == null) {
                return;
            }
            double precio = Double.parseDouble(preciostr);

            Movie mov = new Movie(code, nombre, precio);

            String cambiar = JOptionPane.showInputDialog("Desea cambiar la fecha de estreno (1)SI, (2)NO");

            if (cambiar != null && cambiar.equals("1")) {
                String y = JOptionPane.showInputDialog("Anio de estreno");
                String m = JOptionPane.showInputDialog("Mes (1-12)");
                String d = JOptionPane.showInputDialog("Dia");
                Calendar cal = Calendar.getInstance();
                cal.set(Integer.parseInt(y), Integer.parseInt(m) - 1, Integer.parseInt(d));
                mov.setFechaEstreno(cal);
            }

            if (imagen != null) {
                mov.setImagen(imagen);
            }
            items.add(mov);
            JOptionPane.showMessageDialog(null, "Movie Agregada\n" + mov.toString());
        } else {
            Game game = new Game(code, nombre);
            if (imagen != null) {
                game.setImagen(imagen);
            }
            items.add(game);
            JOptionPane.showMessageDialog(null, "Game agregado\n" + game.toString());
        }
    }

    public static void rentaritem() {
        String code = JOptionPane.showInputDialog("Ingrese el codigo del item a rentar");
        if (code == null) {
            return;
        }
        Rentitem item = buscarItem(code);

        if (item == null) {
            JOptionPane.showMessageDialog(null, "Item no existe");
            return;
        }
        mostraritemconimagen(item);

        String diasstr = JOptionPane.showInputDialog("Ingrese la cantidad de dias: ");
        if (diasstr == null) {
            return;
        }
        int dias = Integer.parseInt(diasstr);
        double total = item.pagorenta(dias);
        JOptionPane.showMessageDialog(null, "RENTA\n" + item.toString() + "\n Dias: " + dias + "\n Total: " + total + " Lps");

    }

    public static void ejecutarsubmenu() {
        String code = JOptionPane.showInputDialog("Ingrese el codigo del item");
        if (code == null) {
            return;
        }
        Rentitem item = buscarItem(code);

        if (item == null) {
            JOptionPane.showMessageDialog(null, "Item no existe");
            return;
        }
        if (item instanceof Menudeacciones) {
            Menudeacciones menu = (Menudeacciones) item;
            boolean continuar = true;

            while (continuar) {
                menu.submenu();
                String opstr = JOptionPane.showInputDialog("Ingrese la opcion 0 para volver");
                if (opstr == null) {
                    break;
                }
                int op = Integer.parseInt(opstr);
                if (op == 0) {
                    continuar = false;
                } else {
                    menu.ejecutaropcion(op);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Este item no tiene submenu");
        }
    }

    public static void imprimirtodo() {
        if (items.size() == 0) {
            JOptionPane.showMessageDialog(null, "No hay items registrados");
            return;
        }

        JPanel panelprincipal = new JPanel();
        panelprincipal.setLayout(new BoxLayout(panelprincipal, BoxLayout.Y_AXIS));
        panelprincipal.setBackground(new Color(30, 30, 40));

        for (int i = 0; i < items.size(); i++) {
            Rentitem item = items.get(i);
            JPanel tarjeta = crearTarjeta(item);
            panelprincipal.add(tarjeta);
            panelprincipal.add(Box.createVerticalStrut(8));
        }
        JScrollPane scroll = new JScrollPane(panelprincipal);
        scroll.setPreferredSize(new Dimension(620, 420));

        JOptionPane.showMessageDialog(null, scroll, "Lista de Items", JOptionPane.PLAIN_MESSAGE);
    }

    public static Rentitem buscarItem(String code) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getCode().equals(code)) {
                return items.get(i);
            }
        }
        return null;
    }

    public static ImageIcon cargarImagen() {
        int respuesta = JOptionPane.showConfirmDialog(null, "Desea cargar una imagen para este item", "IMAGEN", JOptionPane.YES_NO_OPTION);
        if (respuesta != JOptionPane.YES_OPTION) {
            return null;
        }
        JFileChooser elegir = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagenes (jpg, png, gif)", "jpg", "jpeg", "png", "gif");
        elegir.setFileFilter(filtro);

        int resultado = elegir.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = elegir.getSelectedFile();
            ImageIcon original = new ImageIcon(archivo.getAbsolutePath());
            Image escalada = original.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            return new ImageIcon(escalada);
        }
        return null;
    }

    public static void mostraritemconimagen(Rentitem item) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        if (item.getImagen() != null) {
            JLabel imglabel = new JLabel(item.getImagen());
            panel.add(imglabel, BorderLayout.WEST);
        }

        JTextArea texto = new JTextArea(item.toString());
        texto.setEditable(false);
        texto.setBackground(Color.WHITE);
        texto.setFont(new Font("Arial", Font.PLAIN, 13));
        panel.add(texto, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(null, panel, "Detalles del item", JOptionPane.PLAIN_MESSAGE);

    }

    public static JPanel crearTarjeta(Rentitem item) {
        JPanel tarjeta = new JPanel(new BorderLayout(10, 5));
        tarjeta.setBackground(new Color(50, 50, 65));
        tarjeta.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(100, 100, 160), 1), BorderFactory.createEmptyBorder(8, 8, 8, 8)));

        if (item.getImagen() != null) {
            JLabel imglabel = new JLabel(item.getImagen());
            imglabel.setPreferredSize(new Dimension(80, 80));
            tarjeta.add(imglabel, BorderLayout.WEST);

        } else {
            JLabel noimg = new JLabel("SIN IMAGEN");
            noimg.setForeground(Color.gray);
            noimg.setPreferredSize(new Dimension(80, 80));
            noimg.setHorizontalAlignment(SwingConstants.CENTER);
            tarjeta.add(noimg, BorderLayout.WEST);
        }

        JPanel info = new JPanel(new GridLayout(0, 1));
        info.setBackground(new Color(50, 50, 65));

        JLabel lblnombre = new JLabel("Nombre: " + item.getNombre());
        lblnombre.setForeground(Color.WHITE);
        lblnombre.setFont(new Font("Arial", Font.BOLD, 13));

        JLabel lblcode = new JLabel("Codigo: " + item.getCode());
        lblcode.setForeground(Color.LIGHT_GRAY);

        JLabel lblprecio = new JLabel("Precio Base: " + item.getPreciobase() + " Lps");
        lblprecio.setForeground(new Color(10, 220, 180));

        info.add(lblnombre);
        info.add(lblcode);
        info.add(lblprecio);

        if (item instanceof Movie) {
            Movie m = (Movie) item;
            JLabel lblestado = new JLabel("Estado: " + m.getEstado());
            if (m.getEstado().equals("Estreno")) {

                lblestado.setForeground(new Color(255, 215, 0));

            } else {
                lblestado.setForeground(new Color(150, 200, 255));
            }
            info.add(lblestado);
            JLabel lbltipo = new JLabel("Tipo: Movie");
            lbltipo.setForeground(Color.cyan);
            info.add(lbltipo);
        }else if(item instanceof Game){
            JLabel lbltipo = new JLabel("Tipo: PS3 Game");
            lbltipo.setForeground(new Color(255,160,80));
            info.add(lbltipo);
        }
        
        tarjeta.add(info, BorderLayout.CENTER);
        return tarjeta;
    }
}
