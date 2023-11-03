import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class soy1 extends JFrame {

	private JPanel contentPane;
	static int w2 = 100;
    public ArrayList<JPanel> tmp = new ArrayList<JPanel>();
	public ArrayList<Kisi> list =new ArrayList<Kisi>();
 	public ArrayList  list2 =new ArrayList();
 	public ArrayList<JButton> tmp2 =new ArrayList<JButton>();
 	public Node agac;
 	public Node once;
 	public static int syc=0;
 	  int w = 1600;
      int h = 1000;
 	public static JPanel panel = new JPanel();
 	public void gizle()
 	{
 		this.setVisible(false);
 	}
 public void kontrol(Node agac,Kisi k)
 {
	 if(agac==null)
	 {
		 return;
	 }
	 if(agac.e!=null && k.e!=null)
	 {
		 if(agac.e.kisi.ad.contains(k.ad) && k.es.contains(agac.kisi.ad))
		 {
			 syc=1;
		 }
	 }
	 kontrol(agac.c1,k);
	 kontrol(agac.c2,k);
	 kontrol(agac.c3,k);
	 kontrol(agac.c4,k);
	 kontrol(agac.c5,k);
	 return;
 }
  public void yapı2(float en, float yükseklik, float sıra,float fi, float azalt, Kisi k) {
        int a = k.cocuk.size();
        JButton b = new JButton(k.ad);
        if(k.cinsiyet.contains("Erkek"))
        {
        	b.setBackground(Color.blue);
        }
        else
        {
        	b.setBackground(Color.pink);

        }
        if (azalt % 2 == 0) {
            float x = (azalt - (1 + (sıra - 1) * 2)) / (-2) * (en) + fi;
            b.setBounds((int) x, (int) yükseklik, soy1.w2, 20);
        } else {
            float x = fi + (en) * (sıra - (azalt + 1) / 2);
            b.setBounds((int) x, (int) yükseklik, soy1.w2, 20);
        }
       kontrol(agac,k);
        if(syc==0)
        soy1.panel.add(b);
        for (int i = 1; i <= a; i++) {
            yapı2(en / a, yükseklik + 50, i, b.getX(), a, k.cocuk.get(i - 1));
        }
        

        return ;
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
	public soy1(int x) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(w,h);
		oku(x);
		Node n =new Node();
		for(Kisi k : list)
		{
			agac=n.ekle(agac,k,once);
		}
		n.seviye(agac,agac.height+1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		panel.setLayout(null);
		contentPane.add(panel, BorderLayout.CENTER);
	    JButton b1 = new JButton("Back");
	    b1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    	    gizle();
	    	}
	    });
	    b1.setBounds(1440, 795, 80,30);
	    panel.add(b1);
		for (int i = 0; i < list.size(); i++) {
            yapı2(w, i * 300, 1, w / 2 - w2 / 2, 1, list.get(i));
        }
		this.setVisible(true);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
