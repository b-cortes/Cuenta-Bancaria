package CuentaBancaria;

public class Transferencia {
    
    private Cuenta c1;
    private Cuenta c2;

    public Transferencia(Cuenta c1, Cuenta c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    public static boolean transferencia (Cuenta c1, Cuenta c2, int cantidad) {
        synchronized (c1) {
            if (!c1.sacarCantidad(cantidad)) {
                return false;
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (c2) {
                c2.ingresaCantidad(cantidad);
            }
        }
        return true;
    }

    public Cuenta getC1() {
        return c1;
    }

    public Cuenta getC2() {
        return c2;
    }
}
