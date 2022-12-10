package co.com.jungla.sac.presentacion.ventanas;

import co.com.jungla.sac.negocio.delegados.DelegadoRecordatorio;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		VentPrincipal ventPrincipal = new VentPrincipal();
		ventPrincipal.setVisible(true);
		
		DelegadoRecordatorio delegadoRecordatorio = new DelegadoRecordatorio();
		if(delegadoRecordatorio.listarRecordatorios().get(0).isMostrarAlInicio()==true){
			VentMostrarRecordatorios ventMostrarRecordatorios = new VentMostrarRecordatorios();
			ventMostrarRecordatorios.setVisible(true);
		}
	}

}
