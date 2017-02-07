package myapp.domain;

import cn.disco.generator.Generator;

public class CrudGenerator {
	public static void main(String[] args) {
		Generator generator = Generator.getInstance();
		/*
		 * String[] domains = { "cn.disco.necton.base.domain.ContentFilter",
		 * "cn.disco.necton.base.domain.DynamicStaticPair",
		 * "cn.disco.necton.base.domain.Message",
		 * "cn.disco.necton.news.domain.NewsDir",
		 * "cn.disco.necton.news.domain.NewsDoc",
		 * "cn.disco.necton.news.domain.NewsReview",
		 * "cn.disco.necton.news.domain.Review",
		 * "cn.disco.necton.ec.domain.Orders",
		 * "cn.disco.necton.ec.domain.Product",
		 * "cn.disco.necton.ec.domain.ProductDir",
		 * "cn.disco.necton.news.domain.Template"};
		 */
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
