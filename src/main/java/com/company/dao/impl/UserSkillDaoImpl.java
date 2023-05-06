package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserSkillDaoInter;
import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {


    private UserSkill getUserSkill(ResultSet rs) throws Exception{
        int userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
        String skillName = rs.getString("skill_name");
        int power = rs.getInt("power");
        User user = new User();
        return new UserSkill(null, new User(userId), new Skill(skillId, skillName), power);
    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {

        List<UserSkill> result = new ArrayList<>();

        try(Connection connection = connect()) {
            PreparedStatement stmt = connection.prepareStatement("select " +
                    "u.*, " +
                    "us.skill_id, " +
                    "s.name as skill_name, " +
                    "us.power " +
                    "from " +
                    "user_skill us " +
                    "left join user u on us.user_id = u.id " +
                    "left join skill s on us.skill_id = s.id " +
                    "where " +
                    "us.user_id=?");
            stmt.setInt(1,userId);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                UserSkill us = getUserSkill(rs);

                result.add(us);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
}