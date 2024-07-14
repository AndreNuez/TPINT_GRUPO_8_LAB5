package negocioImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IDaoProvincia;
import daoImpl.daoProvincia;
import entidad.Provincia;
import negocio.IProvinciaNegocio;

@Service("servicioProvincia")
public class ProvinciaNegocio implements IProvinciaNegocio {

	@Autowired
	private IDaoProvincia daoProvincia;

	public ProvinciaNegocio() {

	}

	public ProvinciaNegocio(daoProvincia daoprov) {
		this.daoProvincia = daoprov;
	}

	public IDaoProvincia getDaoProvincia() {
		return daoProvincia;
	}

	public void setDaoProvincia(IDaoProvincia daoProvincia) {
		this.daoProvincia = daoProvincia;
	}

	// FIN SPRING CORE

	public List<Provincia> ReadAll() {

		return daoProvincia.ReadAll();
	}

	public Provincia getProvinciaById  (int provinciaId ) {

		return daoProvincia.getProvinciaById(provinciaId);
	}
}
