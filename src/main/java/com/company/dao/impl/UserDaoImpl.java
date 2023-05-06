package com.company.dao.impl;

import com.company.entity.Country;
import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserDaoInter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl  extends AbstractDAO implements UserDaoInter {



    private User getUser(ResultSet rs) throws Exception{
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthplaceStr = rs.getString("birthplace");
        Date birthdate = rs.getDate("birthdate");

        Country nationality = new Country(nationalityId,null,  nationalityStr);
        Country birthplace = new Country(birthplaceId, birthplaceStr, null);

        return new User(id, name, surname, phone, email, birthdate, nationality, birthplace);
    }

    @Override
    public List<User> getAllUsers() {

        List<User> result = new ArrayList<User>();

        try(Connection connection = connect()) {

            PreparedStatement stmt = connection.prepareStatement("select u.*, " +
                    "n.nationality, " +
                    "c.name as birthplace from user u " +
                    "left join country n on u.nationality_id = n.id " +
                    "left join country c on u.birthplace_id = c.id");
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }


    @Override
    public User getById(int userId) {

        User result = null;
        try(Connection connection = connect()){
            PreparedStatement stmt = connection.prepareStatement("select u.*," +
                    "n.nationality, " +
                    "c.name as birthplace from user u " +
                    "left join country n on u.nationality_id = n.id " +
                    "left join country c on u.birthplace_id = c.id WHERE u.id = " + userId);
            stmt.executeQuery();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()){
                result  =getUser(rs);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addUser(User user) {

        try(Connection connection =connect()) {
            PreparedStatement stmt = connection.
                    prepareStatement("INSERT INTO user(name, surname, phone, email) VALUES(?, ?, ?, ?)");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getEmail());

            return stmt.execute();

        }catch (Exception ex){
            ex.printStackTrace();
          return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        try(Connection connection = connect()) {

            PreparedStatement stmt = connection
                    .prepareStatement("UPDATE USER SET name = ?, surname = ?, phone = ?, email = ? where id =?");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getEmail());
            stmt.setInt(5, user.getId());
            return stmt.execute();
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUser(int id) {
        try(Connection connection = connect()) {

            PreparedStatement stmt = connection.prepareStatement("DELETE FROM USER WHERE id = " + id);
            return stmt.execute();
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}