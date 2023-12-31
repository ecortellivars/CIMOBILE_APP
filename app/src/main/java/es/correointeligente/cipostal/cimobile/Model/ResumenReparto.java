package es.correointeligente.cipostal.cimobile.Model;

public class ResumenReparto {
    private Integer totFicheros;
    private Integer totNotificaciones;
    private Integer totNotifGestionadas;
    private Integer totNotifPendientesSegundoHoy;
    private Integer totNotifPendientesSegundoOtroDia;
    private Integer totNotifMarcadas;
    private Integer totNumListaNA;
    private Integer totNumListaCert;
    private Integer totFotos;
    private Integer totResultadosReparto;

    private Integer numEntregados;
    private Integer numDirIncorrectas;
    private Integer numAusentes;
    private Integer numAusentesPendientes;
    private Integer numDesconocidos;
    private Integer numFallecidos;
    private Integer numRehusados;
    private Integer numNadieSeHaceCargo;
    private Integer numNadieSeHaceCargoPendientes;
    private Integer numEntregadosEnOficina;
    private Integer numNoEntregadosEnOficna;


    public ResumenReparto() {
        super();
    }

    public Integer getTotFicheros() {
        return totFicheros;
    }

    public void setTotFicheros(Integer totFicheros) {
        this.totFicheros = totFicheros;
    }

    public Integer getTotNotificaciones() {
        return totNotificaciones;
    }

    public void setTotNotificaciones(Integer totNotificaciones) { this.totNotificaciones = totNotificaciones; }

    public Integer getTotNotifGestionadas() {
        return totNotifGestionadas;
    }

    public void setTotNotifGestionadas(Integer totNotifGestionadas) { this.totNotifGestionadas = totNotifGestionadas; }

    public Integer getTotNotifMarcadas() {
        return totNotifMarcadas;
    }

    public void setTotNotifMarcadas(Integer totNotifMarcadas) { this.totNotifMarcadas = totNotifMarcadas; }

    public Integer getTotFotos() {
        return totFotos;
    }

    public void setTotFotos(Integer totFotos) { this.totFotos = totFotos; }

    public Integer getNumEntregados() { return numEntregados; }

    public void setNumEntregados(Integer numEntregados) { this.numEntregados = numEntregados; }

    public Integer getNumDirIncorrectas() {
        return numDirIncorrectas;
    }

    public void setNumDirIncorrectas(Integer numDirIncorrectas) { this.numDirIncorrectas = numDirIncorrectas; }

    public Integer getNumAusentes() {
        return numAusentes;
    }

    public void setNumAusentes(Integer numAusentes) {
        this.numAusentes = numAusentes;
    }

    public Integer getNumDesconocidos() {
        return numDesconocidos;
    }

    public void setNumDesconocidos(Integer numDesconocidos) { this.numDesconocidos = numDesconocidos; }

    public Integer getNumFallecidos() {
        return numFallecidos;
    }

    public void setNumFallecidos(Integer numFallecidos) {
        this.numFallecidos = numFallecidos;
    }

    public Integer getNumRehusados() {
        return numRehusados;
    }

    public void setNumRehusados(Integer numRehusados) {
        this.numRehusados = numRehusados;
    }

    public Integer getNumNadieSeHaceCargo() {
        return numNadieSeHaceCargo;
    }

    public void setNumNadieSeHaceCargo(Integer numNadieSeHaceCargo) { this.numNadieSeHaceCargo = numNadieSeHaceCargo; }

    public Integer getNumAusentesPendientes() { return numAusentesPendientes; }

    public void setNumAusentesPendientes(Integer numAusentesPendientes) { this.numAusentesPendientes = numAusentesPendientes; }

    public Integer getNumNadieSeHaceCargoPendientes() { return numNadieSeHaceCargoPendientes; }

    public void setNumNadieSeHaceCargoPendientes(Integer numNadieSeHaceCargoPendientes) { this.numNadieSeHaceCargoPendientes = numNadieSeHaceCargoPendientes; }

    public Integer getTotNotifPendientesSegundoHoy() { return totNotifPendientesSegundoHoy; }

    public void setTotNotifPendientesSegundoHoy(Integer totNotifPendientesSegundoHoy) { this.totNotifPendientesSegundoHoy = totNotifPendientesSegundoHoy; }

    public Integer getTotNotifPendientesSegundoOtroDia() { return totNotifPendientesSegundoOtroDia; }

    public void setTotNotifPendientesSegundoOtroDia(Integer totNotifPendientesSegundoOtroDia) { this.totNotifPendientesSegundoOtroDia = totNotifPendientesSegundoOtroDia; }

    public Integer getNumEntregadosEnOficina() {return numEntregadosEnOficina;  }

    public void setNumEntregadosEnOficina(Integer numEntregadosEnOficina) {this.numEntregadosEnOficina = numEntregadosEnOficina; }

    public Integer getNumNoEntregadosEnOficna() {return numNoEntregadosEnOficna;}

    public void setNumNoEntregadosEnOficna(Integer numNoEntregadosEnOficna) {this.numNoEntregadosEnOficna = numNoEntregadosEnOficna;}

    public Integer getTotNumListaCert() {return totNumListaCert;}

    public void setTotNumListaCert(Integer totNumListaCert) {this.totNumListaCert = totNumListaCert;}

    public Integer getTotResultadosReparto() {return totResultadosReparto;}

    public void setTotResultadosReparto(Integer totResultadosReparto) {this.totResultadosReparto = totResultadosReparto;}

    public Integer getTotNumListaNA() {return totNumListaNA;}

    public void setTotNumListaNA(Integer totNumListaNA) {this.totNumListaNA = totNumListaNA;}

}
