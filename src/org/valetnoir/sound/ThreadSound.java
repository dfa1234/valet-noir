package org.valetnoir.sound;

public class ThreadSound extends Thread {
	
	private String fichier;
	
	public ThreadSound(String f){
		fichier=f;
	}
	
	public void run(){
		Sound son=new Sound(fichier);
	}
}
