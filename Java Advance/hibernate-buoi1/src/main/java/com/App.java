package com;

import com.entity.Department;
import com.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.openSession();// session    ==   connection ở JDBC

        List<Department> departments = new ArrayList<>();
        
        // tìm kiếm theo tên
//        try {
//            String hql = "From Department where name like :search ";
//            Query<Department> query = session.createQuery(hql, Department.class);
//            query.setParameter("search", "%123%");// truyền tham số
//            departments = query.list();// lấy ra ds department liên quan
//        } catch (Exception e) {
//            e.printStackTrace();
//        }  finally {
//            session.close();// đóng session
//        }

        // tìm kiesm theo các theo id  :
        // do ID là khóa chính, 1 id -> chỉ có nhiều nhất 1 department hoặc null
//        Department department = new Department();
//        try {
//            String hql = "From Department where id = :searchId ";
//            Query<Department> query = session.createQuery(hql, Department.class);
//            query.setParameter("searchId", 1);// truyền tham số
////            departments = query.list();// lấy ra ds department liên quan
//            department = query.uniqueResult();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }  finally {
//            session.close();// đóng session
//        }

//        Department department = new Department();
//        try {
//            // chỉ dùng cho id
//            department = session.find(Department.class, 1);// tham số đàu tiên: class muốn tìm, tham số thứ 2: giá trị muoosn tìm:
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }  finally {
//            session.close();// đóng session
//        }

        // thêm mới
//        session.beginTransaction();// mở transaction
//        try {
//            Department newDepartment = new Department();
//            newDepartment.setName("Old Department");
//            session.persist(newDepartment); // thêm mới dữ liệu
//            System.out.println("Thêm mới thành công");
//            session.getTransaction().commit();// commit
//        } catch (Exception e) {
//            session.getTransaction().rollback();// rollback khi gap loi
//            System.out.println("Thêm mới thất bại");
//            e.printStackTrace();
//        }  finally {
//            session.close();// đóng session
//        }

        // xóa theo id
//        session.beginTransaction();// mở transaction
//        try {
////            Department department = session.find(Department.class, 21);// tìm ra department có id = 22
////            if (department != null) {
////                session.remove(department);// xóa department đó nếu như tồn tại
////                System.out.println("Xóa thành công");
////            } else {
////                System.out.println("Không tồn tại");
////            }
////            session.getTransaction().commit();// commit
//
//            String hql = "DELETE FROM Department d WHERE d.id = :deleteId";
//            int c = session.createQuery(hql)
//                            .setParameter("deleteId", 20)
//                    .executeUpdate();
//            if (c > 0) {
//                System.out.println("Xóa thành công");
//            } else {
//                System.out.println("Không tồn tại");
//            }
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            session.getTransaction().rollback();// rollback khi gap loi
//            System.out.println("Xóa thất bại");
//            e.printStackTrace();
//        }  finally {
//            session.close();// đóng session
//        }

        // update tên theo id(newName = "department update"    ,  id = 17)
        session.beginTransaction();// mở transaction
        try {
            // tìm ra department cần update theo id
            Department department = session.find(Department.class, 17);

            // update tên
            if (department != null) {
                department.setName("department update");// thay đổi tên + update vào DB
                System.out.println("Update thành công");
            } else {
                System.out.println("Không tìm thấy department");
            }
            session.getTransaction().commit();// commit
        } catch (Exception e) {
            session.getTransaction().rollback();// rollback khi gap loi
            System.out.println("Update thất bại");
            e.printStackTrace();
        }  finally {
            session.close();// đóng session
        }







        // xem ds department
        try {
            session = factory.openSession();
            String hql = "From Department";
            Query<Department> query = session.createQuery(hql, Department.class);
            departments = query.list();// lấy ra ds department
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            session.close();// đóng session
        }

        System.out.println("Danh sách trong DB là: ");
        for (Department department : departments) {
            System.out.println(department);
        }
//        System.out.println(department);



    }
}
