package escola;

public class Main {
    public static void main(String[] args) {
        Aluno aluno1 = new Aluno("Ana", 17, "A123");
        Professor prof1 = new Professor("Carlos", 40, 4500.00);
        Disciplina disciplina1 = new Disciplina("Matemática", 60);

        System.out.println("=== ALUNO ===");
        aluno1.apresentar();

        System.out.println("\n=== PROFESSOR ===");
        prof1.apresentar("Matemática");

        System.out.println("\n=== DISCIPLINA ===");
        disciplina1.exibirInfo();
    }
}
