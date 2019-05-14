package Clases;

public class Cuidador {
    private String nombre;
    private boolean veterinario;

    public Cuidador(String nombre, boolean veterinario) {
        this.nombre = nombre;
        this.veterinario = veterinario;
    }

    public Cuidador() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isVeterinario() {
        return veterinario;
    }

    public void setVeterinario(boolean veterinario) {
        this.veterinario = veterinario;
    }
}
