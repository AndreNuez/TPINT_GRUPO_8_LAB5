package negocioImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	

}
