package jspbook.addrbook;

import java.sql.*;
import java.util.*;

public class AddrBean { 
   
   Connection conn = null;
   PreparedStatement pstmt = null;
   PreparedStatement pstmt2 = null;
   
   /* Oracle 연결정보
   String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
   String jdbc_url = "jdbc:oracle:thin:@220.68.14.7:1521";
   */
   
   /* MySQL 연결정보 */
   String jdbc_driver = "com.mysql.jdbc.Driver";
   String jdbc_url = "jdbc:mysql://localhost:3308/jjsdb"; 
   
   // DB연결 메서드
   void connect() {
      try {
         Class.forName(jdbc_driver);

         conn = DriverManager.getConnection(jdbc_url,"root","rnfl12");
         System.out.println("mysql접속 성공");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   void disconnect() {
      if(pstmt != null) {
         try {
            pstmt.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      } 
      if(conn != null) {
         try {
            conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }
   
   // 수정된 주소록 내용 갱신을 위한 메서드
   public boolean updateDB(AddrBook addrbook) {
      connect();
      
      String sql ="UPDATE member SET ab_name=?, ab_email=?, ab_birth=?, ab_tel=?, ab_comdept=?, ab_memo=?, ab_sex=?, ab_kid=?, ab_job=? where ab_id=?";      
       
      try {
    	  System.out.println("디비 업데이트 시작");
         pstmt = conn.prepareStatement(sql);
         System.out.println("디비 업데이트 pstmt 쿼리문 셋팅");
         pstmt.setString(1,addrbook.getAb_name());
         System.out.println("디비 업데이트 name 들고오기");
         pstmt.setString(2,addrbook.getAb_email());
         System.out.println("디비 업데이트 email 들고오기");
         pstmt.setString(3, addrbook.getAb_birth());
         pstmt.setString(4,addrbook.getAb_tel());
         pstmt.setString(5,addrbook.getAb_comdept());
         pstmt.setString(6,addrbook.getAb_memo());
         pstmt.setString(7,addrbook.getAb_sex());
         pstmt.setString(8,addrbook.getAb_kid());
         pstmt.setString(9,addrbook.getAb_job());
         pstmt.setInt(10,addrbook.getAb_id());
         System.out.println("디비 업데이트 id 들고오기");
         pstmt.executeUpdate();
         
         System.out.println("디비 업데이트 완료");
      } catch (SQLException e) {
         e.printStackTrace();
         return false;
      }
      finally {
         disconnect();
      }
      return true;
   }
   
   // 특정 주소록 게시글 삭제 메서드
   public boolean deleteDB(int gb_id) {
      connect();
      
      String sql ="delete from member where ab_id=?";
      String sql2="ALTER TABLE member AUTO_INCREMENT=1 SET @COUNT = 0 UPDATE member SET member.ab_id = @COUNT:=@COUNT+1";
      
      try {
    	  System.out.println("디비 삭제 시작");
    	 pstmt2 = conn.prepareStatement(sql2);
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1,gb_id);
         pstmt.executeUpdate();
         pstmt2.executeUpdate();
         System.out.println("디비 삭제 완료");
      } catch (SQLException e) {
         e.printStackTrace();
         return false;
      }
      finally {
         disconnect();
      }
      return true;
   }
   
   // 신규 주소록 메시지 추가 메서드
   public boolean insertDB(AddrBook addrbook) {
      connect();
      // sql 문자열 , gb_id 는 자동 등록 되므로 입력하지 않는다.
            
      String sql ="insert into member(ab_name,ab_email,ab_birth,ab_tel,ab_comdept,ab_memo,ab_sex,ab_kid,ab_job) values(?,?,?,?,?,?,?,?,?)";
      
      try {
    	  System.out.println("디비 추가 시작");
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1,addrbook.getAb_name());
         pstmt.setString(2,addrbook.getAb_email());
         pstmt.setString(3, addrbook.getAb_birth());
         pstmt.setString(4,addrbook.getAb_tel());
         pstmt.setString(5,addrbook.getAb_comdept());
         pstmt.setString(6,addrbook.getAb_memo());
         pstmt.setString(7,addrbook.getAb_sex());
         pstmt.setString(8,addrbook.getAb_kid());
         pstmt.setString(9,addrbook.getAb_job());
         pstmt.executeUpdate();
         System.out.println("디비 추가 완료");
      } catch (SQLException e) {
         e.printStackTrace();
         return false;
      }
      finally {
         disconnect();
      }
      return true;
   }

   // 특정 주소록 게시글 가져오는 메서드
   public AddrBook getDB(int gb_id) {
      connect();
      System.out.println("디비 가져오기");
      String sql = "select * from member where ab_id=?";
      AddrBook addrbook = new AddrBook();
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1,gb_id);
         ResultSet rs = pstmt.executeQuery();
         
         // 데이터가 하나만 있으므로 rs.next()를 한번만 실행 한다.
         rs.next();
         addrbook.setAb_id(rs.getInt("ab_id"));
         addrbook.setAb_name(rs.getString("ab_name"));
         addrbook.setAb_email(rs.getString("ab_email"));
         addrbook.setAb_birth(rs.getString("ab_birth"));
         addrbook.setAb_tel(rs.getString("ab_tel"));
         addrbook.setAb_comdept(rs.getString("ab_comdept"));
         addrbook.setAb_memo(rs.getString("ab_memo"));
         addrbook.setAb_sex(rs.getString("ab_sex"));
         addrbook.setAb_kid(rs.getString("ab_kid"));
         addrbook.setAb_job(rs.getString("ab_job"));
         rs.close();
         System.out.println("디비 가져오기 완료");
      } catch (SQLException e) {
         e.printStackTrace();
      }
      finally {
         disconnect();
      }
      return addrbook;
   }
   
   // 전체 주소록 목록을 가져오는 메서드
   public ArrayList<AddrBook> getDBList() {
      connect();
      System.out.println("디비 전체목록 들고오기 시작");
      ArrayList<AddrBook> datas = new ArrayList<AddrBook>();
      
      String sql = "select * from member order by ab_id desc";
      try {
         pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery();
         while(rs.next()) {
            AddrBook addrbook = new AddrBook();
            
            addrbook.setAb_id(rs.getInt("ab_id"));
            addrbook.setAb_name(rs.getString("ab_name"));
            addrbook.setAb_email(rs.getString("ab_email"));
            addrbook.setAb_birth(rs.getString("ab_birth"));
            addrbook.setAb_tel(rs.getString("ab_tel"));
            addrbook.setAb_comdept(rs.getString("ab_comdept"));
            addrbook.setAb_memo(rs.getString("ab_memo"));
            addrbook.setAb_sex(rs.getString("ab_sex"));
            addrbook.setAb_kid(rs.getString("ab_kid"));
            addrbook.setAb_job(rs.getString("ab_job"));
            datas.add(addrbook);
            System.out.println("디비 전체목록 들고오기 완료");
         }
         rs.close();
         
      } catch (SQLException e) {
         e.printStackTrace();
      }
      finally {
         disconnect();
      }
      return datas;
   }
}