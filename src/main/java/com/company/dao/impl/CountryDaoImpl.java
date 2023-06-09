package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.CountryDaoInter;
import com.company.entity.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {

    private Country getCountry(ResultSet rs) throws  Exception{
        int id = rs.getInt("id");
        String country = rs.getString("name");
        String nationality = rs.getString("nationality");

        return new Country(id, country, nationality);
    }

    @Override
    public List<Country> getAllCountries(){
        List<Country> countries = new ArrayList<Country>();
        try(Connection connection = connect()){
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM country");
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()){
                countries.add(getCountry(rs));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return countries;
    }
}
