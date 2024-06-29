package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entidad.Usuario;
import negocioImpl.UsuarioNegocio;
import resources.AppConfig;

@Controller
public class ControladorLogin {
	
	ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
	
	@RequestMapping("Access.do")
	public ModelAndView eventoRedireccionarPrincipal(String txtUsuario, String txtPass)
	{		
		ModelAndView MV = new ModelAndView();		
		Usuario user = (Usuario)appContext.getBean("beanUsuario");
		user.setNombre(txtUsuario);
		user.setPassword(txtPass);
		if (controlLoginUser(user))
		{
		MV.addObject("userLogin", user);
		MV.setViewName("Principal");
		}
		else 
			MV.setViewName("Login");
		//System.out.println(user.getNombre());
		return MV;
	}
	
	private boolean controlLoginUser(Usuario user) {
		UsuarioNegocio userNg = (UsuarioNegocio)appContext.getBean("beanUsuarioNegocio");
		
		return userNg.IsAccessOK(user);
	}
}
