import java.util.Vector;

public class ogrenme_matrisi {
       public double[][] matris_Q;
       
       
       public ogrenme_matrisi(dosyaOku a){
    	   matris_Q = new double[a.get_matris_boyutu()][a.get_matris_boyutu()];
       
         }
       public double[][] get_Q(){
    	   return matris_Q;
       }
       
       public int max_Q(int durum,dosyaOku kazanc){
    	   double max=0;
    	   int hamle1=-2;//R matrisinde olmayan bir deðer olduðu için bu rakam verildi
    	    int sayac=0;
    	    int[] rastgele=new int[kazanc.get_matris_boyutu()];
    	    Vector x=new Vector();
    	    int boyut=kazanc.get_matris_boyutu();
    	   for(int i=0;i<boyut;i++){
    		   if(kazanc.get_matris()[durum][i]>=0){ //R[][] komsuluk varsa 0 veya 100 döner.
    			   
    			   if(max<=matris_Q[durum][i]&&matris_Q[durum][i]!=0){
    				   
    			   if(x.isEmpty()){
   					x.addElement(new Integer(i));
   					  hamle1=i;
   					max=matris_Q[durum][i];
   				   }
    			   
    			   else{
    				   if(max<matris_Q[durum][i]){
    					x.clear();
       					x.addElement(new Integer(i));
       					hamle1=i;
       					max=matris_Q[durum][i];
    				   }
    				   else if(max==matris_Q[durum][i]){
    					   x.addElement(new Integer(i));
          				   hamle1=i;
    				   }
    			  }
    			   
    			   }
    			  
    			  else if(matris_Q[durum][i]==0){
     			      rastgele[sayac]=i;
     			      sayac++;
     		   }
    			    
    		   }
    		   
    		   
    		  
    	   }
    	   
    	   if(hamle1==-2){
    		   int sayi = (int)(Math.random()*sayac);//0-sayac arasinda random sayi üretir.
    		   return rastgele[sayi];
    	   }
    	   
    	   else{
    		   int sayi1 = (int)(Math.random()*x.size());//0-vektor elaman sayisi arasinda random sayi üretir.
    		   return ((Integer)x.get(sayi1)).intValue();
    		   }
    	  
       }
       }
    
       

