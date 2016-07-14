package util;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


public class ConsultaGenerica {
	private static Logger logger = Logger.getLogger(ConsultaGenerica.class.getName());
	
	public String leScript(String diretorio){
		String script = "";
		try {
			String realPath = getDiretorioReal(diretorio);
			BufferedReader br = new BufferedReader(new FileReader(realPath));
			String linha = "";
			while ((linha = br.readLine()) != null) {
				script += " " + linha; 
			}
			return script;
		} catch (Exception e) {
			logger.error("Exceção não esperada", e);
		}
		return "";
	}
	
	public static String getDiretorioReal(String diretorio) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getServletContext().getRealPath(diretorio);
	}
}
