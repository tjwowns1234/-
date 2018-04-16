package team10jsp.ch07;

public class LoginBean {

		public String userid="test name";
		public String passwd="test pass";
		
		final String _userid = "team10";
		final String _passwd = "1234";
		
		public boolean checkUser(String a, String b)
		{
			this.userid = a;
			this.passwd = b;
			if(userid.equals(_userid) && passwd.equals(_passwd) )
				return true;
			else
				return false;
		}
		
		public void setUserid(String userid)
		{
			this.userid = userid;
		}
		public void setPasswd(String passwd)
		{
			this.passwd = passwd;
		}
		public String getUserid()
		{
			return userid;
		}
		public String getPasswd()
		{
			return passwd;
		}
}
