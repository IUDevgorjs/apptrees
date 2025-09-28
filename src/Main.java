import java.util.Scanner;

// Clase principal donde está el menú y la interacción con el usuario
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArbolBinario arbol = new ArbolBinario();
        int opcion;

        do {
            // Mostramos el menú
            System.out.println("\n--- ÁRBOL BINARIO ---");
            System.out.println("1. Insertar número");
            System.out.println("2. Mostrar recorrido en orden");
            System.out.println("3. Buscar número");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");

            // Validamos que lo que ingrese sea un número
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.print("Ingresa el número a insertar: ");
                        if (scanner.hasNextInt()) {
                            int valor = scanner.nextInt();
                            arbol.insertar(valor);
                            System.out.println("Número insertado en el árbol.");
                        } else {
                            System.out.println("Debes ingresar un número válido.");
                            scanner.next(); // limpia el dato incorrecto
                        }
                        break;
                    case 2:
                        System.out.print("Recorrido inorden: ");
                        arbol.inOrden();
                        System.out.println();
                        break;
                    case 3:
                        System.out.print("Ingresa el número a buscar: ");
                        if (scanner.hasNextInt()) {
                            int valor = scanner.nextInt();
                            if (arbol.buscar(valor)) {
                                System.out.println("El número " + valor + " SÍ se encuentra en el árbol.");
                            } else {
                                System.out.println("El número " + valor + " NO se encuentra en el árbol.");
                            }
                        } else {
                            System.out.println("Debes ingresar un número válido.");
                            scanner.next(); // limpia el dato incorrecto
                        }
                        break;
                    case 4:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción incorrecta, intenta nuevamente.");
                }
            } else {
                System.out.println("Opción incorrecta, intenta nuevamente.");
                scanner.next(); // limpia lo que no es número
                opcion = 0; // reiniciamos opción
            }

        } while (opcion != 4);

        scanner.close();
    }
}

// Clase que representa cada nodo del árbol
class Nodo {
    int valor;       // número que guardará el nodo
    Nodo izquierdo;  // hijo izquierdo
    Nodo derecho;    // hijo derecho

    // Constructor para crear un nuevo nodo con un valor
    public Nodo(int valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
    }
}

// Clase que representa el Árbol Binario y sus operaciones
class ArbolBinario {
    Nodo raiz; // raíz del árbol (primer nodo que se inserta)

    // Insertar un número en el árbol
    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

    // Buscar la posición correcta donde poner el número
    private Nodo insertarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            // Si no hay nodo aquí, creamos uno nuevo
            return new Nodo(valor);
        }

        // Si el valor es menor, va al lado izquierdo
        if (valor < actual.valor) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, valor);
        }
        // Si el valor es mayor, va al lado derecho
        else if (valor > actual.valor) {
            actual.derecho = insertarRecursivo(actual.derecho, valor);
        }
        // Si es igual, no lo insertamos (evitamos duplicados)

        return actual;
    }

    // Recorrer el árbol en orden (izquierda - raíz - derecha)
    public void inOrden() {
        inOrdenRecursivo(raiz);
    }

    private void inOrdenRecursivo(Nodo nodo) {
        if (nodo != null) {
            inOrdenRecursivo(nodo.izquierdo);  // recorre lado izquierdo
            System.out.print(nodo.valor + " "); // muestra el valor
            inOrdenRecursivo(nodo.derecho);   // recorre lado derecho
        }
    }

    // Buscar un número en el árbol
    public boolean buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private boolean buscarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            // Si llegamos a un nodo vacío, el número no está en el árbol
            return false;
        }
        if (valor == actual.valor) {
            // Si encontramos el valor en un nodo
            return true;
        }
        // Si el valor es menor, seguimos buscando en la izquierda
        return valor < actual.valor
                ? buscarRecursivo(actual.izquierdo, valor)
                : buscarRecursivo(actual.derecho, valor);
    }
}