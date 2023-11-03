import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Agac4 extends JFrame {
	public ArrayList<Kisi> list =new ArrayList<Kisi>();
 	public ArrayList  list2 =new ArrayList();
 	public ArrayList<Kisi> leafs=new ArrayList<Kisi>();
 	public ArrayList<Kisi> kanGrubu=new ArrayList<Kisi>();
 	public ArrayList<Kisi> kisi=new ArrayList<Kisi>();
 	public ArrayList<Kisi>  aynıMes=new ArrayList<Kisi>();
 	public ArrayList<ArrayList<Kisi>> benzer=new ArrayList<ArrayList<Kisi>>();
 	public ArrayList<Kisi> uvey=new ArrayList<Kisi>();
 	public ArrayList<Kisi> akraba=new ArrayList<Kisi>();
 	public ArrayList<Node> node =new ArrayList<Node>();
 	public Node agac;
 	public Node once;
 	public static int x=-1;
 	public static String gender="no";
	private JPanel contentPane =new JPanel();
	public JPanel panel = new JPanel();
	public JLabel lblNewLabel = new JLabel("");
	public JButton b5 = new JButton("Back");
	public JLabel l1 = new JLabel("");
	public JLabel l2 = new JLabel("");
	public JButton b1 = new JButton("Kan Girisi");
	public JButton b2 = new JButton("Kisi Girisi");
	public JButton b3 = new JButton("Soy Agacı");
	private final JPanel p2 = new JPanel();
	private final JPanel p3 = new JPanel();
	private final JPanel p4 = new JPanel();
	private final JPanel p5 = new JPanel();
	private final JPanel p6 = new JPanel();
	private final JLabel lblNewLabel_1 = new JLabel("");
	private final JLabel lblNewLabel_2 = new JLabel("");
	private final JLabel lblNewLabel_3 = new JLabel("");
	private final JLabel lblNewLabel_4 = new JLabel("");
	private final JLabel lblNewLabel_5 = new JLabel("");
	private final JLabel lblNewLabel_6 = new JLabel("->Aynı Kan Grubunda Olanlar<-");
	private final JLabel lblNewLabel_7 = new JLabel("->Cocugu Olmayanlar<-");
	private final JLabel lblNewLabel_8 = new JLabel("->Aynı İsimliler<-");
	private final JLabel lblNewLabel_9 = new JLabel("->Aynı Meslek<-");
	private final JLabel lblNewLabel_10 = new JLabel("->Üvey Cocuk<-");
	private final JButton btnNewButton = new JButton("Akraba İlişkisi");
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public void akrabalık(ArrayList<Kisi> tmp,int x)
	{
	Kisi k1=tmp.get(0);
	Kisi k2=tmp.get(1);
	Kisi buyuk,kucuk;
	if(k1.seviye>k2.seviye)
	{
	buyuk=k1;	
	kucuk=k2;
	}
	else
	{
		buyuk=k2;
		kucuk=k1;
	}
	int p=k1.seviye-k2.seviye;
		if(p==0)
		{
			if(k1.babaAdı.contains(k2.babaAdı) && k2.anneAdı.contains(k2.anneAdı))
			{
				JOptionPane.showMessageDialog(getContentPane(),k1.ad+" ve "+k2.ad+" kardeştir");
			}
			else if((!k1.es.isEmpty() && !k2.es.isEmpty()) && (k1.es.contains(k2.ad) && k2.es.contains(k1.ad)))
			{
				JOptionPane.showMessageDialog(getContentPane(),k1.ad+" "+k2.ad+"'in"+" esidir");

			}
			else
			{
				JOptionPane.showMessageDialog(getContentPane(),k1.ad+" ve "+k2.ad+" kuzendir");
			}
		}
		else if(p==1 || p==-1)
		{
		if(kucuk.ata.kisi.ad.contains(buyuk.ad) || kucuk.ata.kisi.ad.contains(buyuk.es))
		{
			if(buyuk.cinsiyet.contains("Kadın"))
			JOptionPane.showMessageDialog(getContentPane(),buyuk.ad+" "+kucuk.ad+"'un annesidir");
			else
				JOptionPane.showMessageDialog(getContentPane(),buyuk.ad+" "+kucuk.ad+"'un babasıdır");
		}
		else
		{
				if(kucuk.ata.kisi.cinsiyet.contains("Kadın") && buyuk.cinsiyet.contains("Kadın"))
				{
					JOptionPane.showMessageDialog(getContentPane(),buyuk.ad+" "+kucuk.ad+"'un teyzesidir");
				}
				else if (kucuk.ata.kisi.cinsiyet.contains("Kadın") && buyuk.cinsiyet.contains("Erkek"))
				{
					JOptionPane.showMessageDialog(getContentPane(),buyuk.ad+" "+kucuk.ad+"'un dayısıdır");
				}
				else if(kucuk.ata.kisi.cinsiyet.contains("Erkek") && buyuk.cinsiyet.contains("Erkek"))
				{
					JOptionPane.showMessageDialog(getContentPane(),buyuk.ad+" "+kucuk.ad+"'un amcasıdır");
				}
				else if(kucuk.ata.kisi.cinsiyet.contains("Erkek") && buyuk.cinsiyet.contains("Kadın"))
				{
					JOptionPane.showMessageDialog(getContentPane(),buyuk.ad+" "+kucuk.ad+"'un halasıdır");
				}
		}
		}
		else
		{
			ArrayList<String> s =new ArrayList<String>();
			iliski(kucuk,buyuk,s);
			JOptionPane.showMessageDialog(getContentPane(),buyuk.ad+" "+kucuk.ad+"'un"+s.toString());
				
			
		}
	}
	public void iliski(Kisi kucuk,Kisi buyuk,ArrayList<String> x)
	{
		System.out.println(kucuk.ad);
		if(kucuk.ata!=null)
		{
			if(kucuk.ata.e!=null)
			{
				if(kucuk.ata.e.kisi.ad.contains(buyuk.ad) && kucuk.ata.e.kisi.babaAdı.contains(buyuk.babaAdı))
				if(buyuk.cinsiyet.contains("Kadın"))
				{
		  String c ="annnesi";
		  x.add(c);
		  return;
				}
				else
				{
			String c ="babası";
			x.add(c);
			return;
		}	
			}
			if(kucuk.ata.kisi.ad.contains(buyuk.ad) && kucuk.ata.kisi.babaAdı.contains(buyuk.babaAdı))
			{
					if(buyuk.cinsiyet.contains("Kadın"))
					{
			  String c ="annnesi";
			  x.add(c);
			  return;
					}
					else
					{
				String c ="babası";
				x.add(c);
				return;
			}	
		}
		else if(kucuk.ata.cocuk.size()!=0)
		{
			for(Kisi k : kucuk.ata.cocuk)
			{
				if(k.ad.contains(buyuk.ad) && k.id.contains(buyuk.id))
				{
					if(buyuk.cinsiyet.contains("Kadın"))
					{
						x.add("kız kardeşi");
						return;
					}
					else if (buyuk.cinsiyet.contains("Erkek"))
					{
						x.add("erkek kardeşi");
						return;
					}
				}
			}
		}
	}
		if(kucuk.ata.kisi.cinsiyet.contains("Erkek"))
		{
			x.add(" babasının ");
		}
		else
			x.add(" annesinin ");	
	if(kucuk.ata==null)
		return;
	iliski(kucuk.ata.kisi,buyuk,x);
		return;
	}
	/*public void uveyCocuk(Node agac)
	{
		if(agac==null)
		{
			return;
		}
	    for(Kisi k : agac.cocuk)
	    {
	    	System.out.println("strp 1:"+k.ad+"'in kontrolü...");
	    	if(k.cinsiyet.contains("Erkek"))
	    	{
	        	System.out.println("strp 1.1:"+k.ad+" Erkek");
	    		if(!k.soyad.contains(agac.kisi.soyad))
	    		{
	            	System.out.println("strp 1.2:"+k.ad+" atasının soy ismini taşımıyor yani üvey...");
	    		     if(uvey.size()==0)
	    		     {
	    	            	System.out.println("strp 2:"+k.ad+" uvey arrayListi bos oldugu için eklendi....");
	    	    			uvey.add(k);
	    	    			break;

	    		     }
	    		     else {
	    		    	 
	    		    	 int j=uvey.size();
	    		    	 for(int i=0;i<uvey.size();i++)
	    		    	 {
	 		             	System.out.println("strp 1.4:"+k.ad+" "+uvey.get(i).ad+" harf karşılaştırılması yapılıyor...");
	    		    		 if(uvey.get(i).ad.compareTo(k.ad)<=0)
	    		    		 {
	    		             	System.out.println("strp 1.5:"+k.ad+" "+uvey.get(i).ad+"'dan daha sonra geliyor ve önüne eklendi...");
	    		    			 uvey.add(i,k);
	    	    	    			break;

	    		    		 }
	    		    	 }
	    		    	 if((j+1)!=uvey.size()) {
	 		             	System.out.println("strp 1.5:"+k.ad+" en son sıralamada oldugu için en sona eklendi....");
	    			uvey.add(k);
	    			break;

	    		    	 }
	    		     }
	    		}
	    	}
	    	else
	    	{
	    		if(k.es.isEmpty() && !k.soyad.contains(agac.kisi.soyad))
	    		{
	            	System.out.println("strp 1.1:"+k.ad+" Kadın ve Bekar ve atasının soyadı ile soyadı aynı degil bu yüzden üvey...");
	    			 if(uvey.size()==0)
	    		     {
	 	            	System.out.println("strp 2:"+k.ad+" uvey arrayListi bos oldugu için eklendi....");
	    	    			uvey.add(k);
	    	    			break;

	    		     }
	    		     else {
	    		    	 
	    		    	 int j=uvey.size();
	    		    	 for(int i=0;i<uvey.size();i++)
	    		    	 {
	  		             	System.out.println("strp 1.4:"+k.ad+" "+uvey.get(i).ad+" harf karşılaştırılması yapılıyor...");
	    		    		 if(uvey.get(i).ad.compareTo(k.ad)>=0)
	    		    		 {
	     		             	System.out.println("strp 1.5:"+k.ad+" "+uvey.get(i).ad+"'dan daha sonra geliyor ve önüne eklendi...");
	    		    			 uvey.add(i,k);
	    	    	    			break;

	    		    		 }
	    		    	 }
	    		    	 if((j+1)!=uvey.size()) {
	  		             	System.out.println("strp 1.5:"+k.ad+" en son sıralamada oldugu için en sona eklendi....");
	    			uvey.add(k);
	    			break;

	    		    	 }
	    		     }
	    		}
	    		else if(!k.es.isEmpty() && !k.kızlıkSoyadı.contains(agac.kisi.soyad))
	    		{
	       System.out.println("strp 1.1:"+k.ad+" Kadın ve Evli ve atasının soyadı ile kızlıksoyadı aynı degil bu yüzden üvey...");
	    			 if(uvey.size()==0)
	    		     {
	  	            	System.out.println("strp 2:"+k.ad+" uvey arrayListi bos oldugu için eklendi....");
	    	    			uvey.add(k);
	    	    			break;

	    		     }
	    		     else {
	    		    	 
	    		    	 int j=uvey.size();
	    		    	 for(int i=0;i<uvey.size();i++)
	    		    	 {
	   		             	System.out.println("strp 1.4:"+k.ad+" "+uvey.get(i).ad+" harf karşılaştırılması yapılıyor...");
	    		    		 if(uvey.get(i).ad.compareTo(k.ad)>=0)
	    		    		 {
	      		             	System.out.println("strp 1.5:"+k.ad+" "+uvey.get(i).ad+"'dan daha sonra geliyor ve önüne eklendi...");
	    		    			 uvey.add(i,k);
	    	    	    			break;

	    		    		 }
	    		    	 }
	    		    	 if((j+1)!=uvey.size()) {
	   		             	System.out.println("strp 1.5:"+k.ad+" en son sıralamada oldugu için en sona eklendi....");
	    			uvey.add(k);
	    			break;

	    		    	 }
	    		     }
	    		}
	    	}
	    }
		
	}
	*/
	public void uveyCocuk(Node agac)
	{
		if(agac==null)
		{
			return;
		}
	    for(Kisi k : agac.cocuk)
	    {
	    	System.out.println("strp 1:"+k.ad+"'in kontrolü...");
	        if(agac.kisi.cinsiyet.contains("Erkek"))
	        {
	        	System.out.println("strp 1.1:"+agac.kisi.ad+"' Erkek...");
	        	if(!agac.kisi.es.contains(k.anneAdı))
	        	{
		            	System.out.println("strp 2:"+k.ad+" anne adı babasının eş adı farklı oldugu için ÜVEY....");
	        		 if(uvey.size()==0)
	    		     {
	  	            	System.out.println("strp 3:"+k.ad+" uvey arrayListi bos oldugu için eklendi....");
	    	    			uvey.add(k);
	    	    			break;

	    		     }
	        		 int j=uvey.size();
			    	 for(int i=0;i<uvey.size();i++)
			    	 {
			    		 int g=k.ad.compareTo(uvey.get(i).ad);
			             	System.out.println("step 2.1:"+k.ad+" ve "+agac.kisi.ad+" karsılaştırması tamsayı degeri:"+g);
			             	System.out.println("step 2.2:"+k.ad+" ve "+agac.kisi.ad+" karsılaştırması tamsayı degeri:"+g);
			    		 if(g<=0)
			    		 {
		   		    System.out.println("strp 3:"+k.ad+" "+uvey.get(i).ad+"'dan daha sonra geliyor ve önüne eklendi...");
			    			 uvey.add(i,k);
		    	    			break;

			    		 }
			    	 }
			    	 if((j+1)!=uvey.size()) {
			             	System.out.println("strp 3:"+k.ad+" en son sıralamada oldugu için en sona eklendi....");
				uvey.add(k);
				break;

	        		}
	        	}
	        	
	        }
	        else
	        {
	        	System.out.println("strp 1.1:"+agac.kisi.ad+"' Kadın...");
	        	if(!agac.kisi.es.contains(k.babaAdı))
	        	{
	            	System.out.println("strp 2:"+k.ad+" baba adı annesının eş adı farklı oldugu için ÜVEY....");
	        		 if(uvey.size()==0)
	    		     {
	   	            	System.out.println("strp 3:"+k.ad+" uvey arrayListi bos oldugu için eklendi....");
	    	    			uvey.add(k);
	    	    			break;

	    		     }
	        		 int j=uvey.size();
			    	 for(int i=0;i<uvey.size();i++)
			    	 {
			    		 int g=k.ad.compareTo(uvey.get(i).ad);
			    			System.out.println("step 2.1:"+k.ad+" ve "+agac.kisi.ad+" karsılaştırması tamsayı degeri:"+g);
			             	System.out.println("step 2.2:"+k.ad+" ve "+agac.kisi.ad+" karsılaştırması tamsayı degeri:"+g);
			    		 if(g<=0)
			    		 {
			 	   		    System.out.println("strp 3:"+k.ad+" "+uvey.get(i).ad+"'dan daha sonra geliyor ve önüne eklendi...");
			    			 uvey.add(i,k);
		    	    			break;

			    		 }
			    	 }
			    	 if((j+1)!=uvey.size()) {
			             	System.out.println("strp 3:"+k.ad+" en son sıralamada oldugu için en sona eklendi....");
				uvey.add(k);
				break;

	        		}
	        	}
	        }
	    	
	    }
		
	}
	public void gizle()
	{
		this.setVisible(false);
	}
	 	public void aynıMeslek(Node agac)
	 	{
	 		String meslek=agac.kisi.meslek;
	 		(new Node()).meslekler(agac,aynıMes,meslek);
	 		for(Kisi k:aynıMes)
	 		{
	 			System.out.println(k.ad+" "+k.meslek);
	 		}
	 	}
	 	public void aynıIsım(Node agac)
	    {
	    	(new Node()).isimler(agac,kisi);
	        while (kisi.size()>0)
	        {
	          ArrayList<Kisi> liste=new ArrayList<Kisi>();
	          liste.add(kisi.get(0));
	          System.out.println("kisi.get(0)="+kisi.get(0).ad);
	        	int i=1;
	        	for (;i<kisi.size();i++)
	        	{
	        		if(kisi.get(0).ad.equals(kisi.get(i).ad))
	        		{
	        			liste.add(kisi.get(i));
	        		}
	        		
	        	}
	        	if(liste.size()>=2)
	        	{
	        		benzer.add(liste);
	        		for(Kisi a:liste)
	        		{
	        			System.out.println("liste="+a.ad);
	        		}
	        		System.out.println("-----------------");
	        		kisi.removeAll(liste);
	        	}
	        	else
	        	{
	        		System.out.println("tek="+kisi.get(0).ad);
	        		System.out.println("-----------------");
	        		kisi.remove(0);
	        	}
	        }   	
	    }
	 	public void devamNesil(Node kok,String ad)
	 	{
	 		if(kok==null)
	 		{
	 			return;
	 		}
	 		if(kok.kisi.ad.equals(ad))
	 		{
	 			x=kok.height;
	 			gender=kok.kisi.cinsiyet;
	 			return;
	 		}
	 		devamNesil(kok.c1,ad);
	 		devamNesil(kok.c2,ad);
	 		devamNesil(kok.c3,ad);
	 		devamNesil(kok.c4,ad);
	 		devamNesil(kok.c5,ad);
	 	}
	 	public void kanG(Node agac,String kan)
	 	{
	 		if(agac==null)
	 		{
	 			return;
	 		}
	 		String[] rh=kan.split("\\(");
	 		kanG(agac.c1,kan);
	 		kanG(agac.c2,kan);
	 		kanG(agac.c3,kan);
	 		kanG(agac.c4,kan);
	 		kanG(agac.c5,kan);
	 		if(agac.e!=null)
	 		{
	 			String[] k=agac.e.kisi.kanGrubu.split("\\(");
	 			if(k[0].equals(rh[0]))
	 	 		{
	 	 			if(kanGrubu.size()==0)
	 	 			kanGrubu.add(agac.e.kisi);
	 	 			else
	 	 			{
	 	 			for(int i=0;i<kanGrubu.size();i++)
	 	 			{
	 	 				if(!((agac.e.kisi.ad.equals(kanGrubu.get(i).ad) && (agac.e.kisi.soyad.equals(kanGrubu.get(i).soyad)))&& k[0].equals(rh[0])))
	 	 						{
	 	 					kanGrubu.add(agac.e.kisi);
	 	 					break;
	 	 						}
	 	 			}
	 	 				
	 	 			}
	 	 		}
	 		}
	 		String[] k=agac.kisi.kanGrubu.split("\\(");
	 		if(k[0].equals(rh[0]))
	 		{
	 			if(kanGrubu.size()==0)
	 			kanGrubu.add(agac.kisi);
	 			else
	 			{
	 			for(int i=0;i<kanGrubu.size();i++)
	 			{
	 				if(!((agac.kisi.ad.equals(kanGrubu.get(i).ad) && (agac.kisi.soyad.equals(kanGrubu.get(i).soyad)))&& k[0].equals(rh[0])))
	 						{
	 					kanGrubu.add(agac.kisi);
	 					break;
	 						}
	 			}
	 				
	 			}
	 		}
	 	}
	 	public void leaf(Node agac)
	 	{
	 		if(agac==null)
	 		{
	 			return;
	 		}
	 		leaf(agac.c1);
	 		leaf(agac.c2);
	 		leaf(agac.c3);
	 		leaf(agac.c4);
	 		leaf(agac.c5);
	 		System.out.println("step 1:"+agac.kisi.ad+" geldi....");
	 		if(agac.c1==null && agac.c2==null && agac.c3==null && agac.c4==null && agac.c5==null)
	 		{
	 	 		System.out.println("step 2:"+agac.kisi.ad+"'in cocugu yok....");
	 		if(leafs.size()==0) {
	 		leafs.add(agac.kisi);
	 		System.out.println("step 4:"+agac.kisi.ad+" leafs arraylisti bos oldugu icin eklendi...");
	 		}
	 		else
	 		{
	 			
	 			String[] y2=agac.kisi.dogumTarihi.split("/");
	 			String[] y3=agac.kisi.dogumTarihi.split("\\.");
	 			Integer b=0;
					Integer b2=0;
					Integer b3=0;
		System.out.println("step 2.1:"+agac.kisi.ad+"'in dogum tarihi gün ay yıl olarak ayrılıyor...");		
				if(y2.length==1)
				{
					b=Integer.parseInt(y3[0]);
					b2=Integer.parseInt(y3[1]);
					b3=Integer.parseInt(y3[2]);
				}
				else
				{
					b=Integer.parseInt(y2[0]);
					b2=Integer.parseInt(y2[1]);
					b3=Integer.parseInt(y2[2]);
			/*	if(b3<=23 )
				{
					String tmp="20"+Integer.toString(b3);
					b3=Integer.parseInt(tmp);
				}
				else
				{
					String tmp="19"+Integer.toString(b3);
					b3=Integer.parseInt(tmp);
				}
				*/
	 		}
	        int size=leafs.size();
	 			for (int i=0;i<leafs.size();i++)
	 			{
	 				System.out.println("step 2.2:"+leafs.get(i).ad+"'in dogum tarihi gün ay yıl olarak ayrılıyor...");		
	 				String[] y=leafs.get(i).dogumTarihi.split("/"); 
	 				String[] y4=leafs.get(i).dogumTarihi.split("\\."); 
	 				Integer a=0;
	 				Integer a2=0;
	 				Integer a3=0;
	 				if(y.length==1)
					{
						a=Integer.parseInt(y4[0]);
						a2=Integer.parseInt(y4[1]);
						a3=Integer.parseInt(y4[2]);
					}
					else
					{
						a=Integer.parseInt(y[0]);
						a2=Integer.parseInt(y[1]);
						a3=Integer.parseInt(y[2]);
	 			/*	if(a3<=23)
	 				{
	 					String tmp="20"+Integer.toString(a3);
	 					a3=Integer.parseInt(tmp);
	 				}
	 				else
	 				{
	 					String tmp="19"+Integer.toString(a3);
	 					a3=Integer.parseInt(tmp);
	 				}
	 				*/
	 			}
	 				System.out.println("gun="+b+" "+"ay="+b2+" "+"yıl="+b3);
	 				System.out.println("gun="+a+" "+"ay="+a2+" "+"yıl="+a3);
	 				System.out.println("step 3:"+agac.kisi.ad+" "+leafs.get(i).ad+" yaşları karşılaştırılıyor....");		
	 				if(a3<b3)
	 				{
	 					System.out.println("step 4:"+agac.kisi.ad+" "+leafs.get(i).ad+"'dan daha kucuk ve önüne eklendi");
	 					leafs.add(i,agac.kisi);
	 					break;
	 				}
	 				else if(a3==b3 && a2<b2)
	 				{
	 			 		System.out.println("step 4:"+agac.kisi.ad+" "+leafs.get(i).ad+"'dan daha kucuk ve önüne eklendi");
	 					leafs.add(i,agac.kisi);
	                     break;
	 				}
	 				 else if(a3==b3 && a2==b2 &&  a<b)
	 				{
	 			 		System.out.println("step 4:"+agac.kisi.ad+" "+leafs.get(i).ad+"'dan daha kucuk ve önüne eklendi");
	 					leafs.add(i,agac.kisi);
	                    break;
	 				}
	 			}
	 			if(size+1!=leafs.size())
	 			{
				 		System.out.println("step 4:"+agac.kisi.ad+" "+" sona eklendi.");
	 				leafs.add(agac.kisi);
	 			}
	    		System.out.println("-----------------");
	 		}
	 		}
	 	}
	 	public void oku(int syc)
		{
			Kisi k;
			try {
				FileInputStream in =new FileInputStream(new File("C:\\Users\\Abra\\Desktop\\Kitap 3.xlsx"));
				try {
					XSSFWorkbook dosya = new XSSFWorkbook(in);
					DataFormatter dataFormatter =new DataFormatter();
					XSSFSheet sayfa= dosya.getSheetAt(syc);
					Iterator<Row> itr =sayfa.iterator();
					while(itr.hasNext())
					{
						Row row=itr.next();
						if(row.getRowNum() == 0) {
							// baslik satirini atla
							continue;
						}
						int i=0;
						while(i<13)
						{
							Cell cell =row.getCell(i);
							String value=dataFormatter.formatCellValue(cell);
							System.out.print(value+"---");
						    list2.add(value);
							i++;
						}
						k=new Kisi();
						k.id=(String) list2.get(0);
						k.ad=(String) list2.get(1);
						k.soyad=(String) list2.get(2);
						k.dogumTarihi=(String)list2.get(3);
						k.es=(String) list2.get(4);
						k.esId=(String) list2.get(5);
						k.anneAdı=(String) list2.get(6);
						k.babaAdı=(String) list2.get(7);
						k.kanGrubu=(String) list2.get(8);
						k.meslek=(String) list2.get(9);
						k.medeniHal=(String) list2.get(10);
						k.kızlıkSoyadı=(String) list2.get(11);
						k.cinsiyet=(String) list2.get(12);
						list2.clear();
						list.add(k);
						System.out.println();
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	public Agac4() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,900,900);
		 oku(3);
		 Node n=new Node();
		  for (Kisi a:list)
		  {
			agac=n.ekle(agac,a,once);  
		  }
		  leaf(agac);
		  ArrayList<JLabel> lbl2 =new ArrayList<JLabel>();
		  for(int i=0; i<leafs.size();i++)
		  {
			  JLabel l = new JLabel("");
			 lbl2.add(l);
		  }
		  n.seviye(agac,agac.height);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		panel.setBackground(new Color(255, 255, 0));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(229, 10, 435, 34);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Abra\\Desktop\\image\\family-tree (2).png"));
		lblNewLabel.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		 lblNewLabel.setText(list.get(0).soyad+" Ailesi");
		panel.add(lblNewLabel);
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
					gizle();
					Giris g =new Giris();
					g.setVisible(true);
				}
		});
		b5.setBounds(760, 10, 106, 34);
		panel.add(b5);
		l1.setBounds(10, 63, 161, 25);
		l1.setText("Nesil= "+agac.height);
		panel.add(l1);
		l2.setBounds(10, 92, 161, 25);
		panel.add(l2);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String kan =JOptionPane.showInputDialog(panel,"Lütfen Kan Grubunu Giriniz:");
		   		kanG(agac,kan);
		   		ArrayList<JLabel> lbl =new ArrayList<JLabel>();
		   		for(int i=0;i<kanGrubu.size();i++)
		   		{
		   			JLabel l =new JLabel("");
		   			lbl.add(l);
		   		}
		   		int syc=0;
		   		for(Kisi  k : kanGrubu)
		   		{
		   			p2.add(lbl.get(syc));
		   			lbl.get(syc).setText(k.ad+"->"+k.kanGrubu);
		   			syc++;
		   		}
			}
		});
		b1.setBounds(153, 791, 106, 34);
		panel.add(b1);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ad = JOptionPane.showInputDialog(panel,"Lütfen Devam Nesli Ögrenilecek Kisiyi Giriniz:");
		   		devamNesil(agac,ad);
		   		
		   	       if(x!=-1)
		   	       {
		   	    	   if(!gender.equals("no"))
		   	    	   {
		   	    		   if(gender.contains("Kadın"))
			   	    		   l2.setIcon(new ImageIcon("C:\\Users\\Abra\\Desktop\\woman.png"));

		   	    	   else
		   	    		   l2.setIcon(new ImageIcon("C:\\Users\\Abra\\Desktop\\man.png"));
		   	    	   }
			   			l2.setText(ad+"'nin Nesli= "+x);
		   	       }
		   	       else
			   			l2.setText("Kisi yok");
				
			}
		});
		b2.setBounds(296, 791, 106, 34);
		panel.add(b2);
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		   		soy1 s =new soy1(3);
			}
		});
		b3.setBounds(436, 791, 99, 34);
		panel.add(b3);
		p2.setBackground(new Color(255, 255, 0));
		p2.setBounds(10, 159, 173, 369);
		p2.setLayout(new BoxLayout(p2,BoxLayout.Y_AXIS));
		panel.add(p2);
		
		p2.add(lblNewLabel_6);
		p3.setBackground(new Color(255, 255, 0));
		p3.setBounds(193, 299, 166, 369);
		p3.setLayout(new BoxLayout(p3,BoxLayout.Y_AXIS));
		panel.add(p3);
		
		p3.add(lblNewLabel_7);
		p4.setBackground(new Color(255, 255, 0));
		p4.setBounds(369, 159, 166, 369);
		p4.setLayout(new BoxLayout(p4,BoxLayout.Y_AXIS));
		panel.add(p4);
		
		p4.add(lblNewLabel_8);
		p5.setBackground(new Color(255, 255, 0));
		p5.setBounds(545, 299, 161, 369);
		p5.setLayout(new BoxLayout(p5,BoxLayout.Y_AXIS));
		panel.add(p5);
		
		p5.add(lblNewLabel_9);
		p6.setBackground(new Color(255, 255, 0));
		p6.setBounds(716, 159, 150, 369);
		p6.setLayout(new BoxLayout(p6,BoxLayout.Y_AXIS));
		panel.add(p6);
		
		p6.add(lblNewLabel_10);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Abra\\Desktop\\family (1).png"));
		lblNewLabel_1.setBounds(193, 159, 155, 130);
		
		panel.add(lblNewLabel_1);
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Abra\\Desktop\\family.png"));
		lblNewLabel_2.setBounds(10, 538, 161, 130);
		
		panel.add(lblNewLabel_2);
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Abra\\Desktop\\grandparents.png"));
		lblNewLabel_3.setBounds(369, 538, 166, 130);
		
		panel.add(lblNewLabel_3);
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\Abra\\Desktop\\family (2).png"));
		lblNewLabel_4.setBounds(716, 538, 150, 130);
		
		panel.add(lblNewLabel_4);
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\Abra\\Desktop\\picture.png"));
		lblNewLabel_5.setBounds(550, 159, 156, 130);
		
		panel.add(lblNewLabel_5);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ad1=JOptionPane.showInputDialog(panel,"Lütfen Ilk Kisiyi Giriniz(Ad ve Soyad):");
		   		ArrayList<Kisi> tmp =new ArrayList<Kisi>();
		   		n.arama(agac,akraba,ad1);
		   		if(akraba.size()>1)
		   		{
		   			int i=JOptionPane.showConfirmDialog(panel,"Birden Fazla Kisi Bulundu.ID Girmek İster misiniz: ","Secenek",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		   			if(i==JOptionPane.YES_OPTION)
		   			{
		   			String id =JOptionPane.showInputDialog(panel,"Lütfen "+ad1+" kişisinin ID sini giriniz:");
		   			for(Kisi k : akraba)
		   			{
		   				if(k.id.contains(id))
		   				{
		   					tmp.add(k);
		   					akraba.clear();
		   					break;
		   				}
		   			}
		   			if(tmp.size()==0)
		   			{
		   				JOptionPane.showMessageDialog(panel,"Gecersiz ID");
		   			}
		   			}
		   		}
		   		else if(akraba.size()==0)
		   		{
	   				JOptionPane.showMessageDialog(panel,"Kisi Bulunamadi");
		   		}
		   		else
		   		{
		   		  tmp.add(akraba.get(0));
		   		  akraba.clear();
		   		}
		   		if(tmp.size()==1)
		   		{
		   		String ad2=JOptionPane.showInputDialog(panel,"Lütfen ikinci Kisiyi Giriniz(Ad ve Soyad):");
		   		n.arama(agac,akraba,ad2);
		   		if(akraba.size()>1)
		   		{
		   			int i=JOptionPane.showConfirmDialog(panel,"Birden Fazla Kisi Bulundu.ID Girmek İster misiniz: ","Secenek",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		   			if(i==JOptionPane.YES_OPTION)
		   			{
		   			String id =JOptionPane.showInputDialog(panel,"Lütfen "+ad2+" kişisinin ID sini giriniz:");
		   			for(Kisi k : akraba)
		   			{
		   				if(k.id.contains(id))
		   				{
		   					tmp.add(k);
		   					akraba.clear();
		   					break;
		   				}
		   			}
		   			if(tmp.size()==0)
		   			{
		   				JOptionPane.showMessageDialog(panel,"Gecersiz ID");
		   			}
		   			}
		   		}   
		   		else if(akraba.size()==0)
		   		{
	   				JOptionPane.showMessageDialog(panel,"Kisi Bulunamadi");
		   		}
		   		else
		   		{
		   			tmp.add(akraba.get(0));
		   			akraba.clear();
		   		}
		   		}
		   		for(Kisi k : tmp)
		   		{
		   			System.out.println(k.ad+" "+k.id+" "+k.seviye);
		   		}
		   		if(tmp.size()==2)
		   	 akrabalık(tmp,agac.seviye);
			}
		});
		btnNewButton.setBounds(579, 791, 106, 34);
		
		panel.add(btnNewButton);
		int syc2=0;
		  for(Kisi k : leafs)
		  {
			  p3.add(lbl2.get(syc2));
			  lbl2.get(syc2).setText(k.ad+" "+k.dogumTarihi);
			  syc2++;
		  }
		  aynıIsım(agac);
		  ArrayList<JLabel> lbl3 = new ArrayList<JLabel>();
		for(int i=0;i<15;i++)
		{
			JLabel l =new JLabel("");
			lbl3.add(l);
		}
		int syc3=0;
		for(ArrayList<Kisi> k : benzer)
		{
			
			if(k.size()>=2)
			{
				for(Kisi kisi : k)
				{
					p4.add(lbl3.get(syc3));
					lbl3.get(syc3).setText(kisi.ad+" id:"+kisi.id);
					syc3++;
				}
			}
		}
		
		aynıMeslek(agac);
		int syc4=0;
		ArrayList<JLabel> lbl4 =new ArrayList<JLabel>();
		for(int i=0;i<aynıMes.size();i++)
		{
			JLabel l =new JLabel("");
			lbl4.add(l);
		}
		for(Kisi k : aynıMes)
		{
			p5.add(lbl4.get(syc4));
			lbl4.get(syc4).setText(k.ad+" "+k.soyad+"->"+k.meslek);
			syc4++;
		}
		n.BFS(agac,node);
		for(Node x : node)
		{
			uveyCocuk(x);
		}
		int syc5=0;
		ArrayList<JLabel> lbl5 =new ArrayList<JLabel>();
        for(int i=0;i<uvey.size();i++)
        {
        	JLabel l=new JLabel("");
        	lbl5.add(l);
        }
		for(Kisi k : uvey)
		{
			p6.add(lbl5.get(syc5));
			lbl5.get(syc5).setText(k.ad);
			syc5++;
		}
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

}
