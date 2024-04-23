package modelo.dao;

import java.util.ArrayList;

import modelo.javabeans.Familia;
import modelo.javabeans.Producto;
import modelo.javabeans.Proveedor;

public class CatalogoProductos implements IntCatalogo{
	private ArrayList<Producto> lista;
	
	public CatalogoProductos() {
		lista = new ArrayList<Producto>();
		cargarDatos();
	}
	
	private void cargarDatos() {  //hacemos la carga de datos. En este caso, lo he ejecutado de distintas formas. He introducido un total de cinco productos
		Familia f1 = new Familia(1, "Frutas");
		Proveedor p1 = new Proveedor("32654", "San Francisco", "Agricultura", "sanfrancisco@frutas.es", "España");
		lista.add(new Producto(1, "Manzana Golden Premium", "Manzana", "Gusanito" , 1, f1, p1));
		lista.add(new Producto(2, "Plátano Canario", "Plátano", "Amarilloeselplatano", 2, f1, new Proveedor("12345", "Platacompany", 
	            "Agricultura", "info@platanos.es", "España")));
		lista.add(new Producto(3, "Naranja Valencia Natural", "Naranja", "Naturalvale", 1.50, f1, new Proveedor("54321", "Naranjito", 
	            "Agricultura", "valenaranjas@naranjas.es", "España")));
		lista.add(new Producto(4, "Carne de toro madurada", "Carne de Toro", "OnlyMeat", 5, new Familia(2, "Carnes"), new Proveedor("13579", "Muurico", 
	            "Ganaderia", "carnivoro@carne.es", "Portugal")));
		lista.add(new Producto(5, "Lubina gabardina premium", "Lubina", "Pescanova", 11, new Familia(3, "Pescados"), 
				new Proveedor("11111", "La Mar", "Pesca", "pesca@mar.com", "Francia")));
	}

	@Override
	public boolean altaProducto(Producto producto) {
		if (lista.contains(producto))
			return false;
		else
			return lista.add(producto);
	}

	@Override
	public boolean eliminarProducto(long idProducto) {
		Producto producto = buscarUno(idProducto);
		if (producto != null)
			return lista.remove(producto);
		else 
			return false;
	}

	@Override
	public boolean modificarProducto(Producto producto) {
		int pos = lista.indexOf(producto);
		if (pos != -1) {
			lista.set(pos, producto);
			return true;
		}else
			return false;
			
	}

	@Override
	public Producto buscarUno(long idProducto) {

		for (Producto ele: lista) {
			if (ele.getIdProducto() == idProducto)
				return ele;
		}
		return null;
	}

	@Override
	public ArrayList<Producto> buscarTodos() {
		return lista;
	}

	@Override
	public ArrayList<Producto> buscarPorFamilia(int idFamilia) {
		ArrayList<Producto> aux = new ArrayList<Producto>(); //hacemos un array para buscar los productos
		for (Producto pro: lista) {
			if (pro.getFamilia().getIdFamilia() == idFamilia) //Esto quiere decir que si id familia es igual a id familia nos devolvera el valor.
				aux.add(pro);
		}
		
		return aux;
	}

	@Override
	public ArrayList<Producto> buscarPorPrecioMayorQue(double precio) {
		ArrayList<Producto> aux = new ArrayList<Producto>();
		for (Producto pro: lista) {
			if (pro.getPrecio() > precio) //si el precio es mayor que el precio solicitado nos devolverá el valor.
				aux.add(pro);
		}
		
		return aux;
	}

	@Override
	public ArrayList<Producto> buscarDescripcionLarga(String subCadena) {
		ArrayList<Producto> aux = new ArrayList<Producto>();
		for (Producto pro: lista) {
			if(pro.getDescripcionLarga().equals(subCadena)) //lo mismo que por buscarPorFamilia, pero en este caso se pone un equals ya que es un string.
				aux.add(pro);
		}
		
		return aux;
	}

	@Override
	public ArrayList<Producto> productosPorProveedor(String cif) {
		ArrayList<Producto> aux = new ArrayList<Producto>();
		for (Producto pro: lista) {
			if (pro.getProveedor().getCif().equals(cif)) //mismo ejemplo que el anterior, salvo que aquí tenemos que entrar dentro de provedores a través de producto para ver el cif.
				aux.add(pro);
		}
		return aux;
	}

}
