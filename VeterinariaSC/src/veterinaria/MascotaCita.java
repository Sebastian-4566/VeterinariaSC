package veterinaria;

public class MascotaCita {
    private String nombreDuenio;
    private String nombreMascota;
    private String especie;
    private String servicio;
    private double costoBase;

    public MascotaCita(String nombreDuenio, String nombreMascota, String especie, String servicio, double costoBase) {
        this.nombreDuenio = nombreDuenio;
        this.nombreMascota = nombreMascota;
        this.especie = especie;
        this.servicio = servicio;
        this.costoBase = costoBase;
    }

    public double calcularTotal(boolean esClienteFrecuente) {
        return esClienteFrecuente ? costoBase * 0.90 : costoBase;
    }

    public static <T> void mostrarDetalleEntidad(T entidad) {
        System.out.println("Detalle de la entidad: " + entidad.toString());
    }

    public String aTextoPlano(boolean esFrecuente) {
        return nombreDuenio + "," + nombreMascota + "," + especie + "," + servicio + "," + calcularTotal(esFrecuente);
    }

    public String getNombreDuenio() { return nombreDuenio; }
    public String getNombreMascota() { return nombreMascota; }
    public String getEspecie() { return especie; }
    public String getServicio() { return servicio; }
    public double getCostoBase() { return costoBase; }

    @Override
    public String toString() {
        return "Dueño: " + nombreDuenio + " | Mascota: " + nombreMascota + " | Servicio: " + servicio;
    }
}