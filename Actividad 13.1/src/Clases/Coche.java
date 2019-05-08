package Clases;

public class Coche {

    private  String matrícula,
                    marca,
                    modelo,
                    color;
    private int     anno,
                    precio;

    public Coche(String matrícula, String marca, String modelo, String color, int anno, int precio) {
        this.matrícula = matrícula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.anno = anno;
        this.precio = precio;
    }

    public String getMatrícula() {
        return matrícula;
    }

    public void setMatrícula(String matrícula) {
        this.matrícula = matrícula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "matrícula='" + matrícula + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                ", anno=" + anno +
                ", precio=" + precio +
                "} \n";
    }
}
