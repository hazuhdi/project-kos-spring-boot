package id.ist.fileio.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Facility implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4687294306944554526L;
	

	private Long id;
	private String name;
	private BigDecimal price;

}