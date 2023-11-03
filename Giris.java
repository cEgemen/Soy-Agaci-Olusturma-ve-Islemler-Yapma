import java.awt.BorderLayout;
import java.awt.EventQueue;

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

import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class Giris extends JFrame {
	public ArrayList<Kisi> list =new ArrayList<Kisi>();
 	public ArrayList  list2 =new ArrayList();
 	public ArrayList<Kisi> list3 =new ArrayList<Kisi>();
 	public ArrayList  list4 =new ArrayList();
 	public ArrayList<Kisi> list5 =new ArrayList<Kisi>();
 	public ArrayList  list6 =new ArrayList();
 	public ArrayList<Kisi> list7 =new ArrayList<Kisi>();
 	public ArrayList  list8 =new ArrayList();
 	public Node agac1,agac2,agac3,agac4;
 	public Node once1,once2,once3,once4;
	private JPanel contentPane;
	public void oku(int syc,ArrayList<Kisi> list,ArrayList list2)
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Giris frame = new Giris();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void gizle()
	{
		this.setVisible(false);
	}
	
	public Giris() {
		oku(0,list,list2);
		oku(1,list3,list4);
		oku(2,list5,list6);
		oku(3,list7,list8);
		Node n =new Node();
		for(Kisi k : list)
		{
			agac1=n.ekle(agac1,k,once1);
		}
		for(Kisi k : list3)
		{
			agac2=n.ekle(agac2,k,once2);
		}
		for(Kisi k : list5)
		{
			agac3=n.ekle(agac3,k,once3);
		}
		for(Kisi k : list7)
		{
			agac4=n.ekle(agac4,k,once4);
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1000,800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel p1 = new JPanel();
		p1.setForeground(new Color(255, 0, 255));
		p1.setBackground(new Color(153, 255, 153));
		contentPane.add(p1, BorderLayout.CENTER);
		p1.setLayout(null);
		
		JLabel l1 = new JLabel("FAMİLLY TREE");
		l1.setForeground(new Color(238, 130, 238));
		l1.setFont(new Font("Ink Free", Font.ITALIC, 34));
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setBounds(158, 10, 685, 56);
		p1.add(l1);
		
		JLabel l2 = new JLabel("");
		l2.setIcon(new ImageIcon("C:\\Users\\Abra\\Desktop\\Screenshot_2.png"));
		l2.setBounds(203, 76, 597, 568);
		p1.add(l2);
		
		JButton b1 = new JButton("1.Agac ve Bilgileri");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Agac1 a1 = new Agac1();
				gizle();
			}
		});
		b1.setBounds(24, 189, 169, 38);
		p1.add(b1);
		
		JButton b2 = new JButton("2.Agac ve Bilgileri");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Agac2 a2 =new Agac2();
				gizle();
			}
		});
		b2.setBounds(24, 347, 169, 38);
		p1.add(b2);
		
		JButton b3 = new JButton("3.Agac ve Bilgileri");
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gizle();
				Agac3 a3=new Agac3();
			}
		});
		b3.setBounds(810, 189, 156, 38);
		p1.add(b3);
		
		JButton b4 = new JButton("4.Agac ve Bilgileri");
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Agac4 a4 =new Agac4();
				gizle();
			}
		});
		b4.setBounds(810, 347, 156, 38);
		p1.add(b4);
		
		JButton b6 = new JButton("Kisi Soy Agacı");
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ad = JOptionPane.showInputDialog(p1,"Lütfen Kisi Adını ve Soyadını Giriniz:");
				String id=null;
		       ArrayList<Kisi> kisi = new ArrayList<Kisi>();
		       ArrayList<Kisi> tmp = new ArrayList<Kisi>();
		       n.arama(agac1,kisi,ad);
		       n.arama(agac2,kisi,ad);
		       n.arama(agac3,kisi,ad);
		       n.arama(agac4,kisi,ad);
		       System.out.println("step 1");
		       System.out.println("size"+kisi.size());
				if(kisi.size()>1)
				{
					int i=JOptionPane.showConfirmDialog(p1,"Birden Fazla Kisi Bulundu...ID Girmek İster misiniz","ID",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(i==JOptionPane.YES_OPTION)
					{
						id=JOptionPane.showInputDialog(p1,ad+" kisisinin Id'sinin girinz...");
						for(Kisi k : kisi)
						{
							if(k.id.contains(id))
							{
								tmp.add(k);
								kisi.clear();
								break;
							}
						}
					}
					if(tmp.size()==0)
					{
						JOptionPane.showMessageDialog(p1,"Böyle birisi bulunmamaktadır...");
					}
					else 
					{
						System.out.println("step3="+tmp.get(0).ad+" "+tmp.get(0).id);
						if(tmp.get(0).ata!=null) {
				       n.dolas2(tmp.get(0).ata,kisi);
						System.out.println("step3.1="+kisi.get(0).ad+" "+kisi.get(0).id);
						if(kisi.get(0).ad.contains(agac1.kisi.ad) && kisi.get(0).id.contains(agac1.kisi.id))
						{
							soy1 s = new soy1(0);
							s.setVisible(true);
						}
						else if(kisi.get(0).ad.contains(agac2.kisi.ad) && kisi.get(0).id.contains(agac2.kisi.id))
						{
							soy1 s = new soy1(1);
							s.setVisible(true);
						}
						else if(kisi.get(0).ad.contains(agac3.kisi.ad) && kisi.get(0).id.contains(agac3.kisi.id))
						{
							soy1 s = new soy1(2);
							s.setVisible(true);
						}
						else if(kisi.get(0).ad.contains(agac4.kisi.ad) && kisi.get(0).id.contains(agac4.kisi.id))
						{
							soy1 s = new soy1(3);
							s.setVisible(true);
						}
				       
					}
						else
						{
							System.out.println("step3.2="+tmp.get(0).ad+" "+tmp.get(0).id);
							if(tmp.get(0).ad.contains(agac1.kisi.ad) && tmp.get(0).id.contains(agac1.kisi.id))
							{
								soy1 s = new soy1(0);
								s.setVisible(true);
							}
							else if(tmp.get(0).ad.contains(agac2.kisi.ad) && tmp.get(0).id.contains(agac2.kisi.id))
							{
								soy1 s = new soy1(1);
								s.setVisible(true);
							}
							else if(tmp.get(0).ad.contains(agac3.kisi.ad) && tmp.get(0).id.contains(agac3.kisi.id))
							{
								soy1 s = new soy1(2);
								s.setVisible(true);
							}
							else if(tmp.get(0).ad.contains(agac4.kisi.ad) && tmp.get(0).id.contains(agac4.kisi.id))
							{
								soy1 s = new soy1(3);
								s.setVisible(true);
							}
						}
					}
				}
				else if(kisi.size()==0)		
				{
					JOptionPane.showMessageDialog(p1,"Hatalı Giris.....");
				}
				else
				{
					tmp.add(kisi.get(0));
					kisi.clear();
					System.out.println("step4="+tmp.get(0).ad+" "+tmp.get(0).soyad);
					if(tmp.get(0).ata!=null) {
					   n.dolas2(tmp.get(0).ata,kisi);
						System.out.println("step4.1="+kisi.get(0).ad+" "+kisi.get(0).id);
						if(kisi.get(0).ad.contains(agac1.kisi.ad) && kisi.get(0).id.contains(agac1.kisi.id))
						{
							soy1 s = new soy1(0);
							s.setVisible(true);
						}
						else if(kisi.get(0).ad.contains(agac2.kisi.ad) && kisi.get(0).id.contains(agac2.kisi.id))
						{
							soy1 s = new soy1(1);
							s.setVisible(true);
						}
						else if(kisi.get(0).ad.contains(agac3.kisi.ad) && kisi.get(0).id.contains(agac3.kisi.id))
						{
							soy1 s = new soy1(2);
							s.setVisible(true);
						}
						else if(kisi.get(0).ad.contains(agac4.kisi.ad) && kisi.get(0).id.contains(agac4.kisi.id))
						{
							soy1 s = new soy1(3);
							s.setVisible(true);
						} 
				}
					else
					{
						System.out.println("step4.2="+tmp.get(0).ad+" "+tmp.get(0).id);
						if(tmp.get(0).ad.contains(agac1.kisi.ad) && tmp.get(0).id.contains(agac1.kisi.id))
						{
							soy1 s = new soy1(0);
							s.setVisible(true);
						}
						else if(tmp.get(0).ad.contains(agac2.kisi.ad) && tmp.get(0).id.contains(agac2.kisi.id))
						{
							soy1 s = new soy1(1);
							s.setVisible(true);
						}
						else if(tmp.get(0).ad.contains(agac3.kisi.ad) && tmp.get(0).id.contains(agac3.kisi.id))
						{
							soy1 s = new soy1(2);
							s.setVisible(true);
						}
						else if(tmp.get(0).ad.contains(agac4.kisi.ad) && tmp.get(0).id.contains(agac4.kisi.id))
						{
							soy1 s = new soy1(3);
							s.setVisible(true);
						}
					}
				}
				
			}
		});
		b6.setBounds(413, 654, 156, 38);
		p1.add(b6);
	}

		
	
}
