package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entidad.PerfilUsuario;
import entidad.Usuario;
import negocioImpl.UsuarioNegocio;

@Controller
public class ControladorLogin {

	@Autowired
	@Qualifier("servicioUsuario")
	private UsuarioNegocio userNg;

	@RequestMapping("Access.do")
	public ModelAndView eventoRedireccionarPrincipal(Usuario user, HttpSession session) {
		ModelAndView MV = new ModelAndView();

		if (controlLoginUser(user, session)) {
			MV.addObject("userLogin", user);
			session.setAttribute("NombreUsuario", user.getNombre());
			MV.setViewName("Principal");
		} else {
			MV.addObject("cartelError", "Usuario o contraseña incorrecto");
			session.setAttribute("PerfilUsuario", PerfilUsuario.INVALIDO);
			MV.setViewName("Login");
		}
		return MV;

	}

	private boolean controlLoginUser(Usuario user, HttpSession session) {

		if (session.getAttribute("PerfilUsuario") != null) {
			user = asignarPerfilInvitado(user);
			return true;
		}
		user = userNg.getUsuarioDB(user);
		return user.getPerfil() == 0 ? false : true;
	}

	private Usuario asignarPerfilInvitado(Usuario user) {

		return userNg.getPerfilInvitado(user);
	}

}
