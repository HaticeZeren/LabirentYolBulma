import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

import java.awt.*;
import java.awt.color.*;
import java.awt.geom.Line2D;

public class labirent extends JPanel {
	public static  int[][] matris;
	public static int boyut;
    public static int birim;
    public static int x1,x2,y1,y2;
    //-------------------------
    public static boolean yol_ciz;
    public static ogrenme_matrisi Q;
    public static int baslangic;
    public static int hedef;
    
    public static double max;
	public static int hamle; 
    public labirent(int[][] matris,int boyut){
    	this.matris=matris;
    	this.boyut=boyut;
    	this.yol_ciz=false;
    }
    
    public labirent(ogrenme_matrisi Q_matrisi,int baslangic,int hedef){
    	this.yol_ciz=true;
    	this.Q=Q_matrisi;
    	this.baslangic=baslangic;
    	this.hedef=hedef;
    }
    
public void paintComponent(Graphics g){
	g.setColor(Color.BLACK);
   
	Graphics2D g2=(Graphics2D)g;
	g2.setStroke(new BasicStroke(8));
	 g.drawRect(100,100,400,400);
	 birim=(int)(400/boyut);
		
	   /*
	    g.drawLine(0+60,60,60+60,60);//(0,60)-(60,60) y ekseninde sabit x üzerinde 60px ilerliyor.
	    
	    System.out.println("x1:"+x1);
	    System.out.println("y1:"+y1);
	    */
	   // g.setColor(Color.ORANGE);
	   //g.drawLine(300,100,300+200,100);
	    /*
	    
		
		*/
	   
		for(int j=0;j<boyut;j++){
			for(int i=j*boyut;i<boyut+(j*boyut)-1;i++){
				
				if(matris[i][i+1]==-1){
					//System.out.println(i+".dugum ve"+(i+1)+".dugum arasinda engel var");
					//dikeyde engel ciz
					x2=((i%boyut)>((i+1)%boyut)?(i%boyut):(i+1)%boyut);
					y2=(int)i/boyut;
					//System.out.println("x2"+x2+"  y2:"+y2);

					x2=x2*birim+100;
					y2=y2*birim+100;
				    //g.drawLine(x2,y2,x2,y2+birim);//1 birimlik dikeyde çizgi
                      g2.draw(new Line2D.Float(x2,y2,x2,y2+birim));
				}
				
			}
		}
		
		//*********************************
 
		for(int i=0;i<boyut*boyut-boyut;i++){
			//for(int j=i;j<boyut+i;j++){
				//if(i< boyut*(boyut-1)){
					if(matris[i][i+boyut]==-1){
						//yatayda engel çiz
						y1=((i/boyut)>((i+boyut)/boyut)?(i/boyut):(i+boyut)/boyut);
						x1=i%boyut;
						//System.out.println("x1"+x1+"  y1:"+y1);

						x1=x1*birim+100;
						y1=y1*birim+100;
						
					   // g.drawLine(x1,y1,(x1+birim),y1);//1 birimlik yatayda çizgi
						g2.draw(new Line2D.Float(x1,y1,(x1+birim),y1));
					}
				//}
			//}
		}
		
		
		//yol cizdirme
		max=0;
		if(yol_ciz){
			/*yol cizdirme*/
			g.setColor(Color.RED);
            int gecici;
            /*
            if(baslangic>hedef){
            	gecici=hedef;
            	hedef=baslangic;
            	baslangic=gecici;
            }
			 */
           
			while(baslangic!=hedef){
				int durum=baslangic;
				for(int j=0;j<boyut*boyut;j++){
					
					if(max<Q.matris_Q[durum][j]&&durum!=j){//dugumun kendine donusu olmasýn
					  max=Q.matris_Q[durum][j];
					  hamle=j;
					  
					}
				}
				
				if(max>0){
					//durum-hamle düðümü arasinda yol çiz
					int x0=(durum%boyut);
					int y0=(durum/boyut);
					x0=(x0*birim+100)+(birim/2);
					y0=(y0*birim+100)+(birim/2);
					
					int x1=(hamle%boyut);
					int y1=(hamle/boyut);
					x1=(x1*birim+100)+(birim/2);
					y1=(y1*birim+100)+(birim/2);
					
					if(x0!=x1 && y0==y1){
						//yatayda çizgi çiz
						x0=(x0<x1?x0:x1);
						g2.draw(new Line2D.Float(x0,y0,(x0+birim),y0));//saða doðru çizer
						max=0;
					}
					else if(x0==x1&&y0!=y1){
						//dikeyde çizgi çiz
						y0=(y0<y1?y0:y1);
						 g2.draw(new Line2D.Float(x0,y0,x0,y0+birim));
						 max=0;
					}
					
				}
				baslangic=hamle;
			}
			
		}
		
	}
	
}
