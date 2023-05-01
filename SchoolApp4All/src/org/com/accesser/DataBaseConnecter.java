package org.com.accesser;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import java.util.*;
import org.apache.log4j.Logger;

public class DataBaseConnecter
{
    static  Logger             logger      = Logger.getLogger(DataBaseConnecter.class);
    private DataBaseConnecter  dbConnector = null;
    private ResultSet          rs          = null;// ResultSet for storing the data
    private String             query       = null;// The query string.
    private Statement          stmt        = null;// The statement object
    private PreparedStatement  batchStmt        = null;// The batch statement object
    private PreparedStatement  pstmt       = null;// The Prepared Statement object
    private Connection         con         = null;// The connection object to make database connection.
    private InitialContext     iniCon      = null;
    private DataSource         dataSource  = null;
    private boolean            flag        = false;
    public static String    dbpl_source  = null;
    private static String    dburl_str    = null;
    private static String    dbdriver_str = null;
    public static String    dbuser_str   = null;
    private static String    dbpasswd_str = null;
	private static String    connectionType = null;

	public final static int 	 iSessionInterval;
	public final static String ringtonePath ;
	public static String sUpdateCatchURL ;

	public final static String sVideoStartId;

	public static String strPlanId = null;
	public static String strMasterId = null ;
	public static String str_rapsvc = null ;
	public static String strProviderId= null ;
	public static String strRapServiceId = null;
	public static String strClassificationId = null ;
	public static String strRapDeviceMake  = null;
	public static String strRapDeviceModel= null ;
	public static String strRapSubId= null ;

	public static final boolean isEventId;
	public static final boolean isText; // added on 22-10-2005 @TEXT
	public static final boolean isContentIsKeyword;

	public static int NumberOfContents;
	//added by neha goyal on 7 july 2006 for search
	public static int NumberOfBoxes;
	public static int contentNumber;
	public static String contentTypes[];
	//added by neha goyal on 7 july 2006 for search
	public static String boxTypes[];
	public static String mandatory[];
	public static String deliveryTypes[];
	public static int NumberOfDeliveryTypes;


	public static String noOfInstance = "";
	public static String libDBName[];
	public static String libDBpoolName[];
	public static String libDBURL[];
	public static String libDBUN[];
	public static String libDBPWD[];
	public static String libDBFilesPath[];

	public static String ONELINELOGPATH=null;

	public static String fromUser="";

	private javax.sql.DataSource ds = null;
	private javax.sql.DataSource ds2 = null;

	static
	{
		ResourceBundle bundle = ResourceBundle.getBundle("salestracking");
		iSessionInterval = Integer.parseInt(bundle.getString("SESSION_INTERVAL"));

		ringtonePath = bundle.getString("RINGTONE_DIR");
		sUpdateCatchURL="";
		for(int i=1;i<100;i++)
		{
			try
			{
				sUpdateCatchURL  = sUpdateCatchURL + "," + bundle.getString("UPDATE_DATA_URL_"+i);
			}
			catch(Exception e)
			{
				break;
			}
		}
		sVideoStartId = bundle.getString("VIDEO_START_ID");

		//added on 14-10-2005 for comics COM //Added from comics database connector, by Amit Chorera
		{
			//int temp = 0;
			String str = null;
			boolean temp = true;
			try{
				str  = bundle.getString("isEventId").trim();
				if (str.trim().equals("0")){
					//isEventId = false;
					temp = false;
				}
				else
					temp = true;
					//isEventId = true;
			}
			catch(Exception e){
				temp = true;
				logger.fatal("Error Setting Property : isEventId");
			}
			finally{
				isEventId = temp;
			}
		} // end of du
		{
			contentTypes = new String[100];
			NumberOfContents=0;

			for(int i=0;i<100;i++)
			{
				try
				{
					contentTypes[i] = bundle.getString("CONT"+i).trim();
					NumberOfContents++;
				}
				catch(Exception e)
				{
					break;
				}
			}
			try
			{
				contentNumber= Integer.parseInt(bundle.getString("ContentNumber").trim());
			}
			catch(NumberFormatException e)
			{
				logger.fatal("Error : HGalleryAdmin.property Variable ContentNumber is Not Found or Not Proper");
			}
			catch(Exception ex)
			{
			}

			//Library
			noOfInstance = bundle.getString("NO_OF_INSTANCES");
			int instanceCnt = Integer.parseInt(noOfInstance);
			libDBName = new String[instanceCnt];
			libDBpoolName = new String[instanceCnt];
			libDBURL = new String[instanceCnt];
			libDBUN = new String[instanceCnt];
			libDBPWD = new String[instanceCnt];
			libDBFilesPath = new String[instanceCnt];

			for(int i=0;i<instanceCnt;i++)
			{
				try
				{
					String dbname = bundle.getString("DB_NAME_"+(i+1)).trim();
					String dbpool = bundle.getString("DB_POOL_NAME_"+(i+1)).trim();
					String dburl  = bundle.getString("DB_URL_"+(i+1)).trim();
					String dbuser = bundle.getString("DB_USER_"+(i+1)).trim();
					String dbpwd  = bundle.getString("DB_PASSWD_"+(i+1)).trim();
					String nfspath = bundle.getString("DB_FILE_PATH_"+(i+1)).trim();

					libDBName[i] = dbname;
					libDBpoolName[i] = dbpool;
					libDBURL[i] = dburl;
					libDBUN[i] = dbuser;
					libDBPWD[i] = dbpwd;
					libDBFilesPath[i] = nfspath;
				}
				catch(Exception e)
				{
					e.printStackTrace();
					logger.fatal("INSTANCE INFOP NT FOUND: "+e);
					break;
				}
			}

			//Library ends here

			//added by neha goyal on 7 july 2006 for search
			boxTypes = new String[100];
			mandatory = new String[100];
			NumberOfBoxes=0;

			for(int i=0;i<100;i++)
			{
				try
				{
					String fieldName = bundle.getString("Meta"+i).trim();
					if (fieldName.indexOf(':')==-1)
					{
						fieldName=fieldName+":0";
					}
					boxTypes[i]=fieldName.substring(0,fieldName.indexOf(':'));
					mandatory[i]=fieldName.substring(fieldName.indexOf(':')+1,fieldName.indexOf(':')+2);
					//boxTypes[i] = bundle.getString("Meta"+i).trim();
					NumberOfBoxes++;
				}
				catch(Exception e)
				{
					//e.printStackTrace();
					//logger.fatal("Error : HGalleryAdmin.property Variable CONT"+ i + " Not Found or Not Proper");
					break;
				}
			}
			//=====================================

			deliveryTypes = new String[100];
			for(int i=1;i<100;i++)
			{
				try
				{
					deliveryTypes[i] = bundle.getString("DELIVERY"+i).trim();
					NumberOfDeliveryTypes++;


				}
				catch(Exception e)
				{
					//e.printStackTrace();
					//logger.fatal("Error : HGalleryAdmin.property Variable CONT"+ i + " Not Found or Not Proper");
					break;
				}
			}

		}
		{  //Added By Amit Chorera from Comics DatabaseConnector

			String str = null;
			boolean temp = true;
			try{
				str  = bundle.getString("isText").trim();
				if (str.trim().equals("0")){
					temp = false;
				}
				else
					temp = true;
			}
			catch(Exception e){
				temp = false;
				logger.fatal("Error Setting Property : isText");
			}
			finally{
				isText = temp;
			}
		} // end of dummy block

		{ //Added By Amit Chorera from Comics databaseConnector

			String str = null;
			boolean temp = true ;
			try{
				str  = bundle.getString("isContentIsKeyword");
				if (str.trim().equals("0")){
					temp = false;
				}
				else
					temp = true;
			}
			catch(Exception e){
				temp = true;
				logger.fatal("Error Setting Property : isContentIsKeyword");
			}
			finally{
				isContentIsKeyword = temp;
			}
		} // end

		//if ()

		try
		{
			dbpl_source = bundle.getString("DB_POOL_NAME");
			connectionType = bundle.getString("CONNECTION_TYPE");


			dbdriver_str = bundle.getString("DBDRIVER");
	        dburl_str = bundle.getString("DBURL");
	        dbuser_str = bundle.getString("DBUSER");
	        dbpasswd_str = bundle.getString("DBPASSWD");


	        strPlanId= bundle.getString("planId");
			strMasterId = bundle.getString("masterId");
			str_rapsvc = bundle.getString("_rapsvc");
			strProviderId= bundle.getString("providerId");
			strRapServiceId= bundle.getString("rapServiceId");
			strClassificationId= bundle.getString("classificationId");
			strRapDeviceMake = bundle.getString("rapDeviceMake");
			strRapDeviceModel= bundle.getString("rapDeviceModel");
			strRapSubId = bundle.getString("rapSubId");


			//sUpdateCatchURL = bundle.getString("UPDATE_DATA_URL");
	        ONELINELOGPATH = bundle.getString("onelinelogpath");

		}
		catch(Exception e){
			logger.info("One or More values not provieded in Property file ");
			//logger.in
			logger.fatal("One or More values not provieded in Property file ");
			//throw new Exception("Property File Related Error");
		}


	}

	/**
     * DBConnector constructor comment.
     */
    public DataBaseConnecter() {
        super();
        if(connectionType.equals("2"))
        {
		   	ds = getDataSource();
        }
    }


	//NOT IN USE
    public Connection getConnection() throws Exception{
        con            = null;
        stmt           = null;
        rs             = null;

        try
		{
			if(connectionType.equals("1"))
			{
				Class.forName(dbdriver_str).newInstance();
				con = DriverManager.getConnection(dburl_str,dbuser_str,dbpasswd_str);
			}
			else
			{
				if(ds ==null)
					ds = getDataSource();
				con = ds.getConnection();
				logger.info("Got JNDI Data Source: ");
			}
			//con.setAutoCommit(false);
        }
        catch(ClassNotFoundException cnfe) {
            logger.fatal("### CLASSNOTFOUNDEXCEPTION : DataBaseConnecter :: getConnection() => " + cnfe.toString());
            throw cnfe;
        }
        catch (SQLException sqle) {
            logger.fatal("### SQLEXCEPTION : DataBaseConnecter :: getConnection() => " + sqle.toString());
            throw sqle;
        }
        catch (Exception ex) {
            logger.fatal("### EXCEPTION : DataBaseConnecter :: getConnection() => " + ex.toString());
            throw ex;
        }
        return con;
    }




     public void makeConnection() throws Exception{

	        //Initializing the necessary parameters.
	        con            = null;
	        stmt           = null;
	        rs             = null;

	        try
			{
				if(connectionType.equals("1"))
				{
					Class.forName(dbdriver_str).newInstance();
					con = DriverManager.getConnection(dburl_str,dbuser_str,dbpasswd_str);
					logger.info("Got Connection from JNDI Data Source: makeConnection");
				}
				else
				{
					if(ds ==null)
						ds = getDataSource();
					con = ds.getConnection();
					logger.info("Got Connection from JNDI Data Source: makeConnection");
				}
				//con.setAutoCommit(false);
	        }
	        catch(ClassNotFoundException cnfe) {
	            logger.fatal("### CLASSNOTFOUNDEXCEPTION : DataBaseConnecter :: makeConnection() => " + cnfe.toString());

	            throw cnfe;
	        }
	        catch (SQLException sqle) {

	            logger.fatal("### SQLEXCEPTION : DataBaseConnecter :: makeConnection() => " + sqle.toString());
	            throw sqle;
	        }
	        catch (Exception ex) {

	            logger.fatal("### EXCEPTION : DataBaseConnecter :: makeConnection() => " + ex.toString());
	            throw ex;
	        }

	    }

     public void makeDBConnection(String db_nm) throws Exception
	 {
        //Initializing the necessary parameters.
        con            = null;
        stmt           = null;
        rs             = null;

        try
		{
			if(connectionType.equals("1"))
			{
				for(int i=0;i<libDBName.length;i++)
				{
					if(db_nm.equals(libDBName[i]))
					{
						Class.forName(dbdriver_str).newInstance();
						con = DriverManager.getConnection(libDBURL[i],libDBUN[i],libDBPWD[i]);
						logger.info("Got Connection from JNDI Data Source: makeConnection");
						fromUser = libDBUN[i];
					}
				}
			}
			else
			{
				if(ds2 ==null)
					ds2 = getDBDataSource(db_nm);
				con = ds2.getConnection();
				logger.info("Got Connection from JNDI Data Source: makeConnection");
			}
			//con.setAutoCommit(false);
        }
        catch(ClassNotFoundException cnfe) {
            logger.fatal("### CLASSNOTFOUNDEXCEPTION : DataBaseConnecter :: makeConnection() => " + cnfe.toString());
            throw cnfe;
        }
        catch (SQLException sqle)
		{
            logger.fatal("### SQLEXCEPTION : DataBaseConnecter :: makeConnection() => " + sqle.toString());
            throw sqle;
        }
        catch (Exception ex)
		{
            logger.fatal("### EXCEPTION : DataBaseConnecter :: makeConnection() => " + ex.toString());
            throw ex;
        }
    }


	public boolean updateDatabasePrepared(Object[] values) throws Exception
	{
        try
		{

            pstmt = con.prepareStatement(query);
            if(values.length != 0)
			{
				int leng = values.length; // on 25/11/03
                for (int i=0;i<leng;i++)
				{
                    pstmt.setObject((i+1),values[i]);
                }
            }
		logger.debug("Qyery in DB Connector: @@@@@"+query);
			int j = pstmt.executeUpdate();
            return true;
        }
        catch(SQLException sqle)
		{
            logger.fatal("DataBaseConnecter.queryDatabasePrepared():SQLEXCEPTION=> " + sqle);
            throw sqle;
        }
        catch(Exception ex)
		{
            logger.fatal("DataBaseConnecter.queryDatabasePrepared():EXCEPTION=> " + ex);
            throw ex;
        }
    }

    public boolean updateDBBatch(PreparedStatement mypstmt ,Object[] values) throws Exception
	{
        try
		{


            if(values.length != 0)
			{
				int leng = values.length; // on 25/11/03
                for (int i=0;i<leng;i++)
				{
					if(values[i]==null)
						values[i]="";
                    mypstmt.setObject((i+1),values[i]);
                }
            }
		//logger.debug("Qyery in DB Connector: @@@@@"+query);
			mypstmt.addBatch();
		//	int j = pstmt.executeUpdate();
            return true;
        }
        catch(SQLException sqle)
		{
            logger.fatal("DataBaseConnecter.updateDBBatch():SQLEXCEPTION=> " + sqle);
            throw sqle;
        }
        catch(Exception ex)
		{
            logger.fatal("DataBaseConnecter.updateDBBatch():EXCEPTION=> " + ex);
            throw ex;
        }
    }

    public boolean executeDBBatch(PreparedStatement mypstmt) throws Exception
	{
        try
		{
			int j[] = mypstmt.executeBatch();
            return true;
        }
        catch(SQLException sqle)
		{
            logger.fatal("DataBaseConnecter.queryDatabasePrepared():SQLEXCEPTION=> " + sqle);
            throw sqle;
        }
        catch(Exception ex)
		{
            logger.fatal("DataBaseConnecter.queryDatabasePrepared():EXCEPTION=> " + ex);
            throw ex;
        }
    }


	private DataSource getDataSource()
	{
		try
		{
			InitialContext initialCtx = getContext();
			ds = (javax.sql.DataSource) initialCtx.lookup(dbpl_source);
		}
		 catch (SQLException sqle) {
		 logger.fatal("### SQLEXCEPTION : DataBaseConnecter :: getDataSource() => " + sqle.toString());

		}
		/*catch(NamingException nex){
			logger.fatal("### NAMINGEXCEPTION : DataBaseConnecter :: getConnection() => " + nex.toString());
			throw nex;
		} */
		catch (Exception ex) {
			logger.fatal("### EXCEPTION : DataBaseConnecter :: getDataSource() => " + ex.toString());

        }
        return ds;
	}


	private DataSource getDBDataSource(String db_nm)
	{
		try
		{

			for(int i=0;i<libDBName.length;i++)
			{
				if(db_nm.equals(libDBName[i]))
				{
					InitialContext initialCtx = getContext();
					ds = (javax.sql.DataSource) initialCtx.lookup(libDBpoolName[i]);
				}
			}
		}
		 catch (SQLException sqle) {
			 logger.fatal("### SQLEXCEPTION : DataBaseConnecter :: getDataSource() => " + sqle.toString());

		}
		catch (Exception ex) {
			logger.fatal("### EXCEPTION : DataBaseConnecter :: getDataSource() => " + ex.toString());

        }
        return ds;
	}

    //Added by jay
    /*public void closeDatabase(Connection conn) throws Exception {
        try {
			//logger.fatal(" => Connection Closed");
			if(conn != null)
			{
				conn.close();
				conn = null;
			}
			if(stmt != null)
				stmt.close();
			if(batchStmt != null)
				batchStmt.close();
			if(rs != null)
				rs.close();
			logger.info("Closed connection");
        }
        catch(SQLException sqle) {
            logger.fatal("Sql Exception : DataBaseConnecter :: closeDatabase(SessionData sessionData)"+sqle.getMessage());
            throw sqle;
        }
        catch(Exception exp) {
            logger.fatal("General Exception : DataBaseConnecter ::closeDatabase(SessionData sessionData)"+exp.getMessage());
            throw exp;
        }
		finally
		{
			if(con != null)
	            con.close();// closing the connection object.
		}
    }*/
    public void closeDatabase(SessionData sessionData) throws Exception {
	        try {
				//logger.fatal(" => Connection Closed");
				if(rs != null)
				{
					logger.info("Inside If: Closing Resultset");
					rs.close();
					rs = null;
					logger.info("Inside If: Closed Resultset");
				}
				if(stmt != null)
				{
					logger.info("Closing Statement");
					stmt.close();
					stmt =null;
					logger.info("Statement Closed");
				}
				if(batchStmt != null)
				{
					logger.info("Closing Batch Statement");
					batchStmt.close();
					batchStmt =null;
					logger.info("Batch Statement Closed");
				}
				if(pstmt != null)
				{
					logger.info("Closing pstmt Statement");
					pstmt.close();
					pstmt =null;
					logger.info("Batch pstmt Closed");
				}
				if(con != null)
				{
					logger.info("Closing Connection");
					con.close();
					con = null;
					logger.info("Inside If: Connection Closed");
				}
				System.gc();
				logger.info("Closed connection");
	        }
	        catch(SQLException sqle) {
	            logger.fatal("Sql Exception : DataBaseConnecter :: closeDatabase(SessionData sessionData)"+sqle.getMessage());
	            throw sqle;
	        }
	        catch(Exception exp) {
	            logger.fatal("General Exception : DataBaseConnecter ::closeDatabase(SessionData sessionData)"+exp.getMessage());
	            throw exp;
	        }
			finally
			{
				if(con != null)
		            con.close();// closing the connection object.
			}
    }

    /**
     * Explicit commit function.
     * parameters   : none
     *
     */
    public void commit() throws Exception{
        try {
            con.commit();
			logger.info("Baby data commited.");
        }
        catch(Exception ex) {
           logger.fatal("General Exception :  DataBaseConnecter ::  commit"+ex.toString());
        }
    }

    /**
     * Explicit rollback function.
     * parameters   : none
     *
     */
    public void  rollback() throws Exception{
        try {
            con.rollback();
        }
        catch(Exception ex) {
          logger.fatal("General Exception :  DataBaseConnecter :: rollback()  "+ex.toString());
        }
    }

    public ResultSet queryDatabase() throws Exception {
    	logger.info("@@@@@"+query);
    	try {
            if (stmt  == null)
            	stmt =  con.createStatement(); // creating the statment object.
            rs   = stmt.executeQuery(query);// executing the query and storing the result in the resultset.
            return rs; // returning the resultset.
        }
        catch (SQLException sqle) {
            logger.fatal("### SQLEXCEPTION : DataBaseConnecter :: queryDatabase() => " + sqle.getMessage());
            throw sqle;
        }
        catch(Exception ex) {
            logger.fatal("### EXCEPTION : DataBaseConnecter :: queryDatabase() => " + ex);
            ex.printStackTrace();
            throw ex;
        }
    }





    /**
     * This method will be used by other classes to set query which has to be executed.
     *
     * Creation date: 26-Jul-2002 14:45 hrs
     * parameters   : String query
     * return       : void
     */
    public void setQuery(String query) throws Exception{
    	//addQuotes(query); // for sql injection changd :added  by sunil gandhi
        this.query = query;
    }

    public static String addQuotes(String str1){
    	int i;
    	StringBuffer result ;
    	result = new StringBuffer(str1);
    	int index;

    	for(i=0;i<str1.length();i++)
    	{
    		index = str1.indexOf('\'',i);
    		if ( index == -1)
    			break;
    		result = new StringBuffer(str1);
    		result.insert(index,'\'');

    		i = index + 1;

    		str1 = new String(result);
    	}
    	return str1;
    }


    public boolean getFlag() {
        return this.flag;
    }

	 public void updateDatabase() throws Exception {

	 	logger.info("@@@@@"+query);
	        try {
	            if (stmt  == null)
	            	stmt=con.createStatement();// creating the statment object.
	            if(stmt.executeUpdate(query) >= 0)// executing the query.
	            {
	                flag = true;
	            }
	            con.commit();
	        }
	        catch(SQLException sqle) {
	            flag = false;
	            logger.fatal("Error in updatedatabase: " + sqle.getMessage());
	            throw sqle;
	        }
	        catch(Exception e) {
	            flag = false;
	            logger.fatal("Error in updatedatabase: " + e.getMessage());
	            throw e;
	        }
    }

    public void updateDatabase(boolean isCommit) throws Exception {
			/*  added By sunil gandhi beacause updateDatabase() commit everytime.
			*/
			logger.info("@@@@@"+query);
	        try {
	            if (stmt  == null)
	            	stmt=con.createStatement();// creating the statment object.
	            if(stmt.executeUpdate(query) >= 0)// executing the query.
	            {
	                flag = true;
	            }

				if (isCommit == true)
				{
					con.commit();
				}
				else
				{
					setAutoCommit(false);
					// no commit
				}
	        }
	        catch(SQLException sqle) {
	            flag = false;
	            logger.fatal("Error in updatedatabase: " + sqle.getMessage());
	            throw sqle;
	        }
	        catch(Exception e) {
	            flag = false;
	            logger.fatal("Error in updatedatabase: " + e.getMessage());
	            throw e;
	        }
    }


	public void setAutoCommit(boolean commit)
	{
		try
		{
				con.setAutoCommit(commit);
		}
		catch (SQLException sqle)
		{
            logger.fatal("### SQLEXCEPTION : DataBaseConnecter :: setAutoCommit() => " + sqle.getMessage());
        }
        catch(Exception ex)
		{
            logger.fatal("### EXCEPTION : DataBaseConnecter :: setAutoCommit() => " + ex.getMessage());
        }
	}



  public int callFunction(String command) throws Exception {
        try {


     		//String command1 = ("{ ? = call isUniqueGameName('sun','156')}");

        	CallableStatement cstmt = con.prepareCall(command);

        	//cstmt.setString(2,"sun");
        	//cstmt.setString(3,"156");


        	cstmt.registerOutParameter(1, Types.NUMERIC);


        	int result;

        	cstmt.executeUpdate();
        	result = cstmt.getInt(1);

        	cstmt.close();
			return result;
        }
        catch (SQLException sqle) {
            logger.fatal("### SQLEXCEPTION : DataBaseConnecter :: callFunction => " + sqle.getMessage());
            throw sqle;
        }
        catch(Exception ex) {
            logger.fatal("### EXCEPTION : DataBaseConnecter :: callFunction() => " + ex.getMessage());
            throw ex;
        }
    }


  public void callProcedure(String sProcerdureName) throws Exception {
        try {

				String command = " { call sProcerdureName } " ;
		       	CallableStatement cstmt = con.prepareCall(command);
			   	cstmt.executeUpdate();
	        	cstmt.close();
	    }
        catch (SQLException sqle) {
            logger.fatal("### SQLEXCEPTION : DataBaseConnecter :: callProcedure => " + sqle.getMessage());
            throw sqle;
        }
        catch(Exception ex) {
            logger.fatal("### EXCEPTION : DataBaseConnecter :: callProcedure() => " + ex.getMessage());
            throw ex;
        }
    }



  public int insertData() throws Exception {
        try {
			if (stmt  == null)
				stmt = con.createStatement(); // creating the statment object.
	        int iInsert   = stmt.executeUpdate(query);// executing the query and storing the result in the resultset.
			return iInsert;
        }
        catch (SQLException sqle) {
            logger.fatal("### SQLEXCEPTION : DataBaseConnecter :: insertData() => " + sqle.getMessage());
            throw sqle;
        }
        catch(Exception ex) {
            logger.fatal("### EXCEPTION : DataBaseConnecter :: insertData() => " + ex.getMessage());
            throw ex;
        }
    }


	  public InitialContext getContext() throws Exception {

	    InitialContext ctx = null;

	    try {

	        ctx = new InitialContext();

	    } catch(Exception ne) {

	     throw ne;

	  } finally {
	        return ctx;    //returning InitialContext object.
	    }//end try-catch-finally block
	}

	public ResultSet selectDatabasePrepared(Object aobj[])
        throws Exception
        {
            try
            {
                pstmt = con.prepareStatement(query);
                if(aobj.length != 0)
                {
                    int i = aobj.length;
                    for(int k = 0; k < i; k++)
                        pstmt.setObject(k + 1, aobj[k]);
                }
                logger.debug("Query in DB Connector: @@@@@" + query);
                rs = pstmt.executeQuery();
                return rs;
            }
            catch(Exception e)
            {
                logger.fatal("### SQLEXCEPTION : DataBaseConnecter ::  selectDatabasePrepared(Object aobj[]) " + e.getMessage());
                throw e;
            }
        }

        public void closePstmt() throws Exception
        {
            try
            {
                if(pstmt != null)
                {
                    pstmt.close();
                    pstmt = null;
                }
            }
            catch(Exception e)
            {
                logger.fatal("### SQLEXCEPTION : DataBaseConnecter ::  closePstmt() " + e.getMessage());
                throw e;
            }
        }
}
