package CuentaBancaria;

public class TransferenciaConBloqueo {
    
    public static void main(String[] args) {
        
        Cuenta c1 = new Cuenta("ES388388433883838388484", 20500);
        Cuenta c2 = new Cuenta("ES99090499388484889399", 50000);

        System.out.println("===== Simulación de interbloqueo - Brian Cortés =====");
        System.out.println("Saldos iniciales:");
        System.out.println(c1);
        System.out.println(c2);
        System.out.println();

        Thread h1 = new Thread(new Hilo("Brian Cortes", c1, c2), "Brian Cortes");
        Thread h2 = new Thread(new Hilo("Cortes Brian", c2, c1), "Cortes Brian");

        h1.start();
        h2.start();

        try{
            h1.join();
            h2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("Saldos finales:");
        System.out.println(c1);
        System.out.println(c2);
    }
}
