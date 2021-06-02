package com.hust.nhom2.dao_impl;

import com.hust.nhom2.dao.ProductDao;
import com.hust.nhom2.model.MyConnection;
import com.hust.nhom2.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {

    private MyConnection myConnection = new MyConnection();

    public Product getObject(ResultSet resultSet) throws SQLException {
        Product product = null;
        // sử dụng constructor full tham số
        // lần lượt truyền vào các giá trị thuộc tính tương ứng
        // đối với các đối tượng có nhiều thuộc tính
        // để tránh nhầm trường có thể tạo đối tượng = constructor mặc định
        // sử dụng setter để truyền giá trị
        product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setPrice(resultSet.getDouble("price"));
        product.setImage(resultSet.getString("image"));
        product.setIntroduction(resultSet.getString("introduction"));
        product.setCategoryId(resultSet.getInt("category_id"));
        product.setDeleted(resultSet.getBoolean("deleted"));
        return product;
    }

    public List<Product> findAll() throws SQLException {
        String sql = "select * from product where deleted = false";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    public Product findById(int id) throws SQLException {
        Product product = null;
        //kiểm tra deleted trước id để tối ưu thời gian câu lệnh
        String sql = "select * from product where deleted = false and id = ?";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        //kiểm tra xem có bản ghi hay không nếu có sử dụng hàm getObject để lấy ra đối tượng.
        if (resultSet.next()) {
            product = getObject(resultSet);
        }
        return product;
    }

    public Product insert(Product product) throws SQLException {
        Product newProduct = null;
        String sql = "insert into product (name,price,image,introduction,deleted,category_id) values (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setString(3, product.getImage());
        preparedStatement.setString(4, product.getIntroduction());
        preparedStatement.setBoolean(5, product.isDeleted());
        preparedStatement.setInt(6, product.getCategoryId());
        int rs = preparedStatement.executeUpdate();
        // nếu insert thành công rs > 0, rs là số bản ghi mình vừa thay đổi dữ liệu
        if (rs > 0) {
            // sử dụng hàm getGenerateKeys để trả về ResultSet chứa id tương ứng vừa thêm vào
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            // kiểm tra xem có kết quả result trả về hay k
            if (resultSet.next()) {
                // resultSet.getLong(số thứ tự cột) ở đây chỉ trả về 1 ô nên stt sẽ là 1
                // getGeneratedKeys chứa kiểu trả về là long
                newProduct = findById((int) resultSet.getLong(1));
            }
        }
        return newProduct;
    }

    public boolean update(Product product) throws SQLException {
        boolean result = false;
        String sql = "update product set name = ?,price = ?,image = ?,introduction = ?,deleted = ?,category_id = ? where id = ?";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setString(3, product.getImage());
        preparedStatement.setString(4, product.getIntroduction());
        preparedStatement.setBoolean(5, product.isDeleted());
        preparedStatement.setInt(6, product.getCategoryId());
        preparedStatement.setInt(7,product.getId());
        int rs = preparedStatement.executeUpdate();
        if(rs > 0) result = true;
        return result;
    }

    public boolean delete(int id) throws SQLException {
        boolean result = false;
        //xóa một bản ghi tức là chuyển trường deleted của bản ghi ấy về true
        String sql = "update product set deleted = true where id = ?";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setInt(1, id);
        int rs = preparedStatement.executeUpdate();
        if(rs > 0) result = true;
        return result;
    }

    //Hàm sort by nhận vào trường (field) muốn sắp xếp
    //và sắp xếp trường đấy tăng dần hoặc giảm dần
    //isASC = true thì sẽ là sắp xếp tăng dần
    //isASC = false thì sẽ là sắp xếp giảm dần DESC
    public List<Product> sortBy(String field, boolean isASC) throws SQLException {
        String sql = "select * from product where deleted = false order by " + field + (isASC ? " ASC" : " DESC");
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        return getList(resultSet);
    }

    //Hàm sử dụng để lấy ra danh sách các product có cùng category và phải đẩm bảo category chưa bị xóa
    //dể giải quyết được vấn đè trên thì phải thực hiện tìm kiếm thông tin các bản ghi trên 2 bảng product để lấy
    //ra thông tin của product và bảng category để kiểm tra xem category có id tương ứng đã bị xóa hay chưa
    public List<Product> findByCategory(int idCategory) throws SQLException {
        //product as p để rút gọn cú pháp
        //p.* đẻ lấy chỉ lấy tất cả thông tin ở trong bảng product
        //p.category_id = c.id là điều kiện nối
        //distinct để tránh trường hợp trùng lặp lặp tìm kiếm chỉ xảy ra khi tìm kiếm thông tin trên nhiều bảng một lần
        String sql = "select distinct p.* from product as p, category as c where c.deleted = false and c.id = ? and p.deleted = false and p.category_id = c.id";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setInt(1, idCategory);
        ResultSet resultSet = preparedStatement.executeQuery();
        return getList(resultSet);
    }

    public Product getLastProduct() throws SQLException {
        Product lastProduct = null;
        String sql = "select * from product where deleted = false order by id desc limit 1 ";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            lastProduct = getObject(resultSet);
        }

        return lastProduct;
    }
    public List<Product> search(String txtSearch) throws SQLException {
        String sql = "select * from product where deleted = false and " +
                "name like ? ";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1,"%"+txtSearch+"%");
        ResultSet resultSet = preparedStatement.executeQuery();
        return getList(resultSet);
    }

    @Override
    public List<Product> get3Product() throws SQLException {
        String sql = "select *\n" +
                "from product where deleted = false\n" +
                "order by id asc\n" +
                "limit 3";
        PreparedStatement statement = myConnection.prepare(sql);
        ResultSet resultSet = statement.executeQuery();
        return getList(resultSet);
    }

    @Override
    public List<Product> getNext3Product(int amount) throws SQLException {
        String sql = "select * from product where deleted = false order by id limit 3 offset ? ";
        PreparedStatement statement = myConnection.prepare(sql);
        statement.setInt(1, amount);
        ResultSet resultSet = statement.executeQuery();
        return getList(resultSet);
    }

    @Override
    public int getTotalProduct() throws SQLException {
        String sql = "select count(*) from product where deleted = false";
        PreparedStatement statement = myConnection.prepare(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) return resultSet.getInt(1);
        return 0;
    }

    @Override
    public List<Product> pagingProduct(int index) throws SQLException {
        String sql = "select * from product where deleted = false order by id limit 3 offset ? ";
        PreparedStatement statement = myConnection.prepare(sql);
        statement.setInt(1, (index-1)*3);
        ResultSet resultSet = statement.executeQuery();
        return getList(resultSet);
    }

    @Override
    public List<Product> getProductByPrice(double price1, double price2) throws SQLException {
        String sql = "select * from product where deleted = false and price > ? and price <= ?";
        PreparedStatement statement = myConnection.prepare(sql);
        statement.setDouble(1, price1);
        statement.setDouble(2, price2);
        ResultSet resultSet = statement.executeQuery();
        return getList(resultSet);
    }
}