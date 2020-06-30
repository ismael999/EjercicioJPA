package es.eoi.app;

import java.util.List;
import java.util.Scanner;

import es.eoi.entity.Banco;
import es.eoi.entity.Cliente;
import es.eoi.entity.Cuenta;
import es.eoi.service.BancoService;
import es.eoi.service.ClienteService;
import es.eoi.service.CuentaService;

public class MundoBancario {

	private static BancoService bancoService = new BancoService();
	private static ClienteService clienteService = new ClienteService();
	private static CuentaService cuentaService = new CuentaService();

	public static void main(String[] args) {
			
		menu();

	}

	public static void menu() {
		System.out.println("************** Menú **************");

		System.out.println("0 - SALIR");
		System.out.println("1 - Crear Cliente");
		System.out.println("2 - Crear Banco");
		System.out.println("3 - Buscar Cliente por DNI");
		System.out.println("4 - Buscar Banco por ID");
		System.out.println("5 - Modificar Cliente");
		System.out.println("6 - Modificar un Banco");
		System.out.println("7 - Eliminar un Cliente");
		System.out.println("8 - Eliminar un Banco");
		System.out.println("9 - Lista de Clientes");
		System.out.println("10 - Lista de los bancos");
		System.out.println("11 - Crear Cuenta");
		System.out.println("12 - Modificar Cuenta");
		System.out.println("13 - Eliminar Cuenta");
		System.out.println("14 - Listar Cuentas por Banco");
		System.out.println("15 - Listar Cuentas por Cliente");
		System.out.println("16 - Listar Clientes y sus cuentas");

		Scanner scan = new Scanner(System.in);

		menuController(scan.nextInt());

		scan.close();
	}

	public static void menuController(int option) {
		switch (option) {
		case 0:
			System.out.println("Adiós!");
			break;
		case 1:
			crearCliente();
			menu();
			break;
		case 2:
			crearBanco();
			menu();
			break;
		case 3:
			buscarCliente();
			menu();
			break;
		case 4:
			buscarBanco();
			menu();
			break;
		case 5:
			modificarCliente();
			menu();
			break;
		case 6:
			modificarBanco();
			menu();
			break;
		case 7:
			eliminarCliente();
			menu();
			break;
		case 8:
			eliminarBanco();
			menu();
			break;
		case 9:
			listarClientes();
			menu();
			break;
		case 10:
			listarBancos();
			menu();
			break;
		case 11:
			crearCuenta();
			menu();
			break;
		case 12:
			modificarCuenta();
			menu();
			break;
		case 13:
			eliminarCuenta();
			menu();
		case 14:
			listarCuentasBancos();
			menu();
			break;
		case 15:
			listarCuentasClientes();
			menu();
			break;
		case 16:
			listarClientesCuentas();
			menu();
			break;
		default:
			System.out.println("Opción no valida.");
			menu();
		}
	}

	public static void crearCliente() {
		System.out.print("DNI: ");
		String dni = new Scanner(System.in).next();

		System.out.print("\nNombre: ");
		String nombre = new Scanner(System.in).next();

		System.out.print("\nDirección: ");
		String direccion = new Scanner(System.in).next();

		Cliente cliente = new Cliente(dni, nombre, direccion);

		clienteService.create(cliente);

	}

	public static void crearBanco() {
		System.out.print("Nombre: ");
		String nombre = new Scanner(System.in).next();

		System.out.println("Ciudad: ");
		String ciudad = new Scanner(System.in).next();

		Banco banco = new Banco(nombre, ciudad);

		bancoService.create(banco);

	}

	public static void buscarCliente() {
		System.out.print("DNI: ");
		String dni = new Scanner(System.in).next();

		Cliente cliente = clienteService.findByDni(dni);
		System.out.println(cliente);
	}

	public static void buscarBanco() {
		System.out.print("ID: ");
		int id = new Scanner(System.in).nextInt();
		Banco banco = bancoService.findById(id);
		if (banco != null) {
			System.out.println("----->" + banco);
		} else {
			System.out.println("-----> No hay ningún banco con ese ID.");
		}

	}

	public static void modificarCliente() {
		System.out.print("DNI del cliente a modificar: ");
		String dni = new Scanner(System.in).next();

		Cliente cliente = clienteService.findByDni(dni);

		if (cliente != null) {
			System.out.print("\nNuevo nombre para (" + cliente.getNombre() + "): ");
			String nombre = new Scanner(System.in).next();

			System.out.print("\nNueva direccion: ");
			String direccion = new Scanner(System.in).nextLine();

			cliente.setNombre(nombre);
			cliente.setDireccion(direccion);

			clienteService.update(cliente);
		} else {
			System.out.println("El cliente no existe.");
		}
	}

	public static void modificarBanco() {
		listarBancos();
		System.out.print("ID del banco a modificar: ");
		int id = new Scanner(System.in).nextInt();

		Banco banco = bancoService.findById(id);

		if (banco != null) {
			System.out.print("\nNuevo nombre para (" + banco.getNombre() + "): ");
			String nombre = new Scanner(System.in).nextLine();

			System.out.print("\nNueva ciudad: ");
			String ciudad = new Scanner(System.in).nextLine();

			banco.setCiudad(ciudad);
			banco.setNombre(nombre);

			bancoService.update(banco);
		} else {
			System.out.println("El banco no existe.");
		}
	}

	public static void eliminarCliente() {
		System.out.print("DNI del cliente a BORRAR: ");
		String dni = new Scanner(System.in).next();

		Cliente cliente = clienteService.findByDni(dni);

		if (cliente != null) {
			System.out.println("Si borras el cliente se borrarán todas las cuentas asociadas a el. ¿Quieres continuar? (s / n).");
			String option = new Scanner(System.in).next();
			if(option.equalsIgnoreCase("s")) {
				clienteService.delete(cliente);
			}else {
				System.out.println("Operación cancelada.");
			}
		} else {
			System.out.println("El cliente no existe.");
		}
	}

	public static void eliminarBanco() {
		List<Banco> bancos = bancoService.getAll();

		listarBancos();

		System.out.println("ID del banco a BORRAR: ");
		int id = new Scanner(System.in).nextInt();

		bancoService.delete(id);
	}

	public static void listarClientes() {
		List<Cliente> clientes = clienteService.getAll();

		System.out.println("**************** Clientes ****************");
		for (Cliente cliente : clientes) {
			System.out.println("DNI: " + cliente.getDni() + " | Nombre: " + cliente.getNombre());
		}
	}

	public static void listarBancos() {
		List<Banco> bancos = bancoService.getAll();

		System.out.println("**************** Bancos ****************");
		for (Banco banco : bancos) {
			System.out.println("ID: " + banco.getId() + " | Nombre: " + banco.getNombre());
		}
	}

	public static void crearCuenta() {
		listarBancos();
		System.out.print("Escribe el ID del banco donde quieres crear la cuenta: ");
		int id = new Scanner(System.in).nextInt();
		Banco banco = bancoService.findById(id);

		listarClientes();
		System.out.print("\nEscribe el DNI del cliente propietario de la cuenta: ");
		String dni = new Scanner(System.in).next();
		Cliente cliente = clienteService.findByDni(dni);

		Cuenta cuenta = new Cuenta(banco, cliente);
		cuentaService.create(cuenta);
	}

	public static void modificarCuenta() {
		List<Cuenta> cuentas = cuentaService.getAll();

		for (Cuenta cuenta : cuentas) {
			System.out.println("ID: " + cuenta.getId() + " | Cliente: " + cuenta.getCliente().getNombre() + " | Banco: "
					+ cuenta.getBanco().getNombre());
		}

		System.out.print("Selecciona el ID de la cuenta a modificar: ");
		int idCuenta = new Scanner(System.in).nextInt();
		
		Cuenta cuenta = cuentaService.findById(idCuenta);
		
		if(cuenta != null) {
			listarBancos();
			
			System.out.print("\nEscribe el ID del nuevo banco: ");
			int idBanco = new Scanner(System.in).nextInt();
			Banco banco = bancoService.findById(idBanco);
			
			if(banco != null) {
				cuenta.setBanco(banco);
				cuentaService.update(cuenta);
				System.out.println("	> Cuenta actualizada.");
			}else {
				System.out.println("ID del banco incorrecto.");
			}
		}else {
			System.out.println("El ID de la cuenta es incorrecto.");
		}
	}
	
	public static void eliminarCuenta() {
		List<Cuenta> cuentas = cuentaService.getAll();

		for (Cuenta cuenta : cuentas) {
			System.out.println("ID: " + cuenta.getId() + " | Cliente: " + cuenta.getCliente().getNombre() + " | Banco: "
					+ cuenta.getBanco().getNombre());
		}
		
		System.out.print("Escribe el ID de la Cuenta a BORRAR: ");
		int id = new Scanner(System.in).nextInt();
		Cuenta cuenta = cuentaService.findById(id);
		
		cuentaService.delete(cuenta);
		System.out.println("	> Cuenta Borrada.");
	}
	
	public static void listarCuentasBancos() {
		listarBancos();
		System.out.print("ID del banco: ");
		int id = new Scanner(System.in).nextInt();
		
		List<Cuenta> cuentas = cuentaService.findByBanco(id);
		
		for (Cuenta cuenta : cuentas) {
			System.out.println("ID: " + cuenta.getId() + " | Cliente: " + cuenta.getCliente().getNombre() + " | Banco: "
					+ cuenta.getBanco().getNombre());
		}
	}
	
	public static void listarCuentasClientes() {
		listarClientes();
		System.out.print("DNI del cliente: ");
		String dni = new Scanner(System.in).next();
		
		List<Cuenta> cuentas = cuentaService.findByCliente(dni);
		
		for (Cuenta cuenta : cuentas) {
			System.out.println("ID: " + cuenta.getId() + " | Cliente: " + cuenta.getCliente().getNombre() + " | Banco: "
					+ cuenta.getBanco().getNombre());
		}
	}
	
	public static void listarClientesCuentas() {
		List<Cliente> clientes = clienteService.getAll();
		
		for (Cliente cliente : clientes) {
			List<Cuenta> cuentas = cuentaService.findByCliente(cliente.getDni());
			System.out.println("------ Cliente: " + cliente.getNombre() + " ------");
			for (Cuenta cuenta : cuentas) {
				System.out.println("	> ID: " + cuenta.getId() + " | Banco: "
						+ cuenta.getBanco().getNombre());
			}
			
		}
	}
}
