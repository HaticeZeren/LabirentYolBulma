
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class dosyaOku {
	private FileReader oku;
	private BufferedReader tampon_oku;
	private int sayac;
	public int[][] matris_R;//burayi private yapabilirsin
	public dosyaOku(){
		this.oku=null;
		this.tampon_oku=null;
		this.sayac=0;
	}
	private int dugumSayisi(String dosya_adi) throws IOException{
		oku=new FileReader(dosya_adi);
		tampon_oku=new BufferedReader(oku);
		int sayi;
		while((sayi=tampon_oku.read())!= -1){
			/*if((char)sayi!=',')*///System.out.print((char)sayi);
			if((char)sayi=='\n') sayac++;
		}
		tampon_oku.close();
		return sayac; 
	}
	public  int get_matris_boyutu(){
		return sayac;
	}
	
	public int[][] get_matris(){
		return matris_R;
	}
	private void matris_ata(int[][]matris,int say){
		for(int i=0;i<say;i++){
			for(int j=0;j<say;j++){
			matris[i][j]=-1;
			}
		}
	}
	public void komsuluk(int hedef_dugum,String dosya_adi) throws IOException{
		int dugum_sayisi=dugumSayisi(dosya_adi);
		matris_R=new int[dugum_sayisi][dugum_sayisi];
		System.out.println("matris boyutu:"+dugum_sayisi);
		matris_ata(matris_R,dugum_sayisi);
		oku=new FileReader(dosya_adi);
		tampon_oku=new BufferedReader(oku);
		String satir;
		int dugum_say=0;
		String[] komsular=null;
		while((satir=tampon_oku.readLine())!=null){
			komsular=satir.split(",");
			System.out.println("-------------------");
			for(int j=0;j<komsular.length;j++){
				//System.out.println(sutun);
				//System.out.println("dugumsay:"+dugum_say);
				if(hedef_dugum!=Integer.parseInt(komsular[j]))  matris_R[dugum_say][Integer.parseInt(komsular[j])]=0;
				else {
					matris_R[dugum_say][Integer.parseInt(komsular[j])]=100;
				}
			}
				//System.out.print(komsular[j]);
			System.out.println();
			dugum_say++;
		}
//System.out.println("dugum_say:"+dugum_say);
	}
	
	
	public void matris_yaz(){
		for(int i=0;i<sayac;i++){
			for(int j=0;j<sayac;j++){
			System.out.print(matris_R[i][j]+"     ");
			}
			System.out.println();
		}
	}
}
