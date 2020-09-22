package edu.eci.arep.sparkdocker.documents;


import java.util.Date;

/**
 * The type Message.
 */
public class Message {
    private String mensaje;
    private String fecha;

    /**
     * Instantiates a new Message.
     *
     * @param mensaje the mensaje
     * @param fecha   the fecha
     */
    public Message(String mensaje, String fecha) {
        if (mensaje.contains("<") || mensaje.contains(">") ){
            mensaje = mensaje.replace("<","").replace(">","");
        }
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    /**
     * Gets mensaje.
     *
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Sets mensaje.
     *
     * @param mensaje the mensaje
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Gets fecha.
     *
     * @return the fecha
     */
    public String getFecha() {
        return new Date().toString();
    }

    /**
     * Sets fecha.
     *
     * @param fecha the fecha
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
