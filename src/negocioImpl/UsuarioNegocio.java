package negocioImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import dao.IDaoUsuario;
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

	//FIN SPRING CORE
	
	@Override
	public boolean Add(Usuario usuario) {
		
		return daoUsuario.Add(usuario);
	}

	@Override
	public List<Usuario> ReadAll() {
		
		return daoUsuario.ReadAll();
	}

	@Override
	public boolean IsAccessOK(Usuario usuario) {
		List<Usuario> listUsers = daoUsuario.ReadAll();
		boolean isAccess = false;
		for (Usuario user : listUsers) {
			  if (user.getNombre().equals(usuario.getNombre()) && user.getPassword().equals(usuario.getPassword())) { // Reemplace 123 con el ID real
			    isAccess = true;
			    break; // Salir del bucle una vez encontrado
			  }
			}
		return isAccess; // daoUsuario.getAccess(usuario);
	}
	
	
}
