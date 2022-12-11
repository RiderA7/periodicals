package com.epam.Per1;

import com.epam.Per1.db.ConnectionPool;
import com.epam.Per1.db.Dao;
import com.epam.Per1.db.MySql.MySqlDao;
import com.epam.Per1.db.MySql.MySqlTopicDao;
import com.epam.Per1.db.MySql.MySqlUserDao;
import com.epam.Per1.db.UserDao;
import com.epam.Per1.db.model.Topic;
import com.epam.Per1.db.model.User;
import com.epam.Per1.utils.Utils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@WebServlet("/probe")
public class probe extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        PrintWriter out = resp.getWriter();

        out.println("HiHi Login admin");
//        Connection con = ConnectionPool.getInstance().getConnection();
//        System.out.println(con);
        // List of all topics from DB:
//        List<Topic> topics = new ArrayList<>();
//        try {
//            topics = Dao.getDao().getTopicDao().getAllTopics();
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
//        for(Topic topic : topics){
//            System.out.println(topic.getName());
//        }

        // testing hash function
//        char[] pass = "password".toCharArray();
//        System.out.println(Utils.hash(pass)); // 5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8

        User user = new User();
        Dao dao = Dao.getDao();
        out.println(dao.toString());
        try {
            user = dao.getUserDao().getUserByLogin("admin");
            out.println("111");
        } catch (DbException e) {
            System.out.println("User admin not found");
            e.printStackTrace();
        }
        out.println(user.getLogin().toString());
    }
}
