package conveniencemanagementsystem.persistant.util;

public class IdGenerator {
	public static String generateStaffId(int lastId) {
		 lastId++;
		 String staffId = "Staff" + String.format("%04d", lastId);
		 return staffId;
	}
	
	 public static String generateProductCode(int lastID) {
		 lastID++;
		 String productCode = "P" + String.format("%04d", lastID);
		 return productCode;
	}
}
