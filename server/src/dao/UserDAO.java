package dao;

import model.Otx;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDAO extends EntityDAO<User, Integer> {

    private static final String SQL_UPDATE_USER_BY_ID =
            "UPDATE users SET roleID = ?, login = ?, password = ?, name = ? WHERE id = ?";

    private static final String SQL_CREATE_USER =
            "INSERT INTO users (roleID, login, password, name) VALUES (?,?,?,?)";

    private static final String SQL_DELETE_USER_BY_ID =
            "DELETE FROM users WHERE id = ?";

    private static final String SQL_GET_ALL =
            "SELECT users.id, users.name, users.login, users.password, role.name FROM users JOIN role ON users.roleID = role.id;";

    private static final String SQL_GET_ALL_OTXS =
            "SELECT otxodinfo.nameOtx, otxodinfo.type, otxodinfo.class, otxodinfo.count FROM otxodinfo ";

    private static final String SQL_BLOCK =
            "UPDATE users SET roleID = ? WHERE id = ?";

    private static final String SQL_CREATE_OTX =
            "INSERT INTO otxodinfo (name,type,class,count, id_user,nameOtx) VALUES (?,?,?,?,?,?)";

    private static final String SQL_GET_ALL_BY_USER_ID =
            "SELECT otxodinfo.nameOtx, otxodinfo.type, otxodinfo.class, otxodinfo.count FROM otxodinfo JOIN users ON otxodinfo.id_user = users.id WHERE users.id = ?;";




    public List<Otx> getAllByUserId(String command) {
        List<Otx> listCredit = new ArrayList<>();

        PreparedStatement ps = getPrepareStatement(SQL_GET_ALL_BY_USER_ID);
        try {
            ps.setInt(1, Integer.parseInt(command));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Otx otx = new Otx();

                otx.setNameOtx(rs.getString(1));
                otx.setTypeOtx(rs.getString(2));
                otx.setClassOtx(rs.getString(3));
                otx.setCountOtx(rs.getInt(4));

                listCredit.add(otx);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
        }

        return listCredit;
    }

    @Override
    public List<User> getAll() {
        List<User> list = new LinkedList<>();
        PreparedStatement ps = getPrepareStatement(SQL_GET_ALL);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setLogin(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setRole(rs.getString("role.name"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
        }

        return list;
    }

    public List<Otx> getAllOtxs() {
        List<Otx> list = new LinkedList<>();
        PreparedStatement ps = getPrepareStatement(SQL_GET_ALL_OTXS);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Otx otx = new Otx();
                otx.setNameOtx(rs.getString(1));
                otx.setTypeOtx(rs.getString(2));
                otx.setClassOtx(rs.getString(3));
                otx.setCountOtx(rs.getInt(4));

                list.add(otx);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
        }

        return list;
    }


    @Override
    public boolean delete(Integer id) {
        PreparedStatement st = getPrepareStatement(SQL_DELETE_USER_BY_ID);

        boolean isRemoved = false;

        try {
            st.setInt(1, id);

            int i = st.executeUpdate();
            isRemoved = i > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isRemoved;
    }

    @Override
    public boolean update(User user) {
        PreparedStatement ps = getPrepareStatement(SQL_UPDATE_USER_BY_ID);
        try {
            choseRole(user, ps);
            ps.setInt(5, user.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void choseRole(User user, PreparedStatement ps) throws SQLException {
        int role = 0;
        switch (user.getRole()){
            case "user":
                role = 2;
                break;
            case "admin":
                role = 1;
                break;
            case "blocked":
                role = 3;
                break;
        }
        ps.setInt(1, role);
        ps.setString(2, user.getLogin());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getName());
    }

    @Override
    public boolean create(User user) {
        PreparedStatement ps = getPrepareStatement(SQL_CREATE_USER);
        try {
            choseRole(user, ps);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean createOtx(Otx otx) {
        PreparedStatement ps = getPrepareStatement(SQL_CREATE_OTX);
        try {
            ps.setString(1, otx.getName());
            ps.setString(6, otx.getNameOtx());
            ps.setString(2, otx.getTypeOtx());
            ps.setString(3, otx.getClassOtx());
            ps.setInt(4, otx.getCountOtx());
            ps.setInt(5, otx.getId_user());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void block(int id) {
        PreparedStatement ps = getPrepareStatement(SQL_BLOCK);
        try {
            ps.setInt(1, 3);
            ps.setInt(2, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void unlock(int id) {
        PreparedStatement ps = getPrepareStatement(SQL_BLOCK);
        try {
            ps.setInt(1, 2);
            ps.setInt(2, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
