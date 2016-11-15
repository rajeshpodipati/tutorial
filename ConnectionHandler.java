import java.io.*;
import java.net.Socket;

public class ConnectionHandler extends Thread{

	Socket s;
	
	PrintWriter pw;
	
	BufferedReader br;
	
	public ConnectionHandler(Socket s) throws IOException{
		this.s = s;
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		pw = new PrintWriter(s.getOutputStream());
	}
	
	@Override
	public void run() {
		
		try{
		String reqS = "";
		
		while(br.ready() || reqS.length() == 0){
			reqS +=  (char)br.read();
		}
		
		System.out.println(reqS);
		
		if(reqS !=""){
			HttpRequest req = new HttpRequest(reqS);
			HttpResponse res = new HttpResponse(req);
			
			pw.write(res.response.toCharArray());
		}
		

		pw.close();
		br.close();
		s.close();
		
		//System.exit(0);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		}
}
