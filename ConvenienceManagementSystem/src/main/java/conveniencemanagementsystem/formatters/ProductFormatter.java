package conveniencemanagementsystem.formatters;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import conveniencemanagementsystem.models.ProductBean;

public class ProductFormatter implements Formatter<ProductBean> {
	@Override
	public String print(ProductBean object, Locale locale) {
		return null;
	}

	@Override
	public ProductBean parse(String text, Locale locale) throws ParseException {
		
		ProductBean product = new ProductBean();
		int id=Integer.parseInt(text);
		product.setId(id);
		//course.setId(text);
		return product;
	}
}
