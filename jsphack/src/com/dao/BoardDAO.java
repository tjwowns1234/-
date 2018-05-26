package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


import com.entity.BoardDTO;
import com.entity.PageTO;


public class BoardDAO {

	 DataSource dataFactory;
	 
	 Connection conn = null;
	 PreparedStatement pstmt = null;
		   
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
		  /*
			public BoardDAO(){
				//DataSource ���
				try{
					 Context ctx = new InitialContext();
					dataFactory = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle11g");

				}catch(Exception e){ e.printStackTrace();}
			}//end ������
			*/
		//��Ϻ���
		public ArrayList<BoardDTO> list(){
			ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
				try{
					 
				Class.forName(jdbc_driver);
				 con = DriverManager.getConnection(jdbc_url,"root","rnfl12");
		         System.out.println("mysql접속 성공");
		         
				 String query = "SELECT  num , author, title,  content ,kid,email,  DATE_FORMAT( writeday , '%Y-%m-%d') writeday ,  readcnt , repRoot, repStep, repIndent FROM board order by repRoot desc , repStep asc";
				 pstmt = con.prepareStatement(query);
				 rs = pstmt.executeQuery();

				 while( rs.next()){
					int num = rs.getInt( "num" );
					String author = rs.getString( "author" );
					String title = rs.getString( "title" );
					String content = rs.getString( "content" );
					String kid = rs.getString( "kid" );
					String email = rs.getString( "email" );
					String writeday = rs.getString( "writeday" );
					int readcnt = rs.getInt( "readcnt" );
					int repRoot = rs.getInt( "repRoot");
					int repStep = rs.getInt( "repStep" );
					int repIndent = rs.getInt( "repIndent" );
					BoardDTO data = new BoardDTO();
					data.setNum( num );
					data.setAuthor( author );
					data.setTitle( title );
					data.setContent( content );
					data.setKid(kid);
					data.setEmail(email);
					data.setWriteday( writeday );
					data.setReadcnt( readcnt );
					data.setRepRoot( repRoot);
					data.setRepStep( repStep );
					data.setRepIndent( repIndent );
					list.add( data );
				 }//end while
				
				}catch(Exception e){ 
					e.printStackTrace();
				}finally{
					try {
						if( rs!= null) rs.close();
						if( pstmt!= null) pstmt.close();
						if( con!= null) con.close();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				 return list;

			/*
			connect();
		      System.out.println("디비 전체목록 들고오기 시작");
		      ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		      
		      String sql = "SELECT  num , author, title,  content ,  DATE_FORMAT( writeday , '%Y-%m-%d') writeday ,  readcnt , repRoot, repStep, repIndent FROM board order by repRoot desc , repStep asc";
		      try {
		         pstmt = conn.prepareStatement(sql);
		         ResultSet rs = pstmt.executeQuery();
		         while(rs.next()) {
		        	 int num = rs.getInt( "num" );
						String author = rs.getString( "author" );
						String title = rs.getString( "title" );
						String content = rs.getString( "content" );
						String writeday = rs.getString( "writeday" );
						int readcnt = rs.getInt( "readcnt" );
						int repRoot = rs.getInt( "repRoot");
						int repStep = rs.getInt( "repStep" );
						int repIndent = rs.getInt( "repIndent" );
						BoardDTO data = new BoardDTO();
						data.setNum( num );
						data.setAuthor( author );
						data.setTitle( title );
						data.setContent( content );
						data.setWriteday( writeday );
						data.setReadcnt( readcnt );
						data.setRepRoot( repRoot);
						data.setRepStep( repStep );
						data.setRepIndent( repIndent );
						list.add( data );
		            System.out.println("디비 전체목록 들고오기 완료");
		         }
		         rs.close();
		         
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }
		      finally {
		         disconnect();
		      }
		      return list;
		      */
			}//end select
		
		//�۾��� 
		public void write( String _title, String _author, String _content, String _kid, String _email){
			Connection con = null;
			PreparedStatement pstmt = null;
			String query=null;
			
			try{
				Class.forName(jdbc_driver);
				 con = DriverManager.getConnection(jdbc_url,"root","rnfl12");
		         System.out.println("mysql접속 성공");
		        	 query =" INSERT INTO board (title, author , content, kid,email,  repRoot , repStep , repIndent ) values ( ? , ? , ? , ?,?,(select max(num)+1 from board a) , 0 , 0 )";
			  
			  pstmt = con.prepareStatement( query );

			  pstmt.setString( 1, _title );
			  pstmt.setString( 2, _author );
			  pstmt.setString( 3, _content );
			  pstmt.setString( 4, _kid );
			  pstmt.setString( 5, _email );

			  int n = pstmt.executeUpdate();

			}catch(Exception e){ 
				e.printStackTrace();
			}finally{
				try {
					if( pstmt!= null) pstmt.close();
					if( con!= null) con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//end finally
		 }//end insert
		
		//��ȸ�� 1 ����
		  public  void readCount( String  _num ){
				
			  Connection con = null;
			  PreparedStatement pstmt = null;
				try{
					Class.forName(jdbc_driver);
					 con = DriverManager.getConnection(jdbc_url,"root","rnfl12");
			         System.out.println("mysql접속 성공");
					  String query = "UPDATE board SET readcnt = readcnt + 1 WHERE num="+ _num;
					  pstmt = con.prepareStatement( query );
					  int n = pstmt.executeUpdate( );

				}catch( Exception e){ 
					e.printStackTrace();
				}finally{
					try {
						if( pstmt!= null) pstmt.close();
						if( con!= null) con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}//end finally
		  }//end readCount
		  
		//�� �ڼ��� ����
		public BoardDTO retrieve( String  _num ){

			// ��ȸ�� ����
			readCount(  _num );
			 Connection con = null;
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;
			 BoardDTO data = new BoardDTO();
			 try{
				 Class.forName(jdbc_driver);
				 con = DriverManager.getConnection(jdbc_url,"root","rnfl12");
		         System.out.println("mysql접속 성공");
					
					String query = "SELECT * FROM board WHERE num = ?";
					pstmt = con.prepareStatement( query );
					pstmt.setInt(  1 ,   Integer.parseInt( _num ) );
					rs= pstmt.executeQuery();

					if( rs.next()){
						int num = rs.getInt( "num" );
						String title = rs.getString( "title" );
						String author = rs.getString( "author" );
						String content = rs.getString( "content" );
						String kid = rs.getString( "kid" );
						String email = rs.getString( "email" );
						String writeday = rs.getString( "writeday" );
						int readcnt = rs.getInt( "readcnt" );

						data.setNum( num );
						data.setTitle( title );
						data.setAuthor( author );
						data.setContent( content );
						data.setKid(kid);
						data.setEmail(email);
						data.setWriteday( writeday );
						data.setReadcnt( readcnt );
					}//end if
						
			 }catch( Exception e){ 
				 e.printStackTrace();
			 }finally{
					try {
						if( rs!= null) rs.close();
						if( pstmt!= null) pstmt.close();
						if( con!= null) con.close();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					 return data; 
			
		}//end retrieve
		
		//�� �����ϱ�
		public void update( String  _num , String  _title , String  _author , String  _content, String _kid, String _email ){
				
			 Connection con = null;
			 PreparedStatement pstmt = null;
			
			 
			try{
				Class.forName(jdbc_driver);
				 con = DriverManager.getConnection(jdbc_url,"root","rnfl12");
		         System.out.println("mysql접속 성공");
						String query = "UPDATE board SET  title = ?  ,  author = ? , content = ?, kid=?, email=?  WHERE num = ?";

						pstmt = con.prepareStatement( query );

						pstmt.setString ( 1 , _title );
						pstmt.setString( 2,  _author );
						pstmt.setString( 3 ,  _content );
						pstmt.setString( 4 ,  _kid );
						pstmt.setString( 5 ,  _email );
						pstmt.setInt(  6 ,   Integer.parseInt( _num ) );

					 int n = pstmt.executeUpdate();

				}catch( Exception e){ 
					e.printStackTrace(); 
				}finally{
					try {
						if( pstmt!= null) pstmt.close();
						if( con!= null) con.close();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

		 }//end update
		
		//�� �����ϱ�
		public void delete( String   _num ){
			
			 Connection con = null;
			 PreparedStatement pstmt = null;
			 
			try{
				Class.forName(jdbc_driver);
				 con = DriverManager.getConnection(jdbc_url,"root","rnfl12");
		         System.out.println("mysql접속 성공");
					
					String query = "DELETE FROM board WHERE num = ?";
					pstmt = con.prepareStatement( query );
					pstmt.setInt(  1 ,   Integer.parseInt( _num ) );
					
				  int n =	pstmt.executeUpdate( );

					
			
			}catch( Exception e) { 
				e.printStackTrace();
			}finally{
				try {
					if( pstmt!= null) pstmt.close();
					if( con!= null) con.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}//end delete
		
		// �˻� �ϱ�
		public ArrayList<BoardDTO>  search( String  _searchName,  String  _searchValue )	{
			System.out.println(_searchValue);
			ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
				try{
					Class.forName(jdbc_driver);
					 con = DriverManager.getConnection(jdbc_url,"root","rnfl12");
			         System.out.println("mysql접속 성공");
				 String query = "SELECT  num , author, title,  content ,kid,email,  DATE_FORMAT( writeday , '%Y-%m-%d') writeday ,  readcnt FROM board";
				 System.out.println("mysql 검색 시작");
		       if(  _searchName.equals( "title" )){

						query += "     WHERE  title  LIKE  ?";

			   }else{
						query += "    WHERE  author LIKE ?";
			   }//
		       System.out.println("mysql 검색 쿼리문 만들기");

		         pstmt = con.prepareStatement( query );
				 pstmt.setString(  1 ,   _searchValue );
		        
				 rs = pstmt.executeQuery( );
				 System.out.println(_searchValue);
				 System.out.println("mysql 리스트 뽑아내기 반복");
				 while( rs.next()){
					int num = rs.getInt( "num" );
					String author = rs.getString( "author" );
					String title = rs.getString( "title" );
					String content = rs.getString( "content" );
					String kid = rs.getString("kid");
					String email = rs.getString("email");
					String writeday = rs.getString( "writeday" );
					int readcnt = rs.getInt( "readcnt" );
					
					BoardDTO data = new BoardDTO();
					data.setNum( num );
					data.setAuthor( author );
					data.setTitle( title );
					data.setContent( content );
					data.setKid(kid);
					data.setEmail(email);
					data.setWriteday( writeday );
					data.setReadcnt( readcnt );
					list.add( data );
					System.out.println("mysql 검색 끝");
				 }//end while
					
				}catch(Exception e){ 
					e.printStackTrace();
				}finally{
					try {
						if( rs!= null) rs.close();
						if( pstmt!= null) pstmt.close();
						if( con!= null) con.close();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				 return list;
			}//end search
		
		// �亯�� �Է� �� ����
		public  BoardDTO  replyui( String  _num ){
			
			BoardDTO data = new BoardDTO();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			 try{
				 Class.forName(jdbc_driver);
				 con = DriverManager.getConnection(jdbc_url,"root","rnfl12");
		         System.out.println("mysql접속 성공");
					
					 String query = "SELECT * FROM board WHERE  num = ?";
					 pstmt = con.prepareStatement( query );
					 pstmt.setInt(  1 ,   Integer.parseInt( _num ) );
			         
					 rs  =  pstmt.executeQuery();

				if( rs.next()){
					data.setNum(  rs.getInt( "num" ));
					data.setTitle( rs.getString( "title" ));
					data.setAuthor( rs.getString( "author" ));
					data.setContent( rs.getString( "content" ));
					data.setKid(rs.getString("kid"));
					data.setEmail(rs.getString("email"));
					data.setWriteday( rs.getString( "writeday" ));
					data.setReadcnt( rs.getInt( "readcnt" ));
					data.setRepRoot( rs.getInt( "repRoot" ));
					data.setRepStep( rs.getInt( "repStep" ));
					data.setRepIndent( rs.getInt( "repIndent" ));
				}//end if
					
			 	 }catch( Exception e){ 
			 		 e.printStackTrace();
			 	 }finally{
						try {
							if( rs!= null) rs.close();
							if( pstmt!= null) pstmt.close();
							if( con!= null) con.close();
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					return data;
		}//end replyui

		//�亯���� ���� repStep 1 ����
		public void makeReply( String _root , String _step ){
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try{
				Class.forName(jdbc_driver);
				 con = DriverManager.getConnection(jdbc_url,"root","rnfl12");
		         System.out.println("mysql접속 성공");
					
	String query = "UPDATE board SET repStep = repStep + 1 WHERE  repRoot = ? AND  repStep > ? ";
	             pstmt = con.prepareStatement( query );
	             pstmt.setInt(  1 ,   Integer.parseInt( _root ) );
	             pstmt.setInt(  2 ,   Integer.parseInt( _step ) );
				 int n= pstmt.executeUpdate( );
					 
					
			}catch( Exception e){ 
				e.printStackTrace();
			}finally{
				try {
					if( pstmt!= null) pstmt.close();
					if( con!= null) con.close();		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

	}//
		
		//�亯 �ޱ�
		public void reply( String _num, String _title , String  _author, String _content , String _kid, String _email, String _repRoot,  String _repStep , String _repIndent ){
			
			makeReply( _repRoot,  _repStep );
			
			Connection con = null;
			PreparedStatement pstmt = null;
	try{
		Class.forName(jdbc_driver);
		 con = DriverManager.getConnection(jdbc_url,"root","rnfl12");
        System.out.println("mysql접속 성공");
String query = "INSERT INTO board (title, author, content, kid , email, repRoot, repStep, repIndent ) values ( ? , ? ,?,?, ? , ?, ?, ?) ";
             pstmt = con.prepareStatement( query );

             pstmt.setString ( 1,  _title );
             pstmt.setString ( 2, _author );
             pstmt.setString ( 3, _content );
             pstmt.setString ( 4, _kid );
             pstmt.setString ( 5, _email );
             pstmt.setInt ( 6, Integer.parseInt( _repRoot) );
             pstmt.setInt ( 7,  Integer.parseInt( _repStep) + 1 );
             pstmt.setInt ( 8,  Integer.parseInt( _repIndent) + 1 );

             int n = pstmt.executeUpdate();
		
	}catch( Exception e){ 
		e.printStackTrace();
	}finally{
		try {
			if( pstmt!= null) pstmt.close();
			if( con!= null) con.close();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}//end reply
	
		// ����¡ ó��: ��ü ���ڵ� ���� ���ϱ�
		public int totalCount(){
			
			    int count = 0;
			    Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
					try{
						Class.forName(jdbc_driver);
						 con = DriverManager.getConnection(jdbc_url,"root","rnfl12");
				         System.out.println("mysql접속 성공");
					  
					  String query = "SELECT  count(*) FROM board";
					  pstmt = con.prepareStatement( query );
					  rs =	pstmt.executeQuery( );
					
					 if( rs.next()){
						count = rs.getInt( 1 );
					 }
					
					 }catch( Exception e){ 
				 		 e.printStackTrace();
				 	 }finally{
							try {
								if( rs!= null) rs.close();
								if( pstmt!= null) pstmt.close();
								if( con!= null) con.close();
								
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					return count;
		}//end totalCount
		
		// ������ ����
		public PageTO  page( int curPage ){

			  PageTO   to = new PageTO();
			  int totalCount = totalCount();

			  ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
			  Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
			     try{
			    	 Class.forName(jdbc_driver);
					 con = DriverManager.getConnection(jdbc_url,"root","rnfl12");
			         System.out.println("mysql접속 성공");
			 String query = "SELECT  num , author, title,  content , kid ,email,  DATE_FORMAT( writeday , '%Y-%m-%d') writeday ,  readcnt , repRoot, repStep, repIndent FROM board order by repRoot desc , repStep asc";
			    
			 pstmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
			 rs = pstmt.executeQuery(  );
				
				  int perPage = to.getPerPage(); //5
				  int skip = ( curPage - 1 ) * perPage;
						if( skip > 0 ){
							rs.absolute( skip );
						}
						for( int i = 0 ; i < perPage &&  rs.next() ; i++){
						int num = rs.getInt( "num" );
						String author = rs.getString( "author" );
						String title = rs.getString( "title" );
						String content = rs.getString( "content" );
						String kid = rs.getString("kid");
						String email = rs.getString("email");
						String writeday = rs.getString( "writeday" );
						int readcnt = rs.getInt( "readcnt" );
						int repRoot = rs.getInt( "repRoot");
						int repStep = rs.getInt( "repStep" );
						int repIndent = rs.getInt( "repIndent" );
						BoardDTO data = new BoardDTO();
						data.setNum( num );
						data.setAuthor( author );
						data.setTitle( title );
						data.setContent( content );
						data.setKid(kid);
						data.setEmail(email);
						data.setWriteday( writeday );
						data.setReadcnt( readcnt );
						data.setRepRoot( repRoot);
						data.setRepStep( repStep );
						data.setRepIndent( repIndent );
						list.add( data );
					}//end for
						
						to.setList( list ); // ArrayList ����
						to.setTotalCount( totalCount ); // ��ü ���ڵ� ����
						to.setCurPage( curPage ); // ���� ������

			     }catch( Exception e){ 
			 		 e.printStackTrace();
			 	 }finally{
						try {
							if( rs!= null) rs.close();
							if( pstmt!= null) pstmt.close();
							if( con!= null) con.close();
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					return to;
			}//end page
		
		
		
		
		
		
		

}//end class
