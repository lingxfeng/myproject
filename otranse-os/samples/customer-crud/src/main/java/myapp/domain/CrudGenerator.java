package myapp.domain;

import cn.disco.generator.Generator;

public class CrudGenerator {
	public static void main(String[] args) {
		Generator generator = Generator.getInstance();
		
		String[] domains = { "cn.disco.necton.base.domain.DynamicStaticPair" };
		generator.setTemplateDir("./templates");
		generator.setPri("./");
		try {
			for (int i = 0; i < domains.length; i++)
				generator.doGenerator(new String[] { domains[i] });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
