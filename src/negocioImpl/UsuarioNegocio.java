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


	@Override
	public Usuario getUsuarioDB(Usuario userLogin) {
	    // Obtener la lista de usuarios de la base de datos
	    List<Usuario> usuarios = this.ReadAll();

	    // Recorrer la lista de usuarios para buscar el usuario por nombre y contraseña
	    for (Usuario user : usuarios) {
	        if (user.getNombre().equals(userLogin.getNombre()) && user.getPassword().equals(userLogin.getPassword())) {
	            // Copiar los datos del usuario encontrado al usuario de login
	            userLogin.setId(user.getId());
	            userLogin.setPerfil(user.getPerfil());
	            userLogin.setPassword(""); // Borra la contraseña ingresada por seguridad
	            // No se copian datos adicionales específicos del perfil aquí

	            return userLogin; // Devolver el usuario encontrado y actualizado
	        }
	    }
	    return null; // Devolver null si no se encontró el usuario con las credenciales especificadas
	}


	@Override
	public Usuario getPerfilInvitado(Usuario user) {
		// TODO Auto-generated method stub
		user.setPerfil(PerfilUsuario.INVITADO.getPerfilUsuario());
		return user;
	}
}
