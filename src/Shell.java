

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.omg.CORBA.portable.InputStream;

public class Shell {
public static String programmername="Ravin Lamichhane";
	public static void main(String[] args) throws Exception {
		String response="";
		String terminate="EXIT";
     Scanner newScanner=new Scanner(System.in);
     
		//While loop that runs until the Exit is entered 
		while(!response.toUpperCase().equals(terminate))
		{
			//Prompt the user for input
			System.out.print(">>>");
			response=newScanner.nextLine();
			
			switch(response.toUpperCase())
			{
			
			case "LS":
				
				ls();
				break;
			case "PWD":
				pwd();
				break;
			case "CLEAR":
				clear();
				break;
			case "DATE":
				date();
				break;
			case "HELP":
				help();
				break;
			case "HELP LS":
				
			System.out.println(gethelpfunction(response.substring(response.indexOf(" ")+1)));
			break;
			case "HELP PWD":
				System.out.println(gethelpfunction(response.substring(response.indexOf(" ")+1)));
				break;
			case "HELP CLEAR":
				System.out.println(gethelpfunction(response.substring(response.indexOf(" ")+1)));
				break;
			case "HELP DATE":
				System.out.println(gethelpfunction(response.substring(response.indexOf(" ")+1)));
				break;
			case "HELP EXIT":
				System.out.println(gethelpfunction(response.substring(response.indexOf(" ")+1)));
				break;
			case "HELP WHOAMI":
				System.out.println(gethelpfunction(response.substring(response.indexOf(" ")+1)));
				break;
			case "WHOAMI":
				whoami();
				break;
				
			case "EXIT":
			System.exit(1);
				break;
				default:
					System.out.println("Unknown");
					break;
			}
			
		}
	
		
	}
/*
 * 
 * Prints out programmer name
 */
	private static void whoami() {
		System.out.println(programmername);
		
	}

/*
 * 
 * Prints out help panel 
 */
	private static void help() {
	System.out.println("MyShell, version 1.0, runs on Windows 10,developed by "+programmername+
			"\n Release date Mar 20,2020 "+
			"\n These shell commands are defined internally.Type 'help' to see this list."
			+ "\n Command and it's parameter, if any, should be seperated by one space only."
			+ "\n Type 'help name' to find more about the command name ");
	System.out.println("Command \t \t \t function"
			+ "\n ======= \t \t \t ====== \n "
			+ "ls \t \t \t" +gethelpfunction("ls")+ "\n"
			+ "pwd \t \t \t " +gethelpfunction("pwd")+  "\n"
			+ "clear \t \t \t " +gethelpfunction("clear")+  "\n"
			+ "date \t \t \t " +gethelpfunction("date")+  " \n "
			+"help[cmd] \t \t get help for command cmd('cmd is optional); \n \t \t \t \t without cmd displays this list \n"
			+ "exit \t \t \t "+gethelpfunction("Exit") +"\n"
			+ "whoami \t \t \t "+gethelpfunction("WhoamI")+"\n"
			
			);
	
	
	}
	/*
	 * Prints out date in the format EEE MMM d HH:mm:ss zzz YYYY. eg.Sun Mar  1 11:08:58 CST 2020
	 */

	private static void date() throws IOException, ParseException {
		//Intializing a simple date to read given string in date format
		SimpleDateFormat old= new SimpleDateFormat("EEE MM/dd/YYYY HH:mm:ss.SS zzz");
		// Intializing a simple date to change the date object to required format
		SimpleDateFormat result=new SimpleDateFormat("EEE MMM d HH:mm:ss zzz YYYY");
Process date ;
//reading date from cmd and storing 
		date = new ProcessBuilder("cmd.exe","/c","echo %date%").start();
		//scanner to read the input stream form cmd
		Scanner scanner= new Scanner(new InputStreamReader(date.getInputStream())); 
		String date1=scanner.nextLine();
		scanner.close();
		
		//reading current time from cmd and storing
		Process time;
		time=new ProcessBuilder("cmd.exe","/c","echo %time%").start();
		Scanner scanner1=new Scanner(new InputStreamReader(time.getInputStream()));
		String time1=scanner1.nextLine();

		scanner1.close();
		
		
		//reading current timezone from cmd and storing
		Process timezone;
		timezone=new ProcessBuilder("cmd.exe","/c","tzutil /g").start();
		Scanner scanner2=new Scanner(new InputStreamReader(timezone.getInputStream()));
		String timezone1=scanner2.nextLine();
		scanner2.close();
		//combining date,time and timezone for complete date
		String complete= date1+" "+time1+" "+intial(timezone1);
		//converting string to date object
		java.util.Date resultdate=old.parse(complete);
		//formating date object and printing 
     System.out.println(result.format(resultdate));

		
	}
/*
 * takes string as input
 * returns intial letter of given string
 */

	private static String intial(String timezone1) {
		
		 String result="";
		    String[] myName = timezone1.split(" ");
		    for (int i = 0; i < myName.length; i++) {
		        String s = myName[i];
		      result+=s.charAt(0);
		    }
		    return result;
		    
	}
	

	private static void clear() throws Exception {
		new ProcessBuilder("cmd.exe", "/c", "cls").inheritIO().start().waitFor();
		    
		}
	

	private static void pwd() throws IOException {
		
Process pwd;
		
		
			pwd = new ProcessBuilder("cmd.exe","/c","echo.%cd%").start();
		
		
		Scanner scanner= new Scanner(new InputStreamReader(pwd.getInputStream()));
		
		while(scanner.hasNextLine())
		{
			System.out.println(scanner.nextLine());
		}
		scanner.close();
		
		
	
	}

	private static void ls() throws IOException {
		Process ls;
		
		ls = new ProcessBuilder("cmd.exe","/c","dir").start();
		
		Scanner scanner= new Scanner(new InputStreamReader(ls.getInputStream()));
		
		while(scanner.hasNextLine())
		{
			System.out.println(scanner.nextLine());
		}
		scanner.close();
	}
	
	public static String gethelpfunction(String cmd) 
	{
		switch(cmd.toUpperCase())
		{
		case "LS":
			return "list contents of current directory";
			
		case "PWD":
			return "displayes the current directory";
		
		case "CLEAR":
			return "clears the console";
		
		case "DATE":
			
		
			return "displays current day,date,time and time zone";
			
		case "EXIT":
			
			return "quit console";
		
		case "WHOAMI":
			return "displays the name of programmer";
		
		}
		return null;
		
	
	}

}
