import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Queue;

public class Node {
	public Kisi kisi;
	public Node c1;
	public Node c2;
	public Node c3;
	public Node c4;
	public Node c5;
	public Node e;
	public int height;
	public int seviye=0;
	public ArrayList<Kisi> cocuk=new ArrayList<Kisi>();
	public Node()
	{
		
	}
	public Node(Kisi k)
	{
		kisi=k;
		height=1;
	}
	public Node ekle(Node agac,Kisi k,Node once)
	{
		if(once!=null && (once.kisi.ad.equals(k.babaAdı) || once.kisi.ad.equals(k.anneAdı)))
		{
			once.cocuk.add(k);
			once.kisi.cocuk.add(k);
			k.ata=once;
		}
		once = agac;
		if(agac==null)
		{
			Node kok=new Node(k);
			kok.c1=kok.c2=kok.c3=kok.c4=kok.e=kok.c5=null;
            return kok;
		}		
		if(agac.kisi.esId.contains(k.id))
		{
			Node kok=new Node(k);
			kok.c1=kok.c2=kok.c3=kok.c4=kok.c5=null;
	        agac.e=kok;
            k.e=agac;
	        return agac;
		}
		if(!(agac.kisi.medeniHal.contains("Evli") || agac.kisi.medeniHal.contains("Bekar")))
		{
			if(agac.kisi.cinsiyet.contains("Kadın"))
			{
				agac.kisi.es=k.babaAdı;
			}
			else
				agac.kisi.es=k.anneAdı;
		}
	//	if((agac.kisi.ad.contains(k.babaAdı) || agac.kisi.ad.contains(k.anneAdı)) && (agac.kisi.es.contains(k.anneAdı) || agac.kisi.es.contains(k.babaAdı)))
			if((agac.kisi.ad.contains(k.babaAdı) || agac.kisi.ad.contains(k.anneAdı)))
		{
			if(agac.c1==null)
			{
				agac.c1=ekle(agac.c1,k,once);
				agac.height=1+max(yukseklik(agac.c1),yukseklik(agac.c2),yukseklik(agac.c3),yukseklik(agac.c4),yukseklik(agac.c5));		
				return agac;
			}
			else if(agac.c2==null)
			{
				agac.c2=ekle(agac.c2,k,once);
				agac.height=1+max(yukseklik(agac.c1),yukseklik(agac.c2),yukseklik(agac.c3),yukseklik(agac.c4),yukseklik(agac.c5));		
				return agac;
			}
			else if(agac.c3==null)
			{
				agac.c3=ekle(agac.c3,k,once);
				agac.height=1+max(yukseklik(agac.c1),yukseklik(agac.c2),yukseklik(agac.c3),yukseklik(agac.c4),yukseklik(agac.c5));		
				return agac;
			}
			else if(agac.c4==null)
			{
				agac.c4=ekle(agac.c4,k,once);
				agac.height=1+max(yukseklik(agac.c1),yukseklik(agac.c2),yukseklik(agac.c3),yukseklik(agac.c4),yukseklik(agac.c5));	
				return agac;
			}
			else if(agac.c5==null)
			{
				agac.c5=ekle(agac.c5,k,once);
				agac.height=1+max(yukseklik(agac.c1),yukseklik(agac.c2),yukseklik(agac.c3),yukseklik(agac.c4),yukseklik(agac.c5));	
				return agac;
			}
		}
		if (agac.c1!=null)
		{
			agac.c1=ekle(agac.c1,k,once);	
		}
		if (agac.c2!=null)
		{
			agac.c2=ekle(agac.c2,k,once);	
		}
		
		if (agac.c3!=null)
		{
			agac.c3=ekle(agac.c3,k,once);
		}
		if(agac.c4!=null)
		{
			agac.c4=ekle(agac.c4,k,once);
		}
		if(agac.c5!=null)
		{
			agac.c5=ekle(agac.c5,k,once);
		}
		agac.height=1+max(yukseklik(agac.c1),yukseklik(agac.c2),yukseklik(agac.c3),yukseklik(agac.c4),yukseklik(agac.c5));	
		return agac;
	}
	public int yukseklik(Node agac)
	{
		if(agac==null)
			return 0;
		return agac.height;
	}
	public void derinlik(Node agac)
	{
	 if(agac==null)	
	 {
		 return;
	 }
		derinlik(agac.c1);
		derinlik(agac.c2);
		derinlik(agac.c3);
		derinlik(agac.c4);
		derinlik(agac.c5);
		agac.height=1+max(yukseklik(agac.c1),yukseklik(agac.c2),yukseklik(agac.c3),yukseklik(agac.c4),yukseklik(agac.c5));	
	}
	public int max(int a,int b,int c,int d,int e)
	{
		if(a>=b && a>=c && a>=d && a>=e)
		{
			return a;
		}
		else if(b>=a && b>=c && b>=d && b>=e)
		{
			return b;
		}
		else if(c>=a && c>=b && c>=d && c>=e)
		{
			return c;
		}
		else if(e>=a && e>=b && e>=c && e>=d)
		{
			return d;
		}
		else 
			return e;
	}
   public Node isimler(Node agac,ArrayList<Kisi> tmp)
   {
	   if(agac==null)
	   {
		   return null;   
	   }
	 agac.c1=isimler(agac.c1,tmp);
	  agac.c2=isimler(agac.c2,tmp);
	  agac.c3=isimler(agac.c3,tmp);
	  agac.c4=isimler(agac.c4,tmp);
	  agac.c5=isimler(agac.c5,tmp);
	   if(agac.e!=null)
	   {
		   tmp.add(agac.e.kisi);
	   }
	   tmp.add(agac.kisi);
	   return agac;
   }
   public Node meslekler(Node agac,ArrayList<Kisi> tmp,String meslek)
   {
	   if(agac==null)
	   {
		   return null;
	   }
	   agac.c1=meslekler(agac.c1,tmp,meslek);
	   agac.c2=meslekler(agac.c2,tmp,meslek);
	   agac.c3=meslekler(agac.c3,tmp,meslek);
	   agac.c4=meslekler(agac.c4,tmp,meslek);
	   agac.c5=meslekler(agac.c5,tmp,meslek);
	   if(agac.kisi.meslek.equals(meslek))
	   {
		   tmp.add(agac.kisi);
	   }
	   return agac;
   }
   public void arama(Node kok,ArrayList<Kisi> kisi,String ad)
   {
	   if(kok==null)
	   {
		   return ;
	   }
	   String a =kok.kisi.ad+" "+kok.kisi.soyad;
	   if(a.contains(ad))
	   {
		   kisi.add(kok.kisi);
	   }
	   if((kok.e!=null))
	   {
		  String e=kok.e.kisi.ad+" "+kok.e.kisi.soyad;
		   if(e.contains(ad)) {
			   kisi.add(kok.e.kisi); 
		   }
	   }
	   arama(kok.c1,kisi,ad);
	   arama(kok.c2,kisi,ad);
	   arama(kok.c3,kisi,ad);
	   arama(kok.c4,kisi,ad);
	   arama(kok.c5,kisi,ad);
	   return;
   }
	public void seviye(Node agac,int x)
	{
		if(agac==null)
		{
			return;
		}
		agac.seviye=x-1;
		agac.kisi.seviye=x-1;
		if(agac.e!=null)
		{
			agac.e.seviye=agac.seviye;
			agac.e.kisi.seviye=agac.kisi.seviye;
		}
		seviye(agac.c1,agac.seviye);
		seviye(agac.c2,agac.seviye);
		seviye(agac.c3,agac.seviye);
		seviye(agac.c4,agac.seviye);
		seviye(agac.c5,agac.seviye);
return;		
	}
	public void dolas2(Node deep,ArrayList<Kisi> kisi)
	{
		if(deep.kisi.ata==null)
		{
			kisi.add(deep.kisi);
			return;
		}
	dolas2(deep.kisi.ata,kisi);
	return;
	}
	
	public void BFS(Node kok,ArrayList<Node> n)
	{
		if(kok==null)
		{
			return;
		}
	if(n.size()==0)
	{
	n.add(kok);	
	}
	else
	{
		int j=n.size();
	   for(int i=0;i<n.size();i++)
		{
			if(n.get(i).seviye<=kok.seviye)
			{
				n.add(i,kok);
				break;
			}
		}
	   if((j+1)!=n.size())
	   {
		   n.add(kok);
	   }
	}
	BFS(kok.c1,n);
	BFS(kok.c2,n);
	BFS(kok.c3,n);
	BFS(kok.c4,n);
	BFS(kok.c5,n);
	return;
	}
}
