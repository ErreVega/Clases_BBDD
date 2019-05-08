package Clases;

public class Lista {

    public class Nodo {
        private Coche coche;
        private Nodo siguiente;
        private Nodo anterior;


        public Nodo(Coche coche) {
            this.coche = coche;
        }

        public Nodo(Nodo otro){
            this.coche = otro.coche;
            this.anterior = otro.anterior;
            this.siguiente = otro.siguiente;
        }

        public Coche getCoche() {
            return coche;
        }

        public void setCoche(Coche coche) {
            this.coche = coche;
        }

        public Nodo getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo siguiente) {
            this.siguiente = siguiente;
        }

        public Nodo getAnterior() {
            return anterior;
        }

        public void setAnterior(Nodo anterior) {
            this.anterior = anterior;
        }

        public boolean comparaContacto(String s){
            boolean igual = false;
            s = s.toLowerCase();
            if (this.getCoche().getColor().toLowerCase().contains(s))
                igual = true;
            else if (this.getCoche().getMarca().toLowerCase().contains(s))
                igual = true;
            else if (this.getCoche().getMatrícula().toLowerCase().contains(s))
                igual = true;
            else if (this.getCoche().getModelo().toLowerCase().contains(s))
                igual = true;
            else {
                String dato = "" + this.getCoche().getAnno();
                if (dato.contains(s))
                    igual = true;
                else
                    dato = "" + this.getCoche().getPrecio();
                if (dato.contains(s))
                    igual = true;            }
            return igual;
        }

    }


    private Nodo primero;

    public Lista(Nodo primero) {
        this.primero = primero;
    }

    public Lista() {
    }

    public void insertar(Nodo nodo) {

        if (this.primero == null) {
            this.primero = nodo;
        } else {
            Nodo iNodo = this.primero;

            while (iNodo.getSiguiente() != null) {
                iNodo = iNodo.getSiguiente();
            }
            iNodo.setSiguiente(nodo);
            nodo.setAnterior(iNodo);
        }
    }

    public void insertar(Coche a) {
        Nodo nuevo = new Nodo(a);
        insertar(nuevo);
    }


    public boolean borrar(Nodo nodo) {
        boolean borrado = false;
        if (this.primero != null) {
            if (this.primero.getCoche().equals(nodo.getCoche())) {
                this.primero.getSiguiente().setAnterior(null);
                this.primero = this.primero.getSiguiente();
                borrado = true;
            } else {
                Nodo iNodo = this.primero;
                while (iNodo != null) {
                    if (iNodo.getCoche().equals(nodo.getCoche())) {
                        try {
                            iNodo.getAnterior().setSiguiente(iNodo.getSiguiente());
                        } catch (NullPointerException e) {
                        }
                        try {
                            iNodo.getSiguiente().setAnterior(iNodo.getAnterior());
                        } catch (NullPointerException e) {
                        }
                        iNodo = null;
                        borrado = true;
                    } else
                        iNodo = iNodo.getSiguiente();
                }
            }
        }
        return borrado;
    }

    public boolean borrar(Coche a) {
        Nodo nodo = new Nodo(a);
        return borrar(nodo);
    }

    public Lista busca(String s) {
        Lista respuesta = new Lista();
        if (this.primero != null) {
            Nodo iNodo = this.primero;
            while (iNodo != null) {
                if (iNodo.comparaContacto(s)) {
                    respuesta.insertar(iNodo.getCoche());
                }
                try {
                    iNodo = iNodo.getSiguiente();
                } catch (NullPointerException e) {
                }
            }
        }
        return respuesta;
    }

    public Coche getCoche(int i) {
        int cont = 0;
        Coche respuesta = null;
        if (this.primero != null) {
            Nodo iNodo = this.primero;
            while (iNodo != null && cont <= i) {
                if (cont == i) {
                    respuesta = iNodo.getCoche();
                    iNodo = null;
                } else {
                    cont++;
                    iNodo = iNodo.getSiguiente();
                }
            }
        }
        return respuesta;
    }


    public String toString() {
        String r = "Lista vacia.";
        if (this.primero != null) {
            r = "\n";
            Nodo iNodo = new Nodo(this.primero);
            int cont = 1;
            while (iNodo != null) {
                r = r + cont + ". _ _ _ _ _" +
                        "\n\tMatrícula: " + iNodo.getCoche().getMatrícula() +
                        "\n\tMarca: " + iNodo.getCoche().getMarca() +
                        "\n\tModelo: " + iNodo.getCoche().getModelo() +
                        "\n\tColor: " + iNodo.getCoche().getColor() +
                        "\n\tAño: " + iNodo.getCoche().getAnno() +
                        "\n\tPrecio: " + iNodo.getCoche().getPrecio() +
                        "\n\n";

                iNodo = iNodo.getSiguiente();
                cont++;
            }
        }

        return r;

    }

    public int size(){
        int cont = 0;
        if (this.primero != null) {
            Nodo iNodo = new Nodo(this.primero);
            while (iNodo != null) {
                iNodo = iNodo.getSiguiente();
                cont++;
            }
        }
        return cont;
    }

    public boolean isEmpty(){
        if (this.primero == null)
            return true;
        else
            return false;
    }



}
