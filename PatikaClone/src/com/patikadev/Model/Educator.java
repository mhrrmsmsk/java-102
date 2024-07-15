package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Educator extends User{
    public Educator() {
    }

    public Educator(int id, String name, String uname, String password, String type) {
        super(id, name, uname, password, type);
    }


}
