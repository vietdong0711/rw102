package com.utils;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static SessionFactory sessionFactory;// là đối tượng quản lý toàn bộ session, cả project chỉ tạo duy nhất 1 sessionFactory

    static {
        Configuration cfg = new Configuration();
        cfg.configure();// đọc các cấu hình ở file config hibernate.cfg.xml

        if (sessionFactory == null) {
            sessionFactory = cfg.buildSessionFactory();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
