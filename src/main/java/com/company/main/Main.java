package com.company.main;


import com.company.dao.inter.UserDaoInter;

public class Main {

    public static void main(String[] args)  {

        UserDaoInter userDao = Context.instanceDao();
        System.out.println(userDao.getById(1));

    }
 }