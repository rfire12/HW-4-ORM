package edu.pucmm.sparkjdbc.services;

import edu.pucmm.sparkjdbc.encapsulation.User;
import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BootStrapServices {
    public static void startDb() throws SQLException {
        Server.createTcpServer("-tcpPort",
                "9092",
                "-tcpAllowOthers",
                "-tcpDaemon").start();
    }

    public static void stopDb() throws SQLException {
        Server.shutdownTcpServer("tcp://localhost:9092", "", true, true);
    }

    public static void initDatabase() throws SQLException {
        UsersServices.getInstance().create(new User(UUID.randomUUID().toString(), "admin","Admin","123456","admin"));
    }
}
