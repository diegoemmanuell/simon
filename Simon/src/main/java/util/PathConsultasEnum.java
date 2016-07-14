/**
*Enumresponsavelporguardarocaminhhodosarquivosdeconsulta.
*/
package util;

public enum PathConsultasEnum{
	
	LISTA_TURMAS_POR_SERIE("/WebContent/script/listaTurmasPorSerie.txt"),
	LISTA_TODAS_AS_TURMAS("/WebContent/script/listaTodasAsTurmas.txt");
	
	private String label;
	
	private PathConsultasEnum(String label){
		this.label=label; 
	}
	
	public String getLabel(){
		return label;
	}
}
