package org.fitfest.core;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class CommandSelector {
	private ClassLoader classLoader;
	private Manifest manifest;

	public CommandSelector(URL jarFile) throws IOException {
		classLoader = new URLClassLoader(new URL[] { jarFile });
		manifest = new Manifest(classLoader.getResourceAsStream("META-INF/MANIFEST.MF"));
	}
	
	public static void main(String[] args) throws IOException {
		CommandSelector cs = new CommandSelector(new URL("file:///home/peter/.m2/repository/org/fitnesse/fitnesse/20081201/fitnesse-20081201.jar"));
		
		System.out.println(cs.manifest.getMainAttributes().getValue(Attributes.Name.MANIFEST_VERSION));
	}
}
