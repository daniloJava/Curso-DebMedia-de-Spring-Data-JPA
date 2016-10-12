package br.com.projeto.blog.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**Classe que o Spring reconhece que sempre que tiver 
 * aquele tipo especificado, o Spring converterá automaticamente
 * 
 * @author Danilo Silva
 *
 */
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp>{

	public Timestamp convertToDatabaseColumn(LocalDateTime localDate) {
		return Timestamp.valueOf(localDate);
	}

	public LocalDateTime convertToEntityAttribute(Timestamp time) {
		return time.toLocalDateTime();
	}

}
