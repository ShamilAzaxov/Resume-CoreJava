package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Skill;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

    private Skill getSkill(ResultSet rs) throws Exception {

        int id = rs.getInt("id");
        String name = rs.getString("name");

        return new Skill(id, name);

    }

    @Override
    public List<Skill> getAllSkill() {

        List<Skill> skills = new ArrayList<>();

        try(Connection connection = connect()){
            PreparedStatement stmt = connection.prepareStatement("SELECT * from skill");
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()){
                skills.add(getSkill(rs));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return skills;
    }
}
