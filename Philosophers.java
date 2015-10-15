import java.io.*;
import javax.swing.*;

public class Philosophers extends JFrame implements Runnable{
	Thread th;
	int phils[];
	String pstat[], Mes;
	boolean cond[];
	int i;
	
	JTextArea t;
	
	public Philosophers(){
		super("A Test Simulation by Zakir");
		th=new Thread(this);
		th.start();
		
		phils=new int[6];
		pstat=new String[6];
		cond=new boolean[6];
		
		t=new JTextArea();
		
		getContentPane().add(new JScrollPane(t));
		
		t.setText("Start\n");
		
		for(i=0; i<5; i++){
			pstat[i]="";
			cond[i]=true;
			phils[i]=i;
		}
		
		setSize(300, 300);
		setVisible(true);
	}
	
	public void initphils(int i){
		if(cond[i] && pstat[i].equals("Thinking")){
			cond[i]=false;
			cond[(i+1)%5]=false;
			pstat[i]="Eating";
		}
		else{
			cond[i]=true;
			pstat[i]="Thinking";
		}
		Mes="Philosopher "+i+" is at "+pstat[i]+" condition\n";
	}
	
	public void run(){
		int in=0;
		while(th!=null){
			initphils(in%5);
			try{
				th.sleep(1000);
			}
			catch(InterruptedException ie){
				System.out.print("Thread Interrupeted");
			}
			t.append(Mes);
			in++;
		}
	}
	
	public static void main(String args[]){
		new Philosophers();
	}
}