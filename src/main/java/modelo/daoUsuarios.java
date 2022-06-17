/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.clsUsuarios;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author visitante
 */
public class daoUsuarios {

    private static final String SQL_SELECT = "SELECT Id_usuario, Nombre, Correo, Contraseña, Tipo FROM usuario";
    private static final String SQL_INSERT = "INSERT INTO usuario(Nombre,Correo,Contraseña,Tipo) VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET Nombre=?, Correo=?, Contraseña=?, AND Tipo=? WHERE Id_usuario = ?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE Id_usuario=?";
    private static final String SQL_QUERY = "SELECT Id_usuario, Nombre, Correo, Contraseña, Tipo FROM usuario WHERE Nombre = ?";

    public List<clsUsuarios> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        clsUsuarios usuario = null;
        List<clsUsuarios> usuarios = new ArrayList<clsUsuarios>();
        try {
            conn = clsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_usuario = rs.getInt("Id_usuario");
                String Nombre = rs.getString("Nombre");
                String Correo = rs.getString("Correo");
                String Contraseña = rs.getString("Contraseña");
                String Tipo = rs.getString("Tipo");

                usuario = new clsUsuarios();
                usuario.setIid_usuario(id_usuario);
                usuario.setsNombre(Nombre);
                usuario.setsCorreo(Correo);
                usuario.setsContraseña(Contraseña);
                usuario.setsTipo(Tipo);

                usuarios.add(usuario);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            clsConexion.close(rs);
            clsConexion.close(stmt);
            clsConexion.close(conn);
        }

        return usuarios;
    }

    public int insert(clsUsuarios usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = clsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, usuario.getsNombre());
            stmt.setString(2, usuario.getsCorreo());
            stmt.setString(3, usuario.getsContraseña());
            stmt.setString(3, usuario.getsTipo());

            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            clsConexion.close(stmt);
            clsConexion.close(conn);
        }

        return rows;
    }

    public int update(clsUsuarios usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = clsConexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, usuario.getsNombre());
            stmt.setString(2, usuario.getsCorreo());
            stmt.setString(3, usuario.getsContraseña());
            stmt.setString(3, usuario.getsTipo());

            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            clsConexion.close(stmt);
            clsConexion.close(conn);
        }

        return rows;
    }

    public int delete(clsUsuarios usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = clsConexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, usuario.getIid_usuario());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            clsConexion.close(stmt);
            clsConexion.close(conn);
        }

        return rows;
    }

    public clsUsuarios query(clsUsuarios usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = clsConexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, usuario.getIid_usuario());
            rs = stmt.executeQuery();
            while (rs.next()) {
                 int id_usuario = rs.getInt("Id_usuario");
                String Nombre = rs.getString("Nombre");
                String Correo = rs.getString("Correo");
                String Contraseña = rs.getString("Contraseña");
                String Tipo = rs.getString("Tipo");

                usuario = new clsUsuarios();
                usuario.setIid_usuario(id_usuario);
                usuario.setsNombre(Nombre);
                usuario.setsCorreo(Correo);
                usuario.setsContraseña(Contraseña);
                usuario.setsTipo(Tipo);
            }
            //System.out.println("Registros buscado:" + persona);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            clsConexion.close(rs);
            clsConexion.close(stmt);
            clsConexion.close(conn);
        }

        //return personas;  // Si se utiliza un ArrayList
        return usuario;
    }
}
