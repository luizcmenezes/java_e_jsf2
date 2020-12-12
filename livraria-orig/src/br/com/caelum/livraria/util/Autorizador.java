package br.com.caelum.livraria.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.caelum.livraria.modelo.Usuario;

public class Autorizador implements PhaseListener{

    private static final long serialVersionUID = 1L;

    @Override
    public void afterPhase(PhaseEvent evento) {
    	FacesContext context = evento.getFacesContext();
    	String nomePag = context.getViewRoot().getViewId();
    	
    	System.out.println("Pagina: " + nomePag);
    	
    	if ("/login.xhtml".equals(nomePag)) {
			return;
		}
    	
    	Usuario usuarioLogin = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
    	
    	if (usuarioLogin != null) {
			return;
		}
    	
    	NavigationHandler handler = context.getApplication().getNavigationHandler();
    	handler.handleNavigation(context, null, "login?faces-redirect=true");
    	context.renderResponse();
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}