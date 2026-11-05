package veterinaria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class ControladorVeterinaria implements ActionListener {
    private VistaVeterinaria vista;
    private String nombreArchivo = "citas_veterinaria.txt";

    public ControladorVeterinaria(VistaVeterinaria vista) {
        this.vista = vista;
        this.vista.btnRegistrar.addActionListener(this);
        this.vista.btnSalir.addActionListener(this);
        cargarDatosTablaInicial();
    }

    private void cargarDatosTablaInicial() {
        ArrayList<String[]> listaCitas = new ArrayList<>();
        try {
            File archivo = new File(nombreArchivo);
            if (archivo.exists()) {
                Scanner scanner = new Scanner(archivo);
                while (scanner.hasNextLine()) {
                    String[] datos = scanner.nextLine().split(",");
                    listaCitas.add(datos);
                }
                scanner.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String[] cita : listaCitas) {
            vista.modeloTabla.addRow(cita);
        }
    }

    private void guardarCitaEnArchivo(String lineaTexto) {
        try {
            File archivo = new File(nombreArchivo);
            PrintWriter pw = new PrintWriter(new FileWriter(archivo, true));
            pw.println(lineaTexto);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnSalir) {
            System.exit(0);
        }

        if (e.getSource() == vista.btnRegistrar) {
            if (vista.txtDuenio.getText().trim().isEmpty() || vista.txtMascota.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Por favor complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                String duenio = vista.txtDuenio.getText();
                String mascota = vista.txtMascota.getText();
                String especie = (String) vista.cmbEspecie.getSelectedItem();
                String servicio = (String) vista.cmbServicio.getSelectedItem();
                double costoBase = Double.parseDouble(vista.txtCosto.getText());
                boolean esFrecuente = vista.chkFrecuente.isSelected();

                MascotaCita cita = new MascotaCita(duenio, mascota, especie, servicio, costoBase);
                MascotaCita.mostrarDetalleEntidad(cita);

                double totalPagar = cita.calcularTotal(esFrecuente);

                guardarCitaEnArchivo(cita.aTextoPlano(esFrecuente));

                vista.modeloTabla.addRow(new Object[]{
                    cita.getNombreDuenio(),
                    cita.getNombreMascota(),
                    cita.getEspecie(),
                    cita.getServicio(),
                    String.format("%.2f", totalPagar)
                });

                vista.txtDuenio.setText("");
                vista.txtMascota.setText("");
                JOptionPane.showMessageDialog(vista, "Cita registrada con éxito.");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "El costo base debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}