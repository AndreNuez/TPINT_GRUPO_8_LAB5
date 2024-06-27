package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entidad.Usuario;
import resources.AppConfig;

@Controller
public class ControladorLogin {
	
	ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
	
	@RequestMapping("Access.html")
	public ModelAndView eventoRedireccionarPrincipal(String txtUsuario, String txtPass)
	{		
		ModelAndView MV = new ModelAndView();
		MV.setViewName("Login");
		Usuario user = (Usuario)appContext.getBean("beanUsuario");
		user.setNombre(txtUsuario);
		user.setPassword(txtPass);
		MV.addObject(user);
		
		System.out.println(user.getNombre());
		return MV;
	}
}
