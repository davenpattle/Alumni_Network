package Manager;

import com.google.gson.Gson;

public class ObjectToJsonManager {
private static ObjectToJsonManager instance = new ObjectToJsonManager();	
	
	private ObjectToJsonManager() {
		// Hidden Constructor
	}

	public static ObjectToJsonManager getInstance() {
		return instance;
	}
	
	public String ConvertToJson(Object obj){
		Gson gson = new Gson();
		String str = gson.toJson(obj);
		return str;
	}
}
