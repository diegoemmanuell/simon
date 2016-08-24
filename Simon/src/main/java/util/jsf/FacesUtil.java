
package util.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;


public class FacesUtil{
	public static void setMessageSucesso(String tpMsg, String mensagem){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(tpMsg, mensagem));
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, tpMsg, mensagem);
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}
	
	public static void setMessageError(String tpMsg, String mensagem){
		FacesContext context = FacesContext.getCurrentInstance();
		
		context.addMessage(null, new FacesMessage(tpMsg, mensagem));
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, tpMsg, mensagem);
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}
	
	public static void setMessageAdvertencia(String tpMsg, String mensagem){
		FacesContext context = FacesContext.getCurrentInstance();
		
		context.addMessage(null, new FacesMessage(tpMsg, mensagem));
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, tpMsg, mensagem);
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}
	
	public static Object getObjectSession(String key){
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
	}
	
}