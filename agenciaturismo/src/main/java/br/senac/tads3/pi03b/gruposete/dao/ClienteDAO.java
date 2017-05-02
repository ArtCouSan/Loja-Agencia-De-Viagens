package br.senac.tads3.pi03b.gruposete.dao;

import br.senac.tads3.pi03b.gruposete.models.Cliente;
import br.senac.tads3.pi03b.gruposete.models.Pessoa;
import br.senac.tads3.pi03b.gruposete.utils.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private static Connection con;
    private static PreparedStatement stmn;

    public static void inserir(Cliente cliente) throws SQLException, Exception {

        //Monta a string de inserção de um cliente no BD, utilizando os dados do clientes passados como parâmetro
        String sql = "INSERT INTO Cliente (pessoa, ativo) VALUES (?, ?)";
        //Cria um statement para execução de instruções SQL

        try {
            con = DbUtil.getConnection();
            stmn = con.prepareStatement(sql);
            //Configura os parâmetros do "PreparedStatement"
            stmn.setObject(1, cliente.getPessoa());
            stmn.setBoolean(2, cliente.isAtivo());
            //Executa o comando no banco de dados
            stmn.executeUpdate();
        } finally {
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (stmn != null && !stmn.isClosed()) {
                stmn.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (con != null && !con.isClosed()) {
                con.close();
            }
        }
    }

    public static void alterar(Cliente cliente) throws SQLException, Exception {

        //Monta a string de inserção de um cliente no BD, utilizando os dados do clientes passados como parâmetro
        String sql = "UPDATE Cliente SET pessoa=?, ativo=? WHERE id_cliente=?";
        //Cria um statement para execução de instruções SQL

        try {
            con = DbUtil.getConnection();
            stmn = con.prepareStatement(sql);
            //Configura os parâmetros do "PreparedStatement"
            stmn.setObject(1, cliente.getPessoa());
            stmn.setBoolean(2, cliente.isAtivo());
            stmn.setInt(3, cliente.getId_cliente());
            //Executa o comando no banco de dados
            stmn.executeUpdate();
        } finally {
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (stmn != null && !stmn.isClosed()) {
                stmn.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (con != null && !con.isClosed()) {
                con.close();
            }
        }
    }

    public static void excluir(int id) throws SQLException, Exception {

        //Monta a string de inserção de um cliente no BD, utilizando os dados do clientes passados como parâmetro
        String sql = "DELETE FROM Cliente WHERE id_cliente=?";
        //Cria um statement para execução de instruções SQL

        try {
            con = DbUtil.getConnection();
            stmn = con.prepareStatement(sql);
            //Configura os parâmetros do "PreparedStatement"
            stmn.setInt(1, id);
            //Executa o comando no banco de dados
            stmn.executeUpdate();
        } finally {
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (stmn != null && !stmn.isClosed()) {
                stmn.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (con != null && !con.isClosed()) {
                con.close();
            }
        }
    }

    public static List<Cliente> getListaClientes() throws SQLException, ClassNotFoundException {
        List<Cliente> listaClientes = new ArrayList<>();
        con = DbUtil.getConnection();
        String query = "SELECT * FROM Cliente ORDER BY nome";

        try {
            Statement st = con.createStatement();
            ResultSet resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(resultSet.getInt("id_cliente"));
                cliente.setAtivo(resultSet.getBoolean("ativo"));
                cliente.setPessoa((Pessoa) resultSet.getObject("pessoa"));
                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
        }
        con.close();
        return listaClientes;
    }
}
