package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorInicio {

	@RequestMapping("login.do")
	public ModelAndView eventoRedireccionarPag1()
	{
		ModelAndView MV = new ModelAndView();
		MV.setViewName("Login");
		return MV;
	}
}
