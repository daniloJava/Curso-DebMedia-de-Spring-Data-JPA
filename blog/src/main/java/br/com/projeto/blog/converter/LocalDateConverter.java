package br.com.projeto.blog.converter;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**Classe que o Spring reconhece que sempre que tiver 
 * aquele tipo especificado, o Spring converterá automaticamente
 * 
 * @author Danilo Silva
 *
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date>{

	
	public Date convertToDatabaseColumn(LocalDate localDate) {
		return Date.valueOf(localDate);
	}

	public LocalDate convertToEntityAttribute(Date date) {
		return date.toLocalDate();
	}
	
	
}
