package com.epam.Per1;

import com.epam.Per1.db.ConnectionPool;
import com.epam.Per1.db.Dao;
import com.epam.Per1.db.MySql.MySqlTopicDao;
import com.epam.Per1.db.model.Topic;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/probe")
public class probe extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        PrintWriter out = resp.getWriter();

        out.println("HiHi");
//        Connection con = ConnectionPool.getInstance().getConnection();
//        System.out.println(con);
        List<Topic> topics = new ArrayList<>();
        try {
            topics = Dao.getDao().getTopicDao().getAllTopics();
        } catch (DbException e) {
            e.printStackTrace();
        }
        for(Topic topic : topics){
            System.out.println(topic.getName());
        }
    }
}
