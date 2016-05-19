package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import beans.Personne;

public class BeanLoader {

	/**
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws Exception {

		System.out.println(loadBean("personnes"));

	}

	private static Object loadBean(String filename) throws Exception {
		Properties p = new Properties();
		p.load(new FileReader(filename));
		Class<?> classBean = Class.forName((String) p.get("class"));
		Object bean = classBean.newInstance();
		for (Object key : p.keySet()) {
			if (!key.equals("class")) {
				Method getter = classBean.getMethod("get" + key);
				Method setter = classBean.getMethod("set" + key,
						getter.getReturnType());
				if (getter.getReturnType().equals(String.class)) {
					setter.invoke(bean, p.get(key));
				} else {
					setter.invoke(bean, Integer.parseInt((String) p.get(key)));
				}
			}
		}
		return bean;
	}

}
