package veterinaria;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            VistaVeterinaria vista = new VistaVeterinaria();
            new ControladorVeterinaria(vista);
            vista.setVisible(true);
        });
    }
}