package negocioImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IDaoUsuario;
import entidad.PerfilUsuario;
import entidad.Usuario;
import negocio.IUsuarioNegocio;

@Service("servicioUsuario")
public class UsuarioNegocio implements IUsuarioNegocio {

	@Autowired
	private IDaoUsuario daoUsuario;

	public UsuarioNegocio() {

	}

	public IDaoUsuario getDaoUsuario() {
		return daoUsuario;
	}

	public void setDaoUsuario(IDaoUsuario daoUsuario) {
		this.daoUsuario = daoUsuario;
	}

	// FIN SPRING CORE

	@Override
	public boolean Add(Usuario usuario) {

		return daoUsuario.Add(usuario);
	}

	@Override
	public List<Usuario> ReadAll() {

		return daoUsuario.ReadAll();
	}

	private boolean IsAccessOK(Usuario usuario, Usuario userDb) {
		boolean isAccess = false;
		// Reemplace 123 con el ID real
		if (userDb.getNombre().equals(usuario.getNombre()) && userDb.getPassword().equals(usuario.getPassword())) {
			isAccess = true;			
		}
		return isAccess; // daoUsuario.getAccess(usuario);
	}

	@Override
	public Usuario getUsuarioDB(Usuario userLogin) {
		for (Usuario user : this.ReadAll()) {
			if (this.IsAccessOK(userLogin, user)) {
				userLogin.setId(user.getId());
				userLogin.setPerfil(user.getPerfil());
				userLogin.setPassword(""); // borra la password ingresada para que no quede en el usuario.
				if (userLogin.getPerfil() == PerfilUsuario.MEDICO.getPerfilUsuario())
					userLogin.setMedico(user.getMedico()); // si tiene perfil medico lo completa
				break; // Salir del bucle una vez encontrado
			}
		}
		return userLogin;
	}

	@Override
	public Usuario getPerfilInvitado(Usuario user) {
		// TODO Auto-generated method stub
		user.setPerfil(PerfilUsuario.INVITADO.getPerfilUsuario());
		return user;
	}
}
