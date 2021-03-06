package es.eoi.app;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import es.eoi.entity.Banco;
import es.eoi.entity.Cliente;
import es.eoi.entity.Cuenta;
import es.eoi.service.BancoService;
import es.eoi.service.ClienteService;
import es.eoi.service.CuentaService;

public class MundoBancario {

	public static EntityManager em = Persistence.createEntityManagerFactory("EjercicioJPA").createEntityManager();
	private static BancoService bancoService = new BancoService();
	private static ClienteService clienteService = new ClienteService();
	private static CuentaService cuentaService = new CuentaService();
	

	public static void main(String[] args) {

		menu();

	}

	public static void menu() {
		System.out.println("************** Men� **************");

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
			System.out.println("Adi�s!");
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
			System.out.println("Opci�n no valida.");
			menu();
		}
	}

	public static void crearCliente() {
		System.out.print("DNI: ");
		String dni = new Scanner(System.in).next();

		System.out.print("\nNombre: ");
		String nombre = new Scanner(System.in).next();

		System.out.print("\nDirecci�n: ");
		String direccion = new Scanner(System.in).next();

		Cliente cliente = new Cliente(dni, nombre, direccion);

		clienteService.create(cliente);
		cliente = clienteService.findByDni(cliente.getDni());
		if (cliente != null) {
			System.out.println("	> Cliente creado con �xito");
		} else {
			System.out.println("	> Error al crear el Cliente.");
		}
	}

	public static void crearBanco() {
		System.out.print("Nombre: ");
		String nombre = new Scanner(System.in).next();

		System.out.println("Ciudad: ");
		String ciudad = new Scanner(System.in).next();

		Banco banco = new Banco(nombre, ciudad);

		bancoService.create(banco);

		banco = bancoService.findById(banco.getId());

		if (banco != null) {
			System.out.println("	> Banco creado con �xito.");
		} else {
			System.out.println("	> Error al crear el Banco.");
		}

	}

	public static void buscarCliente() {
		System.out.print("DNI: ");
		String dni = new Scanner(System.in).next();

		Cliente cliente = clienteService.findByDni(dni);

		if (cliente != null) {
			System.out.println(cliente);
		} else {
			System.out.println("	> Cliente no encontrado.");
		}

	}

	public static void buscarBanco() {
		System.out.print("ID: ");
		int id = new Scanner(System.in).nextInt();
		Banco banco = bancoService.findById(id);
		if (banco != null) {
			System.out.println("	>" + banco);
		} else {
			System.out.println("	> No hay ning�n banco con ese ID.");
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
			System.out.println(
					"Si borras el cliente se borrar�n todas las cuentas asociadas a el. �Quieres continuar? (s / n).");
			String option = new Scanner(System.in).next();
			if (option.equalsIgnoreCase("s")) {
				clienteService.delete(cliente);
			} else {
				System.out.println("Operaci�n cancelada.");
			}
		} else {
			System.out.println("El cliente no existe.");
		}
	}

	public static void eliminarBanco() {
		listarBancos();

		System.out.println("ID del banco a BORRAR: ");
		int id = new Scanner(System.in).nextInt();

		bancoService.delete(id);

		Banco banco = bancoService.findById(id);
		if (banco == null) {
			System.out.println("	> El banco se ha eliminado con �xito.");
		} else {
			System.out.println("	> Error al eliminar el banco.");
		}
	}

	public static void listarClientes() {
		List<Cliente> clientes = clienteService.getAll();

		System.out.println("**************** Clientes ****************");
		if (clientes.size() == 0) {
			System.out.println("	> No hay clientes.");
		} else {
			for (Cliente cliente : clientes) {
				System.out.println("DNI: " + cliente.getDni() + " | Nombre: " + cliente.getNombre());
			}
		}
	}

	public static void listarBancos() {
		List<Banco> bancos = bancoService.getAll();

		System.out.println("**************** Bancos ****************");
		if (bancos.size() == 0) {
			System.out.println("	> No hay bancos.");
		} else {
			for (Banco banco : bancos) {
				System.out.println("ID: " + banco.getId() + " | Nombre: " + banco.getNombre());
			}
		}
	}

	public static void crearCuenta() {
		listarBancos();
		System.out.print("Escribe el ID del banco donde quieres crear la cuenta: ");
		int id = new Scanner(System.in).nextInt();
		Banco banco = bancoService.findById(id);

		if (banco != null) {
			listarClientes();
			System.out.print("\nEscribe el DNI del cliente propietario de la cuenta: ");
			String dni = new Scanner(System.in).next();
			Cliente cliente = clienteService.findByDni(dni);

			if (cliente != null) {
				Cuenta cuenta = new Cuenta(banco, cliente);
				cuentaService.create(cuenta);
				cuenta = cuentaService.findById(cuenta.getId());

				if (cuenta != null) {
					System.out.println("	> La cuenta ha sido creada con �xito.");
				} else {
					System.out.println("	> Error al crear la cuenta.");
				}

			} else {
				System.out.println("	> El cliente seleccionado no existe.");
			}
		} else {
			System.out.println("	> El banco seleccionado no existe.");
		}

	}

	public static void modificarCuenta() {
		List<Cuenta> cuentas = cuentaService.getAll();

		if (cuentas.size() == 0) {
			System.out.println("	> No hay cuentas.");
		} else {
			for (Cuenta cuenta : cuentas) {
				System.out.println("ID: " + cuenta.getId() + " | Cliente: " + cuenta.getCliente().getNombre()
						+ " | Banco: " + cuenta.getBanco().getNombre());
			}

			System.out.print("Selecciona el ID de la cuenta a modificar: ");
			int idCuenta = new Scanner(System.in).nextInt();

			Cuenta cuenta = cuentaService.findById(idCuenta);

			if (cuenta != null) {
				listarBancos();

				System.out.print("\nEscribe el ID del nuevo banco: ");
				int idBanco = new Scanner(System.in).nextInt();
				Banco banco = bancoService.findById(idBanco);

				if (banco != null) {
					cuenta.setBanco(banco);
					cuentaService.update(cuenta);
					System.out.println("	> Cuenta actualizada.");
				} else {
					System.out.println("	> ID del banco incorrecto.");
				}
			} else {
				System.out.println("	> El ID de la cuenta es incorrecto.");
			}
		}
	}

	public static void eliminarCuenta() {
		List<Cuenta> cuentas = cuentaService.getAll();

		if (cuentas.size() == 0) {
			System.out.println("	> No hay cuentas.");
		} else {
			for (Cuenta cuenta : cuentas) {
				System.out.println("ID: " + cuenta.getId() + " | Cliente: " + cuenta.getCliente().getNombre()
						+ " | Banco: " + cuenta.getBanco().getNombre());
			}

			System.out.print("Escribe el ID de la Cuenta a BORRAR: ");
			int id = new Scanner(System.in).nextInt();
			Cuenta cuenta = cuentaService.findById(id);

			if (cuenta != null) {
				cuentaService.delete(cuenta);

				cuenta = cuentaService.findById(cuenta.getId());

				if (cuenta == null) {
					System.out.println("	> Cuenta borrada con exito");
				} else {
					System.out.println("	> Error al borrar la cuenta.");
				}

			} else {
				System.out.println("	> La cuenta no existe.");
			}
		}
	}

	public static void listarCuentasBancos() {
		listarBancos();
		List<Banco> bancos = bancoService.getAll();
		
		if(bancos.size() == 0) {
			System.out.println("	> No hay bancos.");
		}else {
			System.out.print("ID del banco: ");
			int id = new Scanner(System.in).nextInt();

			List<Cuenta> cuentas = cuentaService.findByBanco(id);

			if(cuentas.size() == 0) {
				System.out.println("	> No hay cuentas");
			}else {
				for (Cuenta cuenta : cuentas) {
					System.out.println("ID: " + cuenta.getId() + " | Cliente: " + cuenta.getCliente().getNombre() + " | Banco: "
							+ cuenta.getBanco().getNombre());
				}
			}
		}
	}

	public static void listarCuentasClientes() {
		listarClientes();

		List<Cliente> clientes = clienteService.getAll();

		if (clientes.size() == 0) {
			System.out.println("	> No hay clientes.");
		} else {
			System.out.print("DNI del cliente: ");
			String dni = new Scanner(System.in).next();

			List<Cuenta> cuentas = cuentaService.findByCliente(dni);

			if (cuentas.size() == 0) {
				System.out.println("	> No hay cuentas");
			} else {
				for (Cuenta cuenta : cuentas) {
					System.out.println("ID: " + cuenta.getId() + " | Cliente: " + cuenta.getCliente().getNombre()
							+ " | Banco: " + cuenta.getBanco().getNombre());
				}
			}
		}
	}

	public static void listarClientesCuentas() {
		List<Cliente> clientes = clienteService.getAll();

		if (clientes.size() == 0) {
			System.out.println("	> No hay clientes.");
		} else {
			for (Cliente cliente : clientes) {
				List<Cuenta> cuentas = cuentaService.findByCliente(cliente.getDni());
				System.out.println("------ Cliente: " + cliente.getNombre() + " ------");
				if(cuentas.size() == 0) {
					System.out.println("	> No hay cuentas.");
				}else {
					for (Cuenta cuenta : cuentas) {
						System.out.println("	> ID: " + cuenta.getId() + " | Banco: " + cuenta.getBanco().getNombre());
					}
				}
				
			}
		}

	}
}
