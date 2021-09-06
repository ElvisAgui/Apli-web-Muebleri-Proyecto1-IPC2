
package com.proyectoipc.Entidades;

import java.sql.Date;

/**
 *
 * @author elvis_agui
 */
public class Devolucion extends Venta {

    public Devolucion() {
    }

    public Devolucion(String mueble_ensamblado, String cliente, String vendedor, double ganancia, Date fecha, String correlativo) {
        super(mueble_ensamblado, cliente, vendedor, ganancia, fecha, correlativo);
    }
    
    
    
    
}
