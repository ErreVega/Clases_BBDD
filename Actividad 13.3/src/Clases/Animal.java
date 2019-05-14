package Clases;

public class Animal {
    private String nombre,
            color,
            tipoComida,
            cuidador,
            carEspeciales,
            ubicación;

    private int edad,
            cantidadComida; //(kgs/día);


    public Animal() {
    }

    public Animal(String nombre, int edad, String color, String tipoComida, int cantidadComida,
                  String cuidador, String carEspeciales, String ubicación) {
        this.nombre = nombre;
        this.edad = edad;
        this.color = color;
        this.tipoComida = tipoComida;
        this.cantidadComida = cantidadComida;
        this.cuidador = cuidador;
        this.carEspeciales = carEspeciales;
        this.ubicación = ubicación;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(String tipoComida) {
        this.tipoComida = tipoComida;
    }

    public int getCantidadComida() {
        return cantidadComida;
    }

    public void setCantidadComida(int cantidadComida) {
        this.cantidadComida = cantidadComida;
    }

    public String getCuidador() {
        return cuidador;
    }

    public void setCuidador(String cuidador) {
        this.cuidador = cuidador;
    }

    public String getCarEspeciales() {
        return carEspeciales;
    }

    public void setCarEspeciales(String carEspeciales) {
        this.carEspeciales = carEspeciales;
    }

    public String getUbicación() {
        return ubicación;
    }

    public void setUbicación(String ubicación) {
        this.ubicación = ubicación;
    }
}

