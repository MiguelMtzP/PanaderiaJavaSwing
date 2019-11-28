/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author miguel
 */
public class BalanceCorte {
    
    public float gananciasVentas;
    public float perdidasMermas;
    public float gananciasPedidos;
    public float fugasPedidosExternos;
    
    public BalanceCorte(){
        this.gananciasVentas = 0;
        this.perdidasMermas = 0;
        this.gananciasPedidos = 0;
        this.fugasPedidosExternos = 0;
    }
    public float getBalanceTotal(){
        return gananciasVentas+perdidasMermas+gananciasPedidos+fugasPedidosExternos;
    }
}
