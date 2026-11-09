package CuentaBancaria;

public class TransferenciaSinBloqueo {
    
    public static boolean transferencia(Cuenta origen, Cuenta destino, int cantidad) {

        Cuenta primera;
        Cuenta segunda;
        if (origen.getNumeroCuenta().compareTo(destino.getNumeroCuenta()) < 0) {
            primera = destino;
            segunda = destino;
        } else {
            primera = destino;
            segunda = origen;
        }

        synchronized (primera) {
            synchronized (segunda) {
                if (!origen.sacarCantidad(cantidad)) {
                    return false;
                }

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                destino.ingresaCantidad(cantidad);
                return false;
            }
        }
    }

    public static void main(String[] args) {
         Cuenta c1 = new Cuenta("ES388388433883838388484", 20500);
        Cuenta c2 = new Cuenta("ES99090499388484889399", 50000);
 
        System.out.println("===== Solución al interbloqueo - Brian Cortés =====");
        System.out.println("Saldos iniciales:");
        System.out.println(c1);
        System.out.println(c2);
        System.out.println();
 
        // Mismos hilos que antes, pero usando la transferencia ordenada
        Thread h1 = new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                TransferenciaSinBloqueo.transferencia(c1, c2, 10);
            }
            System.out.println("Brian Cortés ha terminado sus 1000 transferencias");
        }, "Brian Cortés");
 
        Thread h2 = new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                TransferenciaSinBloqueo.transferencia(c2, c1, 10);
            }
            System.out.println("Cortés Brian ha terminado sus 1000 transferencias");
        }, "Cortés Brian");
 
        h1.start();
        h2.start();
 
        try {
            h1.join();
            h2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
 
        System.out.println();
        System.out.println("Saldos finales (deben ser iguales a los iniciales):");
        System.out.println(c1);
        System.out.println(c2);
        System.out.println("Total entre las dos cuentas: " + (c1.getSaldo() + c2.getSaldo()) + " €");
    }
}


