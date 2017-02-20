package za.co.reverside.service;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;


public class Convert_CSV_XSL {

//	@SuppressWarnings("deprecation")
//	public static void main(String args[]) throws IOException
//	{ 
//		ArrayList<Object> arList=null;
//		ArrayList<Object> al=null;
//		String thisLine;  
//		FileInputStream fis = new FileInputStream(new File("src/main/java/reversideCIB.csv"));
//		DataInputStream myInput = new DataInputStream(fis);
//		int i=0;
//		arList = new ArrayList<Object>();
//		while ((thisLine = myInput.readLine()) != null)
//		{
//			al = new ArrayList<Object>();
//			String strar[] = thisLine.split(",");
//			for(int j=0;j<strar.length;j++)
//			{
//				al.add(strar[j]);
//			}
//			arList.add(al);
//			System.out.println();
//			i++;
//		} 
//
//		try
//		{
//			HSSFWorkbook hwb = new HSSFWorkbook();
//			HSSFSheet sheet = hwb.createSheet("new sheet");
//			for(int k=0;k<arList.size();k++)
//			{
//				ArrayList<Object> ardata = (ArrayList)arList.get(k);
//				HSSFRow row = sheet.createRow((short) 0+k);
//				for(int p=0;p<ardata.size();p++)
//				{
//					HSSFCell cell = row.createCell((short) p);
//					String data = ardata.get(p).toString();
//					if(data.startsWith("=")){
//						cell.setCellType(Cell.CELL_TYPE_STRING);
//						data=data.replaceAll("\"", "");
//						data=data.replaceAll("=", "");
//						cell.setCellValue(data);
//					}else if(data.startsWith("\"")){
//						data=data.replaceAll("\"", "");
//						cell.setCellType(Cell.CELL_TYPE_STRING);
//						cell.setCellValue(data);
//					}else{
//						data=data.replaceAll("\"", "");
//						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
//						cell.setCellValue(data);
//					}
//				}
//				System.out.println();
//			} 
//			FileOutputStream fileOut = new FileOutputStream("test.xls");
//			hwb.write(fileOut);
//			fileOut.close();
//			System.out.println("Your excel file has been generated");
//		} catch ( Exception ex ) {
//			ex.printStackTrace();
//		} //main method ends
//		finally{
//			myInput.close();
//		}
//	} 
}