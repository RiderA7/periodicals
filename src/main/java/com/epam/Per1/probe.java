package com.epam.Per1;

import com.epam.Per1.db.Dao;
import com.epam.Per1.db.Entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

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

        //testing find user function
//        User user = new User();
//        Dao dao = Dao.getDao();
//        try {
//            user = dao.getUserDao().getUserByLogin("admin");
//        } catch (DbException e) {
//            System.out.println("User admin not found");
//            e.printStackTrace();
//        }
//        out.println(user.toString());

        Dao dao = Dao.getDao();
        User user = new User();
        try {
            user = dao.getUserDao().login("admin", "password".toCharArray());
        } catch (DbException e) {
            System.out.println("User can't login");
            e.printStackTrace();
        }
        out.println("Logged " + user.toString());

        req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
    }
}
