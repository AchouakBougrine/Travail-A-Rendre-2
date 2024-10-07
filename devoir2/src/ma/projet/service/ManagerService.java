package ma.projet.service;
import ma.projet.dao.IDao;
import ma.projet.beans.Manager;
import ma.projet.connexion.Connexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ManagerService implements IDao<Manager> {
    @Override
    public boolean create(Manager manager) {
        try {
            String req = "INSERT INTO manager (nom, salaire) VALUES (?, ?)";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, manager.getNom());
            ps.setDouble(2, manager.getSalaire());
            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);}
        return false;}
    @Override
    public boolean update(Manager manager) {
        try {
            String req = "UPDATE manager SET nom = ?, salaire = ? WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, manager.getNom());
            ps.setDouble(2, manager.getSalaire());
            ps.setInt(3, manager.getId());

            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);}
        return false;
    }

    @Override
    public boolean delete(Manager manager) {
        try {
            String req = "DELETE FROM manager WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, manager.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);}
        return false;}
    @Override
    public Manager getById(int id) {
        Manager manager = null;
        try {
            String req = "SELECT * FROM manager WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                manager = new Manager(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire"));}
        } catch (SQLException ex) {
            Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);}
        return manager;}
    @Override
    public List<Manager> getAll() {
        List<Manager> managers = new ArrayList<>();
        try {
            String req = "SELECT * FROM manager";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                managers.add(new Manager(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire"))); }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);}
        return managers;}
}
