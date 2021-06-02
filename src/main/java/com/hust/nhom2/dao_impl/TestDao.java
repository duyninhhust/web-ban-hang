package com.hust.nhom2.dao_impl;

import com.hust.nhom2.dao.*;
import com.hust.nhom2.model.*;

public class TestDao {

    public static void main(String[] args) {
        CommentDao commentDao = new CommentDaoImpl();
        AccountDao accountDao = new AccountDaoImpl();
        ProductDao productDao = new ProductDaoImpl();
        CategoryDao categoryDao = new CategoryDaoImpl();
        MyConnection myConnection = new MyConnection();
        BillDao billDao = new BillDaoImpl();
        BillDetailDao billDetailDao = new BillDetailDaoImpl();
        try {

            myConnection.connectDB();
            Bill bill = billDao.insert(new Bill("lê tuấn anh","0825050178","Ha noi",1));
            System.out.println(bill);
//            Category category = new Category("Huawei",false);
//            CategoryDao categoryDao = new CategoryDaoImpl();
//            categoryDao.insert(category);
//            categoryDao.update(category);
//            List<Category> list = categoryDao.findAll();
//            for (Category o:list) {
//                System.out.println(o);
//            }
          ;

//          productDao.delete(7);
//           List<Product> list = productDao.findAll();
////

//            List<Comment> list = commentDao.findByProduct(1);
//            for (Comment o:list) {
//                System.out.println(o);
//            }
//            List<Product> list = productDao.pagingProduct(2);
//            System.out.println(list);

//            Comment comment = commentDao.findById(1);
//            System.out.println(comment);
//
//            String sql = "insert into comment(name,email,sdt,comment,idproduct) values (?,?,?,?,?)";
//            PreparedStatement statement = myConnection.prepareUpdate(sql);
//            statement.setString(1, "ahihi");
//            statement.setString(2, "dmm@dddd");
//            statement.setString(3,"comment.getSdt()");
//            statement.setString(4,"comment.getComment())");
//            statement.setInt(5,1);
//
//            int rs =statement.executeUpdate();
//            if (rs > 0){
//                System.out.println("insert successfully!");
//            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
