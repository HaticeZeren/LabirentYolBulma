import java.io.BufferedReader;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import java.awt.color.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import java.awt.*;
public class proje3 extends JPanel{	
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public static int durum=0;
	public static int hamle;
	public static int Q_size;

	
	public static int baslangic;
	public static int baslangic1;
	public static int hedef;
	public static int iterasyon;
	public static String dosya_adi;
	public static int sonraki_durum;
	public static int sonraki_hamle;
	public static dosyaOku dosya;
	public static ogrenme_matrisi a;
	
	public proje3() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 577, 409);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		System.out.println("iþlem yapýlacak girdi dosyasýnýn ismini giriniz:");
		Scanner girdi=new Scanner(System.in);
		dosya_adi=girdi.nextLine();
		JButton btnLabirenticizdir = new JButton("Labirenti \u00C7izdir");
		btnLabirenticizdir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labirent lb=new labirent(dosya.get_matris(),(int) Math.sqrt(dosya.get_matris_boyutu()));
				labirent yol_ciz=new labirent(a,baslangic,hedef);
				//lb.boyut=(int) Math.sqrt(dosya.get_matris_boyutu());
				//lb.R=new int[(int) Math.sqrt(dosya.get_matris_boyutu())][(int) Math.sqrt(dosya.get_matris_boyutu())];
				//lb.matris=dosya.get_matris();
				JFrame jf=new JFrame();		
				jf.add(lb);
				jf.add(yol_ciz);
				//jf.getContentPane().add(new proje());
				jf.setSize(600,600);
				jf.setBackground(Color.WHITE);
				jf.setLocationRelativeTo(null);//panelin ekranýn sol üst köþesinde deðilde ortada çýkmasýný saðlar 
				jf.setResizable(false);//ekranýn boyutunu sabitler
				jf.setVisible(true);
				
			}
		});
		btnLabirenticizdir.setBounds(105, 286, 135, 36);
		frame.getContentPane().add(btnLabirenticizdir);
		
		JButton btnHesapla = new JButton("Hesapla");
		btnHesapla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hedef=Integer.parseInt(textField.getText());
				baslangic=Integer.parseInt(textField_1.getText());
				iterasyon=Integer.parseInt(textField_2.getText());
				durum=baslangic;
				//R matrisi tanimla
				dosya=new dosyaOku();
				try {
					dosya.komsuluk(hedef,dosya_adi);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				dosya.matris_R[hedef][hedef]=100;
				dosya.matris_yaz();
				//Q matrisi tanimla
				a=new ogrenme_matrisi(dosya);
				
				
				
				int[] baslangic_durumu=new int[dosya.get_matris_boyutu()];
				
				for(int i=0;i<dosya.get_matris_boyutu();i++){
					baslangic_durumu[i]=i;
				}
				
				
				
				while(iterasyon>0){
					System.out.println(iterasyon+".iterasyon..............");
					baslangic1=new Random().nextInt(dosya.get_matris_boyutu());
					baslangic1=baslangic_durumu[baslangic1];
					durum=baslangic1;
					//while(basla){
						
						 do{
							 hamle=new Random().nextInt(dosya.get_matris_boyutu());
						 }while(dosya.get_matris()[durum][hamle]==-1);
						// hamle=a.max_Q(durum,dosya);
						 sonraki_durum=hamle;
						 sonraki_hamle=a.max_Q(sonraki_durum,dosya);
						 a.matris_Q[durum][hamle]=dosya.get_matris()[durum][hamle]+(0.8)*a.get_Q()[sonraki_durum][sonraki_hamle];
						 System.out.println("durum("+durum+")->hamle("+hamle+") kazanc="+a.matris_Q[durum][hamle]);
						while(durum!=hedef ){
							durum=sonraki_durum;
							hamle=sonraki_hamle;
							sonraki_durum=hamle;
							sonraki_hamle=a.max_Q(hamle,dosya);
						  if(hamle==hedef){
							  sonraki_hamle=hedef;
							//  basla=false;
						  }
							a.matris_Q[durum][hamle]=dosya.get_matris()[durum][hamle]+(0.8)*a.get_Q()[sonraki_durum][sonraki_hamle];
						   	System.out.println("durum("+durum+")->hamle("+hamle+") kazanc="+a.matris_Q[durum][hamle]);
						
						   	
						}
				//	}
					 
						
					iterasyon--;
					System.out.println("..........................................");

				}
				
			/**outpath dosyayý yazdýrýyorum*/
			dosyaya_yaz dosyala=new dosyaya_yaz(a,dosya,baslangic,hedef);
			try {
				dosyala.OutPath_yaz();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			
			/** Q matrisini yazdir*/
			try {
				dosyala.Out_Q_yaz();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			/** R matrisini yazdir*/
			try {
				dosyala.Out_R_yaz();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
		 	  System.out.println("...............................");
				for(int i=0;i<dosya.get_matris_boyutu();i++){
					for(int j=0;j<dosya.get_matris_boyutu();j++){
						   System.out.print(a.matris_Q[i][j]+"      ");
					}
					System.out.println();
				}
				
				System.out.println(".............................................");
				dosya.matris_yaz();
				
				
				
			}
		});
		btnHesapla.setBounds(315, 286, 125, 36);
		frame.getContentPane().add(btnHesapla);
		
		JLabel lblHedefiGiriniz = new JLabel("Hedefi Giriniz :");
		lblHedefiGiriniz.setBounds(44, 55, 109, 36);
		frame.getContentPane().add(lblHedefiGiriniz);
		
		JLabel lblBalangcGiriniz = new JLabel("Ba\u015Flang\u0131c\u0131 Giriniz :");
		lblBalangcGiriniz.setBounds(44, 114, 109, 24);
		frame.getContentPane().add(lblBalangcGiriniz);
		
		JLabel lblIterasyonSaysnGiriniz = new JLabel("\u0130terasyon Say\u0131s\u0131n\u0131 Giriniz :");
		lblIterasyonSaysnGiriniz.setBounds(44, 170, 148, 24);
		frame.getContentPane().add(lblIterasyonSaysnGiriniz);
		
		textField = new JTextField();
		textField.setBounds(230, 62, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(230, 115, 116, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(230, 171, 116, 22);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
	}
	
	
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					proje3 window = new proje3();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
		
	}
	

}
