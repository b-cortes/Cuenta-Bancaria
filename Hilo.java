package CuentaBancaria;

public class Hilo implements Runnable{
    
    private String nombre;
    private Cuenta cuenta1;
    private Cuenta cuenta2;

    public Hilo(String nombre, Cuenta cuenta1, Cuenta cuenta2) {
        this.nombre = nombre;
        this.cuenta1 = cuenta1;
        this.cuenta2 = cuenta2;
    }

    @Override 
    public void run() {
        for(int i = 1; i <= 1000; i++) {
            boolean hecha = Transferencia.transferencia(cuenta1, cuenta2, 10);
            if(hecha) {
                System.out.println(nombre + " -> transferencia " + i + ": 10 € de " + cuenta1.getNumeroCuenta() + " a " + cuenta2.getNumeroCuenta());
            } else {
                System.out.println(nombre + " -> transferencia " + i + ": saldo insuficiente");
            }
        }
        System.out.println(nombre + " han terminado sus 1000 transferencias");
    }
}
