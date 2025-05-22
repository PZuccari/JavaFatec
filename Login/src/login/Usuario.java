package Login;

import java.sql.SQLException; //Tratar as exceções no banco de dados

public class Usuario {
    //Criação dos atributos privados da classe
    private String usuario;
    private String nome;
    private String senha;

    //Atributo que armazenará o retorno do banco de dados
    private boolean resultUsuario;
    private boolean resultAlteracao;
    private boolean resultExclusao;

    //Atributos Estáticos do Usuário
    static String nomeUsuario;
    static String usuarioSistema;

    //Criação dos getters e setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    //Método de verificação da autenticidade do usuario
    public boolean verificaUsuario(String usuario, String senha) {
        //Fazer a instância da conexão com o banco de dados
        Conexao banco = new Conexao();

        try {
            //Abro a conexão com o banco de dados
            banco.abrirConexao();

            //Criando parâmetro de retorno
            banco.stmt = banco.con.createStatement();

            //Executando a consulta no banco de dados
            banco.resultset = banco.stmt.executeQuery("SELECT * FROM usuario "
                    + " WHERE usuario = '" + usuario + "'"
                    + " AND senha = md5('" + senha + "')");

            //Verificando se existe retorno de dados no banco
            if (banco.resultset.next()) {
                resultUsuario = true;

                //Setamos o Nome e Usuário
                setUsuario(banco.resultset.getString(1));
                setNome(banco.resultset.getString(2));

                //Nos atributos estáticos, realizo as atribuições
                nomeUsuario = getNome();
                usuarioSistema = getUsuario();
            } else {
                resultUsuario = false;
            }

            banco.fecharConexao(); // Fecha nossa conexão com o banco de dados

        } catch (SQLException ec) {
            System.out.println("Erro ao consultar usuário " + ec.getMessage());
        }

        return resultUsuario;
    }

    //Método para alteração dos dados do nome e senha
    public boolean alteraUsuario(String nome, String usuario, String senha) {
        Conexao banco = new Conexao();

        try {
            banco.abrirConexao();
            banco.stmt = banco.con.createStatement();

            banco.stmt.execute("UPDATE usuario SET nome = '" + nome +
                    "', senha = md5('" + senha + "') WHERE usuario = '" +
                    usuario + "'");
            resultAlteracao = true;

        } catch (SQLException ec) {
            System.out.println("Erro ao atualizar usuário " + ec.getMessage());
            resultAlteracao = false;
        }

        banco.fecharConexao();

        return resultAlteracao;
    }

    //Método para exclusão do Usuário do sistema
    public boolean excluiUsuario(String usuario) {
        Conexao banco = new Conexao();

        try {
            //Abro a conexão com o banco de dados
            banco.abrirConexao();

            //Criação do parâmetro de retorno
            banco.stmt = banco.con.createStatement();

            //Executando a alteração no banco de dados
            banco.stmt.execute("DELETE FROM usuario WHERE usuario = '"
                    + usuario + "'");

            //Caso exclua
            resultExclusao = true;

        } catch (SQLException ec) {
            System.out.println("Erro ao excluir usuário " + ec.getMessage());
            resultExclusao = false;
        }

        banco.fecharConexao();

        return resultExclusao;
    }
}