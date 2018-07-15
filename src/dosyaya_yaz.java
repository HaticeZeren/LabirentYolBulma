import java.io.File;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
public class dosyaya_yaz {
	
private ogrenme_matrisi Q_matrisi;
private dosyaOku R_matrisi;
private int baslangic;
private int bitis;
private int hamle;
private double max;

public dosyaya_yaz(ogrenme_matrisi Q,dosyaOku R ,int baslangic,int bitis){
	this.Q_matrisi=Q;
	this.R_matrisi=R;
	this.baslangic=baslangic;
	this.bitis=bitis;
}
public void OutPath_yaz() throws IOException{
	File dosya=new File("outPath.txt");
	FileWriter yaz=new FileWriter(dosya);
	max=0;
	yaz.write(baslangic+"->"); // dosyaya yaz dedik 
	while(baslangic!=bitis){
		int durum=baslangic;
		for(int j=0;j<R_matrisi.get_matris_boyutu();j++){
			
			if(max<Q_matrisi.matris_Q[durum][j]&&durum!=j){//dugumun kendine donusu olmasýn
			  max=Q_matrisi.matris_Q[durum][j];
			  hamle=j;
			  
			}
		}
		if(hamle!=bitis) yaz.write(hamle+"->"); // dosyaya yaz dedik 
		baslangic=hamle;
	}
	
	yaz.write(bitis+" ");
	yaz.close(); // dosyayý yazdýktan sonra kapadýk 
	
}

public void Out_Q_yaz() throws IOException{
	File dosya=new File("out_Q.txt");
	FileWriter yaz=new FileWriter(dosya);

	String newLine = System.getProperty("line.separator");
	for(int i=0;i<R_matrisi.get_matris_boyutu();i++){
		for(int j=0;j<R_matrisi.get_matris_boyutu();j++){
			//yaz.write(Q_matrisi.get_Q()[i][j]+"   ");
			yaz.write(new DecimalFormat("##.##").format(Q_matrisi.get_Q()[i][j])+"\t");
		}
		 yaz.write(newLine);
	}
     yaz.close();
}

public void Out_R_yaz() throws IOException{
	File dosya=new File("out_R.txt");
	FileWriter yaz=new FileWriter(dosya);
	String newLine = System.getProperty("line.separator");

	for(int i=0;i<R_matrisi.get_matris_boyutu();i++){
		for(int j=0;j<R_matrisi.get_matris_boyutu();j++){
			yaz.write(R_matrisi.matris_R[i][j]+"\t");
		}
		 yaz.write(newLine);
	}
     yaz.close();
}

	
}
