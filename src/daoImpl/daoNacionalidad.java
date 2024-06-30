package daoImpl;


import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import dao.IDaoNacionalidad;
import entidad.Nacionalidad;

public class daoNacionalidad implements IDaoNacionalidad {
	@Autowired
    private Conexion conexion;

    public List<Nacionalidad> ReadAll() {

        Session session = conexion.abrirConexion();

        session.beginTransaction();
        @SuppressWarnings({ "unchecked" })
        List<Nacionalidad> list = (List<Nacionalidad>)session.createQuery("from Nacionalidad").list();

        conexion.cerrarConexion();

        return list;
    }
}
