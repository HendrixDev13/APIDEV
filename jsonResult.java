package Programacion.PROGRAMACION.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class jsonResult {
	public int id;
    public String result;
    public String msj;
    public String detail;
    public String message;
    public String status;
    public List<Map<String, Object>> data;
//    public List<ArrayList<String>> dataArrayList;
    
    public jsonResult(){
                   super();
                   this.id = -1;
                   this.result = "ERROR";
                   this.data=new ArrayList<Map<String, Object>>();
//                   this.dataArrayList= new ArrayList<ArrayList<String>>();

    }
    
    public jsonResult(int id, String result, String msj, String detail, List<Map<String, Object>> data) {
                   super();
                   this.id = id;
                   this.result = result;
                   this.msj = msj;
                   this.detail=detail;
                   this.data = data;
//                   this.dataArrayList = dataArrayList;
       }
    public jsonResult(int id, String result, String msj) {
                   super();
                   this.id = id;
                   this.result = result;
                   this.msj = msj;
    }
}
