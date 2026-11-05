package veterinaria;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaVeterinaria extends JFrame {
    public JTextField txtDuenio, txtMascota, txtCosto;
    public JComboBox<String> cmbEspecie, cmbServicio;
    public JCheckBox chkFrecuente;
    public JButton btnRegistrar, btnSalir;
    public JTable tablaCitas;
    public DefaultTableModel modeloTabla;

    public VistaVeterinaria() {
        setTitle("Gestión de Turnos - Veterinaria");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(7, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos de la Cita"));

        panelForm.add(new JLabel("Dueño:"));
        txtDuenio = new JTextField();
        panelForm.add(txtDuenio);

        panelForm.add(new JLabel("Mascota:"));
        txtMascota = new JTextField();
        panelForm.add(txtMascota);

        panelForm.add(new JLabel("Especie:"));
        cmbEspecie = new JComboBox<>(new String[]{"Perro", "Gato", "Otro"});
        panelForm.add(cmbEspecie);

        panelForm.add(new JLabel("Servicio:"));
        cmbServicio = new JComboBox<>(new String[]{"Consulta General", "Vacunación", "Peluquería"});
        panelForm.add(cmbServicio);

        panelForm.add(new JLabel("Costo Base ($):"));
        txtCosto = new JTextField("20.0");
        panelForm.add(txtCosto);

        chkFrecuente = new JCheckBox("¿Cliente Frecuente?");
        panelForm.add(chkFrecuente);
        panelForm.add(new JLabel("")); 

        btnRegistrar = new JButton("Registrar Cita");
        panelForm.add(btnRegistrar);

        btnSalir = new JButton("Salir");
        panelForm.add(btnSalir);

        add(panelForm, BorderLayout.WEST);

        modeloTabla = new DefaultTableModel(new String[]{"Dueño", "Mascota", "Especie", "Servicio", "Total ($)"}, 0);
        tablaCitas = new JTable(modeloTabla);
        add(new JScrollPane(tablaCitas), BorderLayout.CENTER);
    }
}