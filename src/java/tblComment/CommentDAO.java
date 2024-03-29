/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tblComment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.ConnectDB;

/**
 *
 * @author lehuuhieu
 */
public class CommentDAO {

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    public void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (pst != null) {
            pst.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public boolean create(String description, String email, int articleId) throws Exception {
        boolean check = false;

        try {
            String sql = "insert into Comment values (?,?,?)";
            con = ConnectDB.makeConnnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, description);
            pst.setString(2, email);
            pst.setInt(3, articleId);
            check = pst.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public List<CommentDTO> listComment(int articleId) throws Exception {
        List<CommentDTO> list = new ArrayList<>();

        try {
            String sql = "select c.Id, c.Description, c.UserEmail, u.Name\n"
                    + "from Comment c\n"
                    + "join [User] u\n"
                    + "on c.UserEmail = u.Email\n"
                    + "where ArticleId = ?";
            con = ConnectDB.makeConnnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, articleId);
            rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String description = rs.getString(2);
                String userEmail = rs.getString(3);
                String userName = rs.getString(4);
                list.add(new CommentDTO(id, articleId, description, userEmail, userName));
            }
        } finally {
            closeConnection();
        }

        return list;
    }
}
