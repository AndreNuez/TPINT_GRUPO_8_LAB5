package dao;


import java.util.List;

import entidad.Provincia;

public interface IDaoProvincia {

	public List<Provincia> ReadAll();

	public Provincia getProvinciaById(int provinciaId);
}
