package aplicaciones;

import java.util.ArrayList;
import java.util.Scanner;

import modelo.dao.CatalogoProductos;
import modelo.javabeans.Producto;

public class AppCatalogo {
	private static Scanner leer;
	private static CatalogoProductos cdao;
	
	static {
		leer = new Scanner(System.in);
		cdao = new CatalogoProductos();
	}

	public static void main(String[] args) {
		int opcion = 0;
		
		do {
			opcion = pintarmenu();
			if (opcion == 6)
				break;
			switch(opcion) {
			case 1:
				procPorFamilia();
				break;
			case 2:
				procPorProveedor();
				break;
			case 3:
				procPrecioMayor();
				break;
			case 4:
				procDescripcion();
				break;
				
			case 5:
				procEliminar();
				break;
				
			}
			
		}while(opcion >=1 && opcion<=6);
		
		System.out.println("fin de programa");
	}
	
	public static void procPorFamilia() {
		System.out.println("LISTADO POR FAMILIA");
		System.out.println("Introduzca el id de la familia");
		int id = leer.nextInt(); //lee el id de la familia
		ArrayList<Producto> fam = cdao.buscarPorFamilia(id); //se recorre el array para mirar el id
		if (fam != null) { //si el dato introducido no es nulo nos lo devolverá.
			System.out.println(fam);
		}else
			System.out.println("No existe");
	}
	
	public static void procPorProveedor() {
		System.out.println("LISTADO POR PROVEEDOR");
		System.out.println("Introduzca el proveedor");
		String prov = leer.next(); //leer el provedor
		ArrayList<Producto> pro = cdao.productosPorProveedor(prov); //se recorre el array
		if (pro != null) { //si el proveedor se ha introducido bien, saldrá.
			System.out.println(pro);
		}else
			System.out.println("El provedor no existe");
	}
	
	public static void procPrecioMayor() {
		System.out.println("LISTADO POR PRECIO MAYOR");
		System.out.println("Introducir precio");
		double prec = leer.nextDouble();
		System.out.println(cdao.buscarPorPrecioMayorQue(prec)); //se mira si el precio es mayor que el precio consultado
		
	}
	
	public static void procDescripcion() {
		System.out.println("LISTADO POR DESCRIPCION");
		System.out.println("Introduzca descripcion");
		String des = leer.next(); //se lee la descripción
		ArrayList<Producto> descri = cdao.buscarDescripcionLarga(des); //se recorre el array para buscar esa descripción.
		if (descri != null) { //si el dato introducido no es nulo nos lo devolverá.
			System.out.println(descri);
		}else
			System.out.println("No es la descripción correcta");
	}
	
	public static void procEliminar() { //EN EL DE ELEGIR HE DEDICIDO HACER UN METODO QUE ELIMINA UN PRODUCTO.
		System.out.println("ELIMINAR PRODCUTO");
		int productoId = leer.nextInt();
		if (cdao.eliminarProducto(productoId)) { //si el producto pedido coindice se procederá al borrado.
			System.out.println("Eliminado");
		}else
			System.out.println("No se ha eliminado correctamente");
	}
	
	public static int pintarmenu() {
		int opcion = 0;
		System.out.println("1.- Listar Productos de una Familia");
		System.out.println("2.- Listar Productos de un Proveedor");
		System.out.println("3.- Listar productos por Precio mayor");
		System.out.println("4.- Listar Productos por Proveedor");
		System.out.println("5.- Eliminar producto");
		System.out.println("6.- Salir");
		System.out.println("teclee opcion 1 a 5, 6 salir");
		opcion = leer.nextInt();
		
		while (opcion < 1 || opcion > 6) {
			System.out.println("opcion incorrecta");
			opcion = leer.nextInt();
		}
		
		return opcion;
		
	}

}
