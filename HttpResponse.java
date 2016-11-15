import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HttpResponse{

	HttpRequest req;

	String response;

	String root = "D:/root";

	public HttpResponse(HttpRequest request){
		req = request;	

		File f ;
		FileInputStream fis ;
		try {
			System.out.println("::filename::"+req.filename);
			if(req.filename == "" || req.filename == "/" ){
				System.out.println("innnn");
				response = "HTTP/1.1 553 File name required\r\n" +
						"Content-Length: 22\r\n" +
						"Content-Type: text/html\r\n\r\n" +
						"<h1>553 File name required</h1>";
			}else{
				f = new File(root + req.filename);
				fis = new FileInputStream(f);
				response = "HTTP/1.1 200 \r\n";

				response += "Server: Our Java Server/1.0 \r\n";
				response += "Content-Type: text/html \r\n";
				response += "Connection: close \r\n";
				response += "Content-Length: "+f.length() +" \r\n";
				response += "\r\n";

				int s;
				while((s = fis.read()) != -1){
					response += (char) s;
				}
				fis.close();
			}
		}catch(FileNotFoundException e){
			response = "HTTP/1.1 404 Not Found\r\n" +
					"Content-Length: 22\r\n" +
					"Content-Type: text/html\r\n\r\n" +
					"<h1>404 Not Found</h1>";
		}
		catch (Exception e) {
			response = "HTTP/1.1 500 Internal Server error\r\n" +
					"Content-Length: 22\r\n" +
					"Content-Type: text/html\r\n\r\n" +
					"<h1>500 Internal Server error</h1>";
		}
	}
}