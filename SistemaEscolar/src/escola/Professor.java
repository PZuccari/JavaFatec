package escola;

public class Professor extends Pessoa {
    private double salario;

    public Professor() {}

    public Professor(String nome, int idade, double salario) {
        super(nome, idade);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void apresentar(String disciplina) {
        super.apresentar();
        System.out.println("Salário: R$ " + salario + " | Disciplina: " + disciplina);
    }

    @Override
    public void apresentar() {
        super.apresentar();
        System.out.println("Salário: R$ " + salario);
    }
}
