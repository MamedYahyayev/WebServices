package az.maqa.project.dao.impl;

import az.maqa.project.dao.helper.DbHelper;
import az.maqa.project.dao.inter.InformationsDao;
import az.maqa.project.model.Informations;
import az.maqa.project.utility.JDBCUtility;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InformationsDaoImpl implements InformationsDao {

    @Override
    public List<Informations> getInformationsList() throws Exception {
        List<Informations> informationsList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from qanda.information where qanda.information.active = 1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Informations informations = new Informations();
                    informations.setId(rs.getLong("ID"));
                    informations.setInformations(rs.getString("INFO"));

                    informationsList.add(informations);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, ps, rs);
        }
        return informationsList;
    }

    @Override
    public boolean add(Informations information) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "insert into qanda.information(info) values(?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, information.getInformations());
                ps.execute();
                result = true;
                // c.commit();
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, ps, null);
        }
        return result;
    }

    @Override
    public Informations getInformationById(Long id) throws Exception {
        Informations informations = new Informations();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from qanda.information where qanda.information.active = 1 and qanda.information.id = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    informations.setId(rs.getLong("ID"));
                    informations.setInformations(rs.getString("INFO"));
                }
            } else {
                informations = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, ps, rs);
        }
        return informations;
    }

    @Override
    public boolean update(Informations informations, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call INFORMATION_PACK.UPDATE_INFORMATION(?,?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setString(1, informations.getInformations());
                cs.setLong(2, id);
                cs.execute();
                result = true;
                c.commit();
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, null);
        }
        return result;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{ call INFORMATION_PACK.DELETE_INFORMATION(?,?)}";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setInt(1, 0);
                cs.setLong(2, id);
                cs.execute();
                result = true;
                c.commit();
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, null);
        }
        return result;
    }

    @Override
    public List<Informations> search(String keyword) throws Exception {
        List<Informations> informationsList = new ArrayList<>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{? = call INFORMATION_PACK.SEARCH_INFORMATION(?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                //   cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.setString(2, "%" + keyword + "%");
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    Informations informations = new Informations();
                    informations.setId(rs.getLong("ID"));
                    informations.setRownum(rs.getLong("r"));
                    informations.setInformations(rs.getString("INFORMATION"));

                    informationsList.add(informations);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, rs);
        }
        return informationsList;
    }
}
