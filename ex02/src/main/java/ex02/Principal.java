package ex02;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		DAO dao = new DAO();
		dao.conectar();

		Scanner scanner = new Scanner(System.in);

		int opc = 0;
		do {
			System.out.println("Menu:");
			System.out.println("1) Listar");
			System.out.println("2) Inserir");
			System.out.println("3) Excluir");
			System.out.println("4) Atualizar");
			System.out.println("5) Sair");
			System.out.print("Escolha uma opção: ");

			//Ler escolha
			opc = scanner.nextInt();
			scanner.nextLine();

			switch (opc) {
			case 1:
				listarUsuarios(dao);
				break;
			case 2:
				inserirUsuario(scanner, dao);
				break;
			case 3:
				excluirUsuario(scanner, dao);
				break;
			case 4:
				atualizarUsuario(scanner, dao);
				break;
			case 5:
				System.out.println("Programa encerrado.");
				break;
			default:
				System.out.println("Opção inválida.");
			}

		} while (opc != 5);

		scanner.close();
		dao.close();
	}

	private static void listarUsuarios(DAO dao) {
		System.out.println("==== Mostrar usuários === ");
		Usuario[] usuarios = dao.getUsuarios();
		for (int i = 0; i < usuarios.length; i++) {
			System.out.println(usuarios[i].toString());
		}
	}

	private static void inserirUsuario(Scanner scanner, DAO dao) {
		System.out.println("Inserir usuário:");
		System.out.print("Código: ");
		int codigo = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Login: ");
		String login = scanner.nextLine();
		System.out.print("Senha: ");
		String senha = scanner.nextLine();
		System.out.print("Sexo: ");
		char sexo = scanner.nextLine().charAt(0);

		Usuario usuario = new Usuario(codigo, login, senha, sexo);
		if (dao.inserirUsuario(usuario)) {
			System.out.println("Usuário inserido com sucesso.");
		} else {
			System.out.println("Falha ao inserir usuário.");
		}
	}

	private static void excluirUsuario(Scanner scanner, DAO dao) {
		System.out.print("Digite o código do usuário a ser excluído: ");
		int codigo = scanner.nextInt();
		if (dao.excluirUsuario(codigo)) {
			System.out.println("Usuário excluído com sucesso.");
		} else {
			System.out.println("Falha ao excluir usuário.");
		}
	}

	private static void atualizarUsuario(Scanner scanner, DAO dao) {
		System.out.print("Digite o código do usuário a ser atualizado: ");
		int codigo = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Novo login: ");
		String novoLogin = scanner.nextLine();
		System.out.print("Nova senha: ");
		String novaSenha = scanner.nextLine();
		System.out.print("Novo sexo: ");
		char novoSexo = scanner.nextLine().charAt(0);

		Usuario usuario = new Usuario(codigo, novoLogin, novaSenha, novoSexo);
		if (dao.atualizarUsuario(usuario)) {
			System.out.println("Usuário atualizado com sucesso.");
		} else {
			System.out.println("Falha ao atualizar usuário.");
		}
	}
}
